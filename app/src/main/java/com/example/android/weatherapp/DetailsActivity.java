package com.example.android.weatherapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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

        final Button myDailyBtn = (Button) findViewById(R.id.next7DaysBtn);
        myDailyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RelativeLayout myDailyLayout = (RelativeLayout) findViewById(R.id.next7Days);
                myDailyLayout.setVisibility(View.VISIBLE);

                RelativeLayout myHourlyLayout = (RelativeLayout) findViewById(R.id.next24Hours);
                myHourlyLayout.setVisibility(View.GONE);


            }

        });
    }

    private void generateNext24HoursWeather(String myJson) {
        TextView myUnit = (TextView) findViewById(R.id.hourlyUnit);
        String myUnitStr = "Temp(°" + TempUnit + ")";
        myUnit.setText(myUnitStr);

        TableLayout myTableLayout = (TableLayout) findViewById(R.id.myTable);

        TableRow myRow1 = new TableRow(this);
        myRow1.setBackgroundColor(Color.parseColor("#b3b3b3"));
        myRow1.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        myRow1.setPadding(0, 3, 0, 0);

        TextView time1 = new TextView(this);
        time1.setText("02:00AM");
        time1.setTextSize(22);
        time1.setGravity(Gravity.CENTER);
        myRow1.addView(time1);// add the column to the table row here

        ImageView summary1 = new ImageView(this);
        summary1.setImageResource(R.drawable.clear); // set the text for the header
        summary1.setLayoutParams(new ViewGroup.LayoutParams(100,100));
        myRow1.addView(summary1); // add the column to the table row here

        TextView Temp = new TextView(this);
        Temp.setText("56"); // set the text for the header
        time1.setTextSize(22);
        time1.setGravity(Gravity.CENTER);
        myRow1.addView(Temp); // add the column to the table row here

        myTableLayout.addView(myRow1, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));


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

            JSONObject day3 = next7Days.getJSONObject(2);
            //set the description picture
            int day3PicEnd = day1.get("icon").toString().indexOf(".");
            int day3PicBegin = day1.get("icon").toString().indexOf("/");
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
