// IStarWars.aidl
package com.academy.android.aidlserviceexample;

// Declare any non-default types here with import statements
import com.academy.android.aidlserviceexample.DeathStar;
import com.academy.android.aidlserviceexample.IEmpireServiceResponseListener;

interface IStarWars {
    String buildDeathStar(out DeathStar deathStar);
    oneway void findLuke(in IEmpireServiceResponseListener listener);
}



