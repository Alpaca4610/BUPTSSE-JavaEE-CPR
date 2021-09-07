package com.buptcpr.demo.DAO;


import com.buptcpr.demo.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Integer> {

    Object findBypictureID(Integer pictureID);
}
