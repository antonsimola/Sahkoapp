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
            // Muodostetaan piirustuksia --> halutaan piirusten määrä.
            return worksite.getDrawings().size();
        else if(elementType == 2)
            // Muodostetaan ohjeita --> halutaan ohjeiden määrä.
            return worksite.getInstructions().size();
        else
            return 0;  // Annettu väärä 'elementType'
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
                    // Muodostetaan piirustuksia.
                    imageView.setImageResource(worksite.getDrawings().get(position).getDrawingId());
                    textView.setText(worksite.getDrawings().get(position).getTitle());
                    break;
                case 2:
                    // Muodostetaan ohjeita.
                    imageView.setImageResource(worksite.getInstructions().get(position).getInstructionId());
                    textView.setText(worksite.getInstructions().get(position).getTitle());
                    break;
                default:
            }
        } else {
            view = convertView;
        }

        return view;
    }
}
