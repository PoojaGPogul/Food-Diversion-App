package com.example.pooja.foodmgmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

public class NgoHome extends AppCompatActivity  {

    public static TextView email,name,contact,addr;
    static ListView notices;
    public static Activity context;
    public static LinkedList<MyNotice> ll1,ll2;

    DBHelper db;
    Cursor cursor;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_home);

        context=this;

        db=new DBHelper(this);
        cursor=db.retrieveData();
        cursor.moveToFirst();
        str=cursor.getString(cursor.getColumnIndex("id"));
        String nm=cursor.getString(cursor.getColumnIndex("name"));
        String em=cursor.getString(cursor.getColumnIndex("email"));
        String ct=cursor.getString(cursor.getColumnIndex("contact"));
        String adr=cursor.getString(cursor.getColumnIndex("address"));



        name=(TextView)findViewById(R.id.name_of_user);
        name.setText(nm+"\n");
        email=(TextView)findViewById(R.id.email_text);
        email.setText(em+"\n");
        contact=(TextView)findViewById(R.id.contact_text);
        contact.setText(ct+"\n");
        addr=(TextView)findViewById(R.id.addr_text);
        addr.setText(adr+"\n");


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main1, menu);
        invalidateOptionsMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.action_signout:
            {
                String method="signout";
                String ss="";
                if(str.contains("ngo"))
                    ss="ngo";

                BackgroundTask backgroundTask=new BackgroundTask(this);
                backgroundTask.execute(method,str,ss);


            }
            break;
            case R.id.action_change_profile:
            {
                startActivity(new Intent(getApplicationContext(),ChangeProfile.class));

            }
            break;

            case R.id.action_food_details:
            {
                startActivity(new Intent(getApplicationContext(),FoodList.class));

            }
            break;

        }
        return super.onOptionsItemSelected(menuItem);
    }



}
