package com.pratikm.PatientTracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddHealthRecordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEditTextEmailAddress;
    Button mButtonAddRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health_record);
        mEditTextEmailAddress = (EditText) findViewById(R.id.editText_email_add_record);

        mButtonAddRecord = (Button) findViewById(R.id.button_add_record);
        mButtonAddRecord.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_add_record:
                String email = mEditTextEmailAddress.getText().toString();
                Intent intentHealth = new Intent(AddHealthRecordActivity.this, AddHealthActivity.class);
                intentHealth.putExtra("email", email);
                startActivity(intentHealth);
                this.finish();
                break;
        }
    }

}
