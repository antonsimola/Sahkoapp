package com.example.android.horizontalpaging;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AsetuksetFragment extends Fragment {


    public AsetuksetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> array_lang = new ArrayList<>();
        array_lang.add("suomi");
        array_lang.add("ruotsi");
        array_lang.add("englanti");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.simple_textview, array_lang);
        View rootView = inflater.inflate(R.layout.fragment_asetukset, container, false);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner_language);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((MainActivity)getActivity());
        return rootView;
    }


}
