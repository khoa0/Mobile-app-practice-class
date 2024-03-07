package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class exercise5 extends AppCompatActivity {
    TextView result;
    EditText input;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7,btn8, btn9, btn0,
            btnp, btnm, btnmul, btnd, btnR, btnRs;

    int resultDis = 0;

    String operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise5);

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
                        setvalue(result, bt.getText().toString());
                        break;
                    case R.id.btnp:
                        setvalue(result, " + ");
                        operation = "plus";
                        //resultDis = Integer.parseInt(input.getText().toString());
                        break;
                    case R.id.btnm:
                        setvalue(result, " - ");
                        operation = "sub";
                        //resultDis = Integer.parseInt(input.getText().toString());
                        break;
                    case R.id.btnmul:
                        setvalue(result, " * ");
                        operation = "mult";
                        //resultDis = Integer.parseInt(input.getText().toString());
                        break;
                    case R.id.btnd:
                        setvalue(result, " / ");
                        operation = "divi";
                        break;
                    case R.id.btnR:
                        operation = "result";
                        //resultDis = Integer.parseInt(input.getText().toString());
                        resetvalue();
                        break;
                    case R.id.btnRs:
                        operation = "reset";
                        resultDis = 0;
                        resetvalue();
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
    }
    void setvalue(TextView result , String num){
        String last = result.getText().toString();
        if(!last.equals("0")){
            last +=num;
            num=last;
        }
        result.setText(num);
    }
    public void resetvalue() {
        input.setText("");
        result.setText("0");
    }

    public int process (int a, int b){
        Integer presult=0;
        switch (operation)
        {
            case "plus": {
                presult = a + b;
                input.setText(presult.toString());
                break;
            }

            case "sub" :{
                presult = a-b;
                input.setText(presult.toString());
                break;
            }

            case "mult": {
                presult = a*b;
                input.setText(presult.toString());
                break;
            }
            case "divi": {
                presult =  a/b;
                input.setText(presult.toString());
                break;
            }
        }
        return presult;
    }

}

