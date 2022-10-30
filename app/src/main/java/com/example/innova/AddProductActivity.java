package com.example.innova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddProductActivity extends AppCompatActivity {

    EditText productname,spec,price,siturl,purl;
    Button btnAdd,btnBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productname = (EditText)findViewById(R.id.productname);
        spec = (EditText)findViewById(R.id.spec1);
        price = (EditText)findViewById(R.id.price1);
        siturl = (EditText)findViewById(R.id.Siteurl);
        purl = (EditText)findViewById(R.id.img2url);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

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
                startActivity(new Intent(getApplicationContext(), ProductMainActivity.class));
            }
        });
    }
    private void insertData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("productname",productname.getText().toString());
        map.put("spec",spec.getText().toString());
        map.put("price",price.getText().toString());
        map.put("siturl",siturl.getText().toString());
        map.put("purl",purl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("products").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddProductActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddProductActivity.this, "Error while insertion", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clearAll()
    {
        productname.setText("");
        spec.setText("");
        price.setText("");
        siturl.setText("");
        purl.setText("");
    }
}