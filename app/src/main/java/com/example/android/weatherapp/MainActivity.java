package com.example.android.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private Spinner myState;
    private Button mySearchBtn;
    private Button myClearBtn;
    private Button myAboutBtn;
    private TextView myAddress;
    private TextView myCity;
    private TextView myValidate;
    private RadioGroup myRadioGroup;
    private RadioButton myFahrenheit, myCelsius;
    private Context context;
    private String tempUnit;
    private String address;
    private String city;
    private String state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkToForecastIO();
        addListenerOnButton();



    }

    public void linkToForecastIO() {
        ImageView img = (ImageView)findViewById(R.id.forecastio);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://forecast.io"));
                startActivity(intent);
            }
        });
    }

    public void addListenerOnButton() {

        context = this.getApplicationContext();
        myAddress = (TextView) findViewById(R.id.address);
        myCity = (TextView) findViewById(R.id.city);
        myState = (Spinner) findViewById(R.id.state);
        mySearchBtn = (Button) findViewById(R.id.search);
        myClearBtn = (Button) findViewById(R.id.clear);
        myAboutBtn = (Button) findViewById(R.id.about);
        myRadioGroup = (RadioGroup) findViewById(R.id.temp);
        myFahrenheit = (RadioButton) findViewById(R.id.fahrenheit);
        //myCelsius = (RadioButton) findViewById(R.id.celsius);
        myValidate = (TextView) findViewById(R.id.validate);
        myValidate.setTextColor(Color.RED);

        myAboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(myIntent2);
            }
        });

        mySearchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean valid;
                valid = validateInput();
                if (valid == false) {
                    return;
                }

                myValidate.setText("");

                address = myAddress.getText().toString();
                city = myCity.getText().toString();
                state = String.valueOf(myState.getSelectedItem());

                int selectedId = myRadioGroup.getCheckedRadioButtonId();

                if (selectedId == myFahrenheit.getId()) {
                    //myValidate.setText("It's fahrenheit!");
                    tempUnit = "Fahrenheit";
                } else {
                    //myValidate.setText("It's celsius");
                    tempUnit = "Celsius";
                }

                FetchWeatherTask myTask = new FetchWeatherTask();
                myTask.execute();


            }

        });

        myClearBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myAddress.setText("");
                myCity.setText("");
                myState.setSelection(0);
                myFahrenheit.setChecked(true);
                myValidate.setText("");
            }

        });

    }
    public boolean validateInput() {
        /*myAddress = (TextView) findViewById(R.id.address);
        myCity = (TextView) findViewById(R.id.city);
        myState = (Spinner) findViewById(R.id.state);*/
        if (myAddress.getText().toString().equals("")) {
            myValidate.setText("Please enter a street address");
            return false;
        }
        if (myCity.getText().toString().equals("")) {
            myValidate.setText("Please enter a city");
            return false;
        }
        if (String.valueOf(myState.getSelectedItem()).equals("Select")) {
            myValidate.setText("Please enter a state");
            return false;
        }
        return true;
    }

    public class FetchWeatherTask extends AsyncTask<Void, Void, String> {
        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        /*private String getReadableDateString(long time) {
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
            return shortenedDateFormat.format(time);
        }

        private String formatHighLows(double high, double low, String unitType) {

            if (unitType.equals(getString(R.string.pref_units_imperial))) {
                high = (high * 1.8) + 32;
                low = (low * 1.8) + 32;
            } else if (!unitType.equals(getString(R.string.pref_units_metric))) {
                Log.d(LOG_TAG, "Unit type not found: " + unitType);
            }

            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);

            String highLowStr = roundedHigh + "/" + roundedLow;
            return highLowStr;
        }

        private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays) throws JSONException {
            final String OWN_LIST = "list";
            final String OWN_WEATHER = "weather";
            final String OWN_TEMPERATURE = "temp";
            final String OWN_MAX = "max";
            final String OWN_MIN = "min";
            final String OWN_DESCRIPTION = "main";

            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWN_LIST);

            android.text.format.Time dayTime = new android.text.format.Time();
            dayTime.setToNow();

            int julianStartDay = android.text.format.Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

            dayTime = new android.text.format.Time();

            String[] resultStrs = new String[numDays];


            // Data is fetched in Celsius by default.
            // If user prefers to see in Fahrenheit, convert the values here.
            // We do this rather than fetching in Fahrenheit so that the user can
            // change this option without us having to re-fetch the data once
            // we start storing the values in a database.
            SharedPreferences sharedPrefs =
                    PreferenceManager.getDefaultSharedPreferences(getActivity());
            String unitType = sharedPrefs.getString(
                    getString(R.string.pref_units_key),
                    getString(R.string.pref_units_metric));

            for (int i = 0; i < weatherArray.length(); i++) {
                String day;
                String description;
                String highAndLow;

                JSONObject dayForecast = weatherArray.getJSONObject(i);

                long dateTime;

                dateTime = dayTime.setJulianDay(julianStartDay + i);
                day = getReadableDateString(dateTime);

                JSONObject weatherObject = dayForecast.getJSONArray(OWN_WEATHER).getJSONObject(0);
                description = weatherObject.getString(OWN_DESCRIPTION);

                JSONObject temperatureObject = dayForecast.getJSONObject(OWN_TEMPERATURE);
                double high = temperatureObject.getDouble(OWN_MAX);
                double low = temperatureObject.getDouble(OWN_MIN);

                highAndLow = formatHighLows(high, low, unitType);
                resultStrs[i] = day + " - " + description + " - " + highAndLow;

            }

            for (String r : resultStrs) {
                //Log.v(LOG_TAG, "Forecast entry: " + r );
            }

            return resultStrs;

        }*/

        @Override
        protected String doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            /*if (params.length == 0) {
                return null;
            }*/

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String forecastJsonStr = null;


            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                // Will contain the raw JSON response as a string.

                //URL url = new URL("http://default-environment-dtv3aunj2z.elasticbeanstalk.com/index.php?stAddress=3025 Royal St&city=Los Angeles&state=CA&temperature=Fahrenheit");
                final String FORECAST_BASE_URL =
                        "http://default-environment-dtv3aunj2z.elasticbeanstalk.com/index.php?";
                final String ADDRESS_PARAM = "stAddress";
                final String CITY_PARAM = "city";
                final String STATE_PARAM = "state";
                final String TEMP_PARAM = "temperature";
                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter(ADDRESS_PARAM, address)
                        .appendQueryParameter(CITY_PARAM, city)
                        .appendQueryParameter(STATE_PARAM, state)
                        .appendQueryParameter(TEMP_PARAM, tempUnit)
                        .build();

                URL url = new URL(builtUri.toString());

                Log.v(LOG_TAG, "Built URI " + builtUri.toString());




                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();

                Log.v(LOG_TAG, "Forecast JSON String: " + forecastJsonStr);
                return forecastJsonStr;

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            /*try {
                return getWeatherDataFromJson(forecastJsonStr, numDays);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }*/
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Intent myIntent = new Intent(context, ResultActivity.class);
                myIntent.putExtra("myJson", result);
                //myIntent.putExtra("city", myCity.getText().toString());
                //myIntent.putExtra("state", String.valueOf(myState.getSelectedItem()));
                //myIntent.putExtra("temp", tempUnit);
                startActivity(myIntent);
            }
        }

    }


}
