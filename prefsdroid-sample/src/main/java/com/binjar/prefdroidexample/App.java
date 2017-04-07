package com.binjar.prefdroidexample;

import android.app.Application;

import com.binjar.prefsdroid.Preference;

/**
 * Created by Arif Islam on 03-Apr-17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Preference.load().using(this).with(getPackageName()).prepare();
    }
}
