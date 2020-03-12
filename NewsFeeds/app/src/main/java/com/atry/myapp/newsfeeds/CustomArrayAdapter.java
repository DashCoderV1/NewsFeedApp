package com.atry.myapp.newsfeeds;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by ymayu on 10/20/2016.
 */

public class CustomArrayAdapter extends RecyclerView.Adapter<CustomArrayAdapter.ViewHolder> {
    private ArrayList<News> mDataset;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomArrayAdapter(ArrayList myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        News news = mDataset.get(position);
        holder.textView.setText(news.gettitle());
        holder.description.setText(news.getdescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News site=mDataset.get(position);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(site.getsite()));
                view.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView description;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.title);
            textView.setTextSize(15);
            description=(TextView)view.findViewById(R.id.description);
            cardView=(CardView)view.findViewById(R.id.cardview);
        }
    }
}

