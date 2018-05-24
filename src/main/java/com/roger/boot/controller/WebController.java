package com.roger.boot.controller;

import com.roger.boot.entity.Dept;
import com.roger.boot.entity.Emp;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    private List<Emp> emps = new ArrayList<>();
    private List<Dept> depts = new ArrayList<>();

    public WebController() throws Exception{
        emps.add(new Emp(7782, "CLARK", "DEVELOPER", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-02"), 7780f, "RESEARCH"));
        emps.add(new Emp(7839, "KING", "CSO", new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-04"), 8820f, "SALES"));
        depts.add(new Dept(10,"REASERCH" , new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-07")));
        depts.add(new Dept(20,"SALES" , new SimpleDateFormat("yyyy-MM-dd").parse("2015-12-01")));
        depts.add(new Dept(30,"ACCOUNTING" , new SimpleDateFormat("yyyy-MM-dd").parse("2013-03-02")));
    }

    @RequestMapping(value = "/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("emps",emps);
        return modelAndView;
    }
}
