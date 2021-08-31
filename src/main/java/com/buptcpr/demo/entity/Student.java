package com.buptcpr.demo.entity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Student {

    //账号信息
    @NotBlank
    private String username;
    @NotBlank
    private String passwd;

    //用户信息
    @NotBlank
    private String name;
    @NotBlank
    private String studentID;
    @NotBlank
    private String classID;
    @NotEmpty
    private int score;
    @NotEmpty
    private int rank;

}
