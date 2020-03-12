package com.atry.myapp.newsfeeds;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Main_Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>>{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String web=" https://newsapi.org/v1/articles?";
    String apiKey="07538cbd9fa5434ebca1079779b27d4b";
    LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaderManager=getLoaderManager();
        loaderManager.initLoader(1, null,this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(this,SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateUi(ArrayList<News> news){
            mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new CustomArrayAdapter(news);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setNestedScrollingEnabled(false);
        }


    @Override
    public Loader<ArrayList<News>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(web);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String Source = sharedPrefs.getString(
                getString(R.string.settings_source_key),
                getString(R.string.settings_source_default));

        String order=sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));

        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("source", Source);
        uriBuilder.appendQueryParameter("sortBy", order);
        uriBuilder.appendQueryParameter("apiKey",apiKey);

        return new News_Loader(this,uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> newsArrayList) {
        updateUi(newsArrayList);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        updateUi(new ArrayList<News>());
    }

}


