package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.model.DailyForecasts;
import com.example.weatherapp.model.Root;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvWeather;

    private static final String LOGGING_TAG = "weatherDATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWeather = findViewById(R.id.tv_weather);

        ImageView ivAccuWeather = findViewById(R.id.iv_accuweather);

        ivAccuWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.accuweather.com/"));
                startActivity(intent);
            }
        });

        URL url = NetworkUtil.buildURLForWeather();
        Toast.makeText(this, ": "+url, Toast.LENGTH_LONG).show();
        new FetchWeatherData().execute(url);

    }

    class FetchWeatherData extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL weatherURL = urls[0];
            String weatherData = null;
            try {
                weatherData = NetworkUtil.getResponseFromHttpUrl(weatherURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return weatherData;
        }

        @Override
        protected void onPostExecute(String weatherData) {
            if (weatherData != null) {
//                tvWeather.setText(weatherData);
                consumeJson(weatherData);
            }
            super.onPostExecute(weatherData);
        }
    }

    protected void consumeJson(String weatherJSON) {
        if (weatherJSON != null) {
            Gson gson = new Gson();
            Root weatherData = gson.fromJson(weatherJSON, Root.class);
            for (DailyForecasts forecast : weatherData.getDailyForecasts())
            {
                tvWeather.append("Date: " +
                        forecast.getDate().substring(0, 10) +
                        " Min: " +
                        forecast.getTemperature().getMinimum().getValue() +
                        " Max: " +
                        forecast.getTemperature().getMaximum().getValue() +
                        "\n");
            }
        }
    }
}