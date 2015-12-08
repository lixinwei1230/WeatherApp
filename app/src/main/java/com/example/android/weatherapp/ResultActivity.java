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
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity {

    private TextView testText;
    private Bundle extra;
    private Context context;
    private String myCity;
    private String myState;
    private String myImgUrl;
    private String myDes;
    private String lon;
    private String lat;

    CallbackManager callbackManager;
    ShareDialog shareDialog;


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional



        // receive the arguments from the previous Activity
        extra = getIntent().getExtras();
        if (extra == null) {
            return;
        }
        // assign the values to string-arguments
        String myJson = extra.getString("myJson");
        try {
            JSONObject obj = new JSONObject(myJson);
            lon = obj.getString("longitude");
            lat = obj.getString("latitude");
        } catch (JSONException e) {
            Log.e("Json exception: ", e.getMessage(), e);
            e.printStackTrace();
        }

        myCity = extra.getString("city");
        myState = extra.getString("state");
        getRightnowInfoFromJson(myJson);
        addListenerOnButton();
    }


    private void getRightnowInfoFromJson(String forecastJsonStr) {
        try {
            myImgUrl = "http://cs-server.usc.edu:45678/hw/hw8/images/";

            JSONObject obj = new JSONObject(forecastJsonStr);
            JSONObject rightNow = obj.getJSONObject("rightNow");
            testText = (TextView) findViewById(R.id.getInfo);
            //set the description picture
            myDes = rightNow.get("weatherDes").toString();
            int pic_index = rightNow.get("pic_alt").toString().indexOf(".");
            String pic = rightNow.get("pic_alt").toString().substring(0, pic_index);
            ImageView myImg = (ImageView) findViewById(R.id.picDesNow);
            switch(pic) {
                case "clear":
                    myImg.setImageResource(R.drawable.clear);
                    myImgUrl += pic + ".png";
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

            rightNow = null;
            myDes = null;
            myTemp = null;
            myPrefix = null;
            myLowTemp = null;
            myHighTemp = null;
            myPrecipitation = null;
            myChanceOfRain = null;
            myWindSpeed = null;
            myDewPoint = null;
            myHumidity = null;
            myVisibility = null;
            mySunrise = null;
            mySunset = null;

            System.gc();

            //testText.setText(pic);
        } catch (JSONException e) {
            Log.e("Json exception: ", e.getMessage(), e);
            e.printStackTrace();
        }

    }

    private void addListenerOnButton() {
        ImageView img = (ImageView)findViewById(R.id.facebook);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.facebook.com"));
                startActivity(intent);*/
                //Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    String myTitle = "Current Weather in " + myCity + ", " + myState;
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("http://forecast.io/"))
                            .setContentTitle(myTitle)
                            .setImageUrl(Uri.parse(myImgUrl))
                            .setContentDescription(myDes)
                            .build();
                    shareDialog.show(content);
                }

                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(getApplicationContext(), "Facebook Post Successful", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "Posted Cancelled", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(FacebookException e) {
                        Log.v("FACEBOOK_TEST", "share api error " + e);
                    }
                });



                //shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {




               // });
            }
        });

        context = this.getApplicationContext();
        Button myDetailBtn = (Button) findViewById(R.id.detail);
        myDetailBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, DetailsActivity.class);
                myIntent.putExtra("myJson", extra.getString("myJson"));
                myIntent.putExtra("city", extra.getString("city"));
                myIntent.putExtra("state", extra.getString("state"));
                myIntent.putExtra("tempUnit", extra.getString("tempUnit"));
                startActivity(myIntent);
            }

        });

        Button myMapBtn = (Button) findViewById(R.id.map);
        myMapBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent1 = new Intent(ResultActivity.this, MapActivity.class);
                myIntent1.putExtra("city", extra.getString("city"));
                myIntent1.putExtra("state", extra.getString("state"));
                myIntent1.putExtra("lon", lon);
                myIntent1.putExtra("lat", lat);
                startActivity(myIntent1);
            }

        });


    }


}
