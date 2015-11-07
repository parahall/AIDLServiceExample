package com.academy.android.aidlserviceexample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class DarthVaderActivity extends Activity implements View.OnClickListener {

    private static final String TAG = DarthVaderActivity.class.getSimpleName();

    private static long mStartedCommandTime;

    private EmpireServiceReceiver mEmpireServiceReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.darth_vader_activity);
        findViewById(R.id.iv_dva_build_death_star).setOnClickListener(this);
        findViewById(R.id.iv_dva_interact_with_luke).setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mEmpireServiceReceiver = new EmpireServiceReceiver();
        IntentFilter intentFilter = new IntentFilter(
                EmpireService.EMPIRE_SERVICE_ACTION);
        registerReceiver(mEmpireServiceReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mEmpireServiceReceiver);
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, EmpireService.class);
        switch (v.getId()) {
            case R.id.iv_dva_build_death_star:
                intent.putExtra(EmpireService.COMMAND_TYPE_KEY,
                        EmpireService.EmpireServiceCommands.BUILD_DEATH_STAR);
                break;
            case R.id.iv_dva_interact_with_luke:
                intent.putExtra(EmpireService.COMMAND_TYPE_KEY,
                        EmpireService.EmpireServiceCommands.FIND_LUKE);
                break;
        }
        Log.d(TAG, "Started at: "+System.currentTimeMillis());
        startService(intent);
    }

    public class EmpireServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Arrived from service: "+System.currentTimeMillis());
            Toast.makeText(DarthVaderActivity.this, "Death Star deployed and ready for your command, my lord",
                    Toast.LENGTH_LONG).show();
        }
    }
}
