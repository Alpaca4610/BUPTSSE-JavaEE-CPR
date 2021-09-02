package com.buptcpr.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

//todo 添加验证约束
@Data
@Entity
public class College {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int collegeID;//大学ID

    @NotBlank(message = "大学名称不能为空")
    private String name;//大学名称

    private int crank;//投档线对应的排名

    private int tier; //一本、二本、三本

    private String kind;//大学种类

    private int score;//投档线

    public College() {
    }

    public College(int collegeID, String name, int crank, int tier, String kind, int score) {
        this.collegeID = collegeID;
        this.name = name;
        this.crank = crank;
        this.tier = tier;
        this.kind = kind;
        this.score = score;
    }

    public int getCollegeID() {
        return collegeID;
    }

    public void setCollegeID(int collegeID) {
        this.collegeID = collegeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCrank() {
        return crank;
    }

    public void setCrank(int crank) {
        this.crank = crank;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

