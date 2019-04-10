package com.freddy.chat.im.handler;

import android.util.Log;

import com.freddy.chat.bean.AppMessage;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       GroupChatMessageHandler.java</p>
 * <p>@PackageName:     com.freddy.chat.im.handler</p>
 * <b>
 * <p>@Description:     类描述</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/10 03:43</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class GroupChatMessageHandler extends AbstractMessageHandler {

    private static final String TAG = GroupChatMessageHandler.class.getSimpleName();

    @Override
    protected void action(AppMessage message) {
        Log.d(TAG, "收到群聊消息，message=" + message);
    }
}
