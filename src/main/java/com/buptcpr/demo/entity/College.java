package com.buptcpr.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

//todo 添加验证约束
@Data
@Entity
public class College {

    @Id
    @GeneratedValue
    private int collegeID;//大学ID

    @NotBlank(message = "大学名称不能为空")
    private String name;//大学名称

    private int crank;//投档线对应的排名

    private int tier; //一本、二本、三本

    private String kind;//大学种类

    private int score;//投档线
}
