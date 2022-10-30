package com.example.innova;

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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Calculate extends AppCompatActivity {


    EditText PCount,PPrice;// this object get birth year from user to java program
    TextView DisAns;// this object is to display answer
    Button btnBack1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        PCount = findViewById(R.id.TbinputNo);//from hear anwared i am going to refer user input to this object
        PPrice = findViewById(R.id.TbinputNo1);
        DisAns = findViewById(R.id.LblAns);//from here on awrd i am going to refer label answer to this object
        btnBack1 =(Button) findViewById(R.id.btnBack1);

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainSupplierActivity.class));

            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void CalcPay (View view){

        Integer count = Integer.valueOf(PCount.getText().toString());
        Integer price = Integer.valueOf(PPrice.getText().toString());


        String ans = String.valueOf(price * count);

        DisAns.setText("Total Price is  "+ ans +"");


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

    public void  calculateAnswer(){
        TextView objInput = findViewById(R.id.Input);
        calculation objCal = new calculation();
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