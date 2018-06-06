package controller;

import dto.PageBean;
import entity.Exam;
import entity.Score;
import exception.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.TeacherService;
import util.ExcelReader;

import java.io.IOException;
import java.util.Date;
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
    public void add(@RequestParam(value = "examDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date examDate,
                    @RequestParam(value = "examFile") CommonsMultipartFile examFile,
                    @RequestParam(value = "examIndexInFile") Integer examIndexInFile) {
        if(examDate.compareTo(new Date())>0)
            throw new ControlException("考试日期在当前日期之后");
        Map<String,List<Score>> map = null;
        try {
            map = ExcelReader.readExcel2007AsScore(examFile,examIndexInFile);
        } catch (Exception e) {
            throw new ControlException(e);
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
