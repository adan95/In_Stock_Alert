package com.example.instockalert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerification extends AppCompatActivity {


    private TextView verifyReasonTextView;
    private Button resendEmailButton;
    private Dialog verifiedDialog;

    private FirebaseUser user;
    private Boolean isVerified;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        user = FirebaseAuth.getInstance().getCurrentUser();
        isVerified = false;

        waitForVerification();
        setComponents();
    }

    private void waitForVerification() {
        final Handler handler = new Handler();
        final int delay = 5000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){

                user.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            isVerified = user.isEmailVerified();
                            if(isVerified){
                                showVerifiedPopUp();
                            }
                        }
                    }
                });
                if (!isVerified)
                    handler.postDelayed(this, delay);
            }
        }, delay);
    }

    private void setComponents() {
        verifyReasonTextView = findViewById(R.id.verifyReasonTextView);
        resendEmailButton = findViewById(R.id.resendEmailButton);
        verifiedDialog = new Dialog(this);

        verifyReasonTextView.setText(String.format(getString(R.string.verify_reason), user.getEmail()));

        setButtonListeners();
    }

    private void setButtonListeners() {
        resendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmailVerification.this, "This will resend the verification email!", Toast.LENGTH_LONG).show();

                // TODO: Resend verification email
            }
        });
    }

    private void showVerifiedPopUp() {
        ImageView checkmark;
        Button dashboardButton;

        verifiedDialog.setContentView(R.layout.activity_verified_pop_up);
        checkmark = verifiedDialog.findViewById(R.id.checkmarkImageView);
        dashboardButton = verifiedDialog.findViewById(R.id.dashboardButton);

        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifiedDialog.dismiss();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        verifiedDialog.show();
    }
}