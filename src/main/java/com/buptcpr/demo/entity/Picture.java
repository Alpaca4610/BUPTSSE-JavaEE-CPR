package com.buptcpr.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Picture {
    @Id
    private String pictureID;

    private String url;


    public String getPictureID() {
        return pictureID;
    }

    public void setPictureID(String pictureID) {
        this.pictureID = pictureID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Picture(String pictureID, String url) {
        this.pictureID = pictureID;
        this.url = url;

    }

    public Picture() {
    }
}
