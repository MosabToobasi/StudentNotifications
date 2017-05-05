package com.example.mosabtoobasi.studentnotifications;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import util.SharedPrefHelper;
import org.json.JSONException;
import org.json.JSONObject;
//asdasd
public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        final Button signin = (Button) findViewById(R.id.btn_signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //welcome home
                final EditText id = (EditText) findViewById(R.id.Id);
                final EditText pass = (EditText) findViewById(R.id.Password);
                String s = id.getText().toString();
                String e = pass.getText().toString();
                String url = "http://momenserve.netne.net/public/";
                JSONObject para = new JSONObject();
                try {
                    para.put("id", Integer.parseInt(s));
                    para.put("pass", e);
                } catch (JSONException exeption) {
                    Log.d("error", "cannot create json object : " + exeption.getMessage());
                }
                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.POST, url, para, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                //Toast.makeText(getBaseContext(), response.toString(), Toast.LENGTH_LONG).show();
                                SharedPrefHelper sh = new SharedPrefHelper();
                                try {
                                    sh.setSharePref(getBaseContext(), "ID", response.getInt("ID"), getBaseContext().MODE_PRIVATE);
                                    sh.setSharePref(getBaseContext(), "USERNAME", response.getString("T_USERNAME"), getBaseContext().MODE_PRIVATE);
                                    sh.setSharePref(getBaseContext(), "type", "TEACHER", getBaseContext().MODE_PRIVATE);
                                    //Intent i = new Intent(Login.this, Teacher.class);
                                    //startActivity(i);
                                    //finish();
                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub
                                Log.d("error", error.getMessage());
                                //Toast.makeText(getBaseContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                queue.add(jsObjRequest);
                DataBaseHelperahmaddaraghmeh tempdb = new DataBaseHelperahmaddaraghmeh(getBaseContext());
                if (tempdb.isfull()) {

                    if (SharedPrefHelper.getIntSharedPref(getBaseContext(), "ID", getBaseContext().MODE_PRIVATE) != 0) {
                        JsonObjectRequest jsObjRequest1 = new JsonObjectRequest
                                (Request.Method.POST, url + "tclass", para, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {

                                        //Toast.makeText(getBaseContext(), "" + response.length(), Toast.LENGTH_LONG).show();
                                        DataBaseHelperahmaddaraghmeh db = new DataBaseHelperahmaddaraghmeh(getBaseContext());
                                        String name = SharedPrefHelper.getStringSharedPref(getBaseContext(), "USERNAME", getBaseContext().MODE_PRIVATE);
                                        int Tid = SharedPrefHelper.getIntSharedPref(getBaseContext(), "ID", getBaseContext().MODE_PRIVATE);
                                        db.insertteacher(name, Tid);
                                        for (int i = 1; i <= response.length() / 4; i++) {
                                            try {
                                                int clid = response.getInt("CL_ID" + i);
                                                int coid = response.getInt("CO_ID" + i);
                                                String cn = response.getString("CL_NM" + i);
                                                String con = response.getString("CO_NM" + i);
                                                db.insertclass(cn, clid);
                                                db.insertcourse(con, coid);
                                                db.addclassandcoursetoteacher(Tid, clid, coid);
                                                //Toast.makeText(getBaseContext(), "" + clid + " "+coid, Toast.LENGTH_SHORT).show();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO Auto-generated method stub
                                        Log.d("error", error.getMessage());
                                        //Toast.makeText(getBaseContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                                    }

                                });
                        queue.add(jsObjRequest1);
                        JsonObjectRequest jsObjRequest2 = new JsonObjectRequest
                                (Request.Method.POST, url + "marks", para, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        int Tid = SharedPrefHelper.getIntSharedPref(getBaseContext(), "ID", getBaseContext().MODE_PRIVATE);
                                        for (int i=1;i<=response.length()/8;i++) {
                                            try {
                                                DataBaseHelperahmaddaraghmeh db = new DataBaseHelperahmaddaraghmeh(getBaseContext());
                                                db.insertstudent(response.getString("stu"+i+"_NAME"), response.getInt("stu"+i+"_ID"));
                                                db.addclasstostudent(response.getInt("stu"+i+"_CLASS"),response.getInt("stu"+i+"_ID"));
                                                db.addcoursetostudent(response.getInt("stu"+i+"_COURSE"),response.getInt("stu"+i+"_ID"));
                                                db.insertmarkone(response.getInt("stu"+i+"_COURSE"),response.getInt("stu"+i+"_ID"),response.getInt("stu"+i+"_EXAM1"));
                                                db.insertmarktwo(response.getInt("stu"+i+"_COURSE"),response.getInt("stu"+i+"_ID"),response.getInt("stu"+i+"_EXAM2"));
                                                db.insertmarkquizes(response.getInt("stu"+i+"_COURSE"),response.getInt("stu"+i+"_ID"),response.getInt("stu"+i+"_QUIZES"));
                                                DatabaseHelper1 db1 = new DatabaseHelper1(getBaseContext());
                                                db1.insertData(response.getString("stu"+i+"_NAME"),""+Tid,""+response.getInt("stu"+i+"_ATEND"));


                                            }
                                        catch (JSONException ex){
                                            ex.printStackTrace();
                                        }
                                        }
                                    }


                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO Auto-generated method stub
                                        Log.d("error", error.getMessage());
                                        //Toast.makeText(getBaseContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                                    }

                                });

                        if (SharedPrefHelper.getIntSharedPref(getBaseContext(), "ID", getBaseContext().MODE_PRIVATE) != 0) {
                            Intent i = new Intent(Login.this, Teacher.class);
                            startActivity(i);
                            finish();
                        }


                    }
                }
            }
        });
    }

public void inputDat(View view) {
    final EditText id = (EditText) findViewById(R.id.Id);
    final EditText pass = (EditText) findViewById(R.id.Password);
    //Button b = (Button) findViewById(R.id.loginbt);

    String s = id.getText().toString();
    String e = pass.getText().toString();
    System.out.println(s);

   if (s.compareTo("123456") == 0 && e.compareTo("000000") == 0) {
       //Intent i = new Intent(Login.this, Student.class);
        //startActivity(i);
       // finish();
        Intent intent = new Intent(this, Teacher.class);
        Bundle bundle = new Bundle();
        bundle.putString("ID", id.getText().toString());//or intent.putExtra("ID",id.getText().toString()); for every element
        bundle.putString("Password", pass.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);

        //Toast.makeText(this, "fjsaja", Toast.LENGTH_LONG).show();
   }
}
}

