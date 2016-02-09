package com.krzysiek.pogoda;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.util.Locale;

public class ShowWeather extends Activity {

    TextView cityField;
    TextView detailsField;
    TextView currentTemperatureField;

    Handler handler;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);

        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        handler = new Handler();

        cityField = (TextView)findViewById(R.id.city_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);

        String city = getIntent().getStringExtra("city");
        spinner.setVisibility(View.VISIBLE);
        updateWeatherData(city);
    }

    private void updateWeatherData(final String city){
        new Thread(){;
            public void run(){
                final JSONObject json = GetData.getJSON(ShowWeather.this, city);
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(ShowWeather.this,
                                    ShowWeather.this.getString(R.string.error),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(json);
                            spinner.setVisibility(View.GONE);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json){
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject main = json.getJSONObject("main");
            detailsField.setText(
                    "\n" + "Wilgotnosc: " + main.getString("humidity") + "%" +
                            "\n" + "Cisnienie: " + main.getString("pressure") + " hPa");

            currentTemperatureField.setText(
                    String.format("%.2f", main.getDouble("temp"))+ " ℃");

        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }
}