package com.example.pooja.foodmgmt;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Notifications extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DBHelper db;
    Cursor cursor;
    String str;
    public  static  TextView tv1,tv2;
    public static LinearLayout tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        tv1=(TextView)findViewById(R.id.notice_title);
        tv2=(TextView)findViewById(R.id.notice_time);
        tb=(LinearLayout)findViewById(R.id.toolbar);

//        Toast.makeText(getApplicationContext(),"  "+tv1.getText().toString(),Toast.LENGTH_SHORT).show();

        NgoHome.notices=(ListView)findViewById(R.id.notices);
        NgoHome.notices.setOnItemClickListener(this);

        db=new DBHelper(this);
        cursor=db.retrieveData();
        cursor.moveToFirst();
        str=cursor.getString(cursor.getColumnIndex("id"));
        String method="get_notifications";
        String ss="";
        if(str.contains("vendor"))
            ss="vendor";

        BackgroundTask bg=new BackgroundTask(this);
        bg.execute(method,str,ss);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        //If clicked change background color of that item and change status as visited in database

        if(NgoHome.ll1.get(position).getFlag_notice().equals("0"))
                NgoHome.notices.getChildAt(position).setBackgroundColor(Color.WHITE);
        BackgroundTask bg=new BackgroundTask(this);
        String method="change_status_of_notice";
        String fid=NgoHome.ll1.get(position).getFid_notice();
       // Toast.makeText(getApplicationContext(),"Changing status..."+position+"  "+fid+"--",Toast.LENGTH_SHORT).show();
        bg.execute(method,fid);
    }



}
