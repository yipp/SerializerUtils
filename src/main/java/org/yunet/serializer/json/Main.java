package org.yunet.serializer.json;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

public class Main {
    static final Logger log = Logger.getLogger(Main.class);
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

        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        System.out.println(jsonString);
        /**json转对象*/
        Group group2 = JSON.parseObject(jsonString, Group.class);
        System.out.println(group2.toString());
    }
}