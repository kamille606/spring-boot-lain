package com.lain.learn.domain;

public record Student(String name, String email, Integer age) {
    public Student {
        if (age < 0) throw new RuntimeException("年龄不合规");
    }
    public Student(String name, Integer age) {
        this(name, null, age);
    }
    //实例方法
    public String concat() {
        return String.format("姓名:%s,年龄:%d", this.name, this.age);
    }
    //静态方法
    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }
}
