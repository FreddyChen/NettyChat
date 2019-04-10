package com.freddy.chat.im.handler;

import com.freddy.chat.bean.AppMessage;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       IMessageHandler.java</p>
 * <p>@PackageName:     com.freddy.chat.im.handler</p>
 * <b>
 * <p>@Description:     类描述</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/10 03:41</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public interface IMessageHandler {

    void execute(AppMessage message);
}
