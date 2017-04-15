package com.pratikm.PatientTracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(this);
        if(!(session.loggedIn())) {
            logout();
        }

        TextView mTextViewWelcome = (TextView)findViewById(R.id.tv_welcome_username);
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String welcomeMessage;
        if(hour>=12&&hour<=17)
            welcomeMessage = "Good Afternoon";
        else if(hour>=18&&hour<=24)
            welcomeMessage = "Good Evening";
        else
            welcomeMessage = "Good Morning";

        mTextViewWelcome.setText(welcomeMessage);

        Button mButtonAddPatient = (Button)findViewById(R.id.button_add_patient);
        Button mButtonAddRecord = (Button)findViewById(R.id.button_add_health_record);
        Button mButtonSearchName = (Button)findViewById(R.id.button_search_name);
        Button mButtonSearchArrival = (Button)findViewById(R.id.button_search_arrival);


        mButtonAddPatient.setOnClickListener(this);
        mButtonAddRecord.setOnClickListener(this);
        mButtonSearchName.setOnClickListener(this);
        mButtonSearchArrival.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_add_patient:
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
                break;
            case R.id.button_add_health_record:
                Intent addHealthIntent = new Intent(MainActivity.this, AddHealthRecordActivity.class);
                startActivity(addHealthIntent);
                break;
            case R.id.button_search_name:
                Intent searchIntent = new Intent(MainActivity.this, SearchNameActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.button_search_arrival:
                Intent searchDateIntent = new Intent(MainActivity.this, SearchDateActivity.class);
                startActivity(searchDateIntent);
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_logout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivty.class));
    }
}
