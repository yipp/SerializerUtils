package org.yunet.serializer.protostuff;

/**
 * Created by CL-PC202 on 2017/7/3.
 */
public class SerializerTest {
    private String name;
    private String studentNo;
    private int age;
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
