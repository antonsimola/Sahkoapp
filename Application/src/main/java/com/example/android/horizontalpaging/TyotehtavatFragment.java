package com.example.android.horizontalpaging;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
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
        ExpandableListView elv = (ExpandableListView) rootView.findViewById(R.id.list_tyotehtavat);
        ExpListAdapter adapter = new ExpListAdapter(luoData());
        adapter.setInflater(inflater,this);
        elv.setAdapter(adapter);
        return rootView;
    }

    private ArrayList<TyotehtavaOtsikko> luoData(){
        ArrayList<TyotehtavaOtsikko> data = new ArrayList<>();
        TyotehtavaOtsikko otsikko;
        for (int i = 10;i > 1;i--){
            otsikko = new TyotehtavaOtsikko();
            otsikko.setPvm(i+".5.15");
            otsikko.setTyomaa("LUT");
            data.add(otsikko);
        }
        TyotehtavaSisalto sisalto = new TyotehtavaSisalto();
        sisalto.setAloitus("1.5.15");
        sisalto.setDeadline("5.5.15");
        sisalto.setTehtava("Ledien asennus");
        sisalto.setYhteyshenkilo("Makkonen");
        sisalto.setMuuta("");
        sisalto.setOsoite("LUT");
        sisalto.setOhjPiirLinkki("LINKKI");

        for (TyotehtavaOtsikko alkio:data){
            alkio.setSisalto(sisalto);
        }

        return data;
    }
}