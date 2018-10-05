package com.wangyang.mapper;


import com.wangyang.entity.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> selectAll();

    int updateById(Student student);

    int deleteAll();

    int insertMany(List<Student> students);
}
