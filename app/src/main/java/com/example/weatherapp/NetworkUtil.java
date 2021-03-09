package com.example.weatherapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil
{
    public static final String TAG = "ourURL";

    public static final String BASE_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/305605";
    public static final String METRIC_VALUE = "true";
    public static final String METRIC_PARAM = "metric";
    public static final String API_KEY = "";
    public static final String API_PARAM = "apikey";

    public static URL buildURLForWeather()
    {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(API_PARAM, API_KEY)
                .appendQueryParameter(METRIC_PARAM, METRIC_VALUE)
                .build();

        URL url = null;
        try
        {
            url =  new URL(uri.toString());
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        Log.i(TAG, "buildURLForWeather: " + url);

        return url;
    }

    public static String getResponse(URL url) throws IOException
    {
        HttpURLConnection httpURLConnection =  (HttpURLConnection) url.openConnection();
        try
        {
            InputStream in = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("//A");//At start of string
            boolean scannerHasInput = scanner.hasNext();

            if(scannerHasInput)
            {
                return scanner.next();
            }
            else
            {
                return null;
            }
        }
        finally
        {
            httpURLConnection.disconnect();
        }
    }

}
