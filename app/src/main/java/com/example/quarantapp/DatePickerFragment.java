package com.example.quarantapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar todayCalendar = Calendar.getInstance();
        int year = todayCalendar.get(Calendar.YEAR);
        int month = todayCalendar.get(Calendar.MONTH);
        int day = todayCalendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(requireActivity(), (DatePickerDialog.OnDateSetListener) getActivity(),
                year, month, day);
    }
}
