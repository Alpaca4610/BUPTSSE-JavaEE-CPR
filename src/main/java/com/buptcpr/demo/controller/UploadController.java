package com.buptcpr.demo.controller;

import com.buptcpr.demo.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @GetMapping("/upload")
    public Result upload() {
        return Result.success(null);
    }

    @PostMapping("/upload")
    public @ResponseBody Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return Result.error("1","上传失败，请选择文件");
        }

        String fileName = file.getOriginalFilename();
        File dest = new File(this.getClass().getResource("/").getPath()+"/Excel");
        int addcopy=0;
        addcopy=NameDetect(this.getClass().getResource("/").getPath()+"/Excel/"+fileName);

        if(!dest.exists()){//如果文件夹不存在
            dest.mkdir();//创建文件夹
        }
        dest = new File(this.getClass().getResource("/").getPath()+"/Excel/" +strconcater(fileName,addcopy));

        /*
            也许这里需要一个记录器保存已上传的文件列表？
         */
        dest.createNewFile();

        try {
            file.transferTo(dest);
            return Result.success(null);
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return Result.error("1","上传失败");
    }

    private int NameDetect(String s) {
        int i=0;
        if (new File(s).exists())
            i++;
        else
            return i;
        String[] names=s.split("\\.",0);
        return i+NameDetect(names[0] + "_copy." +names[1]);
    }

    String strconcater (String s, int addcopy) throws Exception {
        String[] names=s.split("\\.",0);
        if(addcopy!=0) {
            if (names.length == 2) {
                s = names[0] + "_copy." +names[1];
                return strconcater(s,addcopy-1);
            } else
                throw new Exception("文件名错误");
        }else
            return s;
    }


}