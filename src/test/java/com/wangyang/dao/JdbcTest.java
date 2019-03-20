package com.wangyang.dao;

import com.wangyang.domain.MysqlTimeTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class JdbcTest {

  private Connection connection;

  @Before
  public void init() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding" +
      "=UTF-8&useLegacyDatetimeCode=false", "root", "12345678");

  }

  @Test
  public void insert() throws SQLException {
    String insert = "INSERT INTO mysql_time_test(date_time) VALUES (?)";
    PreparedStatement preparedStatement = connection.prepareStatement(insert);
    System.out.println(System.currentTimeMillis());
    preparedStatement.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
    preparedStatement.executeUpdate();
  }

  @Test
  public void get() throws SQLException {
    String get = "select date_time from mysql_time_test order by create_time desc limit 1";
    ResultSet resultSet = connection.createStatement().executeQuery(get);
    if(resultSet.next()) {
      System.out.println(new String(resultSet.getBytes(1),StandardCharsets.UTF_8));
    }
  }


  @Test
  public void verify() {
    long client = 1545660529000L;
    long server = 1545631729;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String c = dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(client),
      ZoneId.of("UTC")));
    String s = dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochSecond(server),
      ZoneId.of("+08:00")));
    System.out.println(c);
    System.out.println(s);
  }

  @After
  public void destory() throws SQLException {
    connection.close();
  }
}