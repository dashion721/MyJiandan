package com.dashi.a123.myjiandan.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.dashi.a123.myjiandan.BuildConfig;
import com.dashi.a123.myjiandan.R;
import com.dashi.a123.myjiandan.utils.Logger.Logger;
import com.dashi.a123.myjiandan.utils.StrictModeUtil;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java_gen.DaoMaster;
import java_gen.DaoSession;

/**
 * Created by a123 on 16/3/20.
 */
public class JDApplication extends Application{

    private static int COLOR_OF_DIALOG = R.color.primary;
    private static int COLOR_OF_DIALOG_CONTENT = Color.WHITE;

    private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        StrictModeUtil.init();
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        mContext = this;

        if(BuildConfig.DEBUG){
            Logger.init().hideThreadInfo();
        }

        Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
    }

    public static Context getContext(){
        return mContext;
    }

    public static  RefWatcher getRefWatcher(Context context){
        JDApplication application = (JDApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
