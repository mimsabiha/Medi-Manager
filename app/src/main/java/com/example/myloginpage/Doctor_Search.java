package com.example.myloginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doctor_Search extends AppCompatActivity {
    RecyclerView recyclerView;
    String [] titleName,designation,visitingHour,address,qualification,contact;
    DoctorAdapter demoAdapter;
    //LinearLayoutManager layoutManager;
    List<String> doctor;
    List<User>userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        titleName = getResources().getStringArray(R.array.doctrName);
        designation = getResources().getStringArray(R.array.doctorSpecialist);
        visitingHour = getResources().getStringArray(R.array.doctorVisitingHour);
        address = getResources().getStringArray(R.array.doctorAddress);
        qualification = getResources().getStringArray(R.array.doctorqualification);
        contact = getResources().getStringArray(R.array.doctorContactNumber);
        doctor = Arrays.asList(titleName); //It's a list
        for(int i=0;i<titleName.length;i++)
        {
            User user = new User(titleName[i],designation[i]);
            userList.add(user);
        }
        //demoAdapter = new DemoAdapter(this,titleName,designation);
        demoAdapter = new DoctorAdapter(this,userList);
        recyclerView.setAdapter(demoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        demoAdapter.setOnItemClickListener(new DoctorAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //Toast.makeText(getApplicationContext(),"on item click"+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),DoctorProfile.class);
                intent.putExtra("docName",titleName[position]);
                intent.putExtra("docAddress",address[position]);
                intent.putExtra("docVisitingHour",visitingHour[position]);
                intent.putExtra("docContact",contact[position]);
                intent.putExtra("docSpeciality",designation[position]);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Toast.makeText(getApplicationContext(),"on item Long click"+position,Toast.LENGTH_SHORT).show();

            }
        });


    }

    //menu options activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.actin_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                demoAdapter.getFilter().filter(s.toString());
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}