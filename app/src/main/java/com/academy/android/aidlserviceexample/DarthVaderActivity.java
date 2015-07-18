package com.academy.android.aidlserviceexample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class DarthVaderActivity extends Activity implements View.OnClickListener,
        ServiceConnection {

    public static String TAG = DarthVaderActivity.class.getSimpleName();

    private Handler mHandler = new Handler();

    private IStarWars service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.darth_vader_activity);
        findViewById(R.id.iv_dva_build_death_star).setOnClickListener(this);
        findViewById(R.id.iv_dva_interact_with_luke).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!super.bindService(new Intent(this, EmpireService.class), this, BIND_AUTO_CREATE)) {
            Log.w(TAG, "Failed to bind to service");
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.service = IStarWars.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        this.service = null;
    }



    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.iv_dva_interact_with_luke:
                    service.findLuke(new IEmpireServiceResponseListener.Stub() {
                        @Override
                        public void onResponse(final String response) throws RemoteException {
                            //Other process. We should run on UI Thread in order to interact with UI
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(DarthVaderActivity.this, response,
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            });
                        }
                    });
                    break;
                case R.id.iv_dva_build_death_star:
                    String buildDeathStar = service.buildDeathStar(new DeathStar());
                    Toast.makeText(this, buildDeathStar, Toast.LENGTH_LONG).show();
                    break;

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
