package com.pratikm.PatientTracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class HealthDetailsActivity extends AppCompatActivity {
    PatientHealthCRUD healthDetails = new PatientHealthCRUD(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_details);
        RecyclerView mRecyclerView;
        HealthDetailsAdapter mRecyclerViewAdapter;
        String email = getIntent().getStringExtra("clickedPatientEmail");
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_health_details);
        mRecyclerViewAdapter = new HealthDetailsAdapter(this, healthDetails.getHealthList(email));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }
}
