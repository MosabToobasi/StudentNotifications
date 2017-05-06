package com.example.mosabtoobasi.studentnotifications;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import util.SharedPrefHelper;

public class Student_ace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DataBaseHelperahmaddaraghmeh db = new DataBaseHelperahmaddaraghmeh(getBaseContext());
        ArrayList<String> cn = db.getcourses();
        ArrayList<String> fi = new ArrayList<String>();
        int tempsid = SharedPrefHelper.getIntSharedPref(getBaseContext(),"ID",getBaseContext().MODE_PRIVATE);
        String sid = Integer.toString(tempsid);
        for (String Object:cn)
        {
            String cid = db.getcid(Object);
            String first = db.getcmarksne(sid,cid);
            String second = db.getcmarktwo(sid,cid);
            String quiz = db.getcmarkquizes(sid,cid);
            fi.add(Object+"\t"+first+"\t"+second+"\t"+quiz);
        }
        final ListView lv = (ListView)findViewById(R.id.viewListmarks);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,fi);
        lv.setAdapter(ad);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataBaseHelperahmaddaraghmeh db = new DataBaseHelperahmaddaraghmeh(getBaseContext());
        db.getWritableDatabase().close();
        getBaseContext().deleteDatabase(DataBaseHelperahmaddaraghmeh.database_name);
        SharedPrefHelper.removeall(getBaseContext(),getBaseContext().MODE_PRIVATE);
    }
}
