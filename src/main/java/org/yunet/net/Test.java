package org.yunet.net;

/**
 * Created by CL-PC202 on 2017/7/7.
 */
public class Test {
    public static void main(String[] args) {
        A a = new A();
        a.setStr("123");
        String str = a.getStr();
        System.out.println(a.getStr()+"前---");
        str = "456";
        System.out.println(a.getStr()+"后xxxx"+str);
    }
}
