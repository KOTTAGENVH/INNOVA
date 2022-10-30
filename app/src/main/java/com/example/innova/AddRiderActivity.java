package com.example.innova;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddRiderActivity extends AppCompatActivity {

    EditText name,nic,vehicleNo,email,rurl;
    Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rider);

        name=(EditText)findViewById(R.id.txtName);
        nic=(EditText)findViewById(R.id.txtNIC);
        vehicleNo=(EditText)findViewById(R.id.txtVNo);
        email=(EditText)findViewById(R.id.txtEmail);
        rurl=(EditText)findViewById(R.id.txtImageUrl);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnBack=(Button)findViewById(R.id.btBacK);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainRiderActivity.class));
            }
        });
    }

    private void insertData(){
        Map<String,Object>map=new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("nic",nic.getText().toString());
        map.put("vehicleNo",vehicleNo.getText().toString());
        map.put("email",email.getText().toString());
        map.put("rurl",rurl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("riders").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddRiderActivity.this,"Data Inserted Successfully.",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddRiderActivity.this,"Error While Insertion.",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearAll(){
        name.setText("");
        nic.setText("");
        vehicleNo.setText("");
        email.setText("");
        rurl.setText("");
    }
}