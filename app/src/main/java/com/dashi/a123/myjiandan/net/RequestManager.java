package com.dashi.a123.myjiandan.net;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dashi.a123.myjiandan.BuildConfig;
import com.dashi.a123.myjiandan.base.JDApplication;
import com.dashi.a123.myjiandan.utils.Logger.Logger;

import java.util.Objects;

/**
 * Created by a123 on 16/3/20.
 */
public class RequestManager {

    public static final int OUT_TIME =10000;
    public static final int TIMES_OF_RETRY = 1;

    public static RequestQueue mRequestQueue =
            Volley.newRequestQueue(JDApplication.getContext());

    private RequestManager(){

    }

    public static void addRequest(Request<?> request,Object tag){
        if(tag != null){
            request.setTag(tag);
        }
        //给每个请求重设超时，重设次数
        request.setRetryPolicy(new DefaultRetryPolicy(
                OUT_TIME,
                TIMES_OF_RETRY,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(request);

        if(BuildConfig.DEBUG){
            Logger.d(request.getUrl());
        }
    }

    public static void canceAll(Object tag){
        mRequestQueue.cancelAll(tag);
    }
}
