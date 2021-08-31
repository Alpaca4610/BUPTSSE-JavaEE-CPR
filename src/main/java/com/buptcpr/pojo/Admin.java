package com.buptcpr.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Admin {

    //账号信息
    @NotBlank
    private String username;
    @NotBlank
    private String passwd;

    //用户信息
    @NotBlank
    private String name;
    @NotBlank
    private String adminID;

}
