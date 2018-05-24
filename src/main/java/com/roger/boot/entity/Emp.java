package com.roger.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {

    private Integer empno;
    private String ename;
    private String job;
    private Date hiredate;
    private Float sal;
    private String dname;
}
