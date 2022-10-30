package com.example.innova;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AdminCalculation extends AppCompatActivity {

    EditText PCount,PPrice;
    TextView DisAns;
    Button btnBack1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        PCount = findViewById(R.id.TbinputNo);
        PPrice = findViewById(R.id.TbinputNo1);
        DisAns = findViewById(R.id.LblAns);
        btnBack1 =(Button) findViewById(R.id.btnBack1);

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProductMainActivity.class));

            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void CalcAge (View view){

        Integer profit = Integer.valueOf(PCount.getText().toString());
        Integer loss = Integer.valueOf(PPrice.getText().toString());


        String ans = String.valueOf(profit - loss);

        DisAns.setText("Total Profit is  "+ ans +"");


    }


    @Override
    protected void onResume() {
        super.onResume();
        Button objBtn = findViewById(R.id.BtnCal);
        objBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();

            }
        });
    }

    private void  calculateAnswer(){
        TextView objInput = findViewById(R.id.Input);
        profcalculation objCal = new profcalculation();
        RadioButton objUS = findViewById(R.id.RbUs);
        RadioButton objYEN = findViewById(R.id.RbYen);
        TextView objAns = findViewById(R.id.LblMon);

        if(objInput.getText().toString().isEmpty()){
            Toast.makeText(this, "Please Add a Value ", Toast.LENGTH_SHORT).show();

        }




        else {
            if (objUS.isChecked())
                objAns.setText(String.valueOf(objCal.convertRupeestoUs(Float.valueOf(objInput.getText().toString()))));
            else
                objAns.setText(String.valueOf(objCal.convertRupeestoYEN(Float.valueOf(objInput.getText().toString()))));


        }

    }








}