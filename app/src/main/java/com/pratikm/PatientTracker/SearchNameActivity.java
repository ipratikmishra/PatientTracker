package com.pratikm.PatientTracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
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

public class SearchNameActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchNameAdapter.AdapterCallback{

    PatientContactCRUD patients = new PatientContactCRUD(this);
    RecyclerView mRecyclerView;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
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
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchNameActivity.this,AddContactActivity.class));
                finish();
            }
        });
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
        for(int i = 0; i < patientList.size(); i++) {
            PatientContactContract currentPatient =  patientList.get(i);
            String name = currentPatient.getFirstName().toLowerCase();
            if(name.contains(newText)) {
                newList.add(currentPatient);
            }
        }
        mRecyclerViewAdapter.setFilter(newList);
        return true;
    }


    @Override
    public void deleteRecord(String Email) {
        patients.deletePatientByEmail(Email);
    }
}
