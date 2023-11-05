package com.example.myloginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard1 extends AppCompatActivity implements View.OnClickListener {

    private CardView DorctorSearch, AmbulanceService, HealthBlog,HealthTips,MedicineAlarm,MedicineService,chatBot,OpticalAid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);

        DorctorSearch=findViewById(R.id.doctorSearchID);
        AmbulanceService=findViewById(R.id.ambulanceID);
        MedicineAlarm=findViewById(R.id.mediAlarmId);
        HealthBlog=findViewById(R.id.helthBlogID);
        HealthTips=findViewById(R.id.heathTipsID);
        MedicineService=findViewById(R.id.medicineServiceID);

        OpticalAid=findViewById(R.id.OpticalAidId);
        chatBot=findViewById(R.id.ChatBotID);



        DorctorSearch.setOnClickListener(this);
        AmbulanceService.setOnClickListener(this);
        MedicineAlarm.setOnClickListener(this);
        HealthBlog.setOnClickListener(this);
        HealthTips.setOnClickListener(this);
        MedicineService.setOnClickListener(this);

        OpticalAid.setOnClickListener(this);
        chatBot.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.doctorSearchID){
            System.out.println("doctorSearchID");
            Intent noyaIntent = new Intent(getApplicationContext(), Doctor_Search.class);
            startActivity(noyaIntent);
        }
        else if(v.getId()==R.id.ambulanceID){
            System.out.println("doctorSearchID");
            Intent noyaIntent = new Intent(getApplicationContext(),Ambulance.class );
            startActivity(noyaIntent);
        }
        else if(v.getId()==R.id.mediAlarmId){
            //System.out.println("mediAlarmId");
            Intent noyaIntent = new Intent(getApplicationContext(),AlarmMain.class);
            startActivity(noyaIntent);
        }
        else if(v.getId()==R.id.helthBlogID){
            //System.out.println("HealthBlog ");
            Intent noyaIntent = new Intent(getApplicationContext(), HealthBlog.class);
            startActivity(noyaIntent);

        }
        else if(v.getId()==R.id.heathTipsID){
            System.out.println("Health Blog");
            Intent noyaIntent = new Intent(getApplicationContext(), Health_tips.class);
            startActivity(noyaIntent);
        }
        else if(v.getId()==R.id.medicineServiceID)
        {
            System.out.println("medicineServiceID");
            Intent mapIntent =new Intent(getApplicationContext(),MapActivity.class);
            startActivity(mapIntent);
        }
        else if(v.getId()==R.id.OpticalAidId)
        {
            System.out.println("Optical Aid Section");
            Intent opticalAidIntent =new Intent(getApplicationContext(),TextRecognition.class);
            startActivity(opticalAidIntent);
        }
        else if(v.getId()==R.id.ChatBotID)
        {
            System.out.println("ChatBot Section");
            Intent chatIntent =new Intent(getApplicationContext(),ChatActivity.class);
            startActivity(chatIntent);
        }
     
       /* else if(v.getId()==R.id.chatBotId)
        {
            System.out.println("medicineServiceID");
            Intent chatIntent =new Intent(getApplicationContext(),ChatActivity.class);
            startActivity(chatIntent);
        }
        else if(v.getId()==R.id.opticalAidID)
        {
            System.out.println("medicineServiceID");
            Intent opticalAidIntent =new Intent(getApplicationContext(),TextRecognition.class);
            startActivity(opticalAidIntent);
        }*/


    }
}