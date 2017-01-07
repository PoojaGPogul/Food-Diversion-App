package com.example.pooja.foodmgmt;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    public static EditText name,passwd,confirmp,email,contact,address;
    public static  Button signup;
    static Activity context;
    public static TextView invalid_email,invalid_pass,invalid_cnt,invalid_name,invalid_addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        context=this;


        name=(EditText)findViewById(R.id.fullname);
       // uname=(EditText)findViewById(R.id.username);
        passwd=(EditText)findViewById(R.id.password);
        confirmp=(EditText)findViewById(R.id.confirm);
        email=(EditText)findViewById(R.id.email);
        contact=(EditText)findViewById(R.id.contact);
        address=(EditText)findViewById(R.id.address);

        signup=(Button)findViewById(R.id.signup_vendor);
        signup.setOnClickListener(this);

        invalid_email=(TextView)findViewById(R.id.invalid_email);
        invalid_pass=(TextView)findViewById(R.id.invalid_password);
        invalid_cnt=(TextView)findViewById(R.id.invalid_contact);
        invalid_name=(TextView)findViewById(R.id.invalid_name);
        invalid_addr=(TextView)findViewById(R.id.invalid_address);



    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signup_vendor) {
            String nm = name.getText().toString();
            //   String unm=uname.getText().toString();
            String ps = passwd.getText().toString();
            String confirm = confirmp.getText().toString();
            String eml = email.getText().toString();
            String cnt = contact.getText().toString();
            String addr = address.getText().toString();

            invalid_email.setText("");
            invalid_pass.setText("");
            invalid_cnt.setText("");
            invalid_addr.setText("");
            invalid_name.setText("");






            if (nm.isEmpty())
                invalid_name.setText("Invalid Name");
            else if(!nm.isEmpty())
            {
                invalid_name.setText("");
                if (eml.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(eml).matches())
                     invalid_email.setText("Invalid Email");
                else
                {
                    invalid_email.setText("");
                    if (ps.isEmpty())
                        invalid_pass.setText("Invalid Password");
                    else
                    {
                        invalid_pass.setText("");
                        if (!ps.equals(confirm)) {
                            Toast.makeText(this, "Passwords do not match, please retype", Toast.LENGTH_SHORT).show();
                            passwd.setText("");
                            confirmp.setText("");

                        }
                        else
                        {
                        if (cnt.isEmpty())
                            invalid_cnt.setText("Invalid Contact number");
                        else {
                            invalid_cnt.setText("");
                            if (addr.isEmpty())
                                invalid_addr.setText("Invalid Address");
                            else {
                                invalid_addr.setText("");
                                BackgroundTask bg = new BackgroundTask(this);
                                String method = "signup";
                                bg.execute(method, nm, ps, eml, cnt, addr);
                            }
                        }}}}}

        }
    }



    }
