package com.wangyang.controller;

import com.wangyang.dto.ManyExam;
import com.wangyang.dto.PageBean;
import com.wangyang.dto.SingleExam;
import com.wangyang.entity.Score;
import com.wangyang.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class ScoreController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/scores",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateStuScore(@RequestBody Score score){
        teacherService.updateScoreByExamIdAndStuId(score);
    }

    @RequestMapping(value = "/exams/{id}/scores",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageBean<SingleExam> getScoreByExamId(@PathVariable("id") Integer id,
                                                 @RequestParam(value = "lastId",required = false) Integer lastId){
        return new PageBean<>(teacherService.selectScoreByExamId(lastId,id));
    }

    @RequestMapping(value = "/exams/scores",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageBean<ManyExam> getAllScore(){
        return new PageBean<>(teacherService.selectAllScore());
    }
}
