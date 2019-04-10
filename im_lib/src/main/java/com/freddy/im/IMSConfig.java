package com.freddy.im;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       IMSConfig.java</p>
 * <p>@PackageName:     com.freddy.im</p>
 * <b>
 * <p>@Description:     IMS默认配置，若不使用默认配置，应提供set方法给应用层设置</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/05 05:38</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class IMSConfig {

    // 默认重连一个周期失败间隔时长
    public static final int DEFAULT_RECONNECT_INTERVAL = 3 * 1000;
    // 连接超时时长
    public static final int DEFAULT_CONNECT_TIMEOUT = 10 * 1000;
    // 默认一个周期重连次数
    public static final int DEFAULT_RECONNECT_COUNT = 3;
    // 默认重连起始延时时长，重连规则：最大n次，每次延时n * 起始延时时长，重连次数达到n次后，重置
    public static final int DEFAULT_RECONNECT_BASE_DELAY_TIME = 3 * 1000;
    // 默认消息发送失败重发次数
    public static final int DEFAULT_RESEND_COUNT = 3;
    // 默认消息重发间隔时长
    public static final int DEFAULT_RESEND_INTERVAL = 8 * 1000;
    // 默认应用在前台时心跳消息间隔时长
    public static final int DEFAULT_HEARTBEAT_INTERVAL_FOREGROUND = 3 * 1000;
    // 默认应用在后台时心跳消息间隔时长
    public static final int DEFAULT_HEARTBEAT_INTERVAL_BACKGROUND = 30 * 1000;
    // 应用在前台标识
    public static final int APP_STATUS_FOREGROUND = 0;
    // 应用在后台标识
    public static final int APP_STATUS_BACKGROUND = -1;
    public static final String KEY_APP_STATUS = "key_app_status";
    // 默认服务端返回的消息发送成功状态报告
    public static final int DEFAULT_REPORT_SERVER_SEND_MSG_SUCCESSFUL = 1;
    // 默认服务端返回的消息发送失败状态报告
    public static final int DEFAULT_REPORT_SERVER_SEND_MSG_FAILURE = 0;
    // ims连接状态：连接中
    public static final int CONNECT_STATE_CONNECTING = 0;
    // ims连接状态：连接成功
    public static final int CONNECT_STATE_SUCCESSFUL = 1;
    // ims连接状态：连接失败
    public static final int CONNECT_STATE_FAILURE = -1;
}
