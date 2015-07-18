package com.academy.android.aidlserviceexample;

import android.os.RemoteException;
import android.util.Log;

public class IStarWarsImplementation extends IStarWars.Stub {

    @SuppressWarnings("UnusedAssignment")
    @Override
    public String buildDeathStar(DeathStar deathStar) throws RemoteException {
        for (int i = 0; i < 235; i++) {
            try {
                Log.d("XXX", "Empire worker finished job. Went sleep");
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deathStar = new DeathStar(270000, 270000, "THIS IS THE BIG GUN");
        return "Death Star deployed and ready for your command, my lord";
    }


    @Override
    public void findLuke(IEmpireServiceResponseListener listener) throws RemoteException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listener.onResponse("I'm your father, Luke!");
    }


}
