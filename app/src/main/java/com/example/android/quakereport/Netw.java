package com.example.android.quakereport;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public final class Netw {
    public static final String LOG_TAG = Netw.class.getSimpleName();

    public static URL createUrl(String string) {
        URL url = null;
        try {
             url = new URL(string);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "not able to create URL successfully!", e);
        }
        return url;
    }

    public static String extractdata(String URGSrequest) {
        URL url = createUrl(URGSrequest);
        String jsoRes = "";
        try {
            jsoRes = makeHttpReq(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error creating request", e);
        }
        return jsoRes;
    }

    public static String makeHttpReq(URL url) throws IOException {
        String jsonRes = "";
        if (url == null) {
            return jsonRes;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.v(LOG_TAG,"my response code="+urlConnection.getResponseCode());
            if (urlConnection.getResponseCode() == 200) {
                    inputStream=urlConnection.getInputStream();
                    jsonRes=readFrom(inputStream);
            }
            else
            {
                Log.e(LOG_TAG,"error in resposne code"+urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "problem retrieving json response", e);
        } finally
        {
            if(urlConnection!=null) {
                urlConnection.disconnect();
            }
            if(inputStream!=null) {
                inputStream.close();
            }
        }
    return jsonRes;
    }
    public static String readFrom(InputStream inputStream)throws IOException
    {
            StringBuilder stringOut=new StringBuilder();
            if(inputStream!=null)
            {
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader=new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while(line != null)
                {
                    stringOut.append(line);
                    line=reader.readLine();
                }

            }
            return stringOut.toString();
    }
}
