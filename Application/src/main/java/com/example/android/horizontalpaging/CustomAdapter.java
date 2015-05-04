package com.example.android.horizontalpaging;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jayy998 on 23.4.2015.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.NewsViewHolder>{
    List<News> news;
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView row1;
        TextView row2;

        NewsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            row1 = (TextView)itemView.findViewById(R.id.row1);
            row2 = (TextView)itemView.findViewById(R.id.row2);
        }
    }
    CustomAdapter(List<News> news){
        this.news = news;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        NewsViewHolder nvh = new NewsViewHolder(v);
        return nvh;
    }
    @Override
    public void onBindViewHolder(NewsViewHolder personViewHolder, int i) {
        personViewHolder.row1.setText(news.get(i).row1);
        personViewHolder.row2.setText(news.get(i).row2);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemCount() {
        return news.size();
    }
}
