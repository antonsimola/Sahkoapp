package com.example.android.horizontalpaging;

// Lähde: http://stackoverflow.com/questions/6186753/android-how-to-put-the-text-below-the-image-in-grid-view

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samuli on 2.5.2015.
 */
public class TyomaaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Worksite> worksites;

    public TyomaaAdapter(Context c, ArrayList<Worksite> workSites) {

        context = c;
        this.worksites = workSites;
    }

    public int getCount() {
        return worksites.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            // 'file_image' toimii gridin (GridView) yhtenä soluna.
            view = inflater.inflate(R.layout.file_image, null);

            // Haetaan kansion kuva ja asetetaan se gridin yhteen soluun.
            ImageView imageView = (ImageView)view.findViewById(R.id.file_image);
            imageView.setImageResource(worksites.get(position).getFileImage());  // Oli suora resurssiviite.

            // Haetaan kansion selite ja asetetaan se gridin yhteen soluun.
            TextView textView = (TextView)view.findViewById(R.id.file_text);
            textView.setText(worksites.get(position).getName());
        }
        else {
            view = convertView;
        }

        return view;
    }
}
