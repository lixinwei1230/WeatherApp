package com.example.android.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // receive the arguments from the previous Activity
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        // assign the values to string-arguments
        //JSONObject obj = new JSONObject(getIntent().getStringExtra("myJson"));

        TextView passText = (TextView) findViewById(R.id.getInfo);

        String myJson = extras.getString("myJson");


        passText.setText(myJson);


    }
}
