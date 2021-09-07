package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.PictureRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.service.MD5Util;
import com.buptcpr.demo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping(path="/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService1;
    @Autowired
    private PictureRepository pictureRepository1;
    @Autowired
    private MD5Util md5Util;

    //存储图片到本地文件夹/images/ 并将url存入数据库
    @RequestMapping(value = "/savePic", method = RequestMethod.GET)
    public String queryBaseInfo(@RequestParam MultipartFile file , @RequestParam String Name){
        //String filename = pictureService1.fileUpload(file, Name);
        return pictureService1.savePhoto(file, Name);
        // return new CommonResult(200,"上传成功",filename);
    }

    //通过id获取图片url
    @RequestMapping(value = "/getPic",method = RequestMethod.GET)
    public @ResponseBody String GetImage(@RequestParam Integer pictureID){
        return pictureService1.getImage(pictureID);
    }

    //根据id 修改图片信息
    @RequestMapping(value = "/updatePic", method = RequestMethod.GET)
    public Result updatePic(@RequestParam MultipartFile file , @RequestParam Integer pictureID, @RequestParam String Name){
            pictureService1.update(file, pictureID, Name);

        return Result.success(null);
    }

    //根据id从数据库删除图片url信息
    @RequestMapping(path="/deletePic")
    public @ResponseBody String pictureDelete(@RequestParam Integer pictureID)
    {
        return pictureService1.delete(pictureID);
    }
}
