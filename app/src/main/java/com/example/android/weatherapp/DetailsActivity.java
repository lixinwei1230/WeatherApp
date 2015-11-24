package com.example.android.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        // assign the values to string-arguments
        //String myJson = extras.getString("myJson");
        TextView myTest = (TextView) findViewById(R.id.getInfo1);
        myTest.setText(extras.toString());

    }
}
