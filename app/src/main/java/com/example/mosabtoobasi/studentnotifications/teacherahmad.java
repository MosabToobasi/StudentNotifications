package com.example.mosabtoobasi.studentnotifications;

        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Toast;

public class teacherahmad extends AppCompatActivity {
    DataBaseHelperahmaddaraghmeh myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherahmad);
        int curent_teacher_id=1;

        myDb=new  DataBaseHelperahmaddaraghmeh(this);

       boolean x;
        Toast.makeText(teacherahmad.this,"ahmad not inserted",Toast.LENGTH_LONG).show();
     //ملاحظه الجمل اللي تحت في الكمنت استخدمها مره وحده عشان يصير في داتا في اللوكال
        x=myDb.insertstudent("ahmad");
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

    }


}
