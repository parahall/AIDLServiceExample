package com.academy.android.aidlserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class EmpireService extends Service {

    private IStarWarsImplementation service;

    @Override
    public void onCreate() {
        super.onCreate();
        service = new IStarWarsImplementation();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return service;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        this.service = null;
        super.onDestroy();
    }
}

