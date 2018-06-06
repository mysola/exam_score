package mapper;

import entity.Exam;

import java.util.List;

public interface ExamMapper {
    List<Exam> selectAll();

    int insertOne(Exam exam);

    int deleteAll();

    int deleteById(Integer id);
}