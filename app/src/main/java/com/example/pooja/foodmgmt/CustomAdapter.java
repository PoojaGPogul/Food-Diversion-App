package com.example.pooja.foodmgmt;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by swara on 5/11/16.
 */
public class CustomAdapter extends ArrayAdapter
{
    private LinkedList <MyNotice> singleitem;

    public CustomAdapter(Context context, LinkedList<MyNotice> list)
    {

        super(context, R.layout.activity_blog_coustem,list);
        singleitem=list;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater list_inflater=LayoutInflater.from(getContext());
        View coustemview = list_inflater.inflate(R.layout.activity_blog_coustem,parent,false);

        TextView blogtitlecstm_txt=(TextView) coustemview.findViewById(R.id.notice_title);
        TextView blogauthorcstm_txt =(TextView) coustemview.findViewById(R.id.ngo_name_notice);
        TextView blogdatecstm_txt=(TextView) coustemview.findViewById(R.id.data_time_notice);


        if(DBHelper.identify.equals("for_vendor"))
        {
            String str=singleitem.get(position).getFlag_notice();

            if(str.equals("0"))
                coustemview.setBackgroundColor(Color.argb(255,178,210,247));
            else
                coustemview.setBackgroundColor(Color.WHITE);


            blogtitlecstm_txt.setText(singleitem.get(position).getTitle_notice());
            blogauthorcstm_txt.setText("By "+singleitem.get(position).getAuthor_notice());
            blogdatecstm_txt.setText(singleitem.get(position).getDate_notice());
        }
        else
        {
            blogtitlecstm_txt.setText(singleitem.get(position).getAuthor_notice());
            blogdatecstm_txt.setText(singleitem.get(position).getDate_upload_notice());
            blogauthorcstm_txt.setHeight(0);
            blogauthorcstm_txt.offsetTopAndBottom(0);
        }
        return coustemview;


    }
}
