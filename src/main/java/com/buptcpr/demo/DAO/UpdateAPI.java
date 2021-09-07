package com.buptcpr.demo.DAO;


import com.buptcpr.demo.common.exception.ValueNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UpdateAPI {
    @Autowired
    private JdbcTemplate j;

    public UpdateAPI(JdbcTemplate j)
    {this.j=j;}
    public int Result(String TableName, String[] Domains, String[] DomainValue, String[] DomainRange, String[] DomainRangeValue, String[] DT1, String[] DT2) throws ValueNotMatchException
    {
        String SQLBody="update %s set %s where %s;";
        String Formula="";
        String range="";
        if(Domains.length!=DomainValue.length||DomainRange.length!=DomainRangeValue.length) {
            throw new ValueNotMatchException("the Domain range does not match the value needed to be changed!");
        }
        for(int i=0;i<Domains.length;i++)
        {
            Formula=Formula+Domains[i]+'='+DT(DomainValue[i],Integer.parseInt(DT1[i]))+',';
        }
        for(int i=0;i<DomainRange.length;i++)
        {
            range=range+DomainRange[i]+'='+DT(DomainRangeValue[i],Integer.parseInt(DT2[i]))+" and ";
        }
        Formula=Formula.substring(0,Formula.length()-1);
        range=range.substring(0,range.length()-4);
        String sql=String.format(SQLBody,TableName,Formula,range);
        System.out.println(sql);
        j.execute(sql);
        return 1;
    }

    private String DT(String s,int type) {
        return type==0?s:'\"'+s+'\"';
    }

}
