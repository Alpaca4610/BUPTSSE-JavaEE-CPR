package com.buptcpr.demo.controller;

import com.buptcpr.demo.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-15 14:04
 * @Modified Golden-2019211981
 */
@Controller
@CrossOrigin
@RequestMapping("/excel")
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    String directory=this.getClass().getResource("/").getPath() + "/Excel/";

    String TypeLogDir="%s_value_type";

    @Autowired
    JdbcTemplate j;

    @PostMapping("/upload")
    public @ResponseBody
    Result<String> upload(@RequestParam("file") MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            return Result.success();
        }

        String fileName = file.getOriginalFilename();
        File dest = new File(this.getClass().getResource("/").getPath()+"/Excel");
        NameDetect(this.getClass().getResource("/").getPath()+"/Excel/"+fileName);

        if(dest.exists())
            DeleteDir(dest);
        if(!dest.exists()){//如果文件夹不存在
            dest.mkdir();//创建文件夹
        }

        dest = new File(this.getClass().getResource("/").getPath()+"/Excel/" +fileName);

        /*
            也许这里需要一个记录器保存已上传的文件列表？
         */
        dest.createNewFile();

        try {
            file.transferTo(dest);

            File dir=new File(directory);
            if(!dir.exists())
                return Result.error("1",String.format("文件夹%s不存在",dir.getAbsolutePath()));

            String[] child= dir.list();

            for(int i=0;i<child.length;i++) {
                if(child[i].indexOf("_type")==-1) {
                    try {
                        new ExcelToDB().SaveToDB(j, child[i]);
                    } catch (ExportFailException e) {
                        return Result.error("1", e.toString());
                    }
                }else
                    break;
            }

            return Result.success();
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return Result.error("1","qwq");
    }

    private void DeleteDir(File dest) {
        File dir=new File(directory);
        String[] child= dir.list();
        for(int i=0;i< child.length;i++)
        {
            File f=new File(directory+child[i]);
            f.delete();
        }
    }

    private void NameDetect(String s) {
        int i=0;
        File originf=new File(s);
        if (originf.exists())
        {
            originf.delete();
        }
    }
}