package com.example.innova;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ProductMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductMainAdapter mainAdapter;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ProductMainModel> options =
                new FirebaseRecyclerOptions.Builder<ProductMainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), ProductMainModel.class)
                        .build();

        mainAdapter = new ProductMainAdapter(options);
        recyclerView.setAdapter(mainAdapter);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddProductActivity.class));
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
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

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

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<ProductMainModel> options =
                new FirebaseRecyclerOptions.Builder<ProductMainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products").orderByChild("productname").startAt(str).endAt(str+"~"), ProductMainModel.class)
                        .build();

        mainAdapter = new ProductMainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);

    }
}