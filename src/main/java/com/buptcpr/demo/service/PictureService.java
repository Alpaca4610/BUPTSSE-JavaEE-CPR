package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.PictureRepository;
import com.buptcpr.demo.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
public class PictureService {
    @Resource
    private PictureRepository pictureRepository;
    @Autowired
    private MD5Util md5Util;

    public String savePhoto(MultipartFile file, String pictureName) throws Exception{
        Picture picture = new Picture();
        byte[] bytes = file.getBytes();
        picture.setPicture(bytes);
        picture.setPictureName(pictureName);
        System.out.println("pictureName: " + pictureName);
        pictureRepository.save(picture);
        return "Saved";
    }

    //修改学生信息
    public String update( MultipartFile file, String pictureName) throws Exception{

        Picture picture = (Picture) pictureRepository.findByPictureName(pictureName);

        if(picture == null){
            return "fail_picture_not_exits";
        }else{
            byte[] bytes = file.getBytes();
            picture.setPicture(bytes);
            pictureRepository.save(picture);
            return "Saved";
        }
    }

    //删除学生
    public String delete(String pictureName){
        Picture picture = (Picture) pictureRepository.findByPictureName(pictureName);
        if(picture == null){
            return "picture_not_exits";
        }else{
            pictureRepository.deleteById(picture.getPictureID());
            return "success";
        }
    }


    public byte[] getImage(String pictureName){
        Picture picture = (Picture) pictureRepository.findByPictureName(pictureName);
        if(picture == null){
            return new byte[]{'n','o','t','_','e','x','i','s','t'};
        }
        return picture.getPicture();
    }

}
