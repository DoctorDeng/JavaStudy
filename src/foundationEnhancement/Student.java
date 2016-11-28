package foundationEnhancement;

import java.util.Date;

public class Student {
    private Date birth;
    private String name;
    private int age;
    private boolean sex;

    @Override
    public String toString() {
        return "Student{" +
                "birth=" + birth +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    public Student(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        birth = new Date();
    }
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
    public boolean isSex() {
        return sex;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}