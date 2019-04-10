package com.freddy.im;

import com.freddy.im.interf.IMSClientInterface;
import com.freddy.im.protobuf.MessageProtobuf;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.util.internal.StringUtil;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       MsgTimeoutTimerManager.java</p>
 * <p>@PackageName:     com.freddy.im</p>
 * <b>
 * <p>@Description:     消息发送超时管理器，用于管理消息定时器的新增、移除等</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/09 22:42</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class MsgTimeoutTimerManager {

    private Map<String, MsgTimeoutTimer> mMsgTimeoutMap = new ConcurrentHashMap<>();
    private IMSClientInterface imsClient;// ims客户端

    public MsgTimeoutTimerManager(IMSClientInterface imsClient) {
        this.imsClient = imsClient;
    }

    /**
     * 添加消息到发送超时管理器
     *
     * @param msg
     */
    public void add(MessageProtobuf.Msg msg) {
        if (msg == null || msg.getHead() == null) {
            return;
        }

        int handshakeMsgType = -1;
        int heartbeatMsgType = -1;
        int clientReceivedReportMsgType = imsClient.getClientReceivedReportMsgType();
        MessageProtobuf.Msg handshakeMsg = imsClient.getHandshakeMsg();
        if (handshakeMsg != null && handshakeMsg.getHead() != null) {
            handshakeMsgType = handshakeMsg.getHead().getMsgType();
        }
        MessageProtobuf.Msg heartbeatMsg = imsClient.getHeartbeatMsg();
        if (heartbeatMsg != null && heartbeatMsg.getHead() != null) {
            heartbeatMsgType = heartbeatMsg.getHead().getMsgType();
        }

        int msgType = msg.getHead().getMsgType();
        // 握手消息、心跳消息、客户端返回的状态报告消息，不用重发。
        if (msgType == handshakeMsgType || msgType == heartbeatMsgType || msgType == clientReceivedReportMsgType) {
            return;
        }

        String msgId = msg.getHead().getMsgId();
        if (!mMsgTimeoutMap.containsKey(msgId)) {
            MsgTimeoutTimer timer = new MsgTimeoutTimer(imsClient, msg);
            mMsgTimeoutMap.put(msgId, timer);
        }

        System.out.println("添加消息超发送超时管理器，message=" + msg + "\t当前管理器消息数：" + mMsgTimeoutMap.size());
    }

    /**
     * 从发送超时管理器中移除消息，并停止定时器
     *
     * @param msgId
     */
    public void remove(String msgId) {
        if (StringUtil.isNullOrEmpty(msgId)) {
            return;
        }

        MsgTimeoutTimer timer = mMsgTimeoutMap.remove(msgId);
        MessageProtobuf.Msg msg = null;
        if (timer != null) {
            msg = timer.getMsg();
            timer.cancel();
            timer = null;
        }

        System.out.println("从发送消息管理器移除消息，message=" + msg);
    }

    /**
     * 重连成功回调，重连并握手成功时，重发消息发送超时管理器中所有的消息
     */
    public synchronized void onResetConnected() {
        for(Iterator<Map.Entry<String, MsgTimeoutTimer>> it = mMsgTimeoutMap.entrySet().iterator(); it.hasNext();) {
            it.next().getValue().sendMsg();
        }
    }
}
