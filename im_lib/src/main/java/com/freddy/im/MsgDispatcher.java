package com.freddy.im;

import com.freddy.im.listener.OnEventListener;
import com.freddy.im.protobuf.MessageProtobuf;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       MsgDispatcher.java</p>
 * <p>@PackageName:     com.freddy.im</p>
 * <b>
 * <p>@Description:     消息转发器，负责将接收到的消息转发到应用层</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/05 05:05</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class MsgDispatcher {

    private OnEventListener mOnEventListener;

    public MsgDispatcher() {

    }

    public void setOnEventListener(OnEventListener listener) {
        this.mOnEventListener = listener;
    }

    /**
     * 接收消息，并通过OnEventListener转发消息到应用层
     * @param msg
     */
    public void receivedMsg(MessageProtobuf.Msg msg) {
        if(mOnEventListener == null) {
            return;
        }

        mOnEventListener.dispatchMsg(msg);
    }
}
