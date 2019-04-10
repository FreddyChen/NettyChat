package com.freddy.chat.bean;

import com.freddy.chat.utils.StringUtil;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       BaseMessage.java</p>
 * <p>@PackageName:     com.freddy.chat.bean</p>
 * <b>
 * <p>@Description:     消息基类</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/10 00:02</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class BaseMessage {

    protected String msgId;       // 消息id
    protected int msgType;        // 消息类型
    protected int msgContentType; // 消息内容乐行
    protected String fromId;      // 发送者id
    protected String toId;        // 接收者id
    protected long timestamp;     // 消息时间戳
    protected int statusReport;   // 消息状态报告
    protected String extend;      // 扩展字段，以key/value形式存放json
    protected String content;     // 消息内容

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgContentType() {
        return msgContentType;
    }

    public void setMsgContentType(int msgContentType) {
        this.msgContentType = msgContentType;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusReport() {
        return statusReport;
    }

    public void setStatusReport(int statusReport) {
        this.statusReport = statusReport;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        try {
            return this.msgId.hashCode();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }

        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof BaseMessage)) {
            return false;
        }

        return StringUtil.equals(this.msgId, ((BaseMessage) obj).getMsgId());
    }
}
