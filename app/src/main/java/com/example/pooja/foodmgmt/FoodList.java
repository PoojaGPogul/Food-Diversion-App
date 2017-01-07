package com.example.pooja.foodmgmt;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FoodList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DBHelper db;
    Cursor cursor;
    String id;
    public  static  ListView lv;
    public static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        db=new DBHelper(this);
        cursor=db.retrieveData();
        cursor.moveToFirst();
        id=cursor.getString(cursor.getColumnIndex("id"));

        lv=(ListView)findViewById(R.id.listview);
        lv.setOnItemClickListener(this);

        BackgroundTask backgroundTask=new BackgroundTask(this);
        String method="get_food_list";
        backgroundTask.execute(method,id);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        //get position of item i.e. food offer from the list
        index=position;
        startActivity(new Intent(getApplicationContext(),FoodDetails.class));
    }
}
