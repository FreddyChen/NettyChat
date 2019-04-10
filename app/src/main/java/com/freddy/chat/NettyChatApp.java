package com.freddy.chat;

import android.support.multidex.MultiDexApplication;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       NettyChatApp.java</p>
 * <p>@PackageName:     com.freddy.chat</p>
 * <b>
 * <p>@Description:     类描述</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/07 23:58</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class NettyChatApp extends MultiDexApplication {

    private static NettyChatApp instance;

    public static NettyChatApp sharedInstance() {
        if (instance == null) {
            throw new IllegalStateException("app not init...");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
