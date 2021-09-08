package com.buptcpr.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

//todo 添加验证约束

@Entity
public class College {

    @Id
    private String collegeID;//大学ID

    @NotBlank(message = "大学名称不能为空")
    private String name;//大学名称

    private int crank;//投档线对应的排名

    private int prank;//

    private int tier; //一本、二本、三本

    private String kind;//大学种类

    private int score;//投档线

    private int countSelected;//统计选择人数

    private int headCount;//统计录取人数

    public College() {
    }

    public College(String collegeID, String name, int crank, int tier, String kind, int score, int count) {
        this.collegeID = collegeID;
        this.name = name;
        this.crank = crank;
        this.tier = tier;
        this.kind = kind;
        this.score = score;
        this.countSelected = 0;
    }

    public String getCollegeID() {
        return collegeID;
    }

    public void setCollegeID(String collegeID) {
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

    public int getCountSelected() {
        return countSelected;
    }

    public void setCountSelected(int countSelected) {
        this.countSelected = countSelected;
    }

    public void setHeadCount(int countAdmitted) { this.headCount = countAdmitted; }

    public int getHeadCount() { return headCount; }

    public int getPrank() { return prank; }

    public void setPrank(int prank) { this.prank = prank; }
}

