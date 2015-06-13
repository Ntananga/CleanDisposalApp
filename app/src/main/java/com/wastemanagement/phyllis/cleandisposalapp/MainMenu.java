package com.wastemanagement.phyllis.cleandisposalapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainMenu extends ActionBarActivity {

    /* Menue items declarations */
    String[] menuItems={
            "Private Company",
            "Public Authority",
            "Recycling Info"

    };

    String[] option_description={
            "Find a company around your area",
            "Know the council's schedules and collection points",
            "find recycling companies"
    };
    /*Defining the ListView*/

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

         /* call the custom adapter */

        Custom_Adapter adapter = new Custom_Adapter(this, menuItems, option_description);
        listView=(ListView) findViewById(R.id.list);

    /*setting the custom adapter to our list view*/
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedOption = menuItems[+position];
                switch (selectedOption.toString()) {
                    case "Private Company":
                        startActivity(new Intent(getApplicationContext(), PrivateServices.class));

                    case "Public Authority":
                        startActivity(new Intent(getApplicationContext(), PublicServices.class));
                    case "Recycling Info":
                        startActivity(new Intent(getApplicationContext(), RecycleActivity.class));

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
