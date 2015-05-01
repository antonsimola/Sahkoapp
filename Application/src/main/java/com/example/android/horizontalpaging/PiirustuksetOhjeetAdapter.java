package com.example.android.horizontalpaging;
// Lähde: http://stackoverflow.com/questions/6186753/android-how-to-put-the-text-below-the-image-in-grid-view
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samuli on 1.5.2015.
 */
public class PiirustuksetOhjeetAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Worksite> worksites;

    public PiirustuksetOhjeetAdapter(Context c, ArrayList<Worksite> workSites) {

        context = c;
        this.worksites = workSites;
    }

    public int getCount() {
        return filePictures.length;
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
            view = inflater.inflate(R.layout.file_image, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.file_image);
            imageView.setImageResource(filePictures[position]);

            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            TextView textView = (TextView)view.findViewById(R.id.file_text);
            textView.setText(fileTexts[position]);

            /*
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
            */
        }
        else {
            view = convertView;
        }

        return view;
    }

    // Kansioiden kuvat Piirustukset ja Ohjeet -osioon.
    private Integer[] filePictures = {
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512,
            R.drawable.file_512x512
    };

    private String[] fileTexts = {"kuva1", "kuva2","kuva3","kuva4","kuva5","kuva6","kuva7","kuva8",
            "kuva9","kuva10","kuva11","kuva12","kuva13","kuva14","kuva15"};
}
