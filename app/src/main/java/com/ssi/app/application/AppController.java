package com.ssi.app.application;

import android.app.Application;
import android.os.Handler;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.ssi.app.datamodel.Login.Contents;

;

/**
 * Created by Mounzer on 8/22/2017.
 */
@Database(name = AppController.NAME, version = AppController.VERSION)

public class AppController extends Application {

    public static final String NAME = "SSI";
    public static final int VERSION = 1;

    public static final String TAG = AppController.class.getSimpleName();

    public static int REQUEST_DETAIL = 1;
    public static int CLOSE_CODE = 99;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 0;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        AppController.username = username;
    }

    public static String username;



    private static AppController singleton;


    public static Contents getCONTENTS() {
        return CONTENTS;
    }

    public static void setCONTENTS(Contents CONTENTS) {
        AppController.CONTENTS = CONTENTS;
    }

    public static Contents CONTENTS;


    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);



    }


    public static AppController getInstance(){
        return singleton;
    }
    Handler lochandler;
    public Handler getLocationHandler(){
        return lochandler;
    }
    Handler currhandler;

}
