package com.example.techsupport.projetnfcdespi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    EditText txtid, txtprenom, txtnom, txtfonction, txtemail, txtphone;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   // txtid = (EditText) findViewById(R.id.txtid);
        txtprenom = (EditText) findViewById(R.id.txtprenom);
        txtnom = (EditText) findViewById(R.id.txtnom);
        txtfonction = (EditText) findViewById(R.id.txtfonction);
        txtemail = (EditText) findViewById(R.id.txtcourriel);
        txtphone = (EditText) findViewById(R.id.txtphone);


    }
    public  void onClick(View v)
    {
        CarteDespi c = new CarteDespi(txtprenom.getText().toString(),txtnom.getText().toString(),txtfonction.getText().toString(),txtemail.getText().toString(),txtphone.getText().toString(),2);

        c.save();



    }
}
