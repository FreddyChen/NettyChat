package com.freddy.im.netty;

import com.freddy.im.HeartbeatRespHandler;
import com.freddy.im.LoginAuthRespHandler;
import com.freddy.im.protobuf.MessageProtobuf;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       TCPChannelInitializerHandler.java</p>
 * <p>@PackageName:     com.freddy.im.netty</p>
 * <b>
 * <p>@Description:     Channel初始化配置</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/05 07:11</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class TCPChannelInitializerHandler extends ChannelInitializer<Channel> {

    private NettyTcpClient imsClient;

    public TCPChannelInitializerHandler(NettyTcpClient imsClient) {
        this.imsClient = imsClient;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        // netty提供的自定义长度解码器，解决TCP拆包/粘包问题
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(2));
        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535,
                0, 2, 0, 2));

        // 增加protobuf编解码支持
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ProtobufDecoder(MessageProtobuf.Msg.getDefaultInstance()));

        // 握手认证消息响应处理handler
        pipeline.addLast(LoginAuthRespHandler.class.getSimpleName(), new LoginAuthRespHandler(imsClient));
        // 心跳消息响应处理handler
        pipeline.addLast(HeartbeatRespHandler.class.getSimpleName(), new HeartbeatRespHandler(imsClient));
        // 接收消息处理handler
        pipeline.addLast(TCPReadHandler.class.getSimpleName(), new TCPReadHandler(imsClient));
    }
}
