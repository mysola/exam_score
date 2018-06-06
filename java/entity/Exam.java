package entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable{
    @Min(1)
    @NotNull
    private Integer id;
    private String examName;
    private Date examDate;

    public Exam() {
    }

    public Exam(String examName, Date examDate) {
        this.examName = examName;
        this.examDate = examDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", examName='" + examName + '\'' +
                ", examDate=" + examDate +
                '}';
    }
}
