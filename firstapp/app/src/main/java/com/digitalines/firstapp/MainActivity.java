package com.digitalines.firstapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity {

    private static String FIRST_PARAM = "firstparam";

    private static String SECOND_PARAM = "secondparam";

    private static String SECOND_APP_ID = "com.digitalines.secondapp";

    private static String BUNDLE_NAME = "params";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApp();
            }
        });
    }

    private void callApp() {
        String firstParam = ((EditText) findViewById(R.id.editText)).getText().toString();
        String secondParam = ((EditText) findViewById(R.id.editText2)).getText().toString();

        Bundle params = new Bundle();
        params.putString(FIRST_PARAM, firstParam);
        params.putString(SECOND_PARAM, secondParam);

        Intent launchIntent = getPackageManager()
                .getLaunchIntentForPackage(SECOND_APP_ID);

        if (launchIntent != null) {
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchIntent.putExtra(BUNDLE_NAME, params);
            startActivity(launchIntent);
        } else {
            launchIntent = new Intent(Intent.ACTION_VIEW);
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchIntent.setData(Uri.parse("market://details?id=" + SECOND_APP_ID));
            startActivity(launchIntent);
        }
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
