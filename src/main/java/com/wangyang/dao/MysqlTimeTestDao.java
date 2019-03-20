package com.wangyang.dao;


import com.wangyang.domain.MysqlTimeTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MysqlTimeTestDao {
  String TABLE = "mysql_time_test";

  @Insert("insert into " + TABLE + "(time_stamp,date_time) values(#{timeStamp},#{dateTime})")
  int insert(MysqlTimeTest mysqlTimeTest);

  @Select("select * from " + TABLE)
  List<MysqlTimeTest> getAll();
}