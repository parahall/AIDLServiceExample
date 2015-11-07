package com.academy.android.aidlserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class EmpireService extends Service {


    public static final int CALLBACK_MSG = 0;

    private static final String TAG = EmpireService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    enum EmpireServiceCommands {
        BUILD_DEATH_STAR,
        FIND_LUKE
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Arrived at: " + System.currentTimeMillis());
        Messenger messenger = intent.getParcelableExtra("ImperialMessenger");
        EmpireServiceCommands command = (EmpireServiceCommands) intent.getExtras()
                .get("Command type");

        switch (command) {
            case BUILD_DEATH_STAR:

                for (int i = 0; i < 235; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (messenger != null) {
                    Message message = Message.obtain();
                    message.what = CALLBACK_MSG;
                    Bundle data = new Bundle(1);
                    data.putParcelable("result",
                            new DeathStar(270000, 270000, "THIS IS THE BIG GUN"));
                    message.setData(data);
                    try {
                        Log.d(TAG, "Sending back at: " + System.currentTimeMillis());
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case FIND_LUKE:

        }
        return START_NOT_STICKY;
    }


}
