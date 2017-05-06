package com.example.mosabtoobasi.studentnotifications;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.RowSet;

import util.SharedPrefHelper;

public class Teacher extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.student);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        TextView t;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.marks, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            //int id = SharedPrefHelper.getIntSharedPref(getContext(),"ID",getContext().MODE_PRIVATE);
           // String user = SharedPrefHelper.getStringSharedPref(getContext(),"USERNAME",getContext().MODE_PRIVATE);
           // textView.setText(user+" id : "+id);
            Context context;
            context=getContext();
            // Create DatabaseHelper instance
            /*
            DatabaseHelper dataHelper=new DatabaseHelper(context);
            // Reference to TableLayout

            TableLayout tableLayout=(TableLayout)rootView.findViewById(R.id.Tablelayout);
            // Add header row
            TableRow rowHeader = new TableRow(context);
            rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
            rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            String[] headerText={"Course","Exam1","Exam2","Quizes"};
            for(String c:headerText) {
                TextView tv = new TextView(getContext());
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(5, 5, 5, 5);
                tv.setText(c);
                rowHeader.addView(tv);





            }
            tableLayout.addView(rowHeader);

            // Get data from sqlite database and add them to the table
            // Open the database for reading
            SQLiteDatabase db = dataHelper.getReadableDatabase();
            // Start the transaction.
            db.beginTransaction();

            try
            {
                String selectQuery = "SELECT * FROM "+ DatabaseHelper.TABLE_OUTLET;
                Cursor cursor = db.rawQuery(selectQuery,null);
                if(cursor.getCount() >0)
                {
                    while (cursor.moveToNext()) {
                        // Read columns data
                        String outlet_course_name= cursor.getString(cursor.getColumnIndex("outlet_course_name"));
                        String outlet_exam= cursor.getString(cursor.getColumnIndex("outlet_exam"));
                        String outlet_mark= cursor.getString(cursor.getColumnIndex("outlet_mark"));
                        String outlet_mark_from= cursor.getString(cursor.getColumnIndex("outlet_mark_from"));

                        // dara rows
                        TableRow row = new TableRow(context);
                        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.WRAP_CONTENT));
                        final DataBaseHelperahmaddaraghmeh myDb = new DataBaseHelperahmaddaraghmeh(getContext());
                        //  final ArrayList<String> a=myDb.getstudentnameandcoursenameandexams(6,7);
                        final  ArrayList<String> a= new ArrayList<String>(myDb.getstudentnameandcoursenameandexams(6,7));
                        String[] colText={a.get(0),a.get(2),a.get(3),a.get(4)};
                        for(String text:colText) {
                            TextView tv = new TextView(getContext());
                            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT));
                            tv.setGravity(Gravity.CENTER);
                            tv.setTextSize(16);
                            tv.setPadding(5, 5, 5, 5);
                            tv.setText(text);
                            row.addView(tv);

                        }

                        tableLayout.addView(row);


                    }

                }
                db.setTransactionSuccessful();

            }
            catch (SQLiteException e)
            {
                e.printStackTrace();

            }
            finally
            {
                db.endTransaction();
                // End the transaction.
                db.close();
                // Close database
            }
*/
            return rootView;
        }
    }

    //frag2
    public static class PlaceholderFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment2() {
        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //t =(TextView)view.findViewById(R.id.textView1);
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.mark2, container, false);
            final Button A = (Button) rootView.findViewById(R.id.saveformmark);
            //int curent_teacher_id=1;
            final DataBaseHelperahmaddaraghmeh myDb = new DataBaseHelperahmaddaraghmeh(getContext());
            final ArrayList<String> arrayList= new ArrayList<String>(myDb.getclasses());
            final Spinner classspinner=(Spinner)  rootView.findViewById(R.id.spinner2);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,arrayList);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            classspinner.setAdapter(spinnerArrayAdapter);

            final ArrayList<String> arrayList2= new ArrayList<String>(myDb.getcourses());
            final Spinner coursspinner=(Spinner)  rootView.findViewById(R.id.CSpiner);
            ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,arrayList2);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            coursspinner.setAdapter(spinnerArrayAdapter2);


            final String[] selectedclass = {" "};
            final String []selectedcouse={" "};
            classspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                   try{
                       selectedclass[0] = (String) classspinner.getSelectedItem();

                    final ArrayList<String> al1= new ArrayList<String>(myDb.getstudentsid(selectedclass[0],selectedcouse[0]));

                    final Spinner studspin=(Spinner)  getView().findViewById(R.id.StudentSpiner);

                    ArrayAdapter<String> spinnerArrayAdapter60 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,al1);
                    spinnerArrayAdapter60.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    studspin.setAdapter(spinnerArrayAdapter60);
                      studspin.setVisibility(getView().VISIBLE);




                }catch (Exception ex){ Toast.makeText(getContext(),"No Student in "+selectedclass[0]+" and "+selectedcouse[0],Toast.LENGTH_SHORT).show();    final Spinner studspin=(Spinner)  getView().findViewById(R.id.StudentSpiner); studspin.setVisibility(getView().INVISIBLE);}

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }


            });
///







            coursspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    try {  selectedcouse[0] = (String) coursspinner.getSelectedItem();
                    final ArrayList<String> al1= new ArrayList<String>(myDb.getstudentsid(selectedclass[0],selectedcouse[0]));

                    final Spinner studspin=(Spinner)  getView().findViewById(R.id.StudentSpiner);

                    ArrayAdapter<String> spinnerArrayAdapter60 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,al1);
                    spinnerArrayAdapter60.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    studspin.setAdapter(spinnerArrayAdapter60);
                    studspin.setVisibility(View.VISIBLE);




                }catch (Exception ex){ Toast.makeText(getContext(),"No Student in "+selectedclass[0]+" and "+selectedcouse[0],Toast.LENGTH_SHORT).show(); final Spinner studspin=(Spinner)  getView().findViewById(R.id.StudentSpiner); studspin.setVisibility(getView().INVISIBLE);}
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });


            final Spinner studspiner2=(Spinner)  rootView.findViewById(R.id.StudentSpiner);

            final TextView STUNAME=(TextView) rootView.findViewById(R.id.STUNAME);

            final TextView first=(TextView) rootView.findViewById(R.id.Feqzam);
            final TextView second=(TextView) rootView.findViewById(R.id.Seqzam);
            final TextView quizes=(TextView) rootView.findViewById(R.id.Fneqzam);

              studspiner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                       @Override
                                                       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                           Toast.makeText(getContext(),"No Student in ",Toast.LENGTH_SHORT).show();

                                                          try {

                                                           final DataBaseHelperahmaddaraghmeh myDb = new DataBaseHelperahmaddaraghmeh(getContext());
                                                           STUNAME.setText(myDb.getstudentnaem(studspiner2.getSelectedItem().toString()));
                                                              String co=myDb.getcid(coursspinner.getSelectedItem().toString());
                                                              first.setText(myDb.getcmarksne(studspiner2.getSelectedItem().toString(),co));
                                                              second.setText(myDb.getcmarktwo(studspiner2.getSelectedItem().toString(),co));
                                                              quizes.setText(myDb.getcmarkquizes(studspiner2.getSelectedItem().toString(),co));

                                                          }
                                                          catch (Exception ex){Toast.makeText(getContext(),"No marks for "+STUNAME.getText(),Toast.LENGTH_SHORT).show();}
                                                       }

                                                       @Override
                                                       public void onNothingSelected(AdapterView<?> parent) {

                                                       }
                                                   }

              );



                A.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {

                        final DataBaseHelperahmaddaraghmeh myDb = new DataBaseHelperahmaddaraghmeh(getContext());
                        if(first.getText()=="" ||first.getText()==" " || first.getText()==null ||second.getText()=="" ||second.getText()==" " || second.getText()==null ||quizes.getText()=="" ||quizes.getText()==" " || quizes.getText()==null )
                        {Toast.makeText(getContext(),"Error", Toast.LENGTH_LONG).show();}
                     else
                        {
                           try {
                               String co = myDb.getcid(coursspinner.getSelectedItem().toString());

                               myDb.insertmarkonev2(co.toString(), studspiner2.getSelectedItem().toString(), first.getText().toString());
                               myDb.insertmarktwov2(co.toString(), studspiner2.getSelectedItem().toString(), second.getText().toString());
                               myDb.insertmarkquizesv2(co.toString(), studspiner2.getSelectedItem().toString(), quizes.getText().toString());
                               Toast.makeText(getContext(),"Saved Successfully", Toast.LENGTH_LONG).show();

                               //Toast.makeText(getContext(), myDb.getclassid("Saved Successfully"), Toast.LENGTH_LONG).show();
                           }
                           catch (Exception ex){Toast.makeText(getContext(),"Fail", Toast.LENGTH_LONG).show();}

                        }
                    }
                });



            return rootView;
        }

    }


    //frag3
    public static class PlaceholderFragment3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment3() {
        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //t =(TextView)view.findViewById(R.id.textView1);
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment3 newInstance(int sectionNumber) {
            PlaceholderFragment3 fragment = new PlaceholderFragment3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.mark3, container, false);

            return rootView;
        }
    }

    //frag4
    public static class PlaceholderFragment4 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment4() {
        }

        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //t =(TextView)view.findViewById(R.id.textView1);
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment4 newInstance(int sectionNumber) {
            PlaceholderFragment4 fragment = new PlaceholderFragment4();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.mark4, container, false);

            return rootView;
        }
    }


/**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return PlaceholderFragment.newInstance(position + 1);//return whic frag is called
                case 1:
                    return PlaceholderFragment2.newInstance(position + 1);//return whic frag is called
                case 2:
                    return PlaceholderFragment3.newInstance(position + 1);//return whic frag is called
                case 3:
                    return PlaceholderFragment4.newInstance(position + 1);//return whic frag is called
                default:
                    return PlaceholderFragment.newInstance(position + 1);//return whic frag is called
            }
        }

        @Override
        public int getCount() {
            // Show  4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {//name of frag
            switch (position) {
                case 0:
                    return "إظهار العلامات ";
                case 1:
                    return "إدخال العلامات ";
                case 2:
                    return "إظهار الغيابات  ";
                case 3:
                    return "إدخال الغيابات  ";



            }
            return null;
        }
    }
}
