package com.example.android.horizontalpaging;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Anton on 17.4.2015.
 * http://developer.android.com/guide/topics/ui/controls/pickers.html
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    boolean aloitus = true;
    public void setAloitus(boolean bool) {
        // Onko tämä datepicker tarkoitettu aloitus-vai lopetusajankohdan valitsemiseen
        //Muista kutsua ennen dialogin esittämistä!
        aloitus = bool;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Tämänhetkinen aika alkuarvoksi
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView text;
        if (aloitus) {
            text =(TextView) getActivity().findViewById(R.id.text_start);
        } else {
            text =(TextView) getActivity().findViewById(R.id.text_finish);
        }
        if (minute < 10) {
            text.setText(text.getText() + " klo " + hourOfDay + ":0" + minute);
        } else{
            text.setText(text.getText() + " " + hourOfDay + ":" + minute);
        }
    }
}
