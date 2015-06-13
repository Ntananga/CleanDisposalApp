package com.wastemanagement.phyllis.cleandisposalapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    //Progress Dialog
    private ProgressDialog progressDialog;

    //declaring the different fields
    EditText editName, editEmail, editLocation;
    EditText editPhone;
    Button submit;

    // url to create new account
    static String url = "http://cleandisposal.agandi.ug/create_account.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editLocation = (EditText) findViewById(R.id.editLocation);
        editPhone = (EditText) findViewById(R.id.editPhone);
        submit = (Button) findViewById(R.id.submit_button);

        //button click
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creates a background activity
                new createAccount().execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private class createAccount extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         * */

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Creating Account...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();

        }
        /**
         * Creating account
         * */

        @Override
        protected String doInBackground(String... args) {
            String name = editName.getText().toString();
            String email = editEmail.getText().toString();
            String location = editLocation.getText().toString();
            String phone = editPhone.getText().toString();

            //Building the parameters
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("userName", name));
            parameters.add(new BasicNameValuePair("email", email));
            parameters.add(new BasicNameValuePair("location", location));
            parameters.add(new BasicNameValuePair("phone_number", phone));


            // getting JSON Object
            // Note that create account url accepts POST method
            JSonPaser jSonPaser = new JSonPaser();
            JSONObject object =  jSonPaser.makeResquest(url,"POST",parameters);

            // check log cat for response
            //  Log.d("Create Response", object.toString());

            try {
                int success = object.getInt(TAG_SUCCESS);

                if ( success==1 ){
                    //Account created successfully
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                    // closing this screen
                    finish();
                }

                else {
                    //failed to create account
                    Toast.makeText(getApplicationContext(), "Account creation failed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            progressDialog.dismiss();
        }
    }
}
