package com.dashi.a123.myjiandan.utils;

import android.os.Build;
import android.os.StrictMode;

import com.dashi.a123.myjiandan.BuildConfig;

/**
 * Created by a123 on 16/3/20.
 */
public class StrictModeUtil {

    private static boolean isShow = false;

    public static void init(){
        if(false && BuildConfig.DEBUG && Build.VERSION.SDK_INT >
                Build.VERSION_CODES.FROYO){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .penaltyDialog()
            .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build());
        }
    }
}
