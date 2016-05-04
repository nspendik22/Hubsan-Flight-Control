package com.example.nik.drone;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Choice extends AppCompatActivity {


    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

        Button btn1 = (Button) findViewById(R.id.btn1);

        //Button btn2 = (Button) findViewById(R.id.btn2);
        //Button btn3 = (Button) findViewById(R.id.btn3);
        //TextView txt = (TextView) findViewById(R.id.txt);


        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/redfive.TTF");
        //txt.setTypeface(customFont);
        btn1.setTypeface(customFont);
        //btn2.setTypeface(customFont);
        //btn3.setTypeface(customFont);

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choice.this, Fly.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

 /*
        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choice.this, sendData.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    */

    }



}
