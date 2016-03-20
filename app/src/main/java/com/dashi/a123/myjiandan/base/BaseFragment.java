package com.dashi.a123.myjiandan.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dashi.a123.myjiandan.BuildConfig;
import com.dashi.a123.myjiandan.net.RequestManager;
import com.dashi.a123.myjiandan.utils.Logger.LogLevel;
import com.dashi.a123.myjiandan.utils.Logger.Logger;

/**
 * Created by a123 on 16/3/20.
 */
public class BaseFragment extends Fragment implements ConstantString {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(BuildConfig.DEBUG){
            Logger.init(getClass().getSimpleName())
                    .setLogLevel(LogLevel.FULL).hideThreadInfo();
        }else{
            Logger.init(getClass().getSimpleName())
                    .setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JDApplication.getRefWatcher(getActivity()).watch(this);
        RequestManager.canceAll(this);

    }
}
