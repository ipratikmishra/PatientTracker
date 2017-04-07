package com.pratikm.PatientTracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTextViewWelcome = (TextView)findViewById(R.id.tv_welcome_username);
        String welcomeMessage = "Welcome, " + getIntent().getStringExtra("Username");
        mTextViewWelcome.setText(welcomeMessage);

    }
}
