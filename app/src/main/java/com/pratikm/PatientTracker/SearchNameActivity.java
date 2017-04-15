package com.pratikm.PatientTracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchNameActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    PatientContactCRUD patients = new PatientContactCRUD(this);
    RecyclerView mRecyclerView;
    Toolbar toolbar;
    SearchNameAdapter mRecyclerViewAdapter;
    ArrayList<PatientContactContract> patientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_search_name);
        mRecyclerViewAdapter = new SearchNameAdapter(this, patients.getPatientList());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_name, menu);
        MenuItem item = menu.findItem(R.id.search_button);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(Color.WHITE);
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(Color.WHITE);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<PatientContactContract> patientList = patients.getPatientList();
        newText = newText.toLowerCase();
        ArrayList<PatientContactContract> newList = new ArrayList<>();
        for(int id = 0; id<patientList.size(); id++) {
            PatientContactContract currentPatient = patients.getPatientById(id);
            String name = currentPatient.getFirstName().toLowerCase();
            if(name.contains(newText)) {
                newList.add(currentPatient);
            }
        }
        mRecyclerViewAdapter.setFilter(newList);
        return true;
    }
}
