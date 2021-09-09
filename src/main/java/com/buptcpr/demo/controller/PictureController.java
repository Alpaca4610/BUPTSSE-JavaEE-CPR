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
    //输入的pictureID就是图片的名字
    @RequestMapping(value = "/savePic", method = RequestMethod.POST)
    public Result queryBaseInfo(@RequestParam MultipartFile file , @RequestParam String pictureID){
        //String filename = pictureService1.fileUpload(file, Name);
        return pictureService1.savePhoto(file, pictureID);
        // return new CommonResult(200,"上传成功",filename);
    }

    //通过id获取图片url
    @RequestMapping(value = "/getPic",method = RequestMethod.POST)
    public @ResponseBody String GetImage(@RequestParam String  pictureID){
        return pictureService1.getImage(pictureID);
    }

    //根据id 修改图片信息
    //pictureID是图片名字
    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public Result updatePic(@RequestParam MultipartFile file , @RequestParam String pictureID){
            pictureService1.update(file, pictureID);

        return Result.success(null);
    }

    //根据id从数据库删除图片url信息
    //pictureID是图片名字
    @RequestMapping(path="/deletePic")
    public @ResponseBody String pictureDelete(@RequestParam String pictureID)
    {
        return pictureService1.delete(pictureID);
    }
}
