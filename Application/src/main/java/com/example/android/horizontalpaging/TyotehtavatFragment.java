package com.example.android.horizontalpaging;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anton on 2.4.2015.
 */
public class TyotehtavatFragment extends Fragment implements AdapterView.OnItemClickListener {
    ArrayList<String> employees;
    ListPopupWindow lpw;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        employees = new ArrayList<>();
        employees.add("Mauri");
        employees.add("Pekka");
        employees.add("Reiska");
        final View rootView = inflater.inflate(R.layout.fragment_tyotehtavat, container, false);
        ExpandableListView elv = (ExpandableListView) rootView.findViewById(R.id.list_tyotehtavat);
        ExpListAdapter adapter = new ExpListAdapter(luoData());
        MainActivity act = (MainActivity) getActivity();
        adapter.setActivity(act);
        adapter.setInflater(inflater, this);

        elv.setAdapter(adapter);
        // http://www.informit.com/articles/article.aspx?p=2078060&seqNum=4
        Button btn = (Button) rootView.findViewById(R.id.btn_addemployee);
        lpw = new ListPopupWindow(getActivity());
        lpw.setAdapter(new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                employees));
        lpw.setAnchorView(btn);
        lpw.setWidth(300);
        lpw.setHeight(400);
        lpw.setModal(true);
        lpw.setOnItemClickListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lpw.show();
            }
        });

        /* (Samuli lisäsi tämän 14.5.2015)
         * Tapahtumankuuntelija kuuntelee fragmentin painalluksia (käyttäjä hipaisee fragmentin
         * sisältöä), ja painalluksen tapahtuessa piilotetaan näppäimistö.
         */
        // Fragmentin painallusten kuunteleminen:
        // http://stackoverflow.com/questions/21882251/how-to-handle-touch-events-on-a-fragment
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Näppäimistön piilottaminen:
                // http://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
                InputMethodManager manager = (InputMethodManager)rootView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(rootView.getWindowToken(), 0);

                return true;
            }
        });

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
        sisalto.setOhjPiirLinkki("Liittyvät piirustukset ja ohjeet");

        for (TyotehtavaOtsikko alkio:data){
            alkio.setSisalto(sisalto);
        }

        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        TextView text = (TextView) getActivity().findViewById(R.id.tv_employees);
        text.setText(text.getText()+ "\n+ " + employees.get(position));
        lpw.dismiss();
    }
}