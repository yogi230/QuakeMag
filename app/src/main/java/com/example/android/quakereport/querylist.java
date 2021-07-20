package com.example.android.quakereport;


import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public final class querylist {

    private static String SimpleName = null;
    private querylist() {
        //private constructor created so that no object shoud be initialized ever.
        //only methods and variables accesed without creating an object
    }

    public static ArrayList<Item> extractItemList(String RealJson) {
        SimpleName=RealJson;
        ArrayList<Item> arrayList = new ArrayList<Item>();
        if(SimpleName=="")
        {
            return arrayList;
        }
        try {
//                Log.v("querylist","realjson in querylist"+SimpleName);
            JSONObject root = new JSONObject(SimpleName);
            JSONArray features = root.getJSONArray("features");
            for (int i = 0; i < features.length(); i++) {
                JSONObject feature = features.getJSONObject(i).getJSONObject("properties");
                double mag = feature.getDouble("mag");
                String loc = feature.getString("place");
                long tme = feature.getLong("time");
                String url=feature.getString("url");
                arrayList.add(new Item(mag, loc, tme,url));

            }
        } catch (JSONException e) {
            Log.e("QueryList", "Problem parsing the earthquake result", e);
        }
        return arrayList;
    }
}
