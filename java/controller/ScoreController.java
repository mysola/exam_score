package controller;

import dto.ManyExam;
import dto.PageBean;
import dto.SingleExam;
import entity.Score;
import exception.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.TeacherService;
import util.ValidatorUtil;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
public class ScoreController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/scores",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateStuScore(@Validated @RequestBody Score score, BindingResult bindingResult){
        ValidatorUtil.raiseFirstError(bindingResult);
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
