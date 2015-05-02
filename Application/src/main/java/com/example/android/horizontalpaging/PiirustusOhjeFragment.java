package com.example.android.horizontalpaging;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PiirustusOhjeFragment.OnPiirustusOhjeInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PiirustusOhjeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PiirustusOhjeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnPiirustusOhjeInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PiirustusOhjeFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_piirustus_ohje, container, false);

        ImageView imageView = (ImageView)view.findViewById(R.id.piirustus_ohje_image_view);
        TextView textView = (TextView)view.findViewById(R.id.ohje_text_view);

        Bundle bundle = getArguments();

        if(bundle.containsKey(PiirustuksetOhjeetActivity.DRAWING_MESSAGE)) {
            Drawing drawing = (Drawing)bundle.get(PiirustuksetOhjeetActivity.DRAWING_MESSAGE);
            imageView.setImageResource(drawing.getDrawingId());
            textView.setVisibility(View.INVISIBLE);
        }
        else if(bundle.containsKey(PiirustuksetOhjeetActivity.INSTRUCTION_MESSAGE)) {
            Instruction instruction = (Instruction)bundle.get(PiirustuksetOhjeetActivity.INSTRUCTION_MESSAGE);
            textView.setText(instruction.getText());
            imageView.setVisibility(View.INVISIBLE);
        }
        /*
        // Asetetaan piirustuksen/ohjeen kuva ja sisältö.
        ImageView imageView = (ImageView)view.findViewById(R.id.piirustus_ohje_image_view);
        imageView.setImageResource(R.drawable.file_512x512);
        */

        return view;
    }
/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
*/
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
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnPiirustusOhjeInteractionListener {
        // TODO: Update argument type and name
        public void onOhjeInteraction();
        public void onPiirustusInteraction();
    }
}
