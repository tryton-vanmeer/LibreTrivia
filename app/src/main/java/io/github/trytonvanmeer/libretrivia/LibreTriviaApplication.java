package io.github.trytonvanmeer.libretrivia;

import android.app.Application;
import android.content.Context;


public class LibreTriviaApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LibreTriviaApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return LibreTriviaApplication.context;
    }
}
