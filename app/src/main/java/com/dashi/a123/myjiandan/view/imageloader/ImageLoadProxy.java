package com.dashi.a123.myjiandan.view.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.dashi.a123.myjiandan.BuildConfig;
import com.dashi.a123.myjiandan.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by a123 on 16/3/20.
 */
public class ImageLoadProxy {

    private static final int MAX_DISK_CACHE =1024*1024*50;
    private static final int MAX_MEMORY_CACHE = 1024*1024*10;

    private static boolean isShowLog = false;

    private static ImageLoader imageLoader;

    public static ImageLoader getImageLoader(){
        if(imageLoader == null){
            synchronized (ImageLoadProxy.class){
                imageLoader = ImageLoader.getInstance();
            }
        }
        return imageLoader;
    }


    public static void initImageLoader(Context context){
        ImageLoaderConfiguration.Builder builder = new
                ImageLoaderConfiguration.Builder(context);

        builder.tasksProcessingOrder(QueueProcessingType.FIFO);

        builder.diskCacheSize(MAX_DISK_CACHE);
        builder.memoryCacheSize(MAX_MEMORY_CACHE);

        if(BuildConfig.DEBUG && isShowLog){
            builder.writeDebugLogs();
        }
        getImageLoader().init(builder.build());
    }

    public static void displayImage(String url,ImageView taregt,
                                    DisplayImageOptions options){
        imageLoader.displayImage(url, taregt, options);
    }

    /**
     * 头像专用
     * @param url
     * @param target
     */
    public static void displayHeadIcon(String url,ImageView target){
        imageLoader.displayImage(url,target);
    }




    /**
     * 加载头像专用Options,默认加载中，失败和空url为ic_loading_small
     * @return
     */
    public static DisplayImageOptions getOptions4Header(){
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(R.drawable.ic_loading_small)
                .showImageOnFail(R.drawable.ic_loading_small)
                .showImageOnLoading(R.drawable.ic_loading_small)
                .build();
    }

    /**
     * 加载图片列表专用，加载钱会重置View
     * @param loadingResource
     * @return
     */
    public static DisplayImageOptions getOptions4PictrueList(int loadingResource){
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .showImageOnLoading(loadingResource)
                .showImageForEmptyUri(loadingResource)
                .showImageOnFail(loadingResource)
                .build();
    }
}
