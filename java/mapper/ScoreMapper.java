package mapper;

import entity.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {

    List<Score> selectByExamId(Integer id);

    int insertMany(List<Score> scores);

    int updateByExamIdAndStuId(Score score);

    int deleteByExamId(Integer id);

    int deleteAll();

}
