package com.freddy.im.listener;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       IMSConnectStatusCallback.java</p>
 * <p>@PackageName:     com.freddy.im.listener</p>
 * <b>
 * <p>@Description:     IMS连接状态回调</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/03/31 20:07</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public interface IMSConnectStatusCallback {

    /**
     * ims连接中
     */
    void onConnecting();

    /**
     * ims连接成功
     */
    void onConnected();

    /**
     * ims连接失败
     */
    void onConnectFailed();
}
