package com.example.pooja.foodmgmt;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class ChangeProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{

    public  static  Spinner update;
    Button b1;
    public static EditText e_field1,e_field2,e_field3;
    String tt,col_name;
    public  static Activity context;
    public  static TextView invalid_1,invalid_2,invalid_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        context=this;

        update=(Spinner)findViewById(R.id.update_spinner);
        update.setOnItemSelectedListener(this);

        LinkedList list=new LinkedList();
        list.add("Name");
        list.add("Email ID");
        list.add("Password");
        list.add("Contact Number");
        list.add("Address");
        ArrayAdapter dataAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        update.setAdapter(dataAdapter);


        b1=(Button)findViewById(R.id.update_button);
        b1.setOnClickListener(this);



        //EditTexts
        e_field1=(EditText)findViewById(R.id.field1);
        e_field2=(EditText)findViewById(R.id.field2);
        e_field3=(EditText)findViewById(R.id.field3);

        e_field2.setHint("Enter new password");
        e_field3.setHint("Confirm new password");


        //For errors
        invalid_1=(TextView)findViewById(R.id.invalid_data);
        invalid_2=(TextView)findViewById(R.id.invalid_data2);
        invalid_3=(TextView)findViewById(R.id.invalid_data3);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //To find which quantity to update
        tt=parent.getItemAtPosition(position).toString();

        invalid_1.setText("");
        e_field1.setText("");
        e_field2.setText("");
        e_field3.setText("");
        e_field2.setVisibility(View.INVISIBLE);
        e_field3.setVisibility(View.INVISIBLE);
        e_field2.setHeight(0);
        e_field3.setHeight(0);

        if(tt.equals("Name"))
        {
            e_field1.setHint("Enter new name");
            e_field1.setInputType(InputType.TYPE_CLASS_TEXT);
            col_name="name";
        }
        else if(tt.equals("Email ID"))
        {
            e_field1.setHint("Enter new email ID");
            e_field1.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            col_name="email_id";
        }
        else if(tt.equals("Contact Number"))
        {
            e_field1.setHint("Enter new contact number");
            e_field1.setInputType(InputType.TYPE_CLASS_PHONE) ;
            col_name="contact_no";
        }
        else if(tt.equals("Address"))
        {
            e_field1.setHint("Enter new address");
            e_field1.setInputType(InputType.TYPE_CLASS_TEXT);
            col_name="address";
        }
        else if(tt.equals("Password"))
        {
                e_field2.setVisibility(View.VISIBLE);
                e_field3.setVisibility(View.VISIBLE);
                 e_field2.setHeight(80);
                 e_field3.setHeight(80);

                e_field1.setHint("Enter old password");

                //Set inputtype to password
                e_field1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                e_field1.setSelection(e_field1.getText().length());
                col_name="password";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.update_button)
        {

            BackgroundTask bg=new BackgroundTask(this);
            String method="change_profile";

            DBHelper db=new DBHelper(this);
            Cursor cursor=db.retrieveData();
            cursor.moveToFirst();
            String id=cursor.getString(cursor.getColumnIndex("id"));

            String table="";

            if(id.contains("vendor"))
                    table="vendor";
            else
                    table="ngo";

            //If not updating password
            if(!tt.equals("Password"))
            {
                String value = e_field1.getText().toString();
                if(tt.equals("Email ID") && (e_field1.equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()))
                {
                    invalid_1.setText("Invalid Email id");
                }
                else if(e_field1.getText().toString().equals(""))
                {
                    invalid_1.setText("Invalid "+tt);
                }
                else
                {
                    invalid_1.setText("");
                    bg.execute(method, col_name, table, id, value,"");
                }
            }

            //If updating password
            else
            {
                String old_pass=e_field1.getText().toString();
                String new_pass=e_field2.getText().toString();
                String confirm=e_field3.getText().toString();

                if(old_pass.equals(""))
                    invalid_1.setText("Invalid password");

                else if(new_pass.equals(""))
                {
                    invalid_1.setText("");
                    invalid_2.setText("Invalid password");
                }

                else if (!new_pass.equals(confirm))
                {
                         invalid_2.setText("");
                        Toast.makeText(this, "Passwords do not match, please retype", Toast.LENGTH_SHORT).show();
                        e_field2.setText("");
                        e_field3.setText("");
                }

                else
                {
                    invalid_3.setText("");
                    bg.execute(method,col_name,table,id,old_pass,new_pass);
                }

            }
        }

    }
}
