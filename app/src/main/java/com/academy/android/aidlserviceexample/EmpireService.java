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

    enum EmpireServiceCommands {
        BUILD_DEATH_STAR,
        FIND_LUKE
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Messenger messenger = intent.getParcelableExtra("ImperialMessenger");
        EmpireServiceCommands command = (EmpireServiceCommands) intent.getExtras()
                .get("Command type");

        switch (command) {
            case BUILD_DEATH_STAR:

                for (int i = 0; i < 235; i++) {
                    try {
                        Log.d("XXX", "Empire worker finished job. Went sleep");
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (messenger != null) {
                    Message message = Message.obtain(); //
                    message.what = CALLBACK_MSG;
                    Bundle data = new Bundle(1);
                    data.putParcelable("result",
                            new DeathStar(270000, 270000, "THIS IS THE BIG GUN")); //
                    message.setData(data); //
                    try {
                        messenger.send(message); //
                    } catch (RemoteException e) {
                        //…
                    }
                }
                break;
            case FIND_LUKE:

        }
        stopSelf();
        return START_NOT_STICKY;
    }

    public static final int CALLBACK_MSG = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    @Override
//    protected void onHandleIntent(Intent intent) {
//        Messenger messenger = intent.getParcelableExtra("EmperialMessenger");
//        EmpireServiceCommands command = (EmpireServiceCommands) intent.getExtras()
//                .get("Command type");
//
//        switch (command) {
//            case BUILD_DEATH_STAR:
//
//                for (int i = 0; i < 235; i++) {
//                    try {
//                        Log.d("XXX", "Empire worker finished job. Went sleep");
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                if (messenger != null) {
//                    Message message = Message.obtain(); //
//                    message.what = CALLBACK_MSG;
//                    Bundle data = new Bundle(1);
//                    data.putParcelable("result",
//                            new DeathStar(270000, 270000, "THIS IS THE BIG GUN")); //
//                    message.setData(data); //
//                    try {
//                        messenger.send(message); //
//                    } catch (RemoteException e) {
//                        //…
//                    }
//                }
//                break;
//            case FIND_LUKE:
//
//        }
//
//    }
}
