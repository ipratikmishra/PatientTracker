package com.pratikm.PatientTracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    LoginCRUD helper = new LoginCRUD(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    public void onSignUpClick(View view) {

            EditText mEditTextName = (EditText) findViewById(R.id.editText_name_signup);
            EditText mEditTextEmail = (EditText) findViewById(R.id.editText_email_signup);
            EditText mEditTextUsername = (EditText) findViewById(R.id.editText_username_signup);
            EditText mEditTextPassword = (EditText) findViewById(R.id.editText_password_signup);
            EditText mEditTextConfirmPassword = (EditText) findViewById(R.id.editText_password_confirm_signup);

            String name = mEditTextName.getText().toString();
            String username = mEditTextUsername.getText().toString();
            String email = mEditTextEmail.getText().toString();
            String password = mEditTextPassword.getText().toString();
            String confirmPassword = mEditTextConfirmPassword.getText().toString();

            if (!(confirmPassword).equals(password)) {
                Toast error = Toast.makeText(SignUpActivity.this, "Passwords entered do not match!", Toast.LENGTH_SHORT);
                error.show();
            }
            else if (!((helper.searchPass(username)).equals("Not Found"))){
                Toast error = Toast.makeText(SignUpActivity.this, "Username already exists!", Toast.LENGTH_SHORT);
                error.show();
            }
            else if((username).equals("")){
                Toast error = Toast.makeText(SignUpActivity.this, "Please enter valid Username", Toast.LENGTH_SHORT);
                error.show();
            }
            else if((name).equals("")) {
                Toast error = Toast.makeText(SignUpActivity.this, "Please enter valid Name", Toast.LENGTH_SHORT);
                error.show();
            }
            else if(password.length()<6 || password.length()>18) {
                Toast error = Toast.makeText(SignUpActivity.this, "Passwords enter a password longer than 6 characters!", Toast.LENGTH_SHORT);
                error.show();
            }
            else {
                LoginContract c = new LoginContract();
                c.setName(name);
                c.setEmail(email);
                c.setUsername(username);
                c.setPassword(password);

                helper.insertContact(c);
                Toast userCreatedMessage = Toast.makeText(SignUpActivity.this, "New User Registered!", Toast.LENGTH_LONG);
                userCreatedMessage.show();
                this.finish();
            }
    }
}
