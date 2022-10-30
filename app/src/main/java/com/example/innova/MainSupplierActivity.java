package com.example.innova;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainSupplierActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    //FloatingActionButton floatingActionButton;
    FloatingActionButton floatingActionButton1, floatingActionButton2 ,floatingActionButton3,floatingActionButton4;
    Animation fabOpen,fabClose,rotateForward,rotateBackward;
    boolean isOpen = false;// by defulat it is false


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_supplier);


        //animations
        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);
        rotateForward =AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward =AnimationUtils.loadAnimation(this, R.anim.rotate_backward);




        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("supplier"), MainModel.class)
                        .build();


        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);


        floatingActionButton1 = (FloatingActionButton)findViewById(R.id.floatingActionButton1);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
                Toast.makeText(MainSupplierActivity.this, "Add Supplier ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),AddActivity2.class));

            }
        });

        floatingActionButton2 = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
                Toast.makeText(MainSupplierActivity.this, "Calculator Click", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Calculate.class));

            }
        });

        floatingActionButton3 = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
                Toast.makeText(MainSupplierActivity.this, "Back To Owner Portal", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Owner.class));

            }
        });

        floatingActionButton4 = (FloatingActionButton)findViewById(R.id.floatingActionButton4);
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();


            }
        });








    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.sarch,menu);//is one need to be search
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    //product can be use  name
    private void txtSearch(String str){

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("supplier").orderByChild("product").startAt(str).endAt(str+"~"), MainModel.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);


    }


    private void animateFab(){
        if (isOpen){
            floatingActionButton4.startAnimation(rotateForward);
            floatingActionButton2.startAnimation(fabClose);
            floatingActionButton3 .startAnimation(fabClose);
            floatingActionButton1.startAnimation(fabClose);
            floatingActionButton2.setClickable(false);
            floatingActionButton3.setClickable(false);
            floatingActionButton1.setClickable(false);
            isOpen=false;
        }
        else{
            floatingActionButton4.startAnimation(rotateBackward);
            floatingActionButton2.startAnimation(fabOpen);
            floatingActionButton3.startAnimation(fabOpen);
            floatingActionButton1.startAnimation(fabOpen);
            floatingActionButton2.setClickable(true);
            floatingActionButton3.setClickable(true);
            floatingActionButton1.setClickable(true);
            isOpen=true;

        }
    }
}