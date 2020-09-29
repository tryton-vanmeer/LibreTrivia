package io.github.trytonvanmeer.libretrivia;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


public class LibreTriviaApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getAppContext() {
        return LibreTriviaApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LibreTriviaApplication.context = getApplicationContext();
    }
}
