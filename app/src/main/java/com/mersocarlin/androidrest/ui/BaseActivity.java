package com.mersocarlin.androidrest.ui;

import android.os.Bundle;

import com.mersocarlin.androidrest.network.service.BaseService;
import com.octo.android.robospice.SpiceManager;

import roboguice.activity.RoboActivity;

public class BaseActivity extends RoboActivity {

    private SpiceManager manager = new SpiceManager(BaseService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        this.manager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        this.manager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getManager() {
        return this.manager;
    }
}
