package com.mersocarlin.androidrest.config;

import android.app.Application;

import roboguice.RoboGuice;

public class AndroidRESTApplication extends Application {
    static {
        RoboGuice.setUseAnnotationDatabases(false);
    }
}
