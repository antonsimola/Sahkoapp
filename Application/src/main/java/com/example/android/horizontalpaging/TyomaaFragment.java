package com.example.android.horizontalpaging;

// Lähde: http://developer.android.com/guide/topics/ui/layout/gridview.html

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by Samuli on 1.5.2015.
 */
public class TyomaaFragment extends Fragment {

    private OnTyomaaInteractionListener mainActivity;  // Viite MainActivityyn (tiedonsiirto).

    private TyomaaAdapter tyomaaAdapter;  // Muodostaa gridiin (GridView) kansioita.
    private ArrayList<Worksite> workSites;  // Kaikki tyomaat.

    public TyomaaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tyomaa, container, false);

        // Hakupainike työmaahaulle.
        Button searchButton = (Button)rootView.findViewById(R.id.tyomaa_button);

        // Etsitään matriisi (GridView) XML-tiedosta.
        GridView gridview = (GridView)rootView.findViewById(R.id.tyomaa_grid_view);

        tyomaaAdapter = new TyomaaAdapter(rootView.getContext(), workSites);

        // Asetataan matriisille (GridView) adapteri, jonka avulla syötetään matriisiin kuvat.
        gridview.setAdapter(tyomaaAdapter);


        // Tapahtumankuuntelija matriisille (kansion valitseminen).
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainActivity.onTyomaaGridSelected(position);
            }
        });

        // Tapahtumankuuntelija hakupainikkeelle (tehdään työmaahaku).
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchView searchText = (SearchView)rootView.findViewById(
                        R.id.tyomaa_search_view);
                mainActivity.onTyomaaSearch(searchText.getQuery().toString());

            }
        });

        return rootView;
    }

    /* Mahdollistetaan Fragmentin ja MainActivityn välinen tiedonsiirto, eli otetaan viite
     * MainActivityyn rajapintatoteutuksen avulla.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mainActivity = (OnTyomaaInteractionListener) activity;
        } catch (ClassCastException e) {
            // MainActivity ei toteuta rajapintaa.
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /* Puretaan Fragmentin ja MainActivityn välinen yhteys.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    /* Rajapinta, jonka MainActivity toteuttaa. Mahdollistaa Fragmentin ja MainActivityn välisen
     * tiedonsiirron (sopimus rajapinnan toteuttamisesta --> metodi, jota voidaan aina kutsua).
     */
    public interface OnTyomaaInteractionListener {
        public void onTyomaaGridSelected(int position);
        public void onTyomaaSearch(String search);
    }

    // Pitää kutsua heti olion luomisen jälkeen.
    public void setWorkSites(ArrayList<Worksite> workSites) {
        this.workSites = workSites;
    }
}
