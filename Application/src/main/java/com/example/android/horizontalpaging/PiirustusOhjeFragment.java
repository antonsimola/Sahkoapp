package com.example.android.horizontalpaging;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Tekijä Samuli
 * Viimeisin muokkaus: 14.5.2015
 *
 * Tämä fragmentti näyttää käyttäjälle piirustuksen tai ohjeen (riippuu luonnin yhteydessä
 * annetusta oliosta, joka voi olla piirustus (Drawing) tai ohje (Instruction)).
 */
public class PiirustusOhjeFragment extends Fragment {
    // Fragmenttiluokasta oletusarvoisesti löytyvät muuttujat.
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnPiirustusOhjeInteractionListener mListener;

    // Fragmenttiluokasta oletusarvoisesti löytyvä alustaja.
    public static PiirustusOhjeFragment newInstance(String param1, String param2) {
        PiirustusOhjeFragment fragment = new PiirustusOhjeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PiirustusOhjeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Asetetaan fragmentin layout näkyviin.
        View view = inflater.inflate(R.layout.fragment_piirustus_ohje, container, false);

        // Muuttujat fragmentin kuva- ja tekstielementeille.
        ImageView imageView = (ImageView)view.findViewById(R.id.piirustus_ohje_image_view);
        TextView textView = (TextView)view.findViewById(R.id.ohje_text_view);

        Bundle bundle = getArguments();  // Sisältää luonnin yhteydessä annetun olion.

        if(bundle.containsKey(PiirustuksetOhjeetActivity.DRAWING_MESSAGE)) {
            // Fragmentin luonnin yhteydessä sille on annettu piirustus.
            Drawing drawing = (Drawing)bundle.get(PiirustuksetOhjeetActivity.DRAWING_MESSAGE);
            imageView.setImageResource(drawing.getActualDrawingID());
            imageView.setMinimumHeight(500);
            imageView.setMinimumWidth(500);
            textView.setVisibility(View.INVISIBLE);
        }
        else if(bundle.containsKey(PiirustuksetOhjeetActivity.INSTRUCTION_MESSAGE)) {
            // Fragmentin luonnin yhteydessä sille on annettu ohje.
            Instruction instruction = (Instruction)bundle.get(PiirustuksetOhjeetActivity.INSTRUCTION_MESSAGE);
            textView.setText(instruction.getText());
            imageView.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnPiirustusOhjeInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Ei varsinaista käyttöä.
     */
    public interface OnPiirustusOhjeInteractionListener {
        // TODO: Update argument type and name
        public void onOhjeInteraction();
        public void onPiirustusInteraction();
    }
}
