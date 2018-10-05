package com.wangyang.service;

import com.wangyang.dto.ManyExam;
import com.wangyang.dto.SingleExam;
import com.wangyang.entity.Exam;
import com.wangyang.entity.Score;
import com.wangyang.entity.Student;

import java.util.List;

public interface TeacherService {
    //----student start
    List<Student> selectAllStudent();

    int updateStudentById(Student student);

    void deleteAll();

    int insertManyStudent(List<Student> students);
    //----student end

    //----exam start
    List<Exam> selectAllExam();

    int insertOneExam(Exam exam);

    int deleteExamById(Integer id);
    //----exam start

    //----score start
    List<SingleExam> selectScoreByExamId(Integer lastExamId,Integer curExamId);

    List<ManyExam> selectAllScore();

    int insertManyScore(List<Score> scores);

    int updateScoreByExamIdAndStuId(Score score);
    //----score end
}
