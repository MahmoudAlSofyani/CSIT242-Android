package com.example.csit242_project.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.csit242_project.R;

public class SignupActivity extends Activity {


    EditText email_EditText;
    EditText emailConfirm_EditText;
    EditText password_EditText;
    EditText passwordConfirm_EditText;
    TextView errorMessage_TextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        email_EditText = findViewById(R.id.signupActivity_email_EditText);
        emailConfirm_EditText = findViewById(R.id.signupActivity_emailConfirm_EditText);
        password_EditText = findViewById(R.id.signupActivity_password_EditText);
        passwordConfirm_EditText = findViewById(R.id.signupActivity_passwordConfirm_EditText);
        errorMessage_TextView = findViewById(R.id.signupActivity_errorMessage_TextView);
    }

    public void createNewUser(View view) {

        Boolean areFieldsValidated;
        areFieldsValidated = validateFields();

        System.out.println("Are Fields Validated?: " + areFieldsValidated);

    }



    public Boolean validateFields() {

        String email = email_EditText.getText().toString();
        String emailConfirm = emailConfirm_EditText.getText().toString();
        String password = password_EditText.getText().toString();
        String passwordConfirm = passwordConfirm_EditText.getText().toString();
        String errorMessage_hasEmptyFields = "Please make sure all fields are filled up";
        String errorMessage_mismatchEmails = "Please make sure emails match";
        String errorMessage_mismatchPasswords = "please make sure passwords match";

        if(email.isEmpty() || emailConfirm.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            errorMessage_TextView.setText(errorMessage_hasEmptyFields);
            return false;
        } else {
            if(!email.equals(emailConfirm)) {
                errorMessage_TextView.setText(errorMessage_mismatchEmails);
                return false;
            } else if(!password.equals(passwordConfirm)) {
                errorMessage_TextView.setText(errorMessage_mismatchPasswords);
                return false;
            } else {
                return true;
            }
        }
    }
}
