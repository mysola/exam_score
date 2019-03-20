package com.wangyang.controller;


import com.wangyang.dao.MysqlTimeTestDao;
import com.wangyang.domain.MysqlTimeTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(produces = "application/json")
public class StuController {

    @Autowired
    private MysqlTimeTestDao mysqlTimeTestDao;

    @GetMapping(value = "/times")
    @ResponseStatus(HttpStatus.OK)
    public List<MysqlTimeTest> getAll(){
      return mysqlTimeTestDao.getAll();
    }

}
