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
    private ArrayList<Worksite> worksites;  // Kaikki tyomaat.
    private WorksiteControl worksiteControl;
    private GridView gridView;

    public TyomaaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tyomaa, container, false);

        // Kaikki työmaat kontrolliluokan oliolta.
        worksites = worksiteControl.getWorksites();

        // Hakupainike työmaahaulle.
        Button searchButton = (Button)rootView.findViewById(R.id.tyomaa_search_button);
        Button resetSearchButton = (Button)rootView.findViewById(R.id.tyomaa_reset_search_button);

        // Etsitään matriisi (GridView) XML-tiedosta.
        gridView = (GridView)rootView.findViewById(R.id.tyomaa_grid_view);

        tyomaaAdapter = new TyomaaAdapter(rootView.getContext(), worksites);

        // Asetataan matriisille (GridView) adapteri, jonka avulla syötetään matriisiin kuvat.
        gridView.setAdapter(tyomaaAdapter);


        // Tapahtumankuuntelija matriisille (kansion valitseminen).
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                performWorksiteSearch(searchText.getQuery().toString(), rootView);

            }
        });

        // Tapahtumankuuntelija haun peruuttamiselle (kaikki kansiot näkyviin).
        resetSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tyhjennetään hakukenttä.
                SearchView searchText = (SearchView)rootView.findViewById(
                        R.id.tyomaa_search_view);
                searchText.setQuery("", true);

                // Alustetaan adapteri uudestaan.
                tyomaaAdapter = new TyomaaAdapter(rootView.getContext(), worksites);
                gridView.setAdapter(tyomaaAdapter);

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

    private void performWorksiteSearch(String name, View view) {
        ArrayList<Worksite> foundWorksites = worksiteControl.findWorksites(name);

        if(!foundWorksites.isEmpty()) {
            // Läydettiin hakua vastaavia työmaita. Asetetaan uusi XML-sisältö.
            tyomaaAdapter = new TyomaaAdapter(view.getContext(),foundWorksites);
            gridView.setAdapter(tyomaaAdapter);
        }

        /*
        for(Worksite worksite : foundWorksites) {
            System.out.println(worksite.getName());
        }
        */
    }

    // Pitää kutsua heti olion luomisen jälkeen.
    public void setWorksiteControl(WorksiteControl worksiteControl) {
        this.worksiteControl = worksiteControl;
    }

    /* Rajapinta, jonka MainActivity toteuttaa. Mahdollistaa Fragmentin ja MainActivityn välisen
     * tiedonsiirron (sopimus rajapinnan toteuttamisesta --> metodi, jota voidaan aina kutsua).
     */
    public interface OnTyomaaInteractionListener {
        public void onTyomaaGridSelected(int position);
    }
}
