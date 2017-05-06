package com.example.mosabtoobasi.studentnotifications;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
        long result=db.insert(teachercoursclass_table,null,cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
// hoooerw rkwe;r klwe; r/.wel ,r.ew Rew,'; rew rwe
    //fdsfdsfdsfd
    public  boolean isfull()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+course_table;
        Cursor cursor =db.rawQuery(selectQuery,null);
        if(cursor.getCount() >0)
        {
        return  true;
        }
        else {return  false;}
    }


    public  void insertmarkone(int courseid, int studentid, int mark)
    {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(s2_id,studentid);
        cv.put(course2_id,courseid);
        cv.put(exam1,mark);
        db.execSQL("UPDATE "+studentcourse_table+" SET "+exam1+"="+mark+" where "+s2_id2+"="+studentid+" and "+course2_id+"="+courseid+" ");
    }

    public  void insertmarkonev2(String courseid, String studentid, String mark)
    {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(s2_id,studentid);
        cv.put(course2_id,courseid);
        cv.put(exam1,mark);
        db.execSQL("UPDATE "+studentcourse_table+" SET "+exam1+"='"+mark+"' where "+s2_id2+"='"+studentid+"' and "+course2_id+"='"+courseid+"' ");
    }
    ////insert exam1 for a student

    public  void insertmarktwo(int courseid, int studentid, int mark)
    {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(s2_id,studentid);
        cv.put(course2_id,courseid);
        cv.put(exam2,mark);
        db.execSQL("UPDATE "+studentcourse_table+" SET "+exam2+"="+mark+" where "+s2_id2+"="+studentid+" and "+course2_id+"="+courseid+" ");
    }


    public  void insertmarktwov2(String courseid, String studentid, String mark)
    {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(s2_id,studentid);
        cv.put(course2_id,courseid);
        cv.put(exam1,mark);
        db.execSQL("UPDATE "+studentcourse_table+" SET "+exam2+"='"+mark+"' where "+s2_id2+"='"+studentid+"' and "+course2_id+"='"+courseid+"' ");
    }
    ////insert exam2 for a student
    public  void insertmarkquizes(int courseid, int studentid, int mark)
    {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        cv.put(s2_id,studentid);
        cv.put(course2_id,courseid);
        cv.put(quizes,mark);
        db.execSQL("UPDATE "+studentcourse_table+" SET "+quizes+"='"+mark+"' where "+s2_id2+"='"+studentid+"' and "+course2_id+"='"+courseid+"' ");
    }


    public  void insertmarkquizesv2(String courseid,String studentid, String mark)
        {
            ContentValues cv=new ContentValues();
            SQLiteDatabase db=this.getWritableDatabase();
            cv.put(s2_id,studentid);
            cv.put(course2_id,courseid);
            cv.put(exam1,mark);
            db.execSQL("UPDATE "+studentcourse_table+" SET "+quizes+"='"+mark+"' where "+s2_id2+"='"+studentid+"' and "+course2_id+"='"+courseid+"' ");
    }
////insert quiz for a student
    public ArrayList<String> getcourses()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery = "SELECT "+c_name+" FROM "+course_table+" where 1=1";
        Cursor cursor =db.rawQuery(selectQuery,null);
        ArrayList<String> arrayList=new ArrayList<String>();
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                  arrayList.add(cursor.getString(cursor.getColumnIndex(c_name)));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return arrayList;

    }



    public ArrayList<String> getstudentsnames(String classname,String coursename)
    {



       String classid=getclassid(classname);
        String cid=getcid(coursename);


        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery = "SELECT s2id FROM "+studentclass_table+","+studentcourse_table+"where studentclasstable.fclassid="+cid+"and studentcoursetable.c2id="+classid+" ";
        Cursor cursor =db.rawQuery(selectQuery,null);
        ArrayList<String> arrayList=new ArrayList<String>();
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    arrayList.add(cursor.getString(cursor.getColumnIndex("s2id")));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return arrayList;

    }
    public ArrayList<String> getstudentsid(String classname,String coursename)
    {



        String classid=getclassid(classname);
        String cid=getcid(coursename);


        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+studentclass_table+","+studentcourse_table+" where studentclasstable.fclassid='"+classid+"' and studentcoursetable.c2id='"+cid+"' ";
        Cursor cursor =db.rawQuery(selectQuery,null);
        ArrayList<String> arrayList=new ArrayList<String>();
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    arrayList.add(cursor.getString(cursor.getColumnIndex("s2id")));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return arrayList;

    }


///get course name
    public ArrayList<String> getclasses()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery = "SELECT "+class_name+" FROM "+class_table+" where 1=1";
        Cursor cursor =db.rawQuery(selectQuery,null);

        ArrayList<String> arrayList=new ArrayList<String>();
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    arrayList.add(cursor.getString(cursor.getColumnIndex(class_name)));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return arrayList;
    }
////get class class name

    public  String getcmarksne(String studentid, String courseid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery =  "SELECT exam1 from "+studentcourse_table+" where "+s2_id+"="+studentid+" and "+course2_id+"="+courseid+" ";
        String s1="error";

        Cursor cursor =db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    s1 = cursor.getString(cursor.getColumnIndex("exam1"));
                }
             catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return s1;
/**/
    }
    ////return exam1
    public  String getcmarktwo(String studentid, String courseid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery =  "SELECT "+exam2+" from "+studentcourse_table+" where "+s2_id2+"="+studentid+" and "+course2_id+"="+courseid+" ";
        Cursor cursor =db.rawQuery(selectQuery,null);
        String s1="error";
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    s1 = cursor.getString(cursor.getColumnIndex(exam2));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return s1;
    }
    public  String getcmarkquizes(String studentid, String courseid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery =  "SELECT "+quizes+" from "+studentcourse_table+" where "+s2_id2+"="+studentid+" and "+course2_id+"="+courseid+" ";
        Cursor cursor =db.rawQuery(selectQuery,null);
        String s1="error";
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    s1 = cursor.getString(cursor.getColumnIndex(quizes));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return s1;
    }


    public  String getclassid(String classname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery =  "SELECT "+class_id+" from "+class_table+" where "+class_name+"='"+classname+"' ";
        Cursor cursor =db.rawQuery(selectQuery,null);
        String s1="error";
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    s1 = cursor.getString(cursor.getColumnIndex(class_id));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return s1;
    }

    public  String getcid(String coursename)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery1 =  "SELECT "+c_id+" from "+course_table+" where "+c_name+"='"+coursename+"' ";
        Cursor cursor1 =db.rawQuery(selectQuery1,null);
        String s1="error";
        if (cursor1.moveToFirst())
        {
            do
            {
                try {
                    s1 = cursor1.getString(cursor1.getColumnIndex(c_id));
                }
                catch (Exception ex){}


            }while (cursor1.moveToNext());
        }
        return s1;
    }

    public  String getstudentid(String sname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery =  "SELECT "+s_id+" from "+student_table+" where "+s_name+"="+sname+" ";
        Cursor cursor =db.rawQuery(selectQuery,null);
        String s1="error";
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    s1 = cursor.getString(cursor.getColumnIndex(s_id));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return s1;
    }

    public  String getstudentnaem(String sid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery =  "SELECT "+s_name+" from "+student_table+" where "+s_id+"='"+sid+"' ";
        Cursor cursor =db.rawQuery(selectQuery,null);
        String s1="error";
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    s1 = cursor.getString(cursor.getColumnIndex(s_name));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return s1;
    }


    public  String getscname(String sid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery =  "SELECT "+s_name+" from "+student_table+" where "+s_id+"="+sid+" ";
        Cursor cursor =db.rawQuery(selectQuery,null);
        String s1="error";
        if (cursor.moveToFirst())
        {
            do
            {
                try {
                    s1 = cursor.getString(cursor.getColumnIndex(s_name));
                }
                catch (Exception ex){}


            }while (cursor.moveToNext());
        }
        return s1;
    }

   public ArrayList<String> getstudentnameandcoursenameandexams(int studentid,int courseid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery1 =  "SELECT "+c_name+" from "+course_table+" where "+c_id+"="+courseid+" ";
        Cursor cursor1 =db.rawQuery(selectQuery1,null);
        String selectQuery2 =  "SELECT "+s_name+" from "+student_table+" where "+s_id+"="+studentid+" ";
        Cursor cursor2 =db.rawQuery(selectQuery2,null);
        String selectQuery3 =  "SELECT "+exam1+","+exam2+","+quizes+" from "+studentcourse_table+" where "+s2_id+"="+studentid+" and "+course2_id+"="+courseid+"";
        Cursor cursor3 =db.rawQuery(selectQuery3,null);
        ArrayList<String> arrayList=new ArrayList<String>();
        String s1="error";
        String s2="error";
        String s3="error";
        String s4="error";
        String s5="error";
      try {
          if (cursor1.moveToFirst())
          {
              do
              {
                  s1 = cursor1.getString(cursor1.getColumnIndex(c_name));

              }while (cursor1.moveToNext());
          }

        if (cursor2.moveToFirst())
        {
            do
            {
                s2 = cursor2.getString(cursor2.getColumnIndex(s_name));



            }while (cursor2.moveToNext());
        }
        if (cursor3.moveToFirst())
        {
            do
            {
                s3 = cursor3.getString(cursor3.getColumnIndex(exam1));
                s4 = cursor3.getString(cursor3.getColumnIndex(exam2));
                s5 = cursor3.getString(cursor3.getColumnIndex(quizes));


            }while (cursor3.moveToNext());
        }}catch (Exception EX){}
        arrayList.add(s1);
        arrayList.add(s2);
        arrayList.add(s3);
        arrayList.add(s4);
        arrayList.add(s5);
       return arrayList;///example return <"ali","english",42,5,2>

    }









}
