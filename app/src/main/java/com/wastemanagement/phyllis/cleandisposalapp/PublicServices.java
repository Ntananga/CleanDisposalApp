package com.wastemanagement.phyllis.cleandisposalapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;


public class PublicServices extends ActionBarActivity {

    CalendarView calendarView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_services);
        calendarView = (CalendarView) findViewById(R.id.public_calendarView);
        textView = (TextView) findViewById(R.id.councilText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_public_services, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings){
            startActivity(new Intent(this, SettingsActivity.class));
        }
        else if (id == R.id.action_maps){
            startActivity(new Intent(this, MapsActivity.class));
        }
        else if (id == R.id.action_menu){
            startActivity(new Intent(this, MainMenu.class));
        }


        return super.onOptionsItemSelected(item);
    }
}
