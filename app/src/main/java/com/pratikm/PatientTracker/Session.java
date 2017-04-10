package com.pratikm.PatientTracker;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pratikmishra on 09/04/17.
 */

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public Session(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences("PatientTracker", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }
    public void setLoggedIn(Boolean loggedIn) {
        editor.putBoolean("LoggedInMode", loggedIn);
        editor.commit();
    }
    public boolean loggedIn() {
        return prefs.getBoolean("LoggedInMode", false);
    }
}
