package com.example.android.horizontalpaging;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2.4.2015.
 * Edited by jayy998
 */
public class UutisetFragment extends Fragment  {
    View rootView;
    private List<News> news;
    private RecyclerView rv;
    ViewPager mViewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_uutiset, container, false);
        rv = (RecyclerView)rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2));
        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(rootView.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        if (position == 0)
                        {
                            Log.d("OnClick", "Priority");
                            mViewPager.setCurrentItem(0);
                        }
                        else
                        {
                            // Debug with Toasts
                            String txt = "CardID: " + position;
                            Toast toast = Toast.makeText(rootView.getContext(), txt, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                })
        );
        initData();
        initAdapter();
        return rootView;
    }
    private void initData()
    {
        news = new ArrayList<>();
        news.add(new News("Ei uusia työtehtäviä", "Mene työlistaan."));
        news.add(new News("7.5 Kahvi loppu", "Johtajien kupeista kahviloppu. Sihteeri tuo kahvia kokoustilaan pikimmiten."));
        news.add(new News("7.5 Harrilla taas se aika kuusta.", "Johtajien kupeista kahviloppu. Sihteeri tuo kahvia kokoustilaan pikimmiten."));
        news.add(new News("6.5 Harrilla taas se aika kuusta.", "Informaatioluontoista asiaa kaikille. Olkaa varovaisia ollessanne Harrin seurassa."));
        news.add(new News("6.5 Rahat loppu. Ei palkkaa tässä kuussa.", "Palkan maksua jatketaan, kun laskutettavista töistä on saatu rahat."));
        news.add(new News("5.5 Miialla syntymäpäivät huomenna!", "Onnitelkaa sitä söpöä harjoittelijaa ja tulkaa kahvittelemaan pääkonttorille."));
        news.add(new News("5.5 Sähköappi buginen!", "Huomio huomio! Ne meidän koodari pingviinit eivät ole osanneet käyttää javaa... yllätys."));
        news.add(new News("4.5 Firman yhteinen piknikki.", "Lähdetään yhdessä piknikille Lauantaina. Tuokaa itse ruuat ja juomat."));
        news.add(new News("1.5 Sadevaatteet duunareille.", "Käykää nyt ihmeessä hakemassa sadevaatteet. (Omaan piikkiin tietenki)"));
        news.add(new News("1.5 Toimistotäti Maisa lopetti työt.", "Maisa lopetti työt, koska sai nigerialaiselta prinssiltä perintöä."));
        news.add(new News("30.4 Mulla tylsää!", "Tulkaa moikkaa minnuu toimistolle. xD t. miia xoxo"));
        news.add(new News("30.4 Tallinnaan", "Viime hetken vapautumisia viikonloppureissulle Tallinnaan. Ottakaa yhteyttä Miian."));
        news.add(new News("29.4 Maisa & Miia", "Me ollaan Maisa ja Miia. Me ollaan teidän ihanat toimistotädit ;)"));
        news.add(new News("28.4 Ensimmäinen uuutinen!", "Testiuutinen lorem ipsum etc"));
    }
    private void initAdapter()
    {
        CustomAdapter adapter = new CustomAdapter(news);
        rv.setAdapter(adapter);
    }
    public void setViewPager(ViewPager vp) {
        mViewPager = vp;
    }
}