package com.pm.elina.petmemo;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class CalendarActivity extends Activity {
    Button btnInsert;
    Button editbut;
    Button disp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = (Button) findViewById(R.id.createEvent);
        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                insert();
            }
        });

        Button editbut = (Button) findViewById(R.id.editbut);
        editbut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                editcalendar();
            }
        });
        Button disp = (Button) findViewById(R.id.dispbut);
        disp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                disp();
            }
        });

    }
    public void disp() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(2018, 6, 30, 11, 35);
        Uri uri = Uri.parse("content://com.android.calendar/time/"
                + String.valueOf(startTime.getTimeInMillis()));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        // Use the Calendar app to view the time.
        startActivity(intent);
    }

    @SuppressLint("NewApi")
    public void insert() {
        Intent intent = new Intent(Intent.ACTION_INSERT,
                CalendarContract.Events.CONTENT_URI);
        // Add the calendar event details
        intent.putExtra(CalendarContract.Events.TITLE, "Launch!");
        intent.putExtra(CalendarContract.Events.DESCRIPTION,
                "What?");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,
                "Where?");
        Calendar startTime = Calendar.getInstance();
        startTime.set(2018, 6, 30);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                startTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        // Use the Calendar app to add the new event.
        startActivity(intent);
    }
    @SuppressLint("NewApi")
    public void editcalendar() {
        long rowID = 760;
        Uri uri = ContentUris.withAppendedId(
                CalendarContract.Events.CONTENT_URI, rowID);
        Intent intent = new Intent(Intent.ACTION_EDIT, uri);
        // Modify the calendar event details
        Calendar startTime = Calendar.getInstance();
        startTime.set(2018, 6, 30, 0, 30);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                startTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        // Use the Calendar application to edit the event.
        startActivity(intent);
        Toast.makeText(this, "Editing done", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}