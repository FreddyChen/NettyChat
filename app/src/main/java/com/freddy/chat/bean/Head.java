package com.freddy.chat.bean;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       Head.java</p>
 * <p>@PackageName:     com.freddy.chat.bean</p>
 * <b>
 * <p>@Description:     消息头</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/10 00:00</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class Head {

    private String msgId;
    private int msgType;
    private int msgContentType;
    private String fromId;
    private String toId;
    private long timestamp;
    private int statusReport;
    private String extend;

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

    @Override
    public String toString() {
        return "Head{" +
                "msgId='" + msgId + '\'' +
                ", msgType=" + msgType +
                ", msgContentType=" + msgContentType +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", timestamp=" + timestamp +
                ", statusReport=" + statusReport +
                ", extend='" + extend + '\'' +
                '}';
    }
}
