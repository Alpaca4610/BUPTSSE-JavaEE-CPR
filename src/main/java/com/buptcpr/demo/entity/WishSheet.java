package com.buptcpr.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

//todo 完善验证约束

@Data
@Entity
public class WishSheet {

    @NotBlank(message = "学号不能为空")
    @Id
    private String studentID;//学号

    private String wishA;//志愿A

    private String wishB;//志愿B

    private String wishC;//志愿C
}
