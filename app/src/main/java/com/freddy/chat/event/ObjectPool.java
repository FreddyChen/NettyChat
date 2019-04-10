package com.freddy.chat.event;

/**
 * 对象池
 *
 * Created by Freddy on 2015/11/3.
 * chenshichao@outlook.com
 */
public abstract class ObjectPool<T extends PoolableObject> {
    /**
     * 对象容器
     */
    private T[] mContainer;

    private final Object mLock = new Object();

    /**
     * 每次返回对象都放到数据末端，mLength表示前面可用对象数
     */
    private int mLength;

    public ObjectPool(int capacity) {
        mContainer = createObjPool(capacity);
    }

    /**
     * 创建对象池
     * @param capacity
     *                  最大限度容量
     * @return
     *                  对象池
     */
    protected abstract T[] createObjPool(int capacity);

    /**
     * 创建一个新的对象
     * @return
     *          创建成功的对象
     */
    protected abstract T createNewObj();

    /**
     * 从对象池中捞出一个对象，如果池已满，会重新创建一个对象
     * @return
     *          捞出或重新创建的对象
     */
    public final T get() {
        T obj = findFreeObject();
        if(null == obj) {
            obj = createNewObj();
        }else {
            // 清除对象状态
            obj.reset();
        }

        return obj;
    }

    /**
     * 把对象放回池里面
     * @param obj
     *              需要放回对象池的对象
     */
    public final void returnObj(T obj) {
        synchronized (mLock) {
            int size = mContainer.length;
            if(mLength < size) {
                mContainer[mLength] = obj;
                mLength++;
            }
        }
    }

    /**
     * 从池中找到空闲的对象
     * @return
     *          空闲的对象
     */
    private T findFreeObject() {
        T obj = null;
        synchronized (mLock) {
            if(mLength > 0) {
                --mLength;
                obj = mContainer[mLength];
                // 赋值完成后，释放资源
                mContainer[mLength] = null;
            }
        }
        return obj;
    }
}
