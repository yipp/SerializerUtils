package org.yunet.serializer.protostuff;

import io.protostuff.Tag;

/**
 * Created by CL-PC202 on 2017/7/3.
 */
public class SerializerTest {
    @Tag(1)
    private String name;
    @Tag(2)
    private String studentNo;
    @Tag(3)
    private int age;
    @Tag(4)
    private String schoolName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    @Override
    public String toString() {
        return "SerializerTest{" +
                "name='" + name + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", age=" + age +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
