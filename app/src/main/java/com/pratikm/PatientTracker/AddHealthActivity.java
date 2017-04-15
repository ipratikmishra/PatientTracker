package com.pratikm.PatientTracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddHealthActivity extends AppCompatActivity {

    PatientHealthCRUD helper = new PatientHealthCRUD(this);
    Spinner mSpinnerBloodGroup;
    EditText mEditTextDateVisit, mEditTextAge, mEditTextMedication, mEditTextNotes, mEditTextCondition;
    Calendar myCalendar;

    String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health);

        mEditTextAge = (EditText)findViewById(R.id.editText_age);
        mEditTextCondition = (EditText)findViewById(R.id.editText_condition);
        mEditTextMedication = (EditText)findViewById(R.id.editText_medication);
        mEditTextNotes = (EditText)findViewById(R.id.editText_notes);
        mEditTextDateVisit = (EditText)findViewById(R.id.editText_visit_date);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            String email = (String) b.get("email");
            setEmail(email);

        }

        mSpinnerBloodGroup = (Spinner) findViewById(R.id.spinner_blood);
        mSpinnerBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bloodType = mSpinnerBloodGroup.getSelectedItem().toString();
                setBloodType(bloodType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                new DatePickerDialog(AddHealthActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    public void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextDateVisit.setText(sdf.format(myCalendar.getTime()));
    }

    String bloodType;
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void onSaveClick(View view) {


        String age = mEditTextAge.getText().toString();
        String condition = mEditTextCondition.getText().toString();
        String medication = mEditTextMedication.getText().toString();
        String notes = mEditTextNotes.getText().toString();
        String dateVisit = mEditTextDateVisit.getText().toString();

        if(dateVisit.equals("")||getEmail().equals("")) {
            Toast error = Toast.makeText(AddHealthActivity.this, "Required fields * are empty", Toast.LENGTH_SHORT);
            error.show();
        }
        else {

            PatientHealthContract h = new PatientHealthContract();
            h.setEmail(getEmail());
            h.setAge(age);
            h.setBloodGroup(bloodType);
            h.setCondition(condition);
            h.setMedication(medication);
            h.setNotes(notes);
            h.setDateVisit(dateVisit);
            helper.insertPatientHealth(h);
            Toast patientCreatedMessage = Toast.makeText(AddHealthActivity.this, "Health Information Added!", Toast.LENGTH_SHORT);
            patientCreatedMessage.show();
            this.finish();
        }

    }
}
