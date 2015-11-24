package com.example.android.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity {

    private TextView testText;
    private Context context = this.getApplicationContext();
    private String myCity;
    private String myState;
    private String myTempUnit;
    private String myJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // receive the arguments from the previous Activity
        Bundle extra = getIntent().getExtras();
        if (extra == null) {
            return;
        }
        // assign the values to string-arguments
        myJson = extra.getString("myJson");
        myCity = extra.getString("city");
        myState = extra.getString("state");
        myTempUnit = extra.getString("tempUnit");
        getRightnowInfoFromJson(myJson);
        clickFacebookButton();
        //addListenerOnButton();
    }

    private void getRightnowInfoFromJson(String forecastJsonStr) {
        try {
            JSONObject obj = new JSONObject(forecastJsonStr);
            JSONObject rightNow = obj.getJSONObject("rightNow");
            testText = (TextView) findViewById(R.id.getInfo);
            //set the description picture
            int pic_index = rightNow.get("pic_alt").toString().indexOf(".");
            String pic = rightNow.get("pic_alt").toString().substring(0, pic_index);
            ImageView myImg = (ImageView) findViewById(R.id.picDesNow);
            switch(pic) {
                case "clear":
                    myImg.setImageResource(R.drawable.clear);
                    break;
                case "clear_night":
                    myImg.setImageResource(R.drawable.clear_night);
                    break;
                case "cloud_day":
                    myImg.setImageResource(R.drawable.cloud_day);
                    break;
                case "cloud_night":
                    myImg.setImageResource(R.drawable.cloud_night);
                    break;
                case "cloudy":
                    myImg.setImageResource(R.drawable.cloudy);
                    break;
                case "fog":
                    myImg.setImageResource(R.drawable.fog);
                    break;
                case "rain":
                    myImg.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    myImg.setImageResource(R.drawable.sleet);
                    break;
                case "snow":
                    myImg.setImageResource(R.drawable.snow);
                    break;
                case "wind":
                    myImg.setImageResource(R.drawable.wind);
                    break;
                default:
                    myImg.setImageResource(R.drawable.clear);
                    break;

            }

            //set the description
            TextView myDes = (TextView) findViewById(R.id.textDesNow);
            myDes.setText(rightNow.get("weatherCondition").toString());

            TextView myTemp = (TextView) findViewById(R.id.tempDesNow);
            myTemp.setText(rightNow.get("temperature").toString());

            TextView myPrefix = (TextView) findViewById(R.id.tempPrefixNow);
            myPrefix.setText(rightNow.get("postPrefix").toString());

            TextView myLowTemp = (TextView) findViewById(R.id.lowTempNow);
            String lowTemp = "L:" + rightNow.get("lowTemperature").toString() + "°";
            myLowTemp.setText(lowTemp);

            TextView myHighTemp = (TextView) findViewById(R.id.highTempNow);
            String highTemp = "H:" + rightNow.get("highTemperature").toString() + "°";
            myHighTemp.setText(highTemp);

            TextView myPrecipitation = (TextView) findViewById(R.id.precipitationRightNow);
            myPrecipitation.setText(rightNow.get("precipitation").toString());

            TextView myChanceOfRain = (TextView) findViewById(R.id.rainRightNow);
            myChanceOfRain.setText(rightNow.get("chanceOfRian").toString());

            TextView myWindSpeed = (TextView) findViewById(R.id.windSpeedRightNow);
            myWindSpeed.setText(rightNow.get("windSpeed").toString());

            TextView myDewPoint = (TextView) findViewById(R.id.dewPointRightNow);
            myDewPoint.setText(rightNow.get("dewPoint").toString());

            TextView myHumidity = (TextView) findViewById(R.id.humidityRightNow);
            myHumidity.setText(rightNow.get("humidity").toString());

            TextView myVisibility = (TextView) findViewById(R.id.visibilityRightNow);
            myVisibility.setText(rightNow.get("visibility").toString());

            TextView mySunrise = (TextView) findViewById(R.id.sunriseRightNow);
            mySunrise.setText(rightNow.get("sunrise").toString());

            TextView mySunset = (TextView) findViewById(R.id.sunsetRightNow);
            mySunset.setText(rightNow.get("sunset").toString());

            //testText.setText(pic);
        } catch (JSONException e) {
            Log.e("Json exception: ", e.getMessage(), e);
            e.printStackTrace();
        }

    }

    private void clickFacebookButton() {
        ImageView img = (ImageView)findViewById(R.id.facebook);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.facebook.com"));
                startActivity(intent);
            }
        });
    }

    private void addListenerOnButton() {

        Button myDetailBtn = (Button) findViewById(R.id.detail);
        myDetailBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, DetailsActivity.class);
                myIntent.putExtra("myJson", myJson);
                myIntent.putExtra("city", myCity);
                myIntent.putExtra("state", myState);
                myIntent.putExtra("tempUnit", myTempUnit);
                startActivity(myIntent);
            }

        });
    }
    
}
