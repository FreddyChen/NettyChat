package com.freddy.chat.event;

/**
 * 事件对象池
 *
 * Created by Freddy on 2015/11/3.
 * chenshichao@outlook.com
 */
public class CEvenObjPool extends ObjectPool<CEvent> {

    public CEvenObjPool(int capacity) {
        super(capacity);
    }

    @Override
    protected CEvent[] createObjPool(int capacity) {
        return new CEvent[capacity];
    }

    @Override
    protected CEvent createNewObj() {
        return new CEvent();
    }
}
