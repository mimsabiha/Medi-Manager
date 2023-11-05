package com.example.myloginpage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Ambulance extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] nameAmbulance,locationAmbulance,numberAmbulance;
    List<UserAmbulance> userList = new ArrayList<>();
    AmbulanceAdapter ambulanceAdapter;
    private static final int REQUEST_CALL = 1;
    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        recyclerView = findViewById(R.id.recyclerView_Id);
        nameAmbulance = getResources().getStringArray(R.array.ambulanceName);
        locationAmbulance = getResources().getStringArray(R.array.ambulanceLocation);
        numberAmbulance = getResources().getStringArray(R.array.ambulanceNumber);
        for(int i=0;i<numberAmbulance.length;i++)
        {
            UserAmbulance user = new UserAmbulance(nameAmbulance[i],locationAmbulance[i],numberAmbulance[i]);
            userList.add(user);
        }
        ambulanceAdapter = new AmbulanceAdapter(this,userList);
        recyclerView.setAdapter(ambulanceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        ambulanceAdapter.setOnItemClickListener(new AmbulanceAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
               /* String phone = numberAmbulance[position];

                {   // call button fnctionality
                    String s = "tel:" + phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }*/
                pos = setpos(position);
                makePhoneCall(position);


            }

            @Override
            public void onItemLongClick(int position, View v) {
                Toast.makeText(getApplicationContext(),"on item Long click"+position,Toast.LENGTH_SHORT).show();

            }
        });



    }

    public int setpos(int position)
    {
        this.pos = position;
        return  position;
    }

    private void makePhoneCall(int position) {
        String number = numberAmbulance[position];
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Ambulance.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(Ambulance.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall(pos);
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}