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
        byte[] buf = ProtostuffUtils.serializer(s);
        System.out.println(Arrays.toString(buf));
        SerializerTest test = ProtostuffUtils.deserializer(buf,SerializerTest.class);
        System.out.println(test.toString());
    }
}
