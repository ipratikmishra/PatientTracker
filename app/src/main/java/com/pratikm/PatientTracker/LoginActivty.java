package com.pratikm.PatientTracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivty extends AppCompatActivity {

    LoginCRUD helper = new LoginCRUD(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mSignUpButton;
        Button mSignInButton;

        final Session session = new Session(this);
        if(session.loggedIn()) {
            startActivity(new Intent(LoginActivty.this, MainActivity.class));
            finish();
        }
        mSignUpButton = (Button) findViewById(R.id.button_sign_up);
        mSignInButton = (Button) findViewById(R.id.button_sign_in);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.button_sign_up) {
                    Intent intent = new Intent(LoginActivty.this, SignUpActivity.class);
                    startActivity(intent);
                    }
                }
            });
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (view.getId()==R.id.button_sign_in){
                        final EditText mUsernameEditText;
                        final EditText mPasswordEditText;
                        mUsernameEditText = (EditText) findViewById(R.id.editText_username);
                        mPasswordEditText = (EditText) findViewById(R.id.editText_password);
                        String username = mUsernameEditText.getText().toString();
                        String password = mPasswordEditText.getText().toString();
                        String pass = helper.searchPass(username);

                        if(pass.equals(password)){
                            session.setLoggedIn(true);
                            Intent intent = new Intent(LoginActivty.this, MainActivity.class);
                            intent.putExtra("Username",username);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            Toast error1 = Toast.makeText(LoginActivty.this, "Wrong email/password entered!", Toast.LENGTH_LONG);
                            error1.show();
                        }
                    }
            }
        });

    }
}
