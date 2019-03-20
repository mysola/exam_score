package com.wangyang.dao;

import com.wangyang.domain.MysqlTimeTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DaoTest {

  @Autowired
  private MysqlTimeTestDao mysqlTimeTestDao;

  @Test
  @Rollback(false)
  public void test() {
    MysqlTimeTest mysqlTimeTest = new MysqlTimeTest();
    mysqlTimeTest.setDateTime(new Date());
    mysqlTimeTest.setTimeStamp(new Date());
//    System.out.println(System.currentTimeMillis());
//    int insert = mysqlTimeTestDao.insert(mysqlTimeTest);
    mysqlTimeTestDao.getAll().forEach(mysqlTimeTest1 -> System.out.println(mysqlTimeTest1.getTimeStamp().getTime()));
  }
}