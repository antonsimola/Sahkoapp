package com.example.android.horizontalpaging;

/**
 * Created by Anton on 2.4.2015.
 * Edited by jayy998
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2.4.2015.
 */
public class UutisetFragment extends Fragment  {
    View rootView;
    private List<News> news;
    private RecyclerView rv;
    ViewPager mViewPager;
    boolean click = true;
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
                            // TODO: PopupWindow with the content of the clicked card
                            // Currently only testing toasts :(
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
        news.add(new News("21.4 Kahvi loppu", "Johtajien kupeista kahviloppu. Sihteeri tuo kahvia kokoustilaan pikimmiten."));
        news.add(new News("20.4 Harrilla taas se aika kuusta.", "Johtajien kupeista kahviloppu. Sihteeri tuo kahvia kokoustilaan pikimmiten."));
        news.add(new News("19.4 Harrilla taas se aika kuusta.", "Informaatioluontoista asiaa kaikille. Olkaa varovaisia ollessanne Harrin seurassa."));
        news.add(new News("18.4 Rahat loppu. Ei palkkaa tässä kuussa.", "Palkan maksua jatketaan, kun laskutettavista töistä on saatu rahat."));
        news.add(new News("17.4 Miialla syntymäpäivät huomenna!", "Onnitelkaa sitä söpöä harjoittelijaa ja tulkaa kahvittelemaan pääkonttorille."));
        news.add(new News("16.4 Sähköappi buginen!", "Huomio huomio! Ne meidän koodari pingviinit eivät ole osanneet käyttää javaa... yllätys."));
        news.add(new News("15.4 Firman yhteinen piknikki.", "Lähdetään yhdessä piknikille Lauantaina. Tuokaa itse ruuat ja juomat."));
        news.add(new News("14.4 Sadevaatteet duunareille.", "Käykää nyt ihmeessä hakemassa sadevaatteet. (Omaan piikkiin tietenki)"));
        news.add(new News("13.4 Toimistotäti Maisa lopetti työt.", "Maisa lopetti työt, koska sai nigerialaiselta prinssiltä perintöä."));
        news.add(new News("12.4 Mulla tylsää!", "Tulkaa moikkaa minnuu toimistolle. xD t. miia xoxo"));
        news.add(new News("12.4 Tämä on uutinen!", "En keksinyt muuta..."));
    }
    private void initAdapter(){
        CustomAdapter adapter = new CustomAdapter(news);
        rv.setAdapter(adapter);
    }
    public void setViewPager(ViewPager vp) {
        mViewPager = vp;
    }
}