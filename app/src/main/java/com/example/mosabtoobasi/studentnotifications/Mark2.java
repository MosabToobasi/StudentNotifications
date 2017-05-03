package com.example.mosabtoobasi.studentnotifications;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by mosabtoobasi on 3/28/2017.
 */

public class Mark2 extends AppCompatActivity {

    DataBaseHelperahmaddaraghmeh myDb;
final Button A=(Button) findViewById(R.id.Saveformmark);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark2);
        int curent_teacher_id=1;
        myDb=new  DataBaseHelperahmaddaraghmeh(this);
        final   int x= myDb.getcourses();



    A.setOnClickListener(new View.OnClickListener(){


                             @Override
                             public void onClick(View v) {Toast.makeText(Mark2.this,""+x,Toast.LENGTH_LONG).show();}});
///////////////
///





        //ملاحظه الجمل اللي ت
            // حت في الكمنت استخدمها مره وحده عشان يصير في داتا في اللوكال
       /* x=myDb.insertstudent("ahmad");
        x=myDb.insertstudent("ali");
        x=myDb.insertstudent("mohammad");
        x=myDb.insertstudent("omar");
        x=myDb.insertteacher("naser");
        x=myDb.insertteacher("nael");
        x=myDb.insertcourse("English");
        x=myDb.insertcourse("Arabic");
        x=myDb.insertclass("1th");
        x=myDb.insertclass("2th");
        x=myDb.addcoursetostudent(1,1);
        x=myDb.addcoursetostudent(2,1);
        x=myDb.addcoursetostudent(1,2);
        x=myDb.addcoursetostudent(2,2);
        x=myDb.addcoursetostudent(1,3);
        x=myDb.addcoursetostudent(2,3);
        x=myDb.addcoursetostudent(1,4);
        x=myDb.addcoursetostudent(2,4);
        x=myDb.addclasstostudent(1,1);
        x=myDb.addclasstostudent(1,2);
        x=myDb.addclasstostudent(2,3);
        x=myDb.addclasstostudent(2,4);
        x=myDb.addclassandcoursetoteacher(1,1,1);
        x=myDb.addclassandcoursetoteacher(2,1,2);
        x=myDb.addclassandcoursetoteacher(1,2,1);
        x=myDb.addclassandcoursetoteacher(2,2,2);
*/
    }


}
