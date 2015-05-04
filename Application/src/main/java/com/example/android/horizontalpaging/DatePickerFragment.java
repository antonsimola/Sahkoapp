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
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView text;
        if (aloitus) {
            text =(TextView) getActivity().findViewById(R.id.text_start);
        } else {
            text =(TextView) getActivity().findViewById(R.id.text_finish);
        }

        text.setText(day+"." + month +"."+ year);
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setAloitus(aloitus);
        newFragment.show(getFragmentManager(),"timePicker");
    }
}