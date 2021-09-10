package com.example.instockalert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Matcher;


public class SignUp extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private EditText passwordEditText;
    private Button signUpButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        setComponents();
    }

    private void setComponents() {
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        // ============= FOR TESTING =====================
//        String firstName = "Charrisse";
//        String lastName = "Porter";
//        String email = "charrisse.porter581@myci.csuci.edu";
//        String phoneNumber = "1234567890";
//        String password = "@Abcdef12";
//
//
//        firstNameEditText.setText(firstName);
//        lastNameEditText.setText(lastName);
//        emailEditText.setText(email);
//        phoneNumberEditText.setText(phoneNumber);
//        passwordEditText.setText(password);

        // ============= FOR TESTING =====================


        setButtonListeners();
    }

    private void setButtonListeners() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString().trim();
                String lastName = lastNameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // TODO: Set/Check input validation

                Boolean validUserInput = validUserInput(firstName, lastName, email, phoneNumber, password);

                if (validUserInput) {
                    authenticateUser(email, password, firstName, lastName);
                    Intent intent = new Intent(v.getContext(), EmailVerification.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void authenticateUser(String email, String password, String firstName, String lastName) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    setUserDisplayName(firstName, lastName);
                    sendVerificationEmail();
                }
                else {
                    Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendVerificationEmail() {
        String TAG = "success";
        user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });
    }

    private void setUserDisplayName(String firstName, String lastName) {
        String TAG = "success";
        user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(firstName + " " + lastName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }


    private Boolean validUserInput(String firstName, String lastName, String email, String phoneNumber, String password) {
        Boolean isValid = true;

        // Check first name field
        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError("First name is required");
            isValid = false;
        }

        // Check last name field
        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError("Last name is required");
            isValid = false;
        }

        // Check email field
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required");
            isValid = false;
        }

        // Check password field
        if (TextUtils.isEmpty(phoneNumber)) {
            passwordEditText.setError("Phone number is required");
            isValid = false;
        }

        // Check password field
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            isValid = false;
        }

        return isValid;
    }






}