package com.freddy.chat.event;

/**
 * 事件监听器
 *
 * Created by Freddy on 2015/11/3.
 * chenshichao@outlook.com
 */
public interface I_CEventListener {

    /**
     * 事件回调函数<br />
     * <b>注意：</b><br />
     * 如果 obj 使用了对象池（如 socket 事件的对象），<br />
     * 那么事件完成后，obj 即自动回收到对象池，请不要在其它线程继续使用，否则可能会导致数据不正常
     *
     * @param topic
     *              事件名称
     * @param msgCode
     *              消息类型
     * @param resultCode
     *              预留参数
     * @param obj
     *              数据对象
     */
    void onCEvent(String topic, int msgCode, int resultCode, Object obj);
}
