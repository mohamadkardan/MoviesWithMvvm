package com.example.mvvm;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {
    //    singleton pattern
    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if (instance == null) {
            instance = new AppExecutors();
        }
        return instance;
    }

    private final ScheduledExecutorService networkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService getNetworkIO(){
        return networkIO;
    }
}
