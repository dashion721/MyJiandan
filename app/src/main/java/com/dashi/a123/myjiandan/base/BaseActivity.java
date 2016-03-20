package com.dashi.a123.myjiandan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dashi.a123.myjiandan.BuildConfig;
import com.dashi.a123.myjiandan.R;
import com.dashi.a123.myjiandan.utils.Logger.LogLevel;
import com.dashi.a123.myjiandan.utils.Logger.Logger;


/**
 * Created by a123 on 16/3/20.
 */
public class BaseActivity extends AppCompatActivity
        implements ConstantString{

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        if(BuildConfig.DEBUG){
            Logger.init(getClass().getSimpleName())
                    .setLogLevel(LogLevel.FULL).hideThreadInfo();
        }else {
            Logger.init(getClass().getSimpleName())
                    .setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_none,R.anim.trans_center_2_right);
    }
}
