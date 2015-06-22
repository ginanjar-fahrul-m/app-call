package com.digitalines.secondapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

    private static String FIRST_PARAM = "firstparam";

    private static String SECOND_PARAM = "secondparam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri data = getIntent().getData();
        if (data != null) {
            showParams(data);
        }

        Intent activityIntent = getIntent();
        if (activityIntent != null) {
            Bundle params = activityIntent.getExtras();
            if (params != null) {
                showParams(params);
            }
        }
    }

    private void showParams(Uri data) {
        String scheme = data.getScheme();
        String host = data.getHost();

        if (!scheme.isEmpty() && !host.isEmpty()) {
            String firstParam = data.getQueryParameter(FIRST_PARAM);
            String secondParam = data.getQueryParameter(SECOND_PARAM);

            String output = "Data from " + scheme + ":" + host + "/n";
            if (!firstParam.isEmpty()) {
                output += "First Param = " + firstParam + "/n";
            }
            if (!secondParam.isEmpty()) {
                output += "Second Param = " + secondParam + "/n";
            }

            ((TextView) findViewById(R.id.textView)).setText(output);
        }
    }

    private void showParams(Bundle params) {
        String firstParam = params.getString(FIRST_PARAM);
        String secondParam = params.getString(SECOND_PARAM);

        String output = "Data from another app" + "/n";
        if (!firstParam.isEmpty()) {
            output += "First Param = " + firstParam + "/n";
        }
        if (!secondParam.isEmpty()) {
            output += "Second Param = " + secondParam + "/n";
        }

        ((TextView) findViewById(R.id.textView)).setText(output);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
