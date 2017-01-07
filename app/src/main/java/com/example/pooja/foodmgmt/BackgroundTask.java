package com.example.pooja.foodmgmt;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;


import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;

public class BackgroundTask extends AsyncTask<String, Void, String>
{
    public String  method,name,pass,email,contact,addr,tp;
    Activity ctx;

    public static String switching_flag="";

    public BackgroundTask(Activity ctx) {

        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {     }

    @Override
    protected String doInBackground(String... params) {

        //URLS for connecting with php and database
        String reg_url = "http://poojapogul.esy.es/pooja/register.php";
        String login_url = "http://poojapogul.esy.es//pooja/login.php";
        String get_contact_url = "http://poojapogul.esy.es//pooja/get_contacts.php";
        String insert_food = "http://poojapogul.esy.es//pooja/insert_food.php";
        String signout_url = "http://poojapogul.esy.es//pooja/signout.php";
        String food_list_url = "http://poojapogul.esy.es//pooja/get_food_list.php";
        String register_food_url = "http://poojapogul.esy.es/pooja/register_food.php";
        String check_time_url = "http://poojapogul.esy.es/pooja/check_time.php";
        String get_notifications_url = "http://poojapogul.esy.es/pooja/get_notifications.php";
        String change_food_status_url = "http://poojapogul.esy.es/pooja/change_food_status.php";
        String change_profile_url = "http://poojapogul.esy.es/pooja/change_profile.php";


        method = params[0];


        //Send data for registration
        if (method.equals("signup")) {
            name=params[1];
            pass=params[2];
            email = params[3];
            contact = params[4];
            addr=params[5];
            try {

                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(OS, "UTF-8")));

                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email,"UTF-8") + "&" +
                        URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(addr, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        //Send data for login
        else if (method.equals("login")) {
            email = params[1];
            pass = params[2];
            tp = params[3];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));

                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(tp, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        //Send data for getting information if NGOs
        else if (method.equals("load_contacts")) {
            String id = params[1];

            try {
                URL url = new URL(get_contact_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));

                String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        //Send data for inserting food data
        else if (method.equals("insert_food")) {
            String details = params[1];
            String capa = params[2];
            String ngo = params[3];
            String vendor=params[4];

            try {
                URL url = new URL(insert_food);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));

                String data = URLEncoder.encode("details", "UTF-8") + "=" + URLEncoder.encode(details, "UTF-8") + "&" +
                        URLEncoder.encode("ngo", "UTF-8") + "=" + URLEncoder.encode(ngo, "UTF-8") + "&" +
                        URLEncoder.encode("vendor", "UTF-8") + "=" + URLEncoder.encode(vendor, "UTF-8") + "&" +
                        URLEncoder.encode("capacity", "UTF-8") + "=" + URLEncoder.encode(capa, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        //Send data for getting list of food data which is uploaded by doner/vendor
        else if (method.equals("get_food_list")) {
            String id = params[1];

            try {
                URL url = new URL(food_list_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));
                String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //Send data for registration of food by the NGO
        else if (method.equals("register_food")) {
            String food=params[1];

            try {
                URL url = new URL(register_food_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));

                String data =  URLEncoder.encode("foodid", "UTF-8") + "=" + URLEncoder.encode(food, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        //Send data for changing the status that notice have been viewed.
        else if (method.equals("change_status_of_notice")) {
            String food=params[1];

            try {
                URL url = new URL(change_food_status_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));

                String data =  URLEncoder.encode("foodid", "UTF-8") + "=" + URLEncoder.encode(food, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }



        //Send data for getting notifications for vendor/doner
        else if (method.equals("get_notifications")) {
            String id=params[1];
            String type=params[2];

            try {
                URL url = new URL(get_notifications_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));

                String data =  URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+ "&"+
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        //Call for every 5 minutes to the database
        else if (method.equals("check_time")) {

            try {
                URL url = new URL(check_time_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));


                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        //Send data for changing the profile of user
        else if (method.equals("change_profile")) {
            String col = params[1];
            String tbl = params[2];
            String id = params[3];
            String value = params[4];
            String new_pass=params[5];


            try {
                URL url = new URL(change_profile_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));

                String data = URLEncoder.encode("column", "UTF-8") + "=" + URLEncoder.encode(col, "UTF-8") + "&" +
                        URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode(tbl, "UTF-8") + "&" +
                        URLEncoder.encode("value", "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8") + "&" +
                        URLEncoder.encode("new_pass", "UTF-8") + "=" + URLEncoder.encode(new_pass, "UTF-8") + "&" +
                        URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        //Send data for signout
        else if (method.equals("signout")) {
            String id = params[1];
            String type=params[2];

            try {
                URL url = new URL(signout_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));

                String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+"&"+
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    ///From PHP, Data is returned here
    @Override
    protected void onPostExecute(String result) {


        //Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
        //If registration of user is successful
        if (result.startsWith("Registered success"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            ((Activity) CreateAccount.context).startActivity(new Intent(((Activity) CreateAccount.context), Login.class));
            ((Activity) CreateAccount.context).finish();
        }

        //If registration of user is failed
        else if (result.contains("Error...register"))
        {
            Toast.makeText(ctx, result,Toast.LENGTH_LONG).show();
            CreateAccount.name.setText("");
            CreateAccount.passwd.setText("");
            CreateAccount.confirmp.setText("");
            CreateAccount.address.setText("");
            CreateAccount.contact.setText("");
            CreateAccount.email.setText("");
        }

        // Login of user is successful
        else if (result.startsWith("Login success"))
        {
            Toast.makeText(ctx, "Logged in successfully...",Toast.LENGTH_LONG).show();
            String str[] = result.split("---");
            boolean bl=Login.db.updateData(str[2],1,str[3],str[4],str[5],str[6]);

            //Switch to correspoding user home page
            if (result.contains("vendor"))
            {
                Intent intent=new Intent(((Activity) Login.context), VendorHome.class);
                ((Activity) Login.context).startActivity(intent);
                ((Activity) Login.context).finish();
            }
            else if (result.contains("ngo"))
            {
                Intent intent=new Intent(((Activity) Login.context), NgoHome.class);
                ((Activity) Login.context).startActivity(intent);
                ((Activity) Login.context).finish();
            }
        }

        // Login of user is failed
        else if (result.startsWith("Login Failed...Try"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Login.user.setText("");
            Login.pass.setText("");
            Login.invalid_em.setText("Invalid Email");
            Login.invalid_pass.setText("Invalid Password");
        }

        //Gives NGO details like name, contact, address etc.
        else if (result.startsWith("Send "))
        {
            NGODetails.allData=result;
            if(!NGODetails.allData.equals(""))
            {
                try
                {
                    Intent i = new Intent((NGOList.context), NGODetails.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    (NGOList.context).startActivity(i);
                }
                catch (Exception e)
                {
                }
            }
        }

        //Food data uploaded successfully
        else if (result.startsWith("Food"))
        {
            try
            {
                NGOList.clicked="";
                NGODetails.selected="";
                UploadActivity.ddd.setText("");
                Intent i = new Intent((UploadActivity.context), VendorHome.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                (UploadActivity.context).startActivity(i);
                ((Activity)UploadActivity.context).finish();
                ((Activity)NGODetails.context).finish();
                ((Activity)NGOList.context).finish();
                ((Activity)UploadActivity.context).finish();
                Toast.makeText(ctx,"Food data uploaded successfully...",Toast.LENGTH_SHORT).show();

            }
            catch (Exception e)
            {
            }
        }

        //Get data about food from food table to be shown to NGO
        else if(result.contains("!!!!"))
        {

            String actual[]=result.split("!!!!");

            int count=Integer.parseInt(actual[0]);

            String rows[]=actual[1].split("@@@");

            VendorHome.list=new LinkedList();

            for(int i=0;i<rows.length;i++)
            {
                String temp[]=rows[i].split("---");

                MyNotice mm=new MyNotice(temp[0],temp[1],temp[2],temp[3],temp[4]);
                VendorHome.list.add(mm);
            }
            DBHelper.identify="for_ngo";

            ArrayAdapter dataAdapter = new CustomAdapter(ctx,VendorHome.list);
            FoodList.lv.setAdapter(dataAdapter);


        }

        else if(result.contains("Sorry"))
        {
            Toast.makeText(ctx, ""+result, Toast.LENGTH_SHORT).show();
        }

        //Food registered by NGO
        else if(result.contains("SuccessFood registered"))
        {
            Toast.makeText(ctx, "Food registered successfully...", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(((Activity) FoodDetails.context), FoodList.class);
            ((Activity) FoodDetails.context).startActivity(intent);
            ((Activity) FoodDetails.context).finish();

        }

        //Gives details of notifications
        else if(result.contains("Notices")) {
//            Toast.makeText(ctx, "From notices" + result, Toast.LENGTH_SHORT).show();


            String str[] = result.split("###");
            int cnt = Integer.parseInt(str[0]);
            int cnt2 = Integer.parseInt(str[1]);


            String rows_register[] = str[3].split("@@@");
            String rows_delete[] = str[4].split("@@@");


            NgoHome.ll1=new LinkedList<>();
            NgoHome.ll2=new LinkedList<>();


            int j = 0;

            if(cnt!=0) {
                for (int i = 0; i < cnt; i++, j++) {
                    String temp[] = rows_register[i].split("---");

                    if (temp[2].equals("0")) {
                        MyNotice mm = new MyNotice("Registered for your food", temp[1],temp[2],temp[3],temp[4],temp[5]);
                        NgoHome.ll1.add(mm);
                    } else {
                        MyNotice mm = new MyNotice("Registered for your food", temp[1],temp[2],temp[3],temp[4],temp[5]);
                        NgoHome.ll2.add(mm);
                    }
                }
            }

            if(cnt2!=0) {
                for (int i = 0; i < cnt2; i++,j++) {
                    String temp[] = rows_delete[i].split("---");

                    if (temp[2].equals("0")) {
                        MyNotice mm = new MyNotice("It has been crossed 4 hours, your post will no longer exists", temp[1],temp[2],temp[3],temp[4],temp[5]);
                      //  Toast.makeText(ctx, "Food id = " + NgoHome.foodid[0], Toast.LENGTH_SHORT).show();
                        NgoHome.ll1.add(mm);
                    } else {
                        MyNotice mm = new MyNotice("It has been crossed 4 hours, your post will no longer exists",  temp[1],temp[2],temp[3],temp[4],temp[5]);
                        NgoHome.ll2.add(mm);
                    }
                }
            }

            DBHelper.identify="for_vendor";
            NgoHome.ll1.addAll( NgoHome.ll2);

            ArrayAdapter dataAdapter = new CustomAdapter(ctx, NgoHome.ll1);
            NgoHome.notices.setAdapter(dataAdapter);

        }

        //Updating user profile
        else if(result.contains("Updated successfully..."))
        {
            String str[]=result.split("---");
            Toast.makeText(ctx, str[0], Toast.LENGTH_SHORT).show();

            if(str[1].equals("vendor"))
            {
                Intent i = new Intent((ChangeProfile.context), ChangeProfile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                (ChangeProfile.context).startActivity(i);
                ((Activity)ChangeProfile.context).finish();

            }
            else if(str[1].equals("ngo"))
            {
                Intent i = new Intent((ChangeProfile.context), ChangeProfile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                (ChangeProfile.context).startActivity(i);
                ((Activity)ChangeProfile.context).finish();

            }
        }

        ////////////
        else if(result.contains("Password is wrong..."))
        {
            ChangeProfile.invalid_1.setText("Password is wrong");
            ChangeProfile.e_field1.setText("");

        }

        else if(result.contains("signout"))
        {
            DBHelper db=new DBHelper(ctx);

            Cursor cursor=db.retrieveData();
            cursor.moveToFirst();
            String ss=cursor.getString(cursor.getColumnIndex("id")).toString();

            boolean flag=db.updateData("",0,"","","","");
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();

            if (ss.contains("vendor"))
            {
                Intent intent=new Intent(((Activity) VendorHome.context), Login.class);
                ((Activity) VendorHome.context).startActivity(intent);
                ((Activity) VendorHome.context).finish();
            }
            else if (ss.contains("ngo"))
            {
                Intent intent=new Intent(((Activity) NgoHome.context), Login.class);
                ((Activity) NgoHome.context).startActivity(intent);
                ((Activity) NgoHome.context).finish();
            }


        }

        else if(result.contains("Food record Deleted..."))
        {
            DBHelper db=new DBHelper(ctx);
            Cursor cursor=db.retrieveData();
            cursor.moveToFirst();
            if(cursor.getString(cursor.getColumnIndex("id")).toString().contains("vendor"))
            {
                Toast.makeText(ctx,"See Notifications",Toast.LENGTH_SHORT).show();
            }
        }

        else if(result.contains("(((") ||result.contains("yy") )
        {
            //Toast.makeText(ctx,"In ssfs"+result,Toast.LENGTH_SHORT).show();
        }

        else if(result.contains("Eee"))
        {
             //Toast.makeText(ctx,"In Eee"+result,Toast.LENGTH_SHORT).show();
        }


        else {

                Toast.makeText(ctx,"Check your internet connection",Toast.LENGTH_SHORT).show();


        }

            return;

        }



}





