package com.lain.learn;

import com.lain.learn.domain.Student;
import org.junit.Test;

public class StudentTest {

    @Test
    public void test01() {
        Student li = new Student("li", "li@qq.com", -15);
        System.out.println(li.age());
        System.out.println(li.concat());
        System.out.println(Student.toUpperCase("li@qq.com"));
    }

}
