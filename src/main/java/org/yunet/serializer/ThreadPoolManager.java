package org.yunet.serializer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class ThreadPoolManager {
     
    private static ThreadPoolManager tpm = new ThreadPoolManager();
     
    // 线程池维护线程的最少数量
    private final static int CORE_POOL_SIZE = 4;
     
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 10;
     
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
     
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 10;
     
    // 消息缓冲队列
    Queue<String> msgQueue = new LinkedList<String>();
     

     
    final RejectedExecutionHandler handler = new RejectedExecutionHandler(){
 
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(((AccessDBThread )r).getMsg()+"消息放入队列中重新等待执行");
            msgQueue.offer((( AccessDBThread ) r ).getMsg() );
        }
    };
     
    // 管理数据库访问的线程池
     
    @SuppressWarnings({ "rawtypes", "unchecked" })
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,new ArrayBlockingQueue( WORK_QUEUE_SIZE ), this.handler);
     
    // 调度线程池

     
     
    @SuppressWarnings("rawtypes")

    public static ThreadPoolManager newInstance() {
         
        return tpm;
    }
     
    private ThreadPoolManager(){}
     


    public void addLogMsg( String msg ) {
        Runnable task = new AccessDBThread( msg );
        threadPool.execute( task );
    }
}