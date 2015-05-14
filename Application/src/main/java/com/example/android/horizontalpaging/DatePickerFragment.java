package com.example.android.horizontalpaging;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Anton on 17.4.2015.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    boolean aloitus = true;
    public DatePickerFragment() {

    }
    public void setAloitus(boolean bool) {
        // Onko tämä datepicker tarkoitettu aloitus-vai lopetusajankohdan valitsemiseen
        //Muista kutsua ennen dialogin esittämistä!
        aloitus = bool;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // datepickerin alkuarvoksi tämä pvm
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Sisältää sen tekstviewin, kumpaa halutaan päivittää
        TextView text;
        if (aloitus) {
            text =(TextView) getActivity().findViewById(R.id.text_start);
        } else {
            text =(TextView) getActivity().findViewById(R.id.text_finish);
        }
        // lyhenettään vuosi muotoon x.x.2015 -> x.x.15
        String strYear = Integer.toString(year);
        strYear = strYear.substring(strYear.length() -2);
        text.setText(day+"." + month +"."+ strYear);
        //Näytetään pvm:n jälkeen aika-dialogi
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setAloitus(aloitus);
        newFragment.show(getFragmentManager(),"timePicker");
    }
}