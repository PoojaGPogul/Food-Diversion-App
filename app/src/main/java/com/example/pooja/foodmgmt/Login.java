package com.example.pooja.foodmgmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class Login extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    public  static  EditText user,pass;
    public  static Spinner type;
    public static Button login;
    public static String tp;
    public static TextView invalid_em,invalid_pass;
    static Activity context;
    TextView register;
    static  DBHelper db;
    public static LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;
        linearLayout=(LinearLayout)findViewById(R.id.layout);


        db=new DBHelper(this);
        Cursor c=db.retrieveData();
        if(c!=null &&c.getCount()==0)
            db.insertData();

        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        type=(Spinner)findViewById(R.id.type);
        type.setOnItemSelectedListener(this);

        invalid_em=(TextView) findViewById(R.id.invalid_email);
        invalid_pass=(TextView) findViewById(R.id.invalid_password);


        register=(TextView)findViewById(R.id.register_here);
        register.setOnClickListener(this);


        //It is to add in the spinner i.e. Combobox i.e. The types of users
        LinkedList list=new LinkedList();
        list.add("Doner");
        list.add("NGO");
        ArrayAdapter dataAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(dataAdapter);

    }

    @Override
    public void onClick(View v) {

        //For LOGIN
        if (v.getId() == R.id.login) {

            String usr = user.getText().toString();
            String ps = pass.getText().toString();

            //This is for validation of the textfields
            if(usr.equals("") ||  !android.util.Patterns.EMAIL_ADDRESS.matcher(usr).matches())
            {
                invalid_em.setText("Invalid Email");
                invalid_pass.setText("");
            }
            else if(ps.equals(""))
            {
                invalid_pass.setText("Invalid Password");
                invalid_em.setText("");
            }
            else
            {
                invalid_em.setText("");
                invalid_pass.setText("");

                //If email and password are correct, go to database by using this.

                if(!MainActivity.ss)
                    Toast.makeText(getApplicationContext(),"Check your internet connection...",Toast.LENGTH_SHORT).show();
                else
                {
                    BackgroundTask bg = new BackgroundTask(this);
                    String method = "login";
                    bg.execute(method, usr, ps, tp);
                }
            }
        }

        //For REGISTRATION switch to CreateAccount activity
        if(v.getId()==R.id.register_here)
        {
            startActivity(new Intent(getApplicationContext(),CreateAccount.class));
            finish();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //get which item is selected from spinner
        tp=parent.getItemAtPosition(position).toString();
        if(position==0)
            tp="vendor";
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
