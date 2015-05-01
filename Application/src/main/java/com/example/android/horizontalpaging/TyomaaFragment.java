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

    private OnPiirustuksetOhjeetInteractionListener mainActivity;
    private PiirustuksetOhjeetAdapter piirustuksetOhjeetAdapter;
    private ArrayList<Worksite> workSites;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    public TyomaaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tyomaa, container, false);

        // Hakupainike haun toteuttamiseksi.
        Button searchButton = (Button)rootView.findViewById(R.id.tyomaa_button);

        // Etsitään matriisi (GridView) XML-tiedosta.
        GridView gridview = (GridView)rootView.findViewById(R.id.tyomaa_grid_view);

        piirustuksetOhjeetAdapter = new PiirustuksetOhjeetAdapter(rootView.getContext(), workSites);
        // Asetataan matriisille (GridView) adapteri, jonka avulla syötetään matriisiin kuvat.
        gridview.setAdapter(piirustuksetOhjeetAdapter);


        // Tapahtumankuuntelija matriisille (alkion valitseminen).
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainActivity.onPiirustuksetOhjeetGridSelected(position);
            }
        });


        // Tapahtumankuuntelija hakupainikkeelle (tehdään työmaahaku).
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchView searchText = (SearchView)rootView.findViewById(
                        R.id.tyomaa_search_view);
                mainActivity.onPiirustuksetOhjeetSearch(searchText.getQuery().toString());

            }
        });

        return rootView;
    }

    /* Mahdollistetaan Fragmentin ja MainActivityn välinen tiedonsiirto, eli otetaan viite
     * MainActivityyn.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mainActivity = (OnPiirustuksetOhjeetInteractionListener) activity;
        } catch (ClassCastException e) {
            // MainActivity ei toteuta rajapintaa.
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /* Puretaan Fragmentin ja MainActivityn välinen yhteys (referenssi).
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }


    /* Tiedonvälitys --> MainActivity.
     *
    public void onButtonPressed(int position) {
        if (mainActivity != null) {
            mainActivity.onPiirustuksetOhjeetInteraction(position);
        }
    }
*/

    /* Rajapinta, jonka MainActivity toteuttaa. Mahdollistaa Fragmentin ja MainActivityn välisen
     * tiedonsiirron (sopimus rajapinnan toteuttamisesta --> metodi, jota voidaan aina kutsua).
     */
    public interface OnPiirustuksetOhjeetInteractionListener {
        public void onPiirustuksetOhjeetGridSelected(int position);
        public void onPiirustuksetOhjeetSearch(String search);
    }

    // Pitää kutsua heti luomisen jälkeen.
    public void setWorkSites(ArrayList<Worksite> workSites) {
        this.workSites = workSites;
    }
}
