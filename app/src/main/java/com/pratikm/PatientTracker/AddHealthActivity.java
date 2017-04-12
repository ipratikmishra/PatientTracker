package com.pratikm.PatientTracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddHealthActivity extends AppCompatActivity {

    PatientHealthCRUD helper = new PatientHealthCRUD(this);
    String email;
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() { return this.email; }
    Spinner mSpinnerBloodGroup;
    public static String bloodType;
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health);

        Intent intent= getIntent();
        Bundle b = intent.getExtras();

        if(b!=null)
        {
            String email =(String) b.get("email");
            setEmail(email);

        }

        mSpinnerBloodGroup = (Spinner)findViewById(R.id.spinner_blood);
        mSpinnerBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bloodType = mSpinnerBloodGroup.getSelectedItem().toString();
                setBloodType(bloodType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void onSaveClick(View view) {


        EditText mEditTextAge = (EditText)findViewById(R.id.editText_age);

//        String bloodType = mSpinnerBloodGroup.getSelectedItem().toString();


        EditText mEditTextCondition = (EditText)findViewById(R.id.editText_condition);
        EditText mEditTextMedication = (EditText)findViewById(R.id.editText_medication);
        EditText mEditTextNotes = (EditText)findViewById(R.id.editText_notes);
        EditText mEditTextDateVisit = (EditText)findViewById(R.id.editText_visit_date);

        String age = mEditTextAge.getText().toString();
        String condition = mEditTextCondition.getText().toString();
        String medication = mEditTextMedication.getText().toString();
        String notes = mEditTextNotes.getText().toString();
        String dateVisit = mEditTextDateVisit.getText().toString();

        if(dateVisit.equals("")) {
            Toast error = Toast.makeText(AddHealthActivity.this, "Required fields * are empty", Toast.LENGTH_LONG);
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
            Toast patientCreatedMessage = Toast.makeText(AddHealthActivity.this, "Health Information Added!", Toast.LENGTH_LONG);
            patientCreatedMessage.show();
            finish();
        }

    }
}
