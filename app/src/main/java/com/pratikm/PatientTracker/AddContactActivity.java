package com.pratikm.PatientTracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    RadioGroup mRadioGroup;
    RadioButton mRadioButton;

    PatientContactCRUD helper = new PatientContactCRUD(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

    }

    public void rbClick(View view) {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int radioButtonId = mRadioGroup.getCheckedRadioButtonId();
        mRadioButton = (RadioButton) findViewById(radioButtonId);
        String sex = mRadioButton.getText().toString();
        setSexType(sex);
    }

    String sexType = "Female", email;
    public void setSexType(String sexType) {
        this.sexType = sexType;
    }
    public String getSex() {return this.sexType;}
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return this.email;}

    public void onClickSave(View view) {
        EditText mEditTextFirstName = (EditText)findViewById(R.id.editText_firstname);
        EditText mEditTextLastName = (EditText)findViewById(R.id.editText_lastname);
        EditText mEditTextMobile = (EditText)findViewById(R.id.editText_mobile);
        EditText mEditTextEmail = (EditText)findViewById(R.id.editText_email_add);
        EditText mEditTextPostalAddress = (EditText)findViewById(R.id.editText_postal);

        RadioButton mRadioButtonMale = (RadioButton)findViewById(R.id.rb_male);
        RadioButton mRadioButtonFemale = (RadioButton)findViewById(R.id.rb_female);


        String firstName = mEditTextFirstName.getText().toString();
        String lastName = mEditTextLastName.getText().toString();
        String mobile = mEditTextMobile.getText().toString();
        String email = mEditTextEmail.getText().toString();
        String address = mEditTextPostalAddress.getText().toString();
        setEmail(email);

        if(email.equals("")||firstName.equals("")||lastName.equals("")||mobile.equals("")) {
            Toast error = Toast.makeText(AddContactActivity.this, "Required fields * are empty", Toast.LENGTH_SHORT);
            error.show();
        }
        else if(mobile.length()<10) {
            Toast error = Toast.makeText(AddContactActivity.this, "Mobile number must be 10 digits", Toast.LENGTH_SHORT);
            error.show();
        }
        else {
            PatientContactContract c = new PatientContactContract();
            c.setFirstName(firstName);
            c.setLastName(lastName);
            c.setSex(getSex());
            c.setMobile(mobile);
            c.setEmailAddress(email);
            c.setPostalAddress(address);

            helper.insertPatientContact(c);

            Toast patientCreatedMessage = Toast.makeText(AddContactActivity.this, "New Patient Added!", Toast.LENGTH_SHORT);
            patientCreatedMessage.show();
            Intent intentHealth = new Intent(AddContactActivity.this, AddHealthActivity.class);
            intentHealth.putExtra("email", getEmail());
            startActivity(intentHealth);
            this.finish();
        }

    }
}
