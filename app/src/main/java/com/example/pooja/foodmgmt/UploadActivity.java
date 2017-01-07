package com.example.pooja.foodmgmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    public static EditText details,cap;
    public static Button selectNGO,upload;
    public  static String dtl,capacity;
    TextView in_de,in_cp,in_ngo;
    public  static  TextView ddd;
    DBHelper db=new DBHelper(this);

    public static Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        context=this;

        details=(EditText)findViewById(R.id.details_view);
        cap=(EditText)findViewById(R.id.capacity_view);
        details.setText(NGODetails.dtl);
        cap.setText(NGODetails.cap);

        ddd=(TextView)findViewById(R.id.selected_ngo_view);
        ddd.setText(NGODetails.selected);


        selectNGO=(Button)findViewById(R.id.select_ngo);
        upload=(Button)findViewById(R.id.upload);

        selectNGO.setOnClickListener(this);
        upload.setOnClickListener(this);


        in_cp=(TextView)findViewById(R.id.invalid_capacity);
        in_de=(TextView)findViewById(R.id.invalid_details);
        in_ngo=(TextView)findViewById(R.id.invalid_ngo);



    }




    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.select_ngo)
        {
            dtl=details.getText().toString();
            capacity=cap.getText().toString();
            //Toast.makeText(getApplicationContext(),"Fetched from upload =="+dtl+"   "+cap,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),NGOList.class));
        }
        else if(v.getId()==R.id.upload)
        {
            dtl=details.getText().toString();
            capacity=cap.getText().toString();

            if(ddd.getText().toString().equals(""))
            {
                in_ngo.setText("Please select NGO");
            }
            else
            {
                in_ngo.setText("");
                if(dtl.equals(""))
                {
                    in_de.setText("Please fill the details");
                }
                else
                {
                    in_de.setText("");
                    if(capacity.equals(""))
                    {
                        in_cp.setText("Please fill the capacity");
                    }
                    else
                    {
                        in_cp.setText("");
                        String ll=NGOList.clicked;
                     //   Toast.makeText(getApplicationContext(),"back task called....all data filled",Toast.LENGTH_SHORT).show();
                        NGODetails.cap="";
                        NGODetails.dtl="";
                        Cursor cursor=db.retrieveData();
                        cursor.moveToFirst();
                        String id=cursor.getString(cursor.getColumnIndex("id"));
                        BackgroundTask bg=new BackgroundTask(this);
                        String method="insert_food";
                        bg.execute(method,dtl,capacity,ll,id);

                    }
                }
            }
        }

    }
}
