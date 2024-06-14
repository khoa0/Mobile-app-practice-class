package com.example.lab5_alarm;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_TAG = "SharedPrefs";
    private static final String ALARM_TAG = "alarm";
    FloatingActionButton addAlarmBtn;

    ArrayList<Alarm> alarms;

    ListView alarmListView;

    Stack<Integer> daysOfWeek;

    Context mContext;
    AlarmListViewAdapter alarmListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAlarmBtn = findViewById(R.id.addAlarmBtn);

        mContext = this;

        alarms = getDataFromSharedPreferences();

        alarmListViewAdapter = new AlarmListViewAdapter(alarms);

        alarmListView = findViewById(R.id.listAlarm);

        alarmListView.setAdapter(alarmListViewAdapter);

        daysOfWeek = new Stack<>();
        daysOfWeek.push(Calendar.SUNDAY);
        daysOfWeek.push(Calendar.SATURDAY);
        daysOfWeek.push(Calendar.FRIDAY);
        daysOfWeek.push(Calendar.THURSDAY);
        daysOfWeek.push(Calendar.WEDNESDAY);
        daysOfWeek.push(Calendar.TUESDAY);
        daysOfWeek.push(Calendar.MONDAY);

        alarmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Alarm alarm = (Alarm) alarmListViewAdapter.getItem(i);
                Intent intent = new Intent(mContext, SetAlarmActivity.class);
                intent.putExtra("alarm", alarm);
                intent.putExtra("position", i);
                launchSetAlarmActivity.launch(intent);
            }
        });

        addAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                int hourNow = now.get(Calendar.HOUR);
                int minNow = now.get(Calendar.MINUTE);
                boolean amNow = now.get(Calendar.AM_PM) == 0;
                String desc = "";
                int dayNow = now.get(Calendar.DAY_OF_WEEK);

                boolean[] dayOn = new boolean[7];
                for (int i = 0; i < 7; i++) {
                    dayOn[i] = dayNow == daysOfWeek.get(i);
                }
                Alarm alarm = new Alarm(hourNow, minNow, amNow, dayOn, desc);
                Intent intent = new Intent(mContext, SetAlarmActivity.class);
                intent.putExtra("alarm", alarm);
                intent.putExtra("position", -1);
                launchSetAlarmActivity.launch(intent);
            }
        });

        updateNoAlarmTextVisibility();
    }

    ActivityResultLauncher<Intent> launchSetAlarmActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Alarm alarm = data.getParcelableExtra("alarm");
                            int position = data.getIntExtra("position", -1);
                            if (position == -1) {
                                alarms.add(alarm);
                            } else if (data.getBooleanExtra("delete", false)) {
                                alarms.remove(position);
                            } else {
                                alarms.set(position, alarm);
                            }
                            saveDataToSharedPreferences();
                            alarmListViewAdapter.notifyDataSetChanged();
                            updateNoAlarmTextVisibility();
                        }
                    }
                }
            }
    );

    private void updateNoAlarmTextVisibility() {
        if (alarms.size() == 0) {
            ((TextView) findViewById(R.id.textNoAlarm)).setVisibility(View.VISIBLE);
        } else {
            ((TextView) findViewById(R.id.textNoAlarm)).setVisibility(View.GONE);
        }
    }

    private ArrayList<Alarm> getDataFromSharedPreferences() {
        Gson gson = new Gson();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(ALARM_TAG, "");

        Type type = new TypeToken<ArrayList<Alarm>>() {}.getType();
        ArrayList<Alarm> alarmListSaved = gson.fromJson(jsonPreferences, type);
        return alarmListSaved != null ? alarmListSaved : new ArrayList<>();
    }

    private void saveDataToSharedPreferences() {
        Gson gson = new Gson();
        String jsonAlarmList = gson.toJson(alarms);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(ALARM_TAG, jsonAlarmList);
        editor.apply();
    }
}
