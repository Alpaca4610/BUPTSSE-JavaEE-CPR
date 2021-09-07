package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.UpdateAPI;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.common.exception.ValueNotMatchException;
import com.buptcpr.demo.service.CreateCommentaryTableInsertPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@ResponseBody
public class CommentaryAPI {
    @Autowired
    JdbcTemplate j;

    @PostMapping("/commentins")
    public @ResponseBody
    Result Insert(@RequestParam Comment c) throws Exception {
        CreateCommentaryTableInsertPrint.Insert(j,c.Sender,c.Body,Integer.parseInt(c.Upvote),Integer.parseInt(c.Downvote));
        int MyId=0;
        Map<String,Object> temp=j.queryForMap("select id from Commentary order by id desc limit 0,1;");
        MyId=Integer.parseInt(temp.get("id").toString());
        return Result.success(MyId);

    }

    @GetMapping("/commentinstest")
    public @ResponseBody Result<Integer> InsertTest() {
        Comment c=new Comment("Blyat","Bogos binted",1,1);
        CreateCommentaryTableInsertPrint.Insert(j,c.Sender,c.Body,Integer.parseInt(c.Upvote),Integer.parseInt(c.Downvote));
        int MyId=0;
        Map<String,Object> temp=j.queryForMap("select id from Commentary order by id desc limit 0,1;");
        MyId=Integer.parseInt(temp.get("id").toString());
        return Result.success(MyId);
    }

    @PostMapping("/vote")
    public @ResponseBody Result Upvote(@RequestParam Type i) throws ValueNotMatchException {
        String[] dt1={"0"};
        String[] dt2={"0"};
        if(i.up==1)
            new UpdateAPI(j).Result("Commentary","Upvote".split("\\.",0),"Upvote+1".split("\\.",0),
                "id".split("\\.",0),i.id.toString().split("\\.",0),dt1,dt2);
        else
            new UpdateAPI(j).Result("Commentary","Downvote".split("\\.",0),"Downvote-1".split("\\.",0),
                    "id".split("\\.",0),i.id.toString().split("\\.",0),dt1,dt2);
        return Result.success();
    }

    @RequestMapping(value="/votetest",method={RequestMethod.POST, RequestMethod.GET})
    public Result Adapter(HttpServletRequest request, HttpSession session) throws ValueNotMatchException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer upvote=1;
        String[] dt1={"0"};
        String[] dt2={"0"};
        new UpdateAPI(j).Result("Commentary","Upvote".split("\\.",0),"Upvote+1".split("\\.",0),
                "id".split("\\.",0),id.toString().split("\\.",0),dt1,dt2);
        return Result.success();
    }

    @GetMapping("/getcom")
    public Result<List<Map<String,Object>>> getCom()
    {
        return Result.success(CreateCommentaryTableInsertPrint.Result(j));
    }

}

class Comment
{
    String Sender="xxx";
    String Body="NULL";
    String Upvote="0";
    String Downvote="0";
    int id=-1;
    Comment()
    {}
    Comment(String str1,String str2,Integer i1,Integer i2)
    {
        this.Sender=str1;
        this.Body=str2;
    }

}

class Type
{
    Integer id=0;
    Integer up=1;
}

