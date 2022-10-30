package com.example.innova;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainRiderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainRiderAdapter mainRiderAdapter;

    FloatingActionButton floatingActionButton,floatingActionButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rider);

        recyclerView= (RecyclerView)findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager( this));

        FirebaseRecyclerOptions<MainRiderModel> options =
                new FirebaseRecyclerOptions.Builder<MainRiderModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("riders"), MainRiderModel.class)
                        .build();

        mainRiderAdapter= new MainRiderAdapter(options);
        recyclerView.setAdapter(mainRiderAdapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton4);
        floatingActionButton1=(FloatingActionButton)findViewById(R.id.floatingActionButton6);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddRiderActivity.class));
            }
        });

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Owner.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainRiderAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainRiderAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchrider,menu);
        MenuItem item =menu.findItem(R.id.search_rider);
        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtsearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void txtsearch(String str){
        FirebaseRecyclerOptions<MainRiderModel> options =
                new FirebaseRecyclerOptions.Builder<MainRiderModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("riders").orderByChild("name").startAt(str).endAt(str+"~"), MainRiderModel.class)
                        .build();
        mainRiderAdapter=new MainRiderAdapter(options);
        mainRiderAdapter.startListening();
        recyclerView.setAdapter(mainRiderAdapter);
    }

}