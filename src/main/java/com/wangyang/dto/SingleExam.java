package com.wangyang.dto;

import com.wangyang.entity.Score;

import java.io.Serializable;

public class SingleExam implements Serializable{
    private Integer stuNum;
    private String stuName;
    private Double stuScore;
    private Integer stuRank;
    private Double scoreVary;
    private Integer rankVary;

    public SingleExam(Score curScore,Score lastScore) {
        this(curScore);
        this.scoreVary = curScore.getChineseScore()-lastScore.getChineseScore();
        this.rankVary = curScore.getChineseRank()-lastScore.getChineseRank();
    }


    public SingleExam(Score curScore){
        this.stuNum = curScore.getStuNum();
        this.stuName = curScore.getStuName();
        this.stuScore = curScore.getChineseScore();
        this.stuRank = curScore.getChineseRank();
    }

    public SingleExam() {
    }

    @Override
    public String toString() {
        return "SingleExam{" +
                ", stuNum=" + stuNum +
                ", stuName='" + stuName + '\'' +
                ", stuScore=" + stuScore +
                ", stuRank=" + stuRank +
                ", scoreVary=" + scoreVary +
                ", rankVary=" + rankVary +
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

    public Double getStuScore() {
        return stuScore;
    }

    public void setStuScore(Double stuScore) {
        this.stuScore = stuScore;
    }

    public Integer getStuRank() {
        return stuRank;
    }

    public void setStuRank(Integer stuRank) {
        this.stuRank = stuRank;
    }

    public Double getScoreVary() {
        return scoreVary;
    }

    public void setScoreVary(Double scoreVary) {
        this.scoreVary = scoreVary;
    }

    public Integer getRankVary() {
        return rankVary;
    }

    public void setRankVary(Integer rankVary) {
        this.rankVary = rankVary;
    }
}
