package com.example.android.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {


    private String TempUnit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        if (extras.getString("tempUnit").toString().equals("Fahrenheit")) {
            TempUnit = "F";
        } else {
            TempUnit = "C";
        }

        generateHeadLine(extras.getString("city").toString(), extras.getString("state").toString());
        generateNext24HoursWeather(extras.getString("myJson").toString());
        generateNext7DaysWeather(extras.getString("myJson").toString());
        AddListenOnBotton();


    }

    private void AddListenOnBotton() {
        Button myHourlyBtn = (Button) findViewById(R.id.next24HoursBtn);
        myHourlyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RelativeLayout myDailyLayout = (RelativeLayout) findViewById(R.id.next7Days);
                myDailyLayout.setVisibility(View.GONE);

                RelativeLayout myHourlyLayout = (RelativeLayout) findViewById(R.id.next24Hours);
                myHourlyLayout.setVisibility(View.VISIBLE);
            }

        });

        Button myDailyBtn = (Button) findViewById(R.id.next7DaysBtn);
        myDailyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RelativeLayout myDailyLayout = (RelativeLayout) findViewById(R.id.next7Days);
                myDailyLayout.setVisibility(View.VISIBLE);

                RelativeLayout myHourlyLayout = (RelativeLayout) findViewById(R.id.next24Hours);
                myHourlyLayout.setVisibility(View.GONE);
            }

        });

        Button my48HourBtn = (Button) findViewById(R.id.show48Hours);
        my48HourBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TableRow my48HourRow = (TableRow) findViewById(R.id.my48HourBtnBack);
                my48HourRow.setVisibility(View.GONE);

                //TableRow myHead = (TableRow) findViewById(R.id.myHeadline);
                //myHead.setVisibility(View.GONE);

                TableLayout myLayout = (TableLayout) findViewById(R.id.myTable1);
                myLayout.setVisibility(View.VISIBLE);
            }

        });

    }

    private void generateNext24HoursWeather(String myJson) {
        TextView myUnit = (TextView) findViewById(R.id.hourlyUnit);
        TextView myUnit1 = (TextView) findViewById(R.id.hourlyUnit1);
        String myUnitStr = "Temp(°" + TempUnit + ")";
        myUnit.setText(myUnitStr);
        myUnit1.setText(myUnitStr);

        try {
            JSONObject obj = new JSONObject(myJson);
            JSONArray next24Hours = obj.getJSONArray("next24Hours");

            JSONObject hour1 = next24Hours.getJSONObject(0);
            int hour1PicEnd = hour1.get("hourlySummary").toString().indexOf(".");
            int hour1PicBegin = hour1.get("hourlySummary").toString().indexOf("/");
            String pic1 = hour1.get("hourlySummary").toString().substring(hour1PicBegin+1, hour1PicEnd);

            ImageView myImg1 = (ImageView) findViewById(R.id.myImg1);
            switch(pic1) {
                case "clear":
                    myImg1.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg1.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg1.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg1.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg1.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg1.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg1.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg1.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg1.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg1.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg1.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime1 = (TextView) findViewById(R.id.myTime1);
            String myTime1StrIndex = hour1.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime1.setText(myTime1StrIndex);

            TextView myTemp1 = (TextView) findViewById(R.id.myTemp1);
            String myTemp1Str = hour1.get("hourlyTemp").toString();
            myTemp1.setText(myTemp1Str);
            hour1 = null;
            myTemp1 = null;
            myTime1 = null;
            myImg1 = null;

            JSONObject hour2 = next24Hours.getJSONObject(1);
            int hour2PicEnd = hour2.get("hourlySummary").toString().indexOf(".");
            int hour2PicBegin = hour2.get("hourlySummary").toString().indexOf("/");
            String pic2 = hour2.get("hourlySummary").toString().substring(hour2PicBegin+1, hour2PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg2 = (ImageView) findViewById(R.id.myImg2);
            switch(pic2) {
                case "clear":
                    myImg2.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg2.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg2.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg2.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg2.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg2.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg2.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg2.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg2.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg2.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg2.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime2 = (TextView) findViewById(R.id.myTime2);
            String myTime2StrIndex = hour2.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime2.setText(myTime2StrIndex);

            TextView myTemp2 = (TextView) findViewById(R.id.myTemp2);
            String myTemp2Str = hour2.get("hourlyTemp").toString();
            myTemp2.setText(myTemp2Str);
            hour2 = null;
            myTemp2 = null;
            myTime2 = null;
            myImg2 = null;

            JSONObject hour3 = next24Hours.getJSONObject(2);
            int hour3PicEnd = hour3.get("hourlySummary").toString().indexOf(".");
            int hour3PicBegin = hour3.get("hourlySummary").toString().indexOf("/");
            String pic3 = hour3.get("hourlySummary").toString().substring(hour3PicBegin+1, hour3PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg3 = (ImageView) findViewById(R.id.myImg3);
            switch(pic3) {
                case "clear":
                    myImg3.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg3.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg3.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg3.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg3.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg3.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg3.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg3.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg3.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg3.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg3.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime3 = (TextView) findViewById(R.id.myTime3);
            String myTime3StrIndex = hour3.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime3.setText(myTime3StrIndex);

            TextView myTemp3 = (TextView) findViewById(R.id.myTemp3);
            String myTemp3Str = hour3.get("hourlyTemp").toString();
            myTemp3.setText(myTemp3Str);
            hour3 = null;
            myTemp3 = null;
            myTime3 = null;
            myImg3 = null;

            JSONObject hour4 = next24Hours.getJSONObject(3);
            int hour4PicEnd = hour4.get("hourlySummary").toString().indexOf(".");
            int hour4PicBegin = hour4.get("hourlySummary").toString().indexOf("/");
            String pic4 = hour4.get("hourlySummary").toString().substring(hour4PicBegin+1, hour4PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg4 = (ImageView) findViewById(R.id.myImg4);
            switch(pic4) {
                case "clear":
                    myImg4.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg4.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg4.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg4.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg4.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg4.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg4.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg4.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg4.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg4.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg4.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime4 = (TextView) findViewById(R.id.myTime4);
            String myTime4StrIndex = hour4.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime4.setText(myTime4StrIndex);

            TextView myTemp4 = (TextView) findViewById(R.id.myTemp4);
            String myTemp4Str = hour4.get("hourlyTemp").toString();
            myTemp4.setText(myTemp4Str);
            hour4 = null;
            myTemp4 = null;
            myTime4 = null;
            myImg4 = null;

            JSONObject hour5 = next24Hours.getJSONObject(4);
            int hour5PicEnd = hour5.get("hourlySummary").toString().indexOf(".");
            int hour5PicBegin = hour5.get("hourlySummary").toString().indexOf("/");
            String pic5 = hour5.get("hourlySummary").toString().substring(hour5PicBegin+1, hour5PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg5 = (ImageView) findViewById(R.id.myImg5);
            switch(pic5) {
                case "clear":
                    myImg5.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg5.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg5.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg5.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg5.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg5.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg5.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg5.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg5.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg5.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg5.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime5 = (TextView) findViewById(R.id.myTime5);
            String myTime5StrIndex = hour5.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime5.setText(myTime5StrIndex);

            TextView myTemp5 = (TextView) findViewById(R.id.myTemp5);
            String myTemp5Str = hour5.get("hourlyTemp").toString();
            myTemp5.setText(myTemp5Str);
            hour5 = null;
            myTemp5 = null;
            myTime5 = null;
            myImg5 = null;

            JSONObject hour6 = next24Hours.getJSONObject(5);
            int hour6PicEnd = hour6.get("hourlySummary").toString().indexOf(".");
            int hour6PicBegin = hour6.get("hourlySummary").toString().indexOf("/");
            String pic6 = hour6.get("hourlySummary").toString().substring(hour6PicBegin+1, hour6PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg6 = (ImageView) findViewById(R.id.myImg6);
            switch(pic6) {
                case "clear":
                    myImg6.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg6.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg6.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg6.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg6.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg6.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg6.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg6.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg6.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg6.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg6.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime6 = (TextView) findViewById(R.id.myTime6);
            String myTime6StrIndex = hour6.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime6.setText(myTime6StrIndex);

            TextView myTemp6 = (TextView) findViewById(R.id.myTemp6);
            String myTemp6Str = hour6.get("hourlyTemp").toString();
            myTemp6.setText(myTemp6Str);
            hour6 = null;
            myTemp6 = null;
            myTime6 = null;
            myImg6 = null;

            JSONObject hour7 = next24Hours.getJSONObject(6);
            int hour7PicEnd = hour7.get("hourlySummary").toString().indexOf(".");
            int hour7PicBegin = hour7.get("hourlySummary").toString().indexOf("/");
            String pic7 = hour7.get("hourlySummary").toString().substring(hour7PicBegin+1, hour7PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg7 = (ImageView) findViewById(R.id.myImg7);
            switch(pic7) {
                case "clear":
                    myImg7.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg7.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg7.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg7.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg7.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg7.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg7.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg7.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg7.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg7.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg7.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime7 = (TextView) findViewById(R.id.myTime7);
            String myTime7StrIndex = hour7.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime7.setText(myTime7StrIndex);

            TextView myTemp7 = (TextView) findViewById(R.id.myTemp7);
            String myTemp7Str = hour7.get("hourlyTemp").toString();
            myTemp7.setText(myTemp7Str);
            hour7 = null;
            myTemp7 = null;
            myTime7 = null;
            myImg7 = null;

            JSONObject hour8 = next24Hours.getJSONObject(7);
            int hour8PicEnd = hour8.get("hourlySummary").toString().indexOf(".");
            int hour8PicBegin = hour8.get("hourlySummary").toString().indexOf("/");
            String pic8 = hour8.get("hourlySummary").toString().substring(hour8PicBegin+1, hour8PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg8 = (ImageView) findViewById(R.id.myImg8);
            switch(pic8) {
                case "clear":
                    myImg8.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg8.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg8.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg8.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg8.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg8.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg8.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg8.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg8.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg8.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg8.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime8 = (TextView) findViewById(R.id.myTime8);
            String myTime8StrIndex = hour8.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime8.setText(myTime8StrIndex);

            TextView myTemp8 = (TextView) findViewById(R.id.myTemp8);
            String myTemp8Str = hour8.get("hourlyTemp").toString();
            myTemp8.setText(myTemp8Str);
            hour8 = null;
            myTemp8 = null;
            myTime8 = null;
            myImg8 = null;

            JSONObject hour9 = next24Hours.getJSONObject(8);
            int hour9PicEnd = hour9.get("hourlySummary").toString().indexOf(".");
            int hour9PicBegin = hour9.get("hourlySummary").toString().indexOf("/");
            String pic9 = hour9.get("hourlySummary").toString().substring(hour9PicBegin+1, hour9PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg9 = (ImageView) findViewById(R.id.myImg9);
            switch(pic9) {
                case "clear":
                    myImg9.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg9.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg9.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg9.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg9.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg9.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg9.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg9.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg9.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg9.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg9.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime9 = (TextView) findViewById(R.id.myTime9);
            String myTime9StrIndex = hour9.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime9.setText(myTime9StrIndex);

            TextView myTemp9 = (TextView) findViewById(R.id.myTemp9);
            String myTemp9Str = hour9.get("hourlyTemp").toString();
            myTemp9.setText(myTemp9Str);
            hour9 = null;
            myTemp9 = null;
            myTime9 = null;
            myImg9 = null;

            JSONObject hour10 = next24Hours.getJSONObject(9);
            int hour10PicEnd = hour10.get("hourlySummary").toString().indexOf(".");
            int hour10PicBegin = hour10.get("hourlySummary").toString().indexOf("/");
            String pic10 = hour10.get("hourlySummary").toString().substring(hour10PicBegin+1, hour10PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg10 = (ImageView) findViewById(R.id.myImg10);
            switch(pic10) {
                case "clear":
                    myImg10.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg10.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg10.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg10.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg10.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg10.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg10.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg10.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg10.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg10.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg10.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime10 = (TextView) findViewById(R.id.myTime10);
            String myTime10StrIndex = hour10.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime10.setText(myTime10StrIndex);

            TextView myTemp10 = (TextView) findViewById(R.id.myTemp10);
            String myTemp10Str = hour10.get("hourlyTemp").toString();
            myTemp10.setText(myTemp10Str);
            hour10 = null;
            myTemp10 = null;
            myTime10 = null;
            myImg10 = null;

            JSONObject hour11 = next24Hours.getJSONObject(10);
            int hour11PicEnd = hour11.get("hourlySummary").toString().indexOf(".");
            int hour11PicBegin = hour11.get("hourlySummary").toString().indexOf("/");
            String pic11 = hour11.get("hourlySummary").toString().substring(hour11PicBegin+1, hour11PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg11 = (ImageView) findViewById(R.id.myImg11);
            switch(pic11) {
                case "clear":
                    myImg11.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg11.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg11.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg11.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg11.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg11.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg11.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg11.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg11.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg11.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg11.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime11 = (TextView) findViewById(R.id.myTime11);
            String myTime11StrIndex = hour11.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime11.setText(myTime11StrIndex);

            TextView myTemp11 = (TextView) findViewById(R.id.myTemp11);
            String myTemp11Str = hour11.get("hourlyTemp").toString();
            myTemp11.setText(myTemp11Str);
            hour11 = null;
            myTemp11 = null;
            myTime11 = null;
            myImg11 = null;

            JSONObject hour12 = next24Hours.getJSONObject(11);
            int hour12PicEnd = hour12.get("hourlySummary").toString().indexOf(".");
            int hour12PicBegin = hour12.get("hourlySummary").toString().indexOf("/");
            String pic12 = hour12.get("hourlySummary").toString().substring(hour12PicBegin+1, hour12PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg12 = (ImageView) findViewById(R.id.myImg12);
            switch(pic12) {
                case "clear":
                    myImg12.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg12.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg12.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg12.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg12.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg12.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg12.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg12.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg12.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg12.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg12.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime12 = (TextView) findViewById(R.id.myTime12);
            String myTime12StrIndex = hour12.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime12.setText(myTime12StrIndex);

            TextView myTemp12 = (TextView) findViewById(R.id.myTemp12);
            String myTemp12Str = hour12.get("hourlyTemp").toString();
            myTemp12.setText(myTemp12Str);
            hour12 = null;
            myTemp12 = null;
            myTime12 = null;
            myImg12 = null;

            JSONObject hour13 = next24Hours.getJSONObject(12);
            int hour13PicEnd = hour13.get("hourlySummary").toString().indexOf(".");
            int hour13PicBegin = hour13.get("hourlySummary").toString().indexOf("/");
            String pic13 = hour13.get("hourlySummary").toString().substring(hour13PicBegin+1, hour13PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg13 = (ImageView) findViewById(R.id.myImg13);
            switch(pic13) {
                case "clear":
                    myImg13.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg13.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg13.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg13.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg13.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg13.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg13.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg13.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg13.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg13.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg13.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime13 = (TextView) findViewById(R.id.myTime13);
            String myTime13StrIndex = hour13.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime13.setText(myTime13StrIndex);

            TextView myTemp13 = (TextView) findViewById(R.id.myTemp13);
            String myTemp13Str = hour13.get("hourlyTemp").toString();
            myTemp13.setText(myTemp13Str);
            hour13 = null;
            myTemp13 = null;
            myTime13 = null;
            myImg13 = null;

            JSONObject hour14 = next24Hours.getJSONObject(13);
            int hour14PicEnd = hour14.get("hourlySummary").toString().indexOf(".");
            int hour14PicBegin = hour14.get("hourlySummary").toString().indexOf("/");
            String pic14 = hour14.get("hourlySummary").toString().substring(hour14PicBegin+1, hour14PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg14 = (ImageView) findViewById(R.id.myImg14);
            switch(pic14) {
                case "clear":
                    myImg14.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg14.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg14.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg14.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg14.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg14.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg14.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg14.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg14.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg14.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg14.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime14 = (TextView) findViewById(R.id.myTime14);
            String myTime14StrIndex = hour14.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime14.setText(myTime14StrIndex);

            TextView myTemp14 = (TextView) findViewById(R.id.myTemp14);
            String myTemp14Str = hour14.get("hourlyTemp").toString();
            myTemp14.setText(myTemp14Str);
            hour14 = null;
            myTemp14 = null;
            myTime14 = null;
            myImg14 = null;

            JSONObject hour15 = next24Hours.getJSONObject(14);
            int hour15PicEnd = hour15.get("hourlySummary").toString().indexOf(".");
            int hour15PicBegin = hour15.get("hourlySummary").toString().indexOf("/");
            String pic15 = hour15.get("hourlySummary").toString().substring(hour15PicBegin+1, hour15PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg15 = (ImageView) findViewById(R.id.myImg15);
            switch(pic15) {
                case "clear":
                    myImg15.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg15.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg15.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg15.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg15.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg15.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg15.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg15.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg15.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg15.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg15.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime15 = (TextView) findViewById(R.id.myTime15);
            String myTime15StrIndex = hour15.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime15.setText(myTime15StrIndex);

            TextView myTemp15 = (TextView) findViewById(R.id.myTemp15);
            String myTemp15Str = hour15.get("hourlyTemp").toString();
            myTemp15.setText(myTemp15Str);
            hour15 = null;
            myTemp15 = null;
            myTime15 = null;
            myImg15 = null;

            JSONObject hour16 = next24Hours.getJSONObject(15);
            int hour16PicEnd = hour16.get("hourlySummary").toString().indexOf(".");
            int hour16PicBegin = hour16.get("hourlySummary").toString().indexOf("/");
            String pic16 = hour16.get("hourlySummary").toString().substring(hour16PicBegin+1, hour16PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg16 = (ImageView) findViewById(R.id.myImg16);
            switch(pic16) {
                case "clear":
                    myImg16.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg16.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg16.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg16.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg16.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg16.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg16.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg16.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg16.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg16.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg16.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime16 = (TextView) findViewById(R.id.myTime16);
            String myTime16StrIndex = hour16.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime16.setText(myTime16StrIndex);

            TextView myTemp16 = (TextView) findViewById(R.id.myTemp16);
            String myTemp16Str = hour16.get("hourlyTemp").toString();
            myTemp16.setText(myTemp16Str);
            hour16 = null;
            myTemp16 = null;
            myTime16 = null;
            myImg16 = null;

            JSONObject hour17 = next24Hours.getJSONObject(16);
            int hour17PicEnd = hour17.get("hourlySummary").toString().indexOf(".");
            int hour17PicBegin = hour17.get("hourlySummary").toString().indexOf("/");
            String pic17 = hour17.get("hourlySummary").toString().substring(hour17PicBegin+1, hour17PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg17 = (ImageView) findViewById(R.id.myImg17);
            switch(pic17) {
                case "clear":
                    myImg17.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg17.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg17.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg17.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg17.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg17.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg17.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg17.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg17.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg17.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg17.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime17 = (TextView) findViewById(R.id.myTime17);
            String myTime17StrIndex = hour17.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime17.setText(myTime17StrIndex);

            TextView myTemp17 = (TextView) findViewById(R.id.myTemp17);
            String myTemp17Str = hour17.get("hourlyTemp").toString();
            myTemp17.setText(myTemp17Str);
            hour17 = null;
            myTemp17 = null;
            myTime17 = null;
            myImg17 = null;

            JSONObject hour18 = next24Hours.getJSONObject(17);
            int hour18PicEnd = hour18.get("hourlySummary").toString().indexOf(".");
            int hour18PicBegin = hour18.get("hourlySummary").toString().indexOf("/");
            String pic18 = hour18.get("hourlySummary").toString().substring(hour18PicBegin+1, hour18PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg18 = (ImageView) findViewById(R.id.myImg18);
            switch(pic18) {
                case "clear":
                    myImg18.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg18.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg18.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg18.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg18.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg18.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg18.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg18.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg18.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg18.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg18.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime18 = (TextView) findViewById(R.id.myTime18);
            String myTime18StrIndex = hour18.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime18.setText(myTime18StrIndex);

            TextView myTemp18 = (TextView) findViewById(R.id.myTemp18);
            String myTemp18Str = hour18.get("hourlyTemp").toString();
            myTemp18.setText(myTemp18Str);
            hour18 = null;
            myTemp18 = null;
            myTime18 = null;
            myImg18 = null;

            JSONObject hour19 = next24Hours.getJSONObject(18);
            int hour19PicEnd = hour19.get("hourlySummary").toString().indexOf(".");
            int hour19PicBegin = hour19.get("hourlySummary").toString().indexOf("/");
            String pic19 = hour19.get("hourlySummary").toString().substring(hour19PicBegin+1, hour19PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg19 = (ImageView) findViewById(R.id.myImg19);
            switch(pic19) {
                case "clear":
                    myImg19.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg19.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg19.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg19.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg19.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg19.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg19.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg19.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg19.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg19.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg19.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime19 = (TextView) findViewById(R.id.myTime19);
            String myTime19StrIndex = hour19.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime19.setText(myTime19StrIndex);

            TextView myTemp19 = (TextView) findViewById(R.id.myTemp19);
            String myTemp19Str = hour19.get("hourlyTemp").toString();
            myTemp19.setText(myTemp19Str);
            hour19 = null;
            myTemp19 = null;
            myTime19 = null;
            myImg19 = null;

            JSONObject hour20 = next24Hours.getJSONObject(19);
            int hour20PicEnd = hour20.get("hourlySummary").toString().indexOf(".");
            int hour20PicBegin = hour20.get("hourlySummary").toString().indexOf("/");
            String pic20 = hour20.get("hourlySummary").toString().substring(hour20PicBegin+1, hour20PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg20 = (ImageView) findViewById(R.id.myImg20);
            switch(pic20) {
                case "clear":
                    myImg20.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg20.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg20.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg20.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg20.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg20.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg20.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg20.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg20.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg20.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg20.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime20 = (TextView) findViewById(R.id.myTime20);
            String myTime20StrIndex = hour20.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime20.setText(myTime20StrIndex);

            TextView myTemp20 = (TextView) findViewById(R.id.myTemp20);
            String myTemp20Str = hour20.get("hourlyTemp").toString();
            myTemp20.setText(myTemp20Str);
            hour20 = null;
            myTemp20 = null;
            myTime20 = null;
            myImg20 = null;

            JSONObject hour21 = next24Hours.getJSONObject(20);
            int hour21PicEnd = hour21.get("hourlySummary").toString().indexOf(".");
            int hour21PicBegin = hour21.get("hourlySummary").toString().indexOf("/");
            String pic21 = hour21.get("hourlySummary").toString().substring(hour21PicBegin+1, hour21PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg21 = (ImageView) findViewById(R.id.myImg21);
            switch(pic21) {
                case "clear":
                    myImg21.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg21.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg21.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg21.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg21.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg21.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg21.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg21.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg21.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg21.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg21.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime21 = (TextView) findViewById(R.id.myTime21);
            String myTime21StrIndex = hour21.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime21.setText(myTime21StrIndex);

            TextView myTemp21 = (TextView) findViewById(R.id.myTemp21);
            String myTemp21Str = hour21.get("hourlyTemp").toString();
            myTemp21.setText(myTemp21Str);
            hour21 = null;
            myTemp21 = null;
            myTime21 = null;
            myImg21 = null;

            JSONObject hour22 = next24Hours.getJSONObject(21);
            int hour22PicEnd = hour22.get("hourlySummary").toString().indexOf(".");
            int hour22PicBegin = hour22.get("hourlySummary").toString().indexOf("/");
            String pic22 = hour22.get("hourlySummary").toString().substring(hour22PicBegin+1, hour22PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg22 = (ImageView) findViewById(R.id.myImg22);
            switch(pic22) {
                case "clear":
                    myImg22.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg22.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg22.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg22.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg22.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg22.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg22.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg22.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg22.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg22.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg22.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime22 = (TextView) findViewById(R.id.myTime22);
            String myTime22StrIndex = hour22.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime22.setText(myTime22StrIndex);

            TextView myTemp22 = (TextView) findViewById(R.id.myTemp22);
            String myTemp22Str = hour22.get("hourlyTemp").toString();
            myTemp22.setText(myTemp22Str);
            hour22 = null;
            myTemp22 = null;
            myTime22 = null;
            myImg22 = null;

            JSONObject hour23 = next24Hours.getJSONObject(22);
            int hour23PicEnd = hour23.get("hourlySummary").toString().indexOf(".");
            int hour23PicBegin = hour23.get("hourlySummary").toString().indexOf("/");
            String pic23 = hour23.get("hourlySummary").toString().substring(hour23PicBegin+1, hour23PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg23 = (ImageView) findViewById(R.id.myImg23);
            switch(pic23) {
                case "clear":
                    myImg23.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg23.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg23.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg23.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg23.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg23.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg23.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg23.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg23.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg23.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg23.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime23 = (TextView) findViewById(R.id.myTime23);
            String myTime23StrIndex = hour23.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime23.setText(myTime23StrIndex);

            TextView myTemp23 = (TextView) findViewById(R.id.myTemp23);
            String myTemp23Str = hour23.get("hourlyTemp").toString();
            myTemp23.setText(myTemp23Str);
            hour23 = null;
            myTemp23 = null;
            myTime23 = null;
            myImg23 = null;

            JSONObject hour24 = next24Hours.getJSONObject(23);
            int hour24PicEnd = hour24.get("hourlySummary").toString().indexOf(".");
            int hour24PicBegin = hour24.get("hourlySummary").toString().indexOf("/");
            String pic24 = hour24.get("hourlySummary").toString().substring(hour24PicBegin+1, hour24PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg24 = (ImageView) findViewById(R.id.myImg24);
            switch(pic24) {
                case "clear":
                    myImg24.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg24.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg24.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg24.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg24.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg24.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg24.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg24.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg24.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg24.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg24.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime24 = (TextView) findViewById(R.id.myTime24);
            String myTime24StrIndex = hour24.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime24.setText(myTime24StrIndex);

            TextView myTemp24 = (TextView) findViewById(R.id.myTemp24);
            String myTemp24Str = hour24.get("hourlyTemp").toString();
            myTemp24.setText(myTemp24Str);
            hour24 = null;
            myTemp24 = null;
            myTime24 = null;
            myImg24 = null;


            //rendering the next 24 hours description
            JSONObject hour25 = next24Hours.getJSONObject(24);
            int hour25PicEnd = hour25.get("hourlySummary").toString().indexOf(".");
            int hour25PicBegin = hour25.get("hourlySummary").toString().indexOf("/");
            String pic25 = hour25.get("hourlySummary").toString().substring(hour25PicBegin+1, hour25PicEnd);

            ImageView myImg25 = (ImageView) findViewById(R.id.myImg25);
            switch(pic25) {
                case "clear":
                    myImg25.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg25.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg25.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg25.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg25.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg25.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg25.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg25.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg25.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg25.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg25.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime25 = (TextView) findViewById(R.id.myTime25);
            String myTime25StrIndex = hour25.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime25.setText(myTime25StrIndex);

            TextView myTemp25 = (TextView) findViewById(R.id.myTemp25);
            String myTemp25Str = hour25.get("hourlyTemp").toString();
            myTemp25.setText(myTemp25Str);
            hour25 = null;
            myTemp25 = null;
            myTime25 = null;
            myImg25 = null;

            JSONObject hour26 = next24Hours.getJSONObject(25);
            int hour26PicEnd = hour26.get("hourlySummary").toString().indexOf(".");
            int hour26PicBegin = hour26.get("hourlySummary").toString().indexOf("/");
            String pic26 = hour26.get("hourlySummary").toString().substring(hour26PicBegin+1, hour26PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg26 = (ImageView) findViewById(R.id.myImg26);
            switch(pic26) {
                case "clear":
                    myImg26.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg26.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg26.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg26.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg26.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg26.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg26.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg26.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg26.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg26.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg26.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime26 = (TextView) findViewById(R.id.myTime26);
            String myTime26StrIndex = hour26.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime26.setText(myTime26StrIndex);

            TextView myTemp26 = (TextView) findViewById(R.id.myTemp26);
            String myTemp26Str = hour26.get("hourlyTemp").toString();
            myTemp26.setText(myTemp26Str);
            hour26 = null;
            myTemp26 = null;
            myTime26 = null;
            myImg26 = null;

            JSONObject hour27 = next24Hours.getJSONObject(26);
            int hour27PicEnd = hour27.get("hourlySummary").toString().indexOf(".");
            int hour27PicBegin = hour27.get("hourlySummary").toString().indexOf("/");
            String pic27 = hour27.get("hourlySummary").toString().substring(hour27PicBegin+1, hour27PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg27 = (ImageView) findViewById(R.id.myImg27);
            switch(pic27) {
                case "clear":
                    myImg27.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg27.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg27.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg27.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg27.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg27.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg27.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg27.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg27.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg27.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg27.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime27 = (TextView) findViewById(R.id.myTime27);
            String myTime27StrIndex = hour27.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime27.setText(myTime27StrIndex);

            TextView myTemp27 = (TextView) findViewById(R.id.myTemp27);
            String myTemp27Str = hour27.get("hourlyTemp").toString();
            myTemp27.setText(myTemp27Str);
            hour27 = null;
            myTemp27 = null;
            myTime27 = null;
            myImg27 = null;

            JSONObject hour28 = next24Hours.getJSONObject(27);
            int hour28PicEnd = hour28.get("hourlySummary").toString().indexOf(".");
            int hour28PicBegin = hour28.get("hourlySummary").toString().indexOf("/");
            String pic28 = hour28.get("hourlySummary").toString().substring(hour28PicBegin+1, hour28PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg28 = (ImageView) findViewById(R.id.myImg28);
            switch(pic28) {
                case "clear":
                    myImg28.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg28.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg28.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg28.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg28.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg28.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg28.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg28.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg28.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg28.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg28.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime28 = (TextView) findViewById(R.id.myTime28);
            String myTime28StrIndex = hour28.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime28.setText(myTime28StrIndex);

            TextView myTemp28 = (TextView) findViewById(R.id.myTemp28);
            String myTemp28Str = hour28.get("hourlyTemp").toString();
            myTemp28.setText(myTemp28Str);
            hour28 = null;
            myTemp28 = null;
            myTime28= null;
            myImg28 = null;

            JSONObject hour29 = next24Hours.getJSONObject(28);
            int hour29PicEnd = hour29.get("hourlySummary").toString().indexOf(".");
            int hour29PicBegin = hour29.get("hourlySummary").toString().indexOf("/");
            String pic29 = hour29.get("hourlySummary").toString().substring(hour29PicBegin+1, hour29PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg29 = (ImageView) findViewById(R.id.myImg29);
            switch(pic29) {
                case "clear":
                    myImg29.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg29.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg29.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg29.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg29.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg29.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg29.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg29.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg29.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg29.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg29.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime29 = (TextView) findViewById(R.id.myTime29);
            String myTime29StrIndex = hour29.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime29.setText(myTime29StrIndex);

            TextView myTemp29 = (TextView) findViewById(R.id.myTemp29);
            String myTemp29Str = hour29.get("hourlyTemp").toString();
            myTemp29.setText(myTemp29Str);
            hour29 = null;
            myTemp29 = null;
            myTime29 = null;
            myImg29 = null;

            JSONObject hour30 = next24Hours.getJSONObject(29);
            int hour30PicEnd = hour30.get("hourlySummary").toString().indexOf(".");
            int hour30PicBegin = hour30.get("hourlySummary").toString().indexOf("/");
            String pic30 = hour30.get("hourlySummary").toString().substring(hour30PicBegin+1, hour30PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg30 = (ImageView) findViewById(R.id.myImg30);
            switch(pic30) {
                case "clear":
                    myImg30.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg30.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg30.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg30.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg30.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg30.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg30.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg30.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg30.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg30.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg30.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime30 = (TextView) findViewById(R.id.myTime30);
            String myTime30StrIndex = hour30.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime30.setText(myTime30StrIndex);

            TextView myTemp30 = (TextView) findViewById(R.id.myTemp30);
            String myTemp30Str = hour30.get("hourlyTemp").toString();
            myTemp30.setText(myTemp30Str);
            hour30 = null;
            myTemp30 = null;
            myTime30 = null;
            myImg30 = null;

            JSONObject hour31 = next24Hours.getJSONObject(30);
            int hour31PicEnd = hour31.get("hourlySummary").toString().indexOf(".");
            int hour31PicBegin = hour31.get("hourlySummary").toString().indexOf("/");
            String pic31 = hour31.get("hourlySummary").toString().substring(hour31PicBegin+1, hour31PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg31 = (ImageView) findViewById(R.id.myImg31);
            switch(pic31) {
                case "clear":
                    myImg31.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg31.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg31.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg31.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg31.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg31.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg31.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg31.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg31.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg31.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg31.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime31 = (TextView) findViewById(R.id.myTime31);
            String myTime31StrIndex = hour31.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime31.setText(myTime31StrIndex);

            TextView myTemp31 = (TextView) findViewById(R.id.myTemp31);
            String myTemp31Str = hour31.get("hourlyTemp").toString();
            myTemp31.setText(myTemp31Str);
            hour31 = null;
            myTemp31 = null;
            myTime31 = null;
            myImg31 = null;

            JSONObject hour32 = next24Hours.getJSONObject(31);
            int hour32PicEnd = hour32.get("hourlySummary").toString().indexOf(".");
            int hour32PicBegin = hour32.get("hourlySummary").toString().indexOf("/");
            String pic32 = hour32.get("hourlySummary").toString().substring(hour32PicBegin+1, hour32PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg32 = (ImageView) findViewById(R.id.myImg32);
            switch(pic32) {
                case "clear":
                    myImg32.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg32.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg32.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg32.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg32.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg32.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg32.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg32.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg32.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg32.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg32.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime32 = (TextView) findViewById(R.id.myTime32);
            String myTime32StrIndex = hour32.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime32.setText(myTime32StrIndex);

            TextView myTemp32 = (TextView) findViewById(R.id.myTemp32);
            String myTemp32Str = hour32.get("hourlyTemp").toString();
            myTemp32.setText(myTemp32Str);
            hour32 = null;
            myTemp32 = null;
            myTime32 = null;
            myImg32 = null;

            JSONObject hour33 = next24Hours.getJSONObject(32);
            int hour33PicEnd = hour33.get("hourlySummary").toString().indexOf(".");
            int hour33PicBegin = hour33.get("hourlySummary").toString().indexOf("/");
            String pic33 = hour33.get("hourlySummary").toString().substring(hour33PicBegin+1, hour33PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg33 = (ImageView) findViewById(R.id.myImg33);
            switch(pic33) {
                case "clear":
                    myImg33.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg33.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg33.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg33.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg33.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg33.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg33.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg33.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg33.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg33.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg33.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime33 = (TextView) findViewById(R.id.myTime33);
            String myTime33StrIndex = hour33.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime33.setText(myTime33StrIndex);

            TextView myTemp33 = (TextView) findViewById(R.id.myTemp33);
            String myTemp33Str = hour33.get("hourlyTemp").toString();
            myTemp33.setText(myTemp33Str);
            hour33 = null;
            myTemp33 = null;
            myTime33 = null;
            myImg33 = null;

            JSONObject hour34 = next24Hours.getJSONObject(33);
            int hour34PicEnd = hour34.get("hourlySummary").toString().indexOf(".");
            int hour34PicBegin = hour34.get("hourlySummary").toString().indexOf("/");
            String pic34 = hour34.get("hourlySummary").toString().substring(hour34PicBegin+1, hour34PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg34 = (ImageView) findViewById(R.id.myImg34);
            switch(pic34) {
                case "clear":
                    myImg34.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg34.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg34.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg34.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg34.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg34.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg34.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg34.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg34.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg34.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg34.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime34 = (TextView) findViewById(R.id.myTime34);
            String myTime34StrIndex = hour34.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime34.setText(myTime34StrIndex);

            TextView myTemp34 = (TextView) findViewById(R.id.myTemp34);
            String myTemp34Str = hour34.get("hourlyTemp").toString();
            myTemp34.setText(myTemp34Str);
            hour34 = null;
            myTemp34 = null;
            myTime34 = null;
            myImg34 = null;

            JSONObject hour35 = next24Hours.getJSONObject(34);
            int hour35PicEnd = hour35.get("hourlySummary").toString().indexOf(".");
            int hour35PicBegin = hour35.get("hourlySummary").toString().indexOf("/");
            String pic35 = hour35.get("hourlySummary").toString().substring(hour35PicBegin+1, hour35PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg35 = (ImageView) findViewById(R.id.myImg35);
            switch(pic35) {
                case "clear":
                    myImg35.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg35.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg35.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg35.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg35.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg35.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg35.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg35.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg35.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg35.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg35.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime35 = (TextView) findViewById(R.id.myTime35);
            String myTime35StrIndex = hour35.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime35.setText(myTime35StrIndex);

            TextView myTemp35 = (TextView) findViewById(R.id.myTemp35);
            String myTemp35Str = hour35.get("hourlyTemp").toString();
            myTemp35.setText(myTemp35Str);
            hour35 = null;
            myTemp35 = null;
            myTime35 = null;
            myImg35 = null;

            JSONObject hour36 = next24Hours.getJSONObject(35);
            int hour36PicEnd = hour36.get("hourlySummary").toString().indexOf(".");
            int hour36PicBegin = hour36.get("hourlySummary").toString().indexOf("/");
            String pic36 = hour36.get("hourlySummary").toString().substring(hour36PicBegin+1, hour36PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg36 = (ImageView) findViewById(R.id.myImg36);
            switch(pic36) {
                case "clear":
                    myImg36.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg36.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg36.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg36.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg36.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg36.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg36.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg36.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg36.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg36.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg36.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime36 = (TextView) findViewById(R.id.myTime36);
            String myTime36StrIndex = hour36.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime36.setText(myTime36StrIndex);

            TextView myTemp36 = (TextView) findViewById(R.id.myTemp36);
            String myTemp36Str = hour36.get("hourlyTemp").toString();
            myTemp36.setText(myTemp36Str);
            hour36 = null;
            myTemp36 = null;
            myTime36 = null;
            myImg36 = null;

            JSONObject hour37 = next24Hours.getJSONObject(36);
            int hour37PicEnd = hour37.get("hourlySummary").toString().indexOf(".");
            int hour37PicBegin = hour37.get("hourlySummary").toString().indexOf("/");
            String pic37 = hour37.get("hourlySummary").toString().substring(hour37PicBegin+1, hour37PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg37 = (ImageView) findViewById(R.id.myImg37);
            switch(pic37) {
                case "clear":
                    myImg37.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg37.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg37.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg37.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg37.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg37.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg37.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg37.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg37.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg37.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg37.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime37 = (TextView) findViewById(R.id.myTime37);
            String myTime37StrIndex = hour37.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime37.setText(myTime37StrIndex);

            TextView myTemp37 = (TextView) findViewById(R.id.myTemp37);
            String myTemp37Str = hour37.get("hourlyTemp").toString();
            myTemp37.setText(myTemp37Str);
            hour37 = null;
            myTemp37 = null;
            myTime37 = null;
            myImg37 = null;

            JSONObject hour38 = next24Hours.getJSONObject(37);
            int hour38PicEnd = hour38.get("hourlySummary").toString().indexOf(".");
            int hour38PicBegin = hour38.get("hourlySummary").toString().indexOf("/");
            String pic38 = hour38.get("hourlySummary").toString().substring(hour38PicBegin+1, hour38PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg38 = (ImageView) findViewById(R.id.myImg38);
            switch(pic38) {
                case "clear":
                    myImg38.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg38.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg38.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg38.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg38.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg38.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg38.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg38.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg38.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg38.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg38.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime38 = (TextView) findViewById(R.id.myTime38);
            String myTime38StrIndex = hour38.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime38.setText(myTime38StrIndex);

            TextView myTemp38 = (TextView) findViewById(R.id.myTemp38);
            String myTemp38Str = hour38.get("hourlyTemp").toString();
            myTemp38.setText(myTemp38Str);
            hour38 = null;
            myTemp38 = null;
            myTime38 = null;
            myImg38 = null;

            JSONObject hour39 = next24Hours.getJSONObject(38);
            int hour39PicEnd = hour39.get("hourlySummary").toString().indexOf(".");
            int hour39PicBegin = hour39.get("hourlySummary").toString().indexOf("/");
            String pic39 = hour39.get("hourlySummary").toString().substring(hour39PicBegin+1, hour39PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg39 = (ImageView) findViewById(R.id.myImg39);
            switch(pic39) {
                case "clear":
                    myImg39.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg39.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg39.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg39.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg39.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg39.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg39.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg39.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg39.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg39.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg39.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime39 = (TextView) findViewById(R.id.myTime39);
            String myTime39StrIndex = hour39.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime39.setText(myTime39StrIndex);

            TextView myTemp39 = (TextView) findViewById(R.id.myTemp39);
            String myTemp39Str = hour39.get("hourlyTemp").toString();
            myTemp39.setText(myTemp39Str);
            hour39 = null;
            myTemp39 = null;
            myTime39 = null;
            myImg39 = null;

            JSONObject hour40 = next24Hours.getJSONObject(39);
            int hour40PicEnd = hour40.get("hourlySummary").toString().indexOf(".");
            int hour40PicBegin = hour40.get("hourlySummary").toString().indexOf("/");
            String pic40 = hour40.get("hourlySummary").toString().substring(hour40PicBegin+1, hour40PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg40 = (ImageView) findViewById(R.id.myImg40);
            switch(pic40) {
                case "clear":
                    myImg40.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg40.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg40.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg40.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg40.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg40.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg40.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg40.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg40.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg40.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg40.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime40 = (TextView) findViewById(R.id.myTime40);
            String myTime40StrIndex = hour40.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime40.setText(myTime40StrIndex);

            TextView myTemp40 = (TextView) findViewById(R.id.myTemp40);
            String myTemp40Str = hour40.get("hourlyTemp").toString();
            myTemp40.setText(myTemp40Str);
            hour40 = null;
            myTemp40 = null;
            myTime40 = null;
            myImg40 = null;

            JSONObject hour41 = next24Hours.getJSONObject(40);
            int hour41PicEnd = hour41.get("hourlySummary").toString().indexOf(".");
            int hour41PicBegin = hour41.get("hourlySummary").toString().indexOf("/");
            String pic41 = hour41.get("hourlySummary").toString().substring(hour41PicBegin+1, hour41PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg41 = (ImageView) findViewById(R.id.myImg41);
            switch(pic41) {
                case "clear":
                    myImg41.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg41.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg41.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg41.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg41.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg41.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg41.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg41.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg41.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg41.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg41.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime41 = (TextView) findViewById(R.id.myTime41);
            String myTime41StrIndex = hour41.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime41.setText(myTime41StrIndex);

            TextView myTemp41 = (TextView) findViewById(R.id.myTemp41);
            String myTemp41Str = hour41.get("hourlyTemp").toString();
            myTemp41.setText(myTemp41Str);
            hour41 = null;
            myTemp41 = null;
            myTime41 = null;
            myImg41 = null;

            JSONObject hour42 = next24Hours.getJSONObject(41);
            int hour42PicEnd = hour42.get("hourlySummary").toString().indexOf(".");
            int hour42PicBegin = hour42.get("hourlySummary").toString().indexOf("/");
            String pic42 = hour42.get("hourlySummary").toString().substring(hour42PicBegin+1, hour42PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg42 = (ImageView) findViewById(R.id.myImg42);
            switch(pic42) {
                case "clear":
                    myImg42.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg42.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg42.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg42.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg42.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg42.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg42.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg42.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg42.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg42.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg42.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime42 = (TextView) findViewById(R.id.myTime42);
            String myTime42StrIndex = hour42.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime42.setText(myTime42StrIndex);

            TextView myTemp42 = (TextView) findViewById(R.id.myTemp42);
            String myTemp42Str = hour42.get("hourlyTemp").toString();
            myTemp42.setText(myTemp42Str);
            hour42 = null;
            myTemp42 = null;
            myTime42 = null;
            myImg42 = null;

            JSONObject hour43 = next24Hours.getJSONObject(42);
            int hour43PicEnd = hour43.get("hourlySummary").toString().indexOf(".");
            int hour43PicBegin = hour43.get("hourlySummary").toString().indexOf("/");
            String pic43 = hour43.get("hourlySummary").toString().substring(hour43PicBegin+1, hour43PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg43 = (ImageView) findViewById(R.id.myImg43);
            switch(pic43) {
                case "clear":
                    myImg43.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg43.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg43.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg43.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg43.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg43.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg43.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg43.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg43.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg43.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg43.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime43 = (TextView) findViewById(R.id.myTime43);
            String myTime43StrIndex = hour43.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime43.setText(myTime43StrIndex);

            TextView myTemp43 = (TextView) findViewById(R.id.myTemp43);
            String myTemp43Str = hour43.get("hourlyTemp").toString();
            myTemp43.setText(myTemp43Str);
            hour43 = null;
            myTemp43 = null;
            myTime43 = null;
            myImg43 = null;

            JSONObject hour44 = next24Hours.getJSONObject(43);
            int hour44PicEnd = hour44.get("hourlySummary").toString().indexOf(".");
            int hour44PicBegin = hour44.get("hourlySummary").toString().indexOf("/");
            String pic44 = hour44.get("hourlySummary").toString().substring(hour44PicBegin+1, hour44PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg44 = (ImageView) findViewById(R.id.myImg44);
            switch(pic44) {
                case "clear":
                    myImg44.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg44.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg44.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg44.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg44.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg44.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg44.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg44.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg44.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg44.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg44.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime44 = (TextView) findViewById(R.id.myTime44);
            String myTime44StrIndex = hour44.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime44.setText(myTime44StrIndex);

            TextView myTemp44 = (TextView) findViewById(R.id.myTemp44);
            String myTemp44Str = hour44.get("hourlyTemp").toString();
            myTemp44.setText(myTemp44Str);
            hour44 = null;
            myTemp44 = null;
            myTime44 = null;
            myImg44 = null;

            JSONObject hour45 = next24Hours.getJSONObject(44);
            int hour45PicEnd = hour45.get("hourlySummary").toString().indexOf(".");
            int hour45PicBegin = hour45.get("hourlySummary").toString().indexOf("/");
            String pic45 = hour45.get("hourlySummary").toString().substring(hour45PicBegin+1, hour45PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg45 = (ImageView) findViewById(R.id.myImg45);
            switch(pic45) {
                case "clear":
                    myImg45.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg45.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg45.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg45.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg45.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg45.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg45.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg45.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg45.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg45.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg45.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime45 = (TextView) findViewById(R.id.myTime45);
            String myTime45StrIndex = hour45.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime45.setText(myTime45StrIndex);

            TextView myTemp45 = (TextView) findViewById(R.id.myTemp45);
            String myTemp45Str = hour45.get("hourlyTemp").toString();
            myTemp45.setText(myTemp45Str);
            hour45 = null;
            myTemp45 = null;
            myTime45 = null;
            myImg45 = null;

            JSONObject hour46 = next24Hours.getJSONObject(45);
            int hour46PicEnd = hour46.get("hourlySummary").toString().indexOf(".");
            int hour46PicBegin = hour46.get("hourlySummary").toString().indexOf("/");
            String pic46 = hour46.get("hourlySummary").toString().substring(hour46PicBegin+1, hour46PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg46 = (ImageView) findViewById(R.id.myImg46);
            switch(pic46) {
                case "clear":
                    myImg46.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg46.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg46.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg46.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg46.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg46.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg46.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg46.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg46.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg46.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg46.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime46 = (TextView) findViewById(R.id.myTime46);
            String myTime46StrIndex = hour46.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime46.setText(myTime46StrIndex);

            TextView myTemp46 = (TextView) findViewById(R.id.myTemp46);
            String myTemp46Str = hour46.get("hourlyTemp").toString();
            myTemp46.setText(myTemp46Str);
            hour46 = null;
            myTemp46 = null;
            myTime46 = null;
            myImg46 = null;

            JSONObject hour47 = next24Hours.getJSONObject(46);
            int hour47PicEnd = hour47.get("hourlySummary").toString().indexOf(".");
            int hour47PicBegin = hour47.get("hourlySummary").toString().indexOf("/");
            String pic47 = hour47.get("hourlySummary").toString().substring(hour47PicBegin+1, hour47PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg47 = (ImageView) findViewById(R.id.myImg47);
            switch(pic47) {
                case "clear":
                    myImg47.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg47.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg47.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg47.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg47.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg47.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg47.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg47.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg47.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg47.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg47.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime47 = (TextView) findViewById(R.id.myTime47);
            String myTime47StrIndex = hour47.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime47.setText(myTime47StrIndex);

            TextView myTemp47 = (TextView) findViewById(R.id.myTemp47);
            String myTemp47Str = hour47.get("hourlyTemp").toString();
            myTemp47.setText(myTemp47Str);
            hour47 = null;
            myTemp47 = null;
            myTime47 = null;
            myImg47 = null;

            JSONObject hour48 = next24Hours.getJSONObject(47);
            int hour48PicEnd = hour48.get("hourlySummary").toString().indexOf(".");
            int hour48PicBegin = hour48.get("hourlySummary").toString().indexOf("/");
            String pic48 = hour48.get("hourlySummary").toString().substring(hour48PicBegin+1, hour48PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myImg48 = (ImageView) findViewById(R.id.myImg48);
            switch(pic48) {
                case "clear":
                    myImg48.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg48.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg48.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg48.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg48.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg48.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg48.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg48.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg48.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg48.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg48.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myTime48 = (TextView) findViewById(R.id.myTime48);
            String myTime48StrIndex = hour48.get("hourlyTime").toString();
            //String myTime1Str = hour1.get("hourlyTime").toString();
            myTime48.setText(myTime48StrIndex);

            TextView myTemp48 = (TextView) findViewById(R.id.myTemp48);
            String myTemp48Str = hour48.get("hourlyTemp").toString();
            myTemp48.setText(myTemp48Str);
            hour48 = null;
            myTemp48 = null;
            myTime48 = null;
            myImg48 = null;



        } catch (JSONException e) {
            Log.e("Json exception: ", e.getMessage(), e);
            e.printStackTrace();
        }



    }

    private void generateNext7DaysWeather(String myJson) {
        try {
            JSONObject obj = new JSONObject(myJson);
            JSONArray next7Days = obj.getJSONArray("next7Days");
            JSONObject day1 = next7Days.getJSONObject(0);
            //set the description picture
            int day1PicEnd = day1.get("icon").toString().indexOf(".");
            int day1PicBegin = day1.get("icon").toString().indexOf("/");
            String pic1 = day1.get("icon").toString().substring(day1PicBegin+1, day1PicEnd);

            //TextView myTest100 = (TextView) findViewById(R.id.mytest);
            //myTest100.setText(pic1);

            ImageView myDay1Img = (ImageView) findViewById(R.id.day1Img);
            switch(pic1) {
                case "clear":
                    myDay1Img.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myDay1Img.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myDay1Img.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myDay1Img.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myDay1Img.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myDay1Img.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myDay1Img.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myDay1Img.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myDay1Img.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myDay1Img.setImageResource(R.drawable.wind);
                    break;
                default:
                    myDay1Img.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myDay1Des = (TextView) findViewById(R.id.day1);
            String myDay1DesStr = day1.get("day").toString() + ", " + day1.get("monthDate").toString();
            myDay1Des.setText(myDay1DesStr);

            TextView myDay1Temp = (TextView) findViewById(R.id.day1Temp);
            String myDay1TempStr = "Min: " + day1.get("minTemp").toString() + TempUnit + " | " + "Max: " + day1.getString("maxTemp").toString() + TempUnit;
            myDay1Temp.setText(myDay1TempStr);
            day1 = null;
            myDay1Img = null;
            myDay1Des = null;
            myDay1Temp = null;


            JSONObject day2 = next7Days.getJSONObject(1);
            //set the description picture
            int day2PicEnd = day2.get("icon").toString().indexOf(".");
            int day2PicBegin = day2.get("icon").toString().indexOf("/");
            String pic2 = day2.get("icon").toString().substring(day2PicBegin+1, day2PicEnd);
            ImageView myDay2Img = (ImageView) findViewById(R.id.day2Img);
            switch(pic2) {
                case "clear":
                    myDay2Img.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myDay2Img.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myDay2Img.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myDay2Img.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myDay2Img.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myDay2Img.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myDay2Img.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myDay2Img.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myDay2Img.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myDay2Img.setImageResource(R.drawable.wind);
                    break;
                default:
                    myDay2Img.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myDay2Des = (TextView) findViewById(R.id.day2);
            String myDay2DesStr = day2.get("day").toString() + ", " + day2.get("monthDate").toString();
            myDay2Des.setText(myDay2DesStr);

            TextView myDay2Temp = (TextView) findViewById(R.id.day2Temp);
            String myDay2TempStr = "Min: " + day2.get("minTemp").toString() + TempUnit + " | " + "Max: " + day2.getString("maxTemp").toString() + TempUnit;
            myDay2Temp.setText(myDay2TempStr);
            day2 = null;
            myDay2Img = null;
            myDay2Des = null;
            myDay2Temp = null;

            JSONObject day3 = next7Days.getJSONObject(2);
            //set the description picture
            int day3PicEnd = day3.get("icon").toString().indexOf(".");
            int day3PicBegin = day3.get("icon").toString().indexOf("/");
            String pic3 = day3.get("icon").toString().substring(day3PicBegin+1, day3PicEnd);
            ImageView myDay3Img = (ImageView) findViewById(R.id.day3Img);
            switch(pic3) {
                case "clear":
                    myDay3Img.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myDay3Img.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myDay3Img.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myDay3Img.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myDay3Img.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myDay3Img.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myDay3Img.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myDay3Img.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myDay3Img.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myDay3Img.setImageResource(R.drawable.wind);
                    break;
                default:
                    myDay3Img.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myDay3Des = (TextView) findViewById(R.id.day3);
            String myDay3DesStr = day3.get("day").toString() + ", " + day3.get("monthDate").toString();
            myDay3Des.setText(myDay3DesStr);

            TextView myDay3Temp = (TextView) findViewById(R.id.day3Temp);
            String myDay3TempStr = "Min: " + day3.get("minTemp").toString() + TempUnit + " | " + "Max: " + day3.getString("maxTemp").toString() + TempUnit;
            myDay3Temp.setText(myDay3TempStr);
            day3 = null;
            myDay3Img = null;
            myDay3Des = null;
            myDay3Temp = null;

            JSONObject day4 = next7Days.getJSONObject(3);
            //set the description picture
            int day4PicEnd = day4.get("icon").toString().indexOf(".");
            int day4PicBegin = day4.get("icon").toString().indexOf("/");
            String pic4 = day4.get("icon").toString().substring(day4PicBegin+1, day4PicEnd);
            ImageView myDay4Img = (ImageView) findViewById(R.id.day4Img);
            switch(pic4) {
                case "clear":
                    myDay4Img.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myDay4Img.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myDay4Img.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myDay4Img.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myDay4Img.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myDay4Img.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myDay4Img.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myDay4Img.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myDay4Img.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myDay4Img.setImageResource(R.drawable.wind);
                    break;
                default:
                    myDay4Img.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myDay4Des = (TextView) findViewById(R.id.day4);
            String myDay4DesStr = day4.get("day").toString() + ", " + day4.get("monthDate").toString();
            myDay4Des.setText(myDay4DesStr);

            TextView myDay4Temp = (TextView) findViewById(R.id.day4Temp);
            String myDay4TempStr = "Min: " + day4.get("minTemp").toString() + TempUnit + " | " + "Max: " + day4.getString("maxTemp").toString() + TempUnit;
            myDay4Temp.setText(myDay4TempStr);
            day4 = null;
            myDay4Img = null;
            myDay4Des = null;
            myDay4Temp = null;

            JSONObject day5 = next7Days.getJSONObject(4);
            //set the description picture
            int day5PicEnd = day5.get("icon").toString().indexOf(".");
            int day5PicBegin = day5.get("icon").toString().indexOf("/");
            String pic5 = day5.get("icon").toString().substring(day5PicBegin+1, day5PicEnd);
            ImageView myDay5Img = (ImageView) findViewById(R.id.day5Img);
            switch(pic5) {
                case "clear":
                    myDay5Img.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myDay5Img.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myDay5Img.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myDay5Img.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myDay5Img.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myDay5Img.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myDay5Img.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myDay5Img.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myDay5Img.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myDay5Img.setImageResource(R.drawable.wind);
                    break;
                default:
                    myDay5Img.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myDay5Des = (TextView) findViewById(R.id.day5);
            String myDay5DesStr = day5.get("day").toString() + ", " + day5.get("monthDate").toString();
            myDay5Des.setText(myDay5DesStr);

            TextView myDay5Temp = (TextView) findViewById(R.id.day5Temp);
            String myDay5TempStr = "Min: " + day5.get("minTemp").toString() + TempUnit + " | " + "Max: " + day5.getString("maxTemp").toString() + TempUnit;
            myDay5Temp.setText(myDay5TempStr);
            day5 = null;
            myDay5Img = null;
            myDay5Des = null;
            myDay5Temp = null;

            JSONObject day6 = next7Days.getJSONObject(5);
            //set the description picture
            int day6PicEnd = day6.get("icon").toString().indexOf(".");
            int day6PicBegin = day6.get("icon").toString().indexOf("/");
            String pic6 = day6.get("icon").toString().substring(day6PicBegin+1, day6PicEnd);
            ImageView myDay6Img = (ImageView) findViewById(R.id.day6Img);
            switch(pic6) {
                case "clear":
                    myDay6Img.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myDay6Img.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myDay6Img.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myDay6Img.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myDay6Img.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myDay6Img.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myDay6Img.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myDay6Img.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myDay6Img.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myDay6Img.setImageResource(R.drawable.wind);
                    break;
                default:
                    myDay6Img.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myDay6Des = (TextView) findViewById(R.id.day6);
            String myDay6DesStr = day6.get("day").toString() + ", " + day6.get("monthDate").toString();
            myDay6Des.setText(myDay6DesStr);

            TextView myDay6Temp = (TextView) findViewById(R.id.day6Temp);
            String myDay6TempStr = "Min: " + day6.get("minTemp").toString() + TempUnit + " | " + "Max: " + day6.getString("maxTemp").toString() + TempUnit;
            myDay6Temp.setText(myDay6TempStr);
            day6 = null;
            myDay6Img = null;
            myDay6Des = null;
            myDay6Temp = null;

            JSONObject day7 = next7Days.getJSONObject(6);
            //set the description picture
            int day7PicEnd = day7.get("icon").toString().indexOf(".");
            int day7PicBegin = day7.get("icon").toString().indexOf("/");
            String pic7 = day7.get("icon").toString().substring(day7PicBegin+1, day7PicEnd);
            ImageView myDay7Img = (ImageView) findViewById(R.id.day7Img);
            switch(pic7) {
                case "clear":
                    myDay7Img.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myDay7Img.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myDay7Img.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myDay7Img.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myDay7Img.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myDay7Img.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myDay7Img.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myDay7Img.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myDay7Img.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myDay7Img.setImageResource(R.drawable.wind);
                    break;
                default:
                    myDay7Img.setImageResource(R.drawable.clear);
                    break;
            }

            //set the description
            TextView myDay7Des = (TextView) findViewById(R.id.day7);
            String myDay7DesStr = day7.get("day").toString() + ", " + day7.get("monthDate").toString();
            myDay7Des.setText(myDay7DesStr);

            TextView myDay7Temp = (TextView) findViewById(R.id.day7Temp);
            String myDay7TempStr = "Min: " + day7.get("minTemp").toString() + TempUnit + " | " + "Max: " + day7.getString("maxTemp").toString() + TempUnit;
            myDay7Temp.setText(myDay7TempStr);
            day7 = null;
            myDay7Img = null;
            myDay7Des = null;
            myDay7Temp = null;


        } catch (JSONException e) {
            Log.e("Json exception: ", e.getMessage(), e);
            e.printStackTrace();
        }
    }

    private void generateHeadLine(String city, String state) {
        TextView myHeadLine = (TextView) findViewById(R.id.showInfo);
        String myHeadLineStr = "More Details for " + city + ", " + state;
        myHeadLine.setText(myHeadLineStr);
    }

}
