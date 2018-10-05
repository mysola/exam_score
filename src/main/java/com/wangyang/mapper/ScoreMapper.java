package com.wangyang.mapper;


import com.wangyang.entity.Score;

import java.util.List;

public interface ScoreMapper {

    List<Score> selectByExamId(Integer id);

    int insertMany(List<Score> scores);

    int updateByExamIdAndStuId(Score score);

    int deleteByExamId(Integer id);

    int deleteAll();

}
