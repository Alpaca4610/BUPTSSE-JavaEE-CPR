package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.PictureRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.service.MD5Util;
import com.buptcpr.demo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(path="/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private MD5Util md5Util;

    //savePicture
    @RequestMapping(value = "/savePic", method = RequestMethod.POST)
    public Result queryBaseInfo(@RequestParam MultipartFile file , @RequestParam String pictureName){
        try {
            pictureService.savePhoto(file, pictureName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(null);

    }
    //update picture
    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public Result updatePic(@RequestParam MultipartFile file , @RequestParam String pictureName){

        try {
            pictureService.update(file, pictureName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(null);

    }

    //delete picture
    @RequestMapping(path="/deletePic")
    public @ResponseBody String pictureDelete(@RequestParam String pictureName)
    {
        return pictureService.delete(pictureName);
    }


    //read
    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public @ResponseBody byte[] GetImage(@RequestParam String pictureName)throws IOException{
        return pictureService.getImage(pictureName);
    }
}
