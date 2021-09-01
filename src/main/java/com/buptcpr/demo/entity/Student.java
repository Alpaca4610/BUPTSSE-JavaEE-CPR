package com.buptcpr.demo.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Student {
    //账号密码
    @NotBlank(message = "密码不可为空")
    private String passwd;

    //用户信息
    @NotBlank
    private String name;
    @NotBlank(message = "用户不可为空")
    @Id
    private String studentID;
    @NotBlank
    private String classID;

    private int score;

    private int myrank;

    public interface Login{}

    public interface Update{}
}
