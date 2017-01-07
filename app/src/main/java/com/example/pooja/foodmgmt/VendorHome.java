package com.example.pooja.foodmgmt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class VendorHome extends AppCompatActivity {

    public static TextView email,name,contact,addr;

    DBHelper db;
    Cursor cursor;
    String str;
    public static Activity context;
    public  static LinkedList<MyNotice> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home);

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

        db=new DBHelper(this);
        cursor=db.retrieveData();
        cursor.moveToFirst();
        str=cursor.getString(cursor.getColumnIndex("id"));




        NGODetails.selected="";


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        invalidateOptionsMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {

            case R.id.action_signout:
            {
                    String method="signout";
                    String ss="";
                    if(str.contains("vendor"))
                        ss="vendor";

                    BackgroundTask backgroundTask=new BackgroundTask(this);
                    backgroundTask.execute(method,str,ss);

            }
            break;

            case R.id.action_notification:
            {
                startActivity(new Intent(getApplicationContext(),Notifications.class));

            }
            break;

            case R.id.action_change_profile:
            {
                startActivity(new Intent(getApplicationContext(),ChangeProfile.class));

            }
            break;
            case R.id.action_upload_food:
            {
                startActivity(new Intent(getApplicationContext(),UploadActivity.class));

            }
            break;


        }
        return super.onOptionsItemSelected(menuItem);
    }




}
