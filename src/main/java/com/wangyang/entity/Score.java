package com.wangyang.entity;

import java.io.Serializable;

public class Score implements Serializable {

  private Integer stuNum;

  private String stuName;

  private Integer examId;

  private Double chineseScore;

  private Integer chineseRank;

  public Score() {
  }

  public Score(Integer stuNum, Double chineseScore) {
    this.stuNum = stuNum;
    this.chineseScore = chineseScore;
  }

  @Override
  public String toString() {
    return "Score{" +
      "stuNum=" + stuNum +
      ", stuName='" + stuName + '\'' +
      ", examId=" + examId +
      ", chineseScore=" + chineseScore +
      ", chineseRank=" + chineseRank +
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

  public Integer getExamId() {
    return examId;
  }

  public void setExamId(Integer examId) {
    this.examId = examId;
  }

  public Double getChineseScore() {
    return chineseScore;
  }

  public void setChineseScore(Double chineseScore) {
    this.chineseScore = chineseScore;
  }

  public Integer getChineseRank() {
    return chineseRank;
  }

  public void setChineseRank(Integer chineseRank) {
    this.chineseRank = chineseRank;
  }
}
