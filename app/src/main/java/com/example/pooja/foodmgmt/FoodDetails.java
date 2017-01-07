package com.example.pooja.foodmgmt;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodDetails extends AppCompatActivity implements View.OnClickListener
{


    DBHelper db;
    TextView nm,dt,cp,date;
    String id,foodid;
    Button reg;

    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        context=this;

        db=new DBHelper(this);
        Cursor cursor=db.retrieveData();
        cursor.moveToFirst();
        id=cursor.getString(cursor.getColumnIndex("id"));
        /////////////Add You are a ngo ::::::::name in that first textfield



        nm=(TextView)findViewById(R.id.vendor_text);
        dt =(TextView)findViewById(R.id.details_text);
        cp=(TextView)findViewById(R.id.capacity_text);
        date=(TextView)findViewById(R.id.date_text);
        reg=(Button)findViewById(R.id.register_food);
        reg.setOnClickListener(this);

        String name=VendorHome.list.get(FoodList.index).getAuthor_notice();
        String capa=VendorHome.list.get(FoodList.index).getCapacity_notice();
        String dtls=VendorHome.list.get(FoodList.index).getDetails_notice();
        String dttm =VendorHome.list.get(FoodList.index).getDate_upload_notice();;
        foodid=VendorHome.list.get(FoodList.index).getFid_notice();;

        String[] ddd=dttm.split(" ");


        //Display details of food
        nm.setText(nm.getText().toString()+"\n"+name);
        cp.setText(cp.getText().toString()+"\n"+capa);
        dt.setText(dt.getText().toString()+"\n"+dtls);
        date.setText(date.getText().toString()+"\n"+ddd[0]+"    "+ddd[1]);



    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.register_food)
        {
            //If necessary, register for food
            String method="register_food";
            BackgroundTask bg=new BackgroundTask(this);
            bg.execute(method,foodid);

        }

    }
}
