package com.atry.myapp.newsfeeds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ymayu on 10/20/2016.
 */

public class Query  {

    public static ArrayList json(String Response,ArrayList<News> news){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Response==null){
            return new ArrayList<>();
        }
        try {
            JSONObject object=new JSONObject(Response);
            JSONArray array=object.getJSONArray("articles");
            for (int i=0;i<10;i++){
                JSONObject object1=array.getJSONObject(i);
                news.add(new News(object1.getString("title"),object1.getString("description"),object1.getString("url")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }

}
