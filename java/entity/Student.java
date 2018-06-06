package entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Student implements Serializable{
    @Min(1)
    @NotNull
    private Integer stuNum;
    @Length(min=2,max = 10)
    @NotNull
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
