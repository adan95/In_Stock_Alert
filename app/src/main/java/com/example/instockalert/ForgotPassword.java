package com.example.instockalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity{

    //private TextView forgotYourPText;
    //private TextView textScript;
    //private ImageView imageMail;
    private TextView textBack;
    //Email used to reset password
    private EditText editTextEmailAddress;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forgot_password);

            setComponents();
        }

        private void setComponents(){
            //forgotYourPText = (TextView) findViewById(R.id.forgotYourPtext);
            //textScript = (TextView) findViewById(R.id.textScript);
            textBack = (TextView)  findViewById(R.id.textBack);
            editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);

            setButtonListeners();
        }

        private void setButtonListeners() {
            textBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ForgotPassword.this, "This will go back to the login!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), Login.class);
                    startActivity(intent);
                }
            });
    }

}



