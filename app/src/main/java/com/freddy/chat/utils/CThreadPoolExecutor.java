package com.freddy.chat.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>@ProjectName:     BoChat</p>
 * <p>@ClassName:       CThreadPoolExecutor.java</p>
 * <p>@PackageName:     com.bochat.app.utils</p>
 * <b>
 * <p>@Description:     自定义固定大小的线程池
 * 每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
 * 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
 * <p>
 * 合理利用线程池能够带来三个好处：
 * 第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 * 第二：提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行。
 * 第三：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。
 * 我们可以通过ThreadPoolExecutor来创建一个线程池：
 * new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, milliseconds,runnableTaskQueue, handler);
 * <p>
 * corePoolSize（线程池的基本大小）：
 * 当提交一个任务到线程池时，线程池会创建一个线程来执行任务，
 * 即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。
 * 如果调用了线程池的prestartAllCoreThreads方法，线程池会提前创建并启动所有基本线程。
 * <p>
 * runnableTaskQueue（任务队列）：用于保存等待执行的任务的阻塞队列。 可以选择以下几个阻塞队列。
 * ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
 * <p>
 * LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。
 * 静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
 * <p>
 * SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，
 * 吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
 * <p>
 * PriorityBlockingQueue：一个具有优先级的无限阻塞队列。
 * <p>
 * maximumPoolSize（线程池最大大小）：
 * 线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是如果使用了无界的任务队列这个参数就没什么效果。
 * <p>
 * ThreadFactory：
 * 用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。
 * <p>
 * RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。
 * 这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。以下是JDK1.5提供的四种策略。
 * AbortPolicy：直接抛出异常。
 * CallerRunsPolicy：只用调用者所在线程来运行任务。
 * DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
 * DiscardPolicy：不处理，丢弃掉。
 * 当然也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化不能处理的任务。
 * <p>
 * keepAliveTime（线程活动保持时间）：线程池的工作线程空闲后，保持存活的时间。
 * 所以如果任务很多，并且每个任务执行的时间比较短，可以调大这个时间，提高线程的利用率。
 * <p>
 * TimeUnit（线程活动保持时间的单位）：可选的单位有天（DAYS），小时（HOURS），分钟（MINUTES），
 * 毫秒(MILLISECONDS)，微秒(MICROSECONDS, 千分之一毫秒)和毫微秒(NANOSECONDS, 千分之一微秒)。</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/2/3 15:35</p>
 * <p>@email:           chenshichao@outlook.com</p>
 *
 * @see http://www.infoq.com/cn/articles/java-threadPool
 */
public class CThreadPoolExecutor {

    private static final String TAG = CThreadPoolExecutor.class.getSimpleName();
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();// CPU个数
    //    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;// 线程池中核心线程的数量
//    private static final int MAXIMUM_POOL_SIZE = 2 * CPU_COUNT + 1;// 线程池中最大线程数量
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));// 线程池中核心线程的数量
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;// 线程池中最大线程数量
    private static final long KEEP_ALIVE_TIME = 30L;// 非核心线程的超时时长，当系统中非核心线程闲置时间超过keepAliveTime之后，则会被回收。如果ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，则该参数也表示核心线程的超时时长
    private static final int WAIT_COUNT = 128; // 最多排队个数，这里控制线程创建的频率

    private static ThreadPoolExecutor pool = createThreadPoolExecutor();

    private static ThreadPoolExecutor createThreadPoolExecutor() {
        if (pool == null) {
            pool = new ThreadPoolExecutor(
                    CORE_POOL_SIZE,
                    MAXIMUM_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(WAIT_COUNT),
                    new CThreadFactory("CThreadPool", Thread.NORM_PRIORITY - 2),
                    new CHandlerException());
        }

        return pool;
    }

    public static class CThreadFactory implements ThreadFactory {
        private AtomicInteger counter = new AtomicInteger(1);
        private String prefix = "";
        private int priority = Thread.NORM_PRIORITY;

        public CThreadFactory(String prefix, int priority) {
            this.prefix = prefix;
            this.priority = priority;
        }

        public CThreadFactory(String prefix) {
            this.prefix = prefix;
        }

        public Thread newThread(Runnable r) {
            Thread executor = new Thread(r, prefix + " #" + counter.getAndIncrement());
            executor.setDaemon(true);
            executor.setPriority(priority);
            return executor;
        }
    }

    /**
     * 抛弃当前的任务
     */
    private static class CHandlerException extends ThreadPoolExecutor.AbortPolicy {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            Log.d(TAG, "rejectedExecution:" + r);
            Log.e(TAG, logAllThreadStackTrace().toString());
            //            Tips.showForce("任务被拒绝", 5000);
            if (!pool.isShutdown()) {
                pool.shutdown();
                pool = null;
            }

            pool = createThreadPoolExecutor();
        }
    }

    private static ExecutorService jobsForUI = Executors.newFixedThreadPool(
            CORE_POOL_SIZE, new CThreadFactory("CJobsForUI", Thread.NORM_PRIORITY - 1));

    /**
     * 启动一个消耗线程，常驻后台
     *
     * @param r
     */
    public static void startConsumer(final Runnable r, final String name) {
        runInBackground(new Runnable() {
            public void run() {
                new CThreadFactory(name, Thread.NORM_PRIORITY - 3).newThread(r).start();
            }
        });
    }

    /**
     * 提交到其他线程去跑，需要取数据的时候会等待任务完成再继续
     *
     * @param task
     * @return
     */
    public static <T> Future<T> submitTask(Callable<T> task) {
        return jobsForUI.submit(task);
    }

    /**
     * 强制清理任务
     *
     * @param task
     * @return
     */
    public static <T> void cancelTask(Future<T> task) {
        if (task != null) {
            task.cancel(true);
        }
    }

    /**
     * 从 Future 中获取值，如果发生异常，打日志
     *
     * @param future
     * @param tag
     * @param name
     * @return
     */
    public static <T> T getFromTask(Future<T> future, String tag, String name) {
        try {
            return future.get();
        } catch (Exception e) {
            Log.e(tag, (name != null ? name + ": " : "") + e.toString());
        }
        return null;
    }

    public static void runInBackground(Runnable runnable) {
        if (pool == null) {
            createThreadPoolExecutor();
        }

        pool.execute(runnable);
        //        Future future = pool.submit(runnable);
        //        try {
        //            future.get();
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        } catch (ExecutionException e) {
        //            e.printStackTrace();
        //        }
    }

    private static Thread mainThread;
    private static Handler mainHandler;

    static {
        Looper mainLooper = Looper.getMainLooper();
        mainThread = mainLooper.getThread();
        mainHandler = new Handler(mainLooper);
    }

    public static boolean isOnMainThread() {
        return mainThread == Thread.currentThread();
    }

    public static void runOnMainThread(Runnable r) {
        if (isOnMainThread()) {
            r.run();
        } else {
            mainHandler.post(r);
        }
    }

    public static void runOnMainThread(Runnable r, long delayMillis) {
        if (delayMillis <= 0) {
            runOnMainThread(r);
        } else {
            mainHandler.postDelayed(r, delayMillis);
        }
    }

    // 用于记录后台等待的Runnable，第一个参数外面的Runnable，第二个参数是等待中的Runnable
    private static HashMap<Runnable, Runnable> mapToMainHandler = new HashMap<Runnable, Runnable>();

    public static void runInBackground(final Runnable runnable, long delayMillis) {
        if (delayMillis <= 0) {
            runInBackground(runnable);
        } else {
            Runnable mainRunnable = new Runnable() {

                @Override
                public void run() {
                    mapToMainHandler.remove(runnable);
                    pool.execute(runnable);
                }
            };

            mapToMainHandler.put(runnable, mainRunnable);
            mainHandler.postDelayed(mainRunnable, delayMillis);
        }
    }

    /**
     * 对runOnMainThread的，移除Runnable
     *
     * @param r
     */
    public static void removeCallbackOnMainThread(Runnable r) {
        mainHandler.removeCallbacks(r);
    }

    public static void removeCallbackInBackground(Runnable runnable) {
        Runnable mainRunnable = mapToMainHandler.get(runnable);
        if (mainRunnable != null) {
            mainHandler.removeCallbacks(mainRunnable);
        }
    }

    public static void logStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("getActiveCount");
        sb.append(pool.getActiveCount());
        sb.append("\ngetTaskCount");
        sb.append(pool.getTaskCount());
        sb.append("\ngetCompletedTaskCount");
        sb.append(pool.getCompletedTaskCount());
        Log.d(TAG, sb.toString());
    }

    public static StringBuilder logAllThreadStackTrace() {
        StringBuilder builder = new StringBuilder();
        Map<Thread, StackTraceElement[]> liveThreads = Thread.getAllStackTraces();
        for (Iterator<Thread> i = liveThreads.keySet().iterator(); i.hasNext(); ) {
            Thread key = i.next();
            builder.append("Thread ").append(key.getName())
                    .append("\n");
            StackTraceElement[] trace = liveThreads.get(key);
            for (int j = 0; j < trace.length; j++) {
                builder.append("\tat ").append(trace[j]).append("\n");
            }
        }
        return builder;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            final int index = i;
            System.out.println("index=" + index);
            CThreadPoolExecutor.runInBackground(new Runnable() {
                @Override
                public void run() {
                    System.out.println("正在运行第[" + (index + 1) + "]个线程.");
                }
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
