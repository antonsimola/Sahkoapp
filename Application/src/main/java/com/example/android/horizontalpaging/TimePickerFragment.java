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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textAloitus =(TextView) getActivity().findViewById(R.id.text_start);
        if (minute < 10) {
            textAloitus.setText(textAloitus.getText() + " klo " + hourOfDay + ":0" + minute);
        } else{
            textAloitus.setText(textAloitus.getText() + " " + hourOfDay + ":" + minute);
        }
    }
}
