package com.example.myloginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.messaging.FirebaseMessaging;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailRegisterEditText, passRegisterEditText,phoneRegisterEditText,fullNameEditText,userNameEditText;
    private Button signUpRegisterBtn, loginInRegisterBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        emailRegisterEditText = findViewById(R.id.emailRegisterID);
        passRegisterEditText= findViewById(R.id.passRegisterID);
        phoneRegisterEditText= findViewById(R.id.phoneRegisterID);
        fullNameEditText = findViewById(R.id.fullNameID);
        userNameEditText = findViewById(R.id.userNameID);

        signUpRegisterBtn = findViewById(R.id.signupRegisterID);
        loginInRegisterBtn = findViewById(R.id.loginRegisterID);

        progressRegister=findViewById(R.id.registerPBID);

        signUpRegisterBtn.setOnClickListener(this);
        loginInRegisterBtn.setOnClickListener(this);

        //getting notification from firebase
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                    }
                });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signupRegisterID:
                userRegister();
                break;

            case R.id.loginRegisterID:
                Intent noyaIntent =new Intent(getApplicationContext(),logIn.class);
                startActivity(noyaIntent);
                break;


        }

    }

    private void userRegister() {

        String email = emailRegisterEditText.getText().toString().trim();
        String password = passRegisterEditText.getText().toString().trim();

        String fullName = fullNameEditText.getText().toString().trim();
        String userName = userNameEditText.getText().toString().trim();
        String phoneNumber = phoneRegisterEditText.getText().toString().trim();


        //checking another stuff
        if(fullName.isEmpty())
        {
            fullNameEditText.setError("Enter your Name");
            fullNameEditText.requestFocus();
            return;
        }
        if(userName.isEmpty())
        {
            userNameEditText.setError("Enter an User Name");
            userNameEditText.requestFocus();
            return;
        }
        if(phoneNumber.isEmpty()||phoneNumber.length()<11)
        {
            phoneRegisterEditText.setError("Enter a valid Phone Number");
            phoneRegisterEditText.requestFocus();
            return;
        }

        //checking the validity of the Email ID and main part
        if(email.isEmpty())
        {
            emailRegisterEditText.setError("Enter an email address");
            emailRegisterEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailRegisterEditText.setError("Enter a valid email address");
            emailRegisterEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            passRegisterEditText.setError("Enter a Password");
            passRegisterEditText.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            passRegisterEditText.setError("Password should be at least 6 character");
            passRegisterEditText.requestFocus();
            return;
        }




        progressRegister.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    progressRegister.setVisibility(View.GONE);

                    //if i want here i go to profile activity
                    //Intent noyaTntent22= new Intent(getApplicationContext(),user_profile.class);

                    Intent noyaIntent12 =new Intent(getApplicationContext(),logIn.class);
                    startActivity(noyaIntent12);
                    Toast.makeText(getApplicationContext(), "Register is Successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "User is already Registered ", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Log.d("akash", "Error :"+task.getException().getMessage());
                    }


                }

            }
        });



    }
}