package com.buptcpr.demo.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
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
    @Id
    @GeneratedValue
    private String adminID;

}
