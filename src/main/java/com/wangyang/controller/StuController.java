package com.wangyang.controller;


import com.wangyang.dto.PageBean;
import com.wangyang.entity.Student;
import com.wangyang.service.TeacherService;
import com.wangyang.util.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;


@RestController
@Validated
@RequestMapping(produces = "application/json")
public class StuController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/students",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageBean<Student> getAll(){
        return new PageBean<>(teacherService.selectAllStudent());
    }

    @RequestMapping(value = "/students",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestParam(value = "stuFile") MultipartFile stuFile) {
        List<Student> students = null;
        try {
            students = ExcelReader.readExcel2007AsStudent(stuFile);
        } catch (Exception e) {

        }
        teacherService.insertManyStudent(students);
    }


    @RequestMapping(value = "/students",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        teacherService.deleteAll();
    }

    @RequestMapping(value = "/students",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@Validated @RequestBody Student student){
        teacherService.updateStudentById(student);
    }
}
