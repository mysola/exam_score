package controller;

import dto.PageBean;
import entity.Student;
import exception.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.TeacherService;
import util.ExcelReader;
import util.ValidatorUtil;

import java.io.IOException;
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
    public void add(@RequestParam(value = "stuFile") CommonsMultipartFile stuFile) {
        List<Student> students = null;
        try {
            students = ExcelReader.readExcel2007AsStudent(stuFile);
        } catch (Exception e) {
            throw new ControlException(e);
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
    public void update(@Validated @RequestBody Student student,
                       BindingResult bindingResult){
        ValidatorUtil.raiseFirstError(bindingResult);
        teacherService.updateStudentById(student);
    }
}
