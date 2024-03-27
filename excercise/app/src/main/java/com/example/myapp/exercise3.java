package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class exercise3 extends AppCompatActivity {
    TextView result;
    EditText input;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7,btn8, btn9, btn0,
            btnp, btnm, btnmul, btnd, btnR, btnRs, exercise_4btn;

    int lastValue = 0;
    int value;
    String secondvalue;
    boolean flag = false;
    String operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex3);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnp = findViewById(R.id.btnp);
        btnm = findViewById(R.id.btnm);
        btnmul = findViewById(R.id.btnmul);
        btnd = findViewById(R.id.btnd);
        btnR = findViewById(R.id.btnR);
        btnRs = findViewById(R.id.btnRs);

        input = findViewById(R.id.input);
        result = findViewById(R.id.result);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button bt = (Button) view;
                switch (bt.getId()) {
                    case R.id.btn0:
                    case R.id.btn1:
                    case R.id.btn2:
                    case R.id.btn3:
                    case R.id.btn4:
                    case R.id.btn5:
                    case R.id.btn6:
                    case R.id.btn7:
                    case R.id.btn8:
                    case R.id.btn9:
                        if (flag)
                        {
                            resetvalue();
                            flag = false;
                        }

                        setvalue(result, bt.getText().toString());
                        break;
                    case R.id.btnp:
                        operation = " + ";
                        value = Integer.parseInt(result.getText().toString());
                        if (!flag)
                            lastValue = lastValue + value;
                        result.setText("");
                        input.setText(Integer.toString(lastValue)  + operation);
                        flag = false;
                        break;
                    case R.id.btnm:
                        operation = " - ";
                        value = Integer.parseInt(result.getText().toString());
                        if (!flag)
                        {
                            if (lastValue != 0)
                                lastValue = lastValue - value;
                            else
                                lastValue = value;
                        }
                        result.setText("");
                        input.setText(Integer.toString(lastValue)  + operation);
                        flag = false;
                        break;
                    case R.id.btnmul:
                        operation = " * ";
                        value = Integer.parseInt(result.getText().toString());
                        if (!flag)
                        {
                            if (lastValue != 0)
                                lastValue = lastValue * value;
                            else
                                lastValue = value;
                        }
                        result.setText("");
                        input.setText(Integer.toString(lastValue)  + operation);
                        flag = false;
                        break;
                    case R.id.btnd:
                        operation = " / ";
                        value = Integer.parseInt(result.getText().toString());
                        if (!flag)
                        {
                            if (lastValue != 0)
                                lastValue = lastValue / value;
                            else
                                lastValue = value;
                        }
                        result.setText("");
                        input.setText(Integer.toString(lastValue)  + operation);
                        flag = false;
                        break;
                    case R.id.btnR:
                        if (flag)
                        {
                            input.setText(Integer.toString(lastValue)  + operation + secondvalue);
                            lastValue = process(lastValue, Integer.parseInt(
                                    secondvalue ));
                            result.setText(Integer.toString(lastValue));
                        }
                        else {
                            secondvalue = result.getText().toString();
                            setvalue(input, result.getText().toString());
                            lastValue = process(lastValue, Integer.parseInt(
                                    result.getText().toString() ));
                            result.setText(Integer.toString(lastValue));
                        }
                        flag = true;
                        break;
                    case R.id.btnRs:
                        resetvalue();
                        flag = false;
                        break;

                }

            }
        };
        btn0.setOnClickListener(buttonListener);
        btn1.setOnClickListener(buttonListener);
        btn2.setOnClickListener(buttonListener);
        btn3.setOnClickListener(buttonListener);
        btn4.setOnClickListener(buttonListener);
        btn5.setOnClickListener(buttonListener);
        btn6.setOnClickListener(buttonListener);
        btn7.setOnClickListener(buttonListener);
        btn8.setOnClickListener(buttonListener);
        btn9.setOnClickListener(buttonListener);
        btnp.setOnClickListener(buttonListener);
        btnm.setOnClickListener(buttonListener);
        btnmul.setOnClickListener(buttonListener);
        btnd.setOnClickListener(buttonListener);
        btnR.setOnClickListener(buttonListener);
        btnRs.setOnClickListener(buttonListener);

        exercise_4btn = findViewById(R.id.exercise_4btn);

        exercise_4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });
    }

    void setvalue(TextView result, String num) {
        String currentValue = result.getText().toString();
        if (currentValue.equals("0")) {
            result.setText(num);
        } else {
            result.setText(currentValue + num);
        }
    }
    public void resetvalue() {
        lastValue = 0;
        input.setText("");
        result.setText("0");
    }

    public int process (int a, int b){
        Integer presult=0;
        switch (operation)
        {
            case " + ": {
                presult = a + b;
                break;
            }
            case " - " :{
                presult = a-b;
                break;
            }
            case " * ": {
                presult = a*b;
                break;
            }
            case " / ": {
                if (b != 0) {
                    presult = a / b;
                } else {
                    input.setText("Error: Division by zero");
                }
                break;
            }
        }
        return presult;
    }

    protected void goNextExercise() {
        Intent intent = new Intent(this , exercise4_main.class);
        startActivity(intent);
    }

}
