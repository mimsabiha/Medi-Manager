package com.example.myloginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class DoctorProfile extends AppCompatActivity {
    TextInputLayout textInputLayout1;
    TextView textInputLayout2,textInputLayout3,textInputLayout4,textInputLayout5;
    String [] docName,docAddress,docVisitingHour,docContact,docSpecialty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        textInputLayout1 = findViewById(R.id.outlinedTextField_1);
        textInputLayout2 = findViewById(R.id.outlinedTextField_2);
        textInputLayout3 = findViewById(R.id.outlinedTextField_3);
        textInputLayout4 = findViewById(R.id.outlinedTextField_4);
        textInputLayout5 = findViewById(R.id.outlinedTextField_5);
        textInputLayout1.getEditText().setText(getIntent().getStringExtra("docName"));
        textInputLayout2.setText(getIntent().getStringExtra("docAddress"));
        textInputLayout3.setText(getIntent().getStringExtra("docVisitingHour"));
        textInputLayout4.setText(getIntent().getStringExtra("docContact"));
        textInputLayout5.setText(getIntent().getStringExtra("docSpeciality"));
    }
}