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

    private static final String BASE_URL = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/305605";
    private static final String METRIC_VALUE = "true";
    private static final String METRIC_PARAM = "metric";
    private static final String API_KEY = "";
    private static final String API_PARAM = "apikey";
    private static final String LOGGING_TAG = "URLWECREATED";
    private NetworkUtil() {
    }

    public static URL buildURLForWeather()
    {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(API_PARAM, BuildConfig.ACCUWEATHER_API_KEY)
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

    public static String getResponseFromHttpUrl(URL url) throws IOException
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
