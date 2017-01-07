package com.example.pooja.foodmgmt;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NGODetails extends AppCompatActivity implements View.OnClickListener {

    public static String selected="";
    public static Activity context;
    public static String allData="";
    public static String cap,dtl;
    String name,contact_no,email_id,address;
    Button sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodetails);
       // Toast.makeText(NGOList.context,allData,Toast.LENGTH_SHORT).show();
        context=this;

        sel=(Button)findViewById(R.id.selectbutton);
        sel.setOnClickListener(this);


        String[] str=allData.split("---");
        name=str[1];
        email_id=str[2];
        contact_no=str[3];
        address=str[4];
       // Toast.makeText(getApplicationContext(),"in changing... "+str[1]+"  "+str[2]+"  "+str[3]+"  "+str[4]+"  ",Toast.LENGTH_SHORT).show();



        //Display user info on contacts page
        TextView tv1=(TextView)findViewById(R.id.title);
        tv1.setText(name);

        TextView tv2= (TextView) findViewById(R.id.contact_text);
        tv2.setText(tv2.getText()+contact_no);

        TextView tv3= (TextView) findViewById(R.id.location_text);
        tv3.setText(address);

        TextView tv4= (TextView) findViewById(R.id.email_text);
        tv4.setText(email_id);

//        Toast.makeText(getApplicationContext(),"Fetched from upload to details =="+UploadActivity.dtl+"   "+UploadActivity.capacity,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.selectbutton)
        {
            selected=name;
            cap=UploadActivity.capacity;
            dtl=UploadActivity.dtl;
            UploadActivity.ddd.setText(selected);
            startActivity(new Intent(getApplicationContext(),UploadActivity.class));
            finish();

        }
    }



   public void onMapClicked(View view) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    public void onCallClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "91-"+contact_no));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }
    public void onWebClicked(View view)
    {
    //    String url = "http://www.wcewlug.org";
      //  Intent i = new Intent(Intent.ACTION_VIEW);
      //  i.setData(Uri.parse(url));
      //  startActivity(i);
    }


}
