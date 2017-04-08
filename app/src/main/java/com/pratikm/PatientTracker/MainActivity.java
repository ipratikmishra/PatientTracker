package com.pratikm.PatientTracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

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

    }
}
