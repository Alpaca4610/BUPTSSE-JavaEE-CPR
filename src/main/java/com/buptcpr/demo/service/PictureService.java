package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.PictureRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class PictureService {

    @Resource
    private PictureRepository pictureRepository1;
    @Autowired
    private MD5Util md5Util;

    public Result savePhoto(MultipartFile file, String pictureID) {
        Picture picture = new Picture();
        String filename = fileUpload(file, pictureID);
        picture.setUrl(filename);
        picture.setPictureID(pictureID);
        System.out.println("pictureName: " + pictureID);
        pictureRepository1.save(picture);
        return Result.success();
    }

    public String fileUpload(MultipartFile file, String pictureID) {
        if (file.isEmpty()) {
            return "空文件";
        }else {
            String directory=this.getClass().getResource("/").getPath() + "/static/images/";
            File dir = new File(directory);
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = directory;  // 上传后的路径
            fileName = UUID.randomUUID().toString().replace("-", "") + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String filename = "/images/" + fileName;
            System.out.println(filename);
            System.out.println(directory);
            System.out.println(dir.getAbsolutePath());
            return filename;
        }
    }

    public String getImage(String pictureID){
        Picture picture = (Picture) pictureRepository1.findBypictureID(pictureID);
        if(picture == null){
            return "not_exists";
        }

        return picture.getUrl();
    }

    public String update( MultipartFile file, String pictureID){

        Picture picture = (Picture) pictureRepository1.findBypictureID(pictureID);

        if(picture == null){
            return "fail_picture_not_exits";
        }else{
            String filename = fileUpload(file,pictureID);
            picture.setUrl(filename);
            pictureRepository1.save(picture);
            return "updated";
        }
    }

    public String delete(String pictureID){
        Picture picture = (Picture) pictureRepository1.findBypictureID(pictureID);
        if(picture == null){
            return "picture_not_exits";
        }else{
            pictureRepository1.deleteById(pictureID);
            return "success";
        }
    }

}
