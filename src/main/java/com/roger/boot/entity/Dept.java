package com.roger.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {

    private Integer deptno;
    private String dname;
    private Date foundingTime;
}
