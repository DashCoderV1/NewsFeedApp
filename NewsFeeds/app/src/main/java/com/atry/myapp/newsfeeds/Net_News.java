package com.atry.myapp.newsfeeds;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by ymayu on 10/20/2016.
 */

public class Net_News {
    public static final String LOG_TAG = Main_Activity.class.getName();

    String jsonResponse;
    InputStream inputStream;
    HttpURLConnection urlConnection;

    public URL createUrl(String usgs) {
        URL url = null;
        try {
            url = new URL(usgs);
        } catch (MalformedURLException e) {
            Log.e("Its a deformed Url", String.valueOf(e));
        }
        return url;
    }



    public String fetchData(URL url) throws IOException{

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public String netEarth(String usgs){
        URL url = createUrl(usgs);
        String Sample=null;
        try {
            Sample=fetchData(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Sample;
    }

}
