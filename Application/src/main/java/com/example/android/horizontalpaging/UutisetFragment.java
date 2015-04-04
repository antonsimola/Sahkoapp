package com.example.android.horizontalpaging;

/**
 * Created by Anton on 2.4.2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Anton on 2.4.2015.
 */
public class UutisetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_uutiset, container, false);

        return rootView;
    }
}