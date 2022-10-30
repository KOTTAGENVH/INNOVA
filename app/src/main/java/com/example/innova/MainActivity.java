package com.example.innova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText _txtUser, _txtPass;
    Button _btnLogin;
    Spinner _spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txtPass=(EditText)findViewById(R.id.txtPass);
        _txtUser=(EditText)findViewById(R.id.txtUser);
        _btnLogin=(Button)findViewById(R.id.btnLogin);
        _spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.usertype, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = _spinner.getSelectedItem().toString();
                if (_txtUser.getText().toString().equals("Admin") && _txtPass.getText().toString().equals("Admin") && item.equals("Admin")) {
                    Intent intent = new Intent(MainActivity.this, Admin.class);
                    startActivity(intent);
                }else if(_txtUser.getText().toString().equals("user") && _txtPass.getText().toString().equals("user") && item.equals("user")){
                    Intent intent = new Intent(MainActivity.this, user.class);
                    startActivity(intent);
                }else if(_txtUser.getText().toString().equals("Owner") && _txtPass.getText().toString().equals("Owner") && item.equals("Owner")){
                    Intent intent = new Intent(MainActivity.this, Owner.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}