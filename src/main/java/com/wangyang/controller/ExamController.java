package com.wangyang.controller;

import com.wangyang.dto.PageBean;
import com.wangyang.entity.Exam;
import com.wangyang.entity.Score;
import com.wangyang.service.TeacherService;
import com.wangyang.util.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(produces = "application/json")
public class ExamController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/exams",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageBean<Exam> getAll(){
        return new PageBean<>(teacherService.selectAllExam());
    }


    @RequestMapping(value = "/exams",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestParam(value = "examDate") String examDate,
                    @RequestParam(value = "examFile") MultipartFile examFile,
                    @RequestParam(value = "examIndexInFile") Integer examIndexInFile) {

        Map<String,List<Score>> map = null;
        try {
            map = ExcelReader.readExcel2007AsScore(examFile,examIndexInFile);
        } catch (Exception e) {

        }
        Iterator entryIter = map.entrySet().iterator();
        Map.Entry<String,List<Score>> entry = null;
        Exam exam = null;
        if(entryIter.hasNext()){
            entry = (Map.Entry)entryIter.next();
            exam = new Exam(entry.getKey(),examDate);
            teacherService.insertOneExam(exam);
            for(Score score : entry.getValue()){
                score.setExamId(exam.getId());
            }
            teacherService.insertManyScore(entry.getValue());
        }
    }

    @RequestMapping(value = "/exams/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        teacherService.deleteExamById(id);
    }

}
