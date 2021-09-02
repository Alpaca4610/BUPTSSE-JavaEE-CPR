package com.buptcpr.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Class {
    @Id
    private String classID;
}
