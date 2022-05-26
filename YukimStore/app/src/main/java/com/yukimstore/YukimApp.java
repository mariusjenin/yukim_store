package com.yukimstore;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.yukimstore.activity.abstract_activity.YukimActivity;

public class YukimApp extends Application implements LifecycleObserver {

    private static Application YUKIMAPP;

    @SuppressLint("StaticFieldLeak")
    private static YukimActivity currentAct;

    public static Application getApplication() {
        return YUKIMAPP;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    public static YukimActivity getCurrentAct() {
        return currentAct;
    }

    public static void setCurrentAct(YukimActivity cA) {
        currentAct = cA;
    }

    public static boolean FOREGROUND;

    @Override
    public void onCreate() {
        super.onCreate();
        FOREGROUND = true;
        YUKIMAPP = this;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public static void onAppBackgrounded() {
        //App in background
        FOREGROUND = false;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public static void onAppForegrounded() {
        // App in foreground
        FOREGROUND = true;
    }

    public static boolean isForeground() {
        return FOREGROUND;
    }
}
