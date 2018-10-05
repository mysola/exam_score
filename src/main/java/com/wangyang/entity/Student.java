package com.wangyang.entity;

import java.io.Serializable;

public class Student implements Serializable{
    private Integer stuNum;

    private String stuName;

    public Student(Integer stuNum, String stuName) {
        this.stuNum = stuNum;
        this.stuName = stuName;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                ", stuNum=" + stuNum +
                ", stuName='" + stuName + '\'' +
                '}';
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}
