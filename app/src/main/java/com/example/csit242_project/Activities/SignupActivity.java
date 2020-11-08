package com.example.csit242_project.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Models.UserAccount;

public class SignupActivity extends Activity {

    private FirebaseAuth auth;

    EditText email_EditText;
    EditText emailConfirm_EditText;
    EditText password_EditText;
    EditText passwordConfirm_EditText;
    TextView errorMessage_TextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        auth = FirebaseAuth.getInstance();
        email_EditText = findViewById(R.id.signupActivity_email_EditText);
        emailConfirm_EditText = findViewById(R.id.signupActivity_emailConfirm_EditText);
        password_EditText = findViewById(R.id.signupActivity_password_EditText);
        passwordConfirm_EditText = findViewById(R.id.signupActivity_passwordConfirm_EditText);
        errorMessage_TextView = findViewById(R.id.signupActivity_errorMessage_TextView);
    }


    public void createNewUser(View view) {

        Boolean areFieldsValidated;
        areFieldsValidated = validateFields();
        if(areFieldsValidated){
            String email = email_EditText.getText().toString();
            String password = password_EditText.getText().toString();
            UserAccount newUser = new UserAccount(email, password);
            auth.createUserWithEmailAndPassword(newUser.getEmail(), newUser.getPassword()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        System.out.println("User was created successfully!");
                    } else {
                        System.out.println("User was not created");
                    }
                }
            });
        }
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
            Toast.makeText(this, errorMessage_hasEmptyFields, Toast.LENGTH_LONG).show();
            return false;
        } else {
            if(!email.equals(emailConfirm)) {
                Toast.makeText(this, errorMessage_mismatchEmails, Toast.LENGTH_LONG).show();
                return false;
            } else if(!password.equals(passwordConfirm)) {
                Toast.makeText(this, errorMessage_mismatchPasswords, Toast.LENGTH_LONG).show();
                return false;
            } else {
                return true;
            }
        }
    }
}
