package com.wangyang.service.impl;

import com.wangyang.dto.ManyExam;
import com.wangyang.dto.SingleExam;
import com.wangyang.entity.Exam;
import com.wangyang.entity.Score;
import com.wangyang.entity.Student;
import com.wangyang.mapper.ExamMapper;
import com.wangyang.mapper.ScoreMapper;
import com.wangyang.mapper.StudentMapper;
import com.wangyang.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

  private static final int MAX_STU_SUM = 80;

  private static final Comparator<SingleExam> COMPARATOR = (o1, o2) -> {
    if (o1.getStuNum() < o2.getStuNum()) {
      return -1;
    }
    if (o1.getStuNum().equals(o2.getStuNum())) {
      return 0;
    }
    return 1;
  };

  @Autowired
  private StudentMapper studentMapper;

  @Autowired
  private ExamMapper examMapper;

  @Autowired
  private ScoreMapper scoreMapper;

  public List<Student> selectAllStudent() {
    return studentMapper.selectAll();
  }

  public int updateStudentById(Student student) {
    int result = studentMapper.updateById(student);
    return result;
  }

  public void deleteAll() {
    scoreMapper.deleteAll();
    examMapper.deleteAll();
    studentMapper.deleteAll();
  }

  public int insertManyStudent(List<Student> students) {
    return studentMapper.insertMany(students);
  }

  public List<Exam> selectAllExam() {
    return examMapper.selectAll();
  }

  public int insertOneExam(Exam exam) {
    return examMapper.insertOne(exam);
  }

  public int deleteExamById(Integer id) {
    scoreMapper.deleteByExamId(id);
    return examMapper.deleteById(id);
  }

  protected List<Score> correctRank(List<Score> exam) {
    int j = 0, realRank = 0;
    for (int i = 0; i < exam.size(); i++) {
      j = i - 1;
      while (j >= 0 && exam.get(i).getChineseScore().equals(exam.get(j).getChineseScore())) {
        j--;
      }
      realRank = j < 0 ? 1 : exam.get(i).getChineseRank() - i + j + 1;
      exam.get(i).setChineseRank(realRank);
    }
    return exam;
  }

  protected List<SingleExam> generateSingleExam(List<Score> lastExam, List<Score> curExam) {
    List<SingleExam> singleExams = new ArrayList<>(MAX_STU_SUM);
    if (curExam == null || curExam.isEmpty())
      return singleExams;
    if (lastExam == null || lastExam.isEmpty())
      return generateSingleExam(curExam);
    for (int i = 0; i < curExam.size(); i++) {
      for (int j = 0; i - j >= 0 || i + j < lastExam.size(); j++) {
        if (i - j >= 0)
          if (lastExam.get(i - j).getStuNum().equals(curExam.get(i).getStuNum())) {
            singleExams.add(new SingleExam(curExam.get(i), lastExam.get(i - j)));
            break;
          }
        if (i + j < lastExam.size())
          if (lastExam.get(i + j).getStuNum().equals(curExam.get(i).getStuNum())) {
            singleExams.add(new SingleExam(curExam.get(i), lastExam.get(i + j)));
            break;
          }
      }
    }
    return singleExams;
  }

  protected List<SingleExam> generateSingleExam(List<Score> curExam) {
    List<SingleExam> singleExams = new ArrayList<>(MAX_STU_SUM);
    for (Score score : curExam) {
      singleExams.add(new SingleExam(score));
    }
    return singleExams;
  }

  public List<SingleExam> selectScoreByExamId(Integer lastExamId, Integer curExamId) {
    List<SingleExam> singleExams = null;
    //取出考试的分数
    List<Score> curScores = scoreMapper.selectByExamId(curExamId);
    correctRank(curScores);
    //存在对比的考试
    if (lastExamId != null) {
      List<Score> lastScores = scoreMapper.selectByExamId(lastExamId);
      correctRank(lastScores);
      singleExams = generateSingleExam(lastScores, curScores);
    } else {
      singleExams = generateSingleExam(curScores);
    }
    return singleExams;
  }

  public List<ManyExam> selectAllScore() {
    List<ManyExam> manyExams = new ArrayList<>(MAX_STU_SUM);
    //取出所有考试
    List<Exam> exams = examMapper.selectAll();
    if (exams.isEmpty()) {
      return manyExams;
    }

    //取出所有考试的分数
    List<List<SingleExam>> singleExamsList = new ArrayList<>(exams.size());
    List<Score> lastExam = null, curExam = null;
    List<SingleExam> temp = null;
    for (int i = 0; i < exams.size(); i++) {
      curExam = scoreMapper.selectByExamId(exams.get(i).getId());
      temp = generateSingleExam(lastExam, curExam);
      temp.sort(COMPARATOR);
      singleExamsList.add(temp);
      lastExam = curExam;
    }

    ManyExam manyExam = null;
    for (int i = 0; i < singleExamsList.size(); i++) {
      for (int j = 0; j < singleExamsList.get(0).size(); j++) {
        if (i == 0) {
          manyExam = new ManyExam(singleExamsList.size());
          manyExam.setStuNum(singleExamsList.get(i).get(j).getStuNum());
          manyExam.setStuName(singleExamsList.get(i).get(j).getStuName());
          manyExams.add(manyExam);
        }
        manyExam = manyExams.get(j);
        manyExam.addExam(singleExamsList.get(i).get(j));
      }
    }
    return manyExams;
  }

  public int insertManyScore(List<Score> scores) {
    return scoreMapper.insertMany(scores);
  }

  public int updateScoreByExamIdAndStuId(Score score) {
    int result = scoreMapper.updateByExamIdAndStuId(score);
    return result;
  }


}
