package com.atry.myapp.newsfeeds;

/**
 * Created by ymayu on 10/20/2016.
 */

public class News {
    private String mdescription;
    private String mtitle;
    private String murl;

    public News(String title, String description, String url){
        mdescription=description;
        mtitle=title;
        murl=url;
    }

    public String getdescription(){
        return mdescription;
    }
    public String gettitle(){
        return mtitle;
    }
    public String getsite(){
        return murl;
    }
}
