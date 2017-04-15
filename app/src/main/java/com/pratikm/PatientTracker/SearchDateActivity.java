package com.pratikm.PatientTracker;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SearchDateActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SearchDateAdapter mRecyclerViewAdapter;
    PatientHealthCRUD healthRecords = new PatientHealthCRUD(this);
    String date;
    EditText mEditTextDateVisit;
    Button mButtonSearchDate;
    Calendar myCalendar;
    public void setDate(String date){this.date = date;}
    public String getDate(){return this.date;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_date);

        mEditTextDateVisit = (EditText)findViewById(R.id.editText_search_date);
        mButtonSearchDate = (Button)findViewById(R.id.button_search_date);

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        mEditTextDateVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                new DatePickerDialog(SearchDateActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        mButtonSearchDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchButtonClick(view);
            }
        });
    }
    public void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextDateVisit.setText(sdf.format(myCalendar.getTime()));
        String date = mEditTextDateVisit.getText().toString();
        setDate(date);
    }
    public void onSearchButtonClick(View view){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_search_date);
        mRecyclerViewAdapter = new SearchDateAdapter(this, healthRecords.getHealthListByDate(getDate()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }
}
