package com.wangyang;

import com.wangyang.convert.ManyExamPageBeanMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "com.wangyang.mapper")
public class ExamScoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExamScoreApplication.class, args);
  }

  @Bean
  public HttpMessageConverters customConverters(){
    return new HttpMessageConverters(new ManyExamPageBeanMessageConverter());
  }
}
