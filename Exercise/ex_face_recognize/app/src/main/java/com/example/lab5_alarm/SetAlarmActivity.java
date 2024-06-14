package com.example.lab5_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

public class SetAlarmActivity extends AppCompatActivity {
    Alarm alarm;

    ImageButton back, save, delete;

    TimePicker timePicker;

    EditText desc;

    CheckBox[] checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        Intent intent = getIntent();
        alarm = intent.getParcelableExtra("alarm");

        int position = intent.getIntExtra("position", -1);

        timePicker = findViewById(R.id.alarmTimePicker);

        checkBoxes = new CheckBox[]{
                findViewById(R.id.checkMo),
                findViewById(R.id.checkTu),
                findViewById(R.id.checkWe),
                findViewById(R.id.checkTh),
                findViewById(R.id.checkFr),
                findViewById(R.id.checkSa),
                findViewById(R.id.checkSu),
        };

        desc = findViewById(R.id.alarmDesc);

        for (int i = 0; i < 7; i++) {
            if (alarm.getDays()[i]) {
                checkBoxes[i].setChecked(true);
            }
        }

        desc.setText(alarm.getDesc());

        if (alarm.am) {
            timePicker.setHour(alarm.getHour());
        } else {
            timePicker.setHour(alarm.getHour() + 12);
        }
        timePicker.setMinute(alarm.getMin());

        back = findViewById(R.id.backToMainBtn);
        save = findViewById(R.id.saveAlarm);
        delete = findViewById(R.id.deleteAlarm);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean[] days = new boolean[7];
                for (int i = 0; i < 7; i++) {
                    days[i] = checkBoxes[i].isChecked();
                }
                alarm.setDays(days);
                alarm.setDesc(desc.getText().toString());

                if (timePicker.getHour() > 11) {
                    alarm.setHour(timePicker.getHour() - 12);
                    alarm.am = false;
                } else {
                    alarm.setHour(timePicker.getHour());
                    alarm.am = true;
                }

                alarm.setMin(timePicker.getMinute());

                Intent intent = new Intent();
                intent.putExtra("alarm", alarm);
                intent.putExtra("position", position);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("alarm", alarm);
                intent.putExtra("position", position);
                intent.putExtra("delete", true);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
