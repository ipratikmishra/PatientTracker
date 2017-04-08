package com.pratikm.PatientTracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTextViewWelcome = (TextView)findViewById(R.id.tv_welcome_username);
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String welcomeMessage;
        if(hour>=12&&hour<=24)
            welcomeMessage = "Good Afternoon, " + getIntent().getStringExtra("Username");
        else
            welcomeMessage = "Good Morning, " + getIntent().getStringExtra("Username");

        mTextViewWelcome.setText(welcomeMessage);

        Button mButtonAddPatient = (Button)findViewById(R.id.button_add_patient);
        Button mButtonSearchName = (Button)findViewById(R.id.button_search_name);
        Button mButtonSearchArrival = (Button)findViewById(R.id.button_search_arrival);


        mButtonAddPatient.setOnClickListener(this);
        mButtonSearchName.setOnClickListener(this);
        mButtonSearchArrival.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_add_patient:
                Intent intent = new Intent(MainActivity.this, AddPatientActivity.class);
                startActivity(intent);
                break;
            case R.id.button_search_name:
                break;
            case R.id.button_search_arrival:
                break;
        }
    }
}
