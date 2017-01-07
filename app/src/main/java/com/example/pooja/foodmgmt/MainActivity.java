package com.example.pooja.foodmgmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{


    DBHelper db;
    public static boolean ss;
    static  Activity c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c=this;

        //Create sqlite database object.
        db=new DBHelper(this);
        Cursor c=db.retrieveData();

        //Only one row is allowed in my sqlite. So check if already present. If not, insert data.
        if(c!=null &&c.getCount()==0)
            db.insertData();


        Cursor cursor=db.retrieveData();
        cursor.moveToFirst();

        //Get id of user from database
        String id=cursor.getString(cursor.getColumnIndex("id"));
        if(id.equals(""))
        {
            //If user is already not logged in, switch to Login page
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }


        //If user is already logged in, switch to corresponding user's home page
        else
        {
            if(id.startsWith("vendor"))
            {

                startActivity(new Intent(getApplicationContext(),VendorHome.class));
                finish();
            }
            if(id.startsWith("ngo"))
            {
                startActivity(new Intent(getApplicationContext(),NgoHome.class));
                finish();
            }
        }



        class CheckNetwork {
            public boolean haveNetworkConnection() {

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                return netInfo != null;
            }

        }

        Timer t1 = new Timer();
        t1.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                CheckNetwork cc=new CheckNetwork();
                ss=cc.haveNetworkConnection();
            }


        }

            ,0,3000);




        //This is for running continuously after 5 minutes to check whether uploaded food data have crossed 4 hours of time limit.
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                CheckNetwork cc=new CheckNetwork();
                ss=cc.haveNetworkConnection();
                if(!ss)

                {
                    String method = "check_time";
                    BackgroundTask bg = new BackgroundTask(MainActivity.this);
                    bg.execute(method);
                }
            }

        },0,300000);



    }


}
