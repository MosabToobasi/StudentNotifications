package com.example.mosabtoobasi.studentnotifications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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

