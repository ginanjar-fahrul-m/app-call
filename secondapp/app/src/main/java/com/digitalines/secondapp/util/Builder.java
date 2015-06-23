package com.digitalines.secondapp.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ginanjar on 6/23/15.
 */
public class Builder {

    public static String FIRST_PARAM = "firstparam";

    public static String SECOND_PARAM = "secondparam";

    public static String BUNDLE_NAME = "params";

    private static Builder instance;

    private Builder() {

    }

    public static Builder getInstance() {
        if (instance == null) {
            instance = new Builder();
        }

        return instance;
    }

    public void buildMessage(Activity context, TextView textView) {
        Uri data = context.getIntent().getData();
        if (data != null) {
            showParams(data, textView);
        }

        Intent activityIntent = context.getIntent();
        if (activityIntent != null) {
            Bundle params = activityIntent.getBundleExtra(BUNDLE_NAME);
            if (params != null) {
                showParams(params, textView);
            }
        }
    }

    private void showParams(Uri data, TextView textView) {
        String scheme = data.getScheme();
        String host = data.getHost();

        if (!scheme.isEmpty() && !host.isEmpty()) {
            String firstParam = data.getQueryParameter(FIRST_PARAM);
            String secondParam = data.getQueryParameter(SECOND_PARAM);

            String output = "Data from " + scheme + "://" + host + "\n\n";
            if (firstParam != null && !firstParam.isEmpty()) {
                output += "First param = " + firstParam + "\n";
            }
            if (secondParam != null && !secondParam.isEmpty()) {
                output += "Second param = " + secondParam + "\n";
            }

            textView.setText(output);
        }
    }

    private void showParams(Bundle params, TextView textView) {
        String firstParam = params.getString(FIRST_PARAM);
        String secondParam = params.getString(SECOND_PARAM);

        String output = "Data from another app" + "\n\n";
        if (!firstParam.isEmpty()) {
            output += "First param = " + firstParam + "\n";
        }
        if (!secondParam.isEmpty()) {
            output += "Second param = " + secondParam + "\n";
        }

        textView.setText(output);
    }
}
