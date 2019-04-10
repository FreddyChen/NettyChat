package com.freddy.chat.im;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.freddy.im.IMSClientFactory;
import com.freddy.im.interf.IMSClientInterface;
import com.freddy.im.protobuf.MessageProtobuf;

import java.util.Vector;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       IMSClientBootstrap.java</p>
 * <p>@PackageName:     com.freddy.chat.im</p>
 * <b>
 * <p>@Description:     应用层的imsClient启动器</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/08 00:25</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class IMSClientBootstrap {

    private static final IMSClientBootstrap INSTANCE = new IMSClientBootstrap();
    private IMSClientInterface imsClient;
    private boolean isActive;

    private IMSClientBootstrap() {

    }

    public static IMSClientBootstrap getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        String userId = "100001";
        String token = "token_" + userId;
        IMSClientBootstrap bootstrap = IMSClientBootstrap.getInstance();
        String hosts = "[{\"host\":\"127.0.0.1\", \"port\":8866}]";
        bootstrap.init(userId, token, hosts, 0);
    }

    public synchronized void init(String userId, String token, String hosts, int appStatus) {
        if (!isActive()) {
            Vector<String> serverUrlList = convertHosts(hosts);
            if (serverUrlList == null || serverUrlList.size() == 0) {
                System.out.println("init IMLibClientBootstrap error,ims hosts is null");
                return;
            }

            isActive = true;
            System.out.println("init IMLibClientBootstrap, servers=" + hosts);
            if (null != imsClient) {
                imsClient.close();
            }
            imsClient = IMSClientFactory.getIMSClient();
            updateAppStatus(appStatus);
            imsClient.init(serverUrlList, new IMSEventListener(userId, token), new IMSConnectStatusListener());
        }
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessage(MessageProtobuf.Msg msg) {
        if (isActive) {
            imsClient.sendMsg(msg);
        }
    }

    private Vector<String> convertHosts(String hosts) {
        if (hosts != null && hosts.length() > 0) {
            JSONArray hostArray = JSONArray.parseArray(hosts);
            if (null != hostArray && hostArray.size() > 0) {
                Vector<String> serverUrlList = new Vector<String>();
                JSONObject host;
                for (int i = 0; i < hostArray.size(); i++) {
                    host = JSON.parseObject(hostArray.get(i).toString());
                    serverUrlList.add(host.getString("host") + " "
                            + host.getInteger("port"));
                }
                return serverUrlList;
            }
        }
        return null;
    }

    public void updateAppStatus(int appStatus) {
        if (imsClient == null) {
            return;
        }

        imsClient.setAppStatus(appStatus);
    }
}
