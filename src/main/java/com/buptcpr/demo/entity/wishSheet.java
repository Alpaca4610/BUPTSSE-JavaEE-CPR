package com.buptcpr.demo.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

//todo 完善验证约束

@Data
public class wishSheet {

    @NotBlank(message = "学号不能为空")
    private String studentID;//学号

    private int wishA;//志愿A

    private int wishB;//志愿B

    private int wishC;//志愿C
}
