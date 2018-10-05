package com.wangyang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value = "/allExam")
    public String getAllExamPage(){
        return "allExam";
    }

    @RequestMapping(value = "/main")
    public String getMainPage(){
        return "main";
    }

    @RequestMapping(value = "/singleExam")
    public String getSingleExamPage(){
        return "singleExam";
    }
}