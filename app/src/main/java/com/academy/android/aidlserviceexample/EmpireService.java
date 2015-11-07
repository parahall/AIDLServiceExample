package com.academy.android.aidlserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class EmpireService extends Service {


    public static final String COMMAND_TYPE_KEY = "Command type";
    public static final String EMPIRE_SERVICE_ACTION
            = "com.academy.android.aidlserviceexample.EMPIRE_SERVICE_ACTION";

    public static final int CALLBACK_MSG = 0;

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
        EmpireServiceCommands command = (EmpireServiceCommands) intent.getExtras()
                .get(COMMAND_TYPE_KEY);

        switch (command) {
            case BUILD_DEATH_STAR:

                for (int i = 0; i < 235; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent jobDoneIntent = new Intent(EMPIRE_SERVICE_ACTION);
                jobDoneIntent.putExtra("result",
                        new DeathStar(270000, 270000, "THIS IS THE BIG GUN"));
                sendBroadcast(jobDoneIntent);
                break;
            case FIND_LUKE:

        }
        stopSelf();
        return START_NOT_STICKY;
    }


}
