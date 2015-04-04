package com.example.android.horizontalpaging;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Anton on 2.4.2015.
 */
public class TyotehtavatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tyotehtavat, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.tyotehtavat);
        ArrayList<String> kohteet = new ArrayList<>();
        kohteet.add("Lappeenranta University Of Technology");
        kohteet.add("Lappeenranta University Of Technology2");
        kohteet.add("Lappeenranta University Of Technology3");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                kohteet);
        return rootView;
    }
}