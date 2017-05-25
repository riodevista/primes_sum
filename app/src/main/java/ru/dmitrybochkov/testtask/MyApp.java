package ru.dmitrybochkov.testtask;

import android.app.Application;


/**
 * Created by Dmitry Bochkov on 26.12.2016.
 */

public class MyApp extends Application {

    private static MyApp instance;


    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null)
            instance = this;

    }

}
