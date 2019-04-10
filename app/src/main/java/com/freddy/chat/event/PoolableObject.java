package com.freddy.chat.event;

/**
 * 对象池中的对象要求实现PoolableObject接口
 *
 * Created by Freddy on 2015/11/3.
 * chenshichao@outlook.com
 */
public interface PoolableObject {

    /**
     * 恢复到默认状态
     */
    void reset();
}
