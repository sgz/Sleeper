package com.apps.serghei.sleeper;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by serghei on 7/25/2016.
 */
public class TimePickerFragment extends DialogFragment {

    private int hour;
    private int minute;

    private TimePickerDialog.OnTimeSetListener mListener;

    public TimePickerFragment(){}

    public TimePickerFragment(int h, int m, TimePickerDialog.OnTimeSetListener listener) {
        hour = h;
        minute = m;
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), mListener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
}
