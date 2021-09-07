package com.buptcpr.demo.service;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class CreateCommentaryTableInsertPrint {
    static final String CreateTable="create table Commentary(Commenter varchar(40),Body varchar(255),Upvote int,Downvote int,id int not null auto_increment,primary key(id));";
    static final String insExample="insert into Commentary values(\"1\",\"1\",-1,-1,0);";
    static final String TestExist = "select * from Commentary where id=0;";

    public static Boolean Insert(JdbcTemplate j,String strs0,String strs1,int i1,int i2)
    {
        try{j.queryForList(TestExist);}
        catch(Exception e){
            j.execute(CreateTable);
            j.execute(insExample);
        }
        try{j.execute(String.format("insert into Commentary values(\"%s\",\"%s\",%d,%d)",strs0,strs1,i1,i2));}
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
    public static List<Map<String,Object>> Result(JdbcTemplate j)
    {
        return j.queryForList("select * from Commentary;");
    }

}
