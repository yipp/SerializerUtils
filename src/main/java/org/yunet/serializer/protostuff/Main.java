package org.yunet.serializer.protostuff;

import java.util.Arrays;

/**
 * Created by CL-PC202 on 2017/7/3.
 */
public class Main {
    public static void main(String[] args){
        SerializerTest s = new SerializerTest();
        s.setName("易");
        s.setAge(23);
        s.setSchoolName("理工");
        s.setStudentNo("12233121");
        ddd d = new ddd();
        d.setSerializerTest(s);
        byte[] buf = ProtostuffUtils.serializer(s);
        byte[] buf1 = ProtostuffUtils.serializer(d);

        System.out.println(Arrays.toString(buf));
        System.out.println(Arrays.toString(buf1));
        SerializerTest test = ProtostuffUtils.deserializer(buf,SerializerTest.class);
        ddd test1 = ProtostuffUtils.deserializer(buf1,ddd.class);
        System.out.println(test);
        System.out.println(test1.getSerializerTest());
    }
}
