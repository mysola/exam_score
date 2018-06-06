package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManyExam implements Serializable{
    private Integer stuNum;
    private String stuName;
    private List<Double> stuScores;
    private List<Integer> stuRanks;
    private List<Double> scoreVarys;
    private List<Integer> rankVarys;
    private Integer examSum;

    public ManyExam() {
    }

    public ManyExam(Integer examSum) {
        this.examSum = examSum;
        stuScores = new ArrayList<>(examSum);
        stuRanks = new ArrayList<>(examSum);
        scoreVarys = new ArrayList<>(examSum);
        rankVarys = new ArrayList<>(examSum);
    }

    public void addExam(SingleExam singleExam){
        stuScores.add(singleExam.getStuScore());
        stuRanks.add(singleExam.getStuRank());
        scoreVarys.add(singleExam.getScoreVary());
        rankVarys.add(singleExam.getRankVary());
    }

    @Override
    public String toString() {
        return "ManyExam{" +
                ", stuNum=" + stuNum +
                ", stuName='" + stuName + '\'' +
                ", stuScores=" + stuScores +
                ", stuRanks=" + stuRanks +
                ", scoreVarys=" + scoreVarys +
                ", rankVarys=" + rankVarys +
                ", examSum=" + examSum +
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

    public List<Double> getStuScores() {
        return stuScores;
    }

    public void setStuScores(List<Double> stuScores) {
        this.stuScores = stuScores;
    }

    public List<Integer> getStuRanks() {
        return stuRanks;
    }

    public void setStuRanks(List<Integer> stuRanks) {
        this.stuRanks = stuRanks;
    }

    public List<Double> getScoreVarys() {
        return scoreVarys;
    }

    public void setScoreVarys(List<Double> scoreVarys) {
        this.scoreVarys = scoreVarys;
    }

    public List<Integer> getRankVarys() {
        return rankVarys;
    }

    public void setRankVarys(List<Integer> rankVarys) {
        this.rankVarys = rankVarys;
    }

    public Integer getExamSum() {
        return examSum;
    }

    public void setExamSum(Integer examSum) {
        this.examSum = examSum;
    }
}
