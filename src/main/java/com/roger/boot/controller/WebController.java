package com.roger.boot.controller;

import com.roger.boot.entity.Dept;
import com.roger.boot.entity.Emp;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class WebController {

    private List<Emp> emps = new ArrayList<>();
    private List<Dept> depts = new ArrayList<>();

    @Value("${app.upload.location}")
    private String uploadFileLocation = null;

    public WebController() throws Exception{
        emps.add(new Emp(7782, "CLARK", "DEVELOPER", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-02"), 7780f, "RESEARCH",null));
        emps.add(new Emp(7839, "KING", "CSO", new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-04"), 8820f, "SALES",null));
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

    @GetMapping("/dept")
    @ResponseBody
    public List<Dept> obtainDepts(){
        List<Dept> deptList = new ArrayList<>();
        deptList.add(new Dept(-1,"请选择",null));
        deptList.addAll(depts);
        return deptList;
    }

    @GetMapping("/job")
    @ResponseBody
    public List<String> obtainJobsByDName(String dName){
        List<String> jobList = new ArrayList<>();
        jobList.add("请选择");
        if(dName.equals("REASERCH")){
            jobList.add("CTO");
            jobList.add("Programmer");
        }
        if(dName.equals("SALES")){
            jobList.add("CSO");
            jobList.add("Saler");
        }
        if(dName.equals("ACCOUNTING")){
            jobList.add("CFO");
            jobList.add("Cashier");
        }
        return jobList;
    }


    @PostMapping("/addEmp")
    public ModelAndView addEmp(Emp emp,@RequestParam("photo") MultipartFile photo) throws IOException {

        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String originalFileName = photo.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        emp.setPhotoFile(fileName+extension);
        emps.add(emp);

        FileCopyUtils.copy(photo.getInputStream(),new FileOutputStream(uploadFileLocation+fileName+extension));

        ModelAndView modelAndView = new ModelAndView("redirect:/");

        return modelAndView;
    }

}
