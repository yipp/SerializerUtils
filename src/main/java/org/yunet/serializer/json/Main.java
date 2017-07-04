package org.yunet.serializer.json;
import com.alibaba.fastjson.JSON;
public class Main {
    public static void main(String[] args) {
        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");
        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.getUsers().add(guestUser);
        group.getUsers().add(rootUser);
        /**对象转json*/
        String jsonString = JSON.toJSONString(group);

        System.out.println(jsonString);
        /**json转对象*/
        Group group2 = JSON.parseObject(jsonString, Group.class);
        System.out.println(group2.toString());
    }
}