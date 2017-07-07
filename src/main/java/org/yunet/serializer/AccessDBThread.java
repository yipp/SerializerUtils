package org.yunet.serializer;

//线程池中工作的线程
public class AccessDBThread implements Runnable {
 
    private String msg;
     

    public AccessDBThread(String msg) {
        this.msg = msg;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void run() {

        // 向数据库中添加Msg变量值
        System.out.println("Added the message: "+msg+" into the Database");
    }
 
}