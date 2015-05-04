package com.example.android.horizontalpaging;

// Lähde: http://stackoverflow.com/questions/6186753/android-how-to-put-the-text-below-the-image-in-grid-view

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Samuli on 2.5.2015.
 */
public class PiirustuksetOhjeetAdapter extends BaseAdapter {
    private Context context;
    private Worksite worksite;
    private int elementType;

    public PiirustuksetOhjeetAdapter(Context c, Worksite worksite, int elementType) {

        context = c;
        this.worksite = worksite;
        this.elementType = elementType;
    }

    public int getCount() {
        if(elementType == 1)
            return worksite.getDrawings().size();
        else if(elementType == 2)
            return worksite.getInstructions().size();
        else
            return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.drawing_instruction_image, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.drawing_instruction_image);
            TextView textView = (TextView)view.findViewById(R.id.drawing_instruction_text);

            switch(elementType) {
                case 1:
                    imageView.setImageResource(worksite.getDrawings().get(position).getDrawingId());
                    textView.setText(worksite.getDrawings().get(position).getTitle());
                    break;
                case 2:
                    imageView.setImageResource(worksite.getInstructions().get(position).getInstructionId());
                    textView.setText(worksite.getInstructions().get(position).getTitle());
                    break;
                default:
            }

            /*
            Worksite worksite = worksites.get(position);
            Drawing drawing = worksite.getDrawings().get(position);


            ImageView imageView = (ImageView) view.findViewById(R.id.file_image);
            imageView.setImageResource(worksites.get(position).getFileImage());  // Oli suora resurssiviite.

            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            TextView textView = (TextView) view.findViewById(R.id.file_text);
            textView.setText(worksites.get(position).getName());
            */
            /*
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
            */
        } else {
            view = convertView;
        }

        return view;
    }
}
