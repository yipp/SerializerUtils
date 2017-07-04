package org.yunet.serializer.ecxel;

import java.lang.reflect.Field;
import java.util.Date;  
  
public class User  
{  
  
   private String name;
   private int age;
   private int limit;
   private int grade;
   private int gold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", limit=" + limit +
                ", grade=" + grade +
                ", gold=" + gold +
                '}';
    }
}