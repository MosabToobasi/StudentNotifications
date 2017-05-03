package com.example.mosabtoobasi.studentnotifications;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by EliteBook8570w on 5/1/2017.
 */

public class DataBaseHelperahmaddaraghmeh extends SQLiteOpenHelper
{
    public  static  final String database_name="mydatabasenew.db";

    public  static  final String teacher_table="teachertable";
    public  static  final String t_id="tid";
    public  static  final String t_name="tname";

    public  static  final String student_table="studenttable";
    public  static  final String s_id="sid";
    public  static  final String s_name="sname";

    public  static  final String class_table="classtable";
    public  static  final String class_id="classid";
    public  static  final String class_name="classname";

    public  static  final String course_table="coursetable";
    public  static  final String c_id="cid";
    public  static  final String c_name="cname";

    public  static  final String studentcourse_table="studentcoursetable";
    public  static  final String course2_id="c2id";
    public  static  final String s2_id="s2id";
    public  static  final String exam1="exam1";
    public  static  final String exam2="exam2";
    public  static  final String quizes="quizes";




    public  static  final String teachercoursclass_table="teachercoursclasstable";
    public  static  final String ft_id="ftid";
    public  static  final String fc_id="fcid";
    public  static  final String fclass_id="fclassid";

    public  static  final String studentclass_table="studentclasstable";
    public  static  final String fclass_id2="fclassid";
    public  static  final String s2_id2="s2id";

    public DataBaseHelperahmaddaraghmeh(Context context) {
        super(context, database_name, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+teacher_table+" (tid integer primary key,tname text)");
        db.execSQL("create table "+student_table+" (sid integer primary key,sname text)");
        db.execSQL("create table "+class_table+" (classid integer primary key,classname text)");
        db.execSQL("create table "+course_table+" (cid integer primary key,cname text)");
        db.execSQL("create table "+studentcourse_table+" (c2id integer,s2id integer,exam1 integer,exam2 integer,quizes integer,FOREIGN KEY(c2id) REFERENCES coursetable(cid),FOREIGN KEY(s2id) REFERENCES studenttable(sid))");
        db.execSQL("create table "+studentclass_table+" (fclassid integer,s2id integer,FOREIGN KEY(fclassid) REFERENCES classtable(classid),FOREIGN KEY(s2id) REFERENCES studenttable(sid))");
        db.execSQL("create table "+teachercoursclass_table+" (ftid integer,fcid integer,fclassid integer,FOREIGN KEY(ftid) REFERENCES teachertable(tid),FOREIGN KEY(fcid) REFERENCES coursetable(cid),FOREIGN KEY(fclassid) REFERENCES classtable(classid))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+teacher_table);
        db.execSQL("drop table if exists "+student_table);
        db.execSQL("drop table if exists "+class_table);
        db.execSQL("drop table if exists "+course_table);
        db.execSQL("drop table if exists "+studentcourse_table);
        db.execSQL("drop table if exists "+studentclass_table);
        db.execSQL("drop table if exists "+teachercoursclass_table);
        onCreate(db);
    }

    public  boolean insertteacher(String name,int value)
{
    ContentValues cv=new ContentValues();
    SQLiteDatabase db=this.getWritableDatabase();
    cv.put(t_name,name);
    cv.put(t_id,value);
    long result=db.insert(teacher_table,null,cv);
    if(result==-1){return false;}
    else {return true;}
}
    public  boolean insertstudent(String name,int value)
    {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(s_name,name);
        cv.put(s_id,value);
        long result=db.insert(student_table,null,cv);
        if(result==-1){return false;}
        else {return true;}
    }
    public  boolean insertclass(String name,int value)
    {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(class_name,name);
        cv.put(class_id,value);
        long result=db.insert(class_table,null,cv);
        if(result==-1){return false;}
        else {return true;}
    }
    public  boolean insertcourse(String name,int value)
    {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(c_name,name);
        cv.put(c_id,value);
        long result=db.insert(course_table,null,cv);
        if(result==-1){return false;}
        else {return true;}
    }

    public  boolean addcoursetostudent(int coursevalue,int studentid)
    {

        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(course2_id,coursevalue);
        cv.put(s2_id,studentid);
        cv.put(exam1,"0");
        cv.put(exam2,"0");
        cv.put(quizes,"0");
        long result=db.insert(studentcourse_table,null,cv);
        if(result==-1){return false;}
        else {return true;}
    }
    public  boolean  addclasstostudent(int classid,int studentid) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        cv.put(fclass_id2, classid);
        cv.put(s2_id2, studentid);

        long result = db.insert(studentclass_table, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public  boolean addclassandcoursetoteacher(int teacherid,int classid,int courseid )
    {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(ft_id,teacherid);
        cv.put(fc_id,courseid);
        cv.put(fclass_id,classid);
        db.execSQL("create table "+teacher_table+" (tid integer primary key autoincrement,tname text)");


        long result=db.insert(teachercoursclass_table,null,cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public  boolean isfull(int teacherid,int classid,int courseid )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+ DatabaseHelper.TABLE_OUTLET;
        Cursor cursor =db.rawQuery(selectQuery,null);
        if(cursor.getCount() >0)
        {
        return  true;
        }
        else {return  false;}
    }

}
