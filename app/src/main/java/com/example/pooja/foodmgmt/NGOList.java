package com.example.pooja.foodmgmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NGOList extends AppCompatActivity implements View.OnClickListener {

    public static Button b1,b2;
    public static String clicked;
    public static Activity context;
   // public static String allData="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngolist);
        context=this;
        clicked="";


        b1=(Button)findViewById(R.id.ngo_1);
        b2=(Button)findViewById(R.id.ngo_2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        b1.setText("Lions Club Care Foundation Charitable Trust, Sangli");
        b2.setText("Balkalyan Sankul, Kolhapur");

    }

    @Override
    public void onClick(View v) {

        //maintaining which ngo is selected
        if(v.getId()==R.id.ngo_1)
        {
            clicked="ngo_1";
        }
        else if(v.getId()==R.id.ngo_2)
        {
            clicked="ngo_2";
        }

        //Call to database to get details of NGOs


        //Toast.makeText(getApplicationContext(),"Fetched from upload to list =="+UploadActivity.dtl+"   "+UploadActivity.capacity,Toast.LENGTH_SHORT).show();
        BackgroundTask bg=new BackgroundTask(this);
        String method="load_contacts";
        bg.execute(method,clicked);

    }
}
