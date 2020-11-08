package com.example.csit242_project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Models.UserAccount;

public class LoginActivity extends Activity {

    private FirebaseAuth auth;
    EditText email_EditText;
    EditText password_EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        auth = FirebaseAuth.getInstance();
        email_EditText = findViewById(R.id.loginActivity_email_EditText);
        password_EditText = findViewById(R.id.loginActivity_password_EditText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
    }

    public void loginInAsExistingUser(View view) {
        if(validateFields()) {
            String email = email_EditText.getText().toString();
            String password = password_EditText.getText().toString();
            String errorMessage = "Please make sure the email format is correct and you entered the correct password";

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        System.out.println("Logged in successfully");
                        goToMainDashboardActivity();
                    } else {
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void goToMainDashboardActivity() {
        Intent intent = new Intent(this, MainDashboardActivity.class);
        startActivity(intent);
    }

    public Boolean validateFields() {

        String email = email_EditText.getText().toString();
        String password = password_EditText.getText().toString();
        String errorMessage_hasEmptyFields = "Please make sure all fields are filled up";

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, errorMessage_hasEmptyFields, Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }


    public void goToSignUpActivity(View view) {
        Intent goToSignUpActivity_Intent = new Intent(this, SignupActivity.class);
        startActivity(goToSignUpActivity_Intent);
    }
}