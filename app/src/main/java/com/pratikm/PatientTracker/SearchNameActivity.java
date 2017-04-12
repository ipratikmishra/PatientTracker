package com.pratikm.PatientTracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class SearchNameActivity extends AppCompatActivity {

    PatientContactCRUD patients = new PatientContactCRUD(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);
        RecyclerView mRecyclerView;
        SearchNameAdapter mRecyclerViewAdapter;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_search_name);
        mRecyclerViewAdapter = new SearchNameAdapter(this, patients.getPatientList());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_name, menu);
        MenuItem item = menu.findItem(R.id.search_button);
        SearchView searchView = (SearchView) item.getActionView();
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(Color.WHITE);
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(Color.WHITE);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
