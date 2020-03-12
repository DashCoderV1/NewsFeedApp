package com.atry.myapp.newsfeeds;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by ymayu on 10/25/2016.
 */

public class News_Loader extends AsyncTaskLoader<ArrayList<News>>{

    String url1;
    String Response;
    public News_Loader(Context context, String url) {
        super(context);
        url1=url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        Net_News Net=new Net_News();
        Response=Net.netEarth(url1);
        ArrayList arrayList =new ArrayList();
        Query.json(Response,arrayList);

        return arrayList;
    }

    @Override
    public void reset() {
        super.reset();
    }

}
