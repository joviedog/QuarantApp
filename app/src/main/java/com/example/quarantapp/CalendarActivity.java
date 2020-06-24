package com.example.quarantapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener
    , DatePickerDialog.OnDateSetListener {
    private TextView mLabelFecha, mLabelHora;
    Button mFecha;
    Button mSetHora;
    Button mCancelarAlarma;
    private Button mButtonCreateReminder;
    private int hora, minuto, ano, mes, dia;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        // Contenido del layout
        mFecha = findViewById(R.id.bnSeleccionarDia);
        mSetHora = findViewById(R.id.btnSeleccionarHora);
        mLabelFecha = findViewById(R.id.FechaUsuario);
        mLabelHora = findViewById(R.id.HoraUsuario);
        mButtonCreateReminder = findViewById(R.id.buttonCrearReminder);
        mCancelarAlarma = findViewById(R.id.cancelarAlarma);

        mSetHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker = new TimePickerFragment();
                timepicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        mFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        mButtonCreateReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hora);
                calendar.set(Calendar.MINUTE, minuto);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.DAY_OF_MONTH, dia);
                calendar.set(Calendar.MONTH, mes);
                calendar.set(Calendar.YEAR, ano);
                updateTimeText();
                startAlarm(calendar);
            }
        });

        mCancelarAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }
    private void updateTimeText(){
        Toast.makeText(CalendarActivity.this, "Alarma Programada", Toast.LENGTH_LONG).show();
    }
    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

    }
    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarma Cancelada", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mLabelHora.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
        hora = hourOfDay;
        minuto = minute;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mLabelFecha.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month)+ "/"+ String.valueOf(year));
        ano = year;
        mes = month;
        dia = dayOfMonth;

    }
}
