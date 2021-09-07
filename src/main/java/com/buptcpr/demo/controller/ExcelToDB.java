package com.buptcpr.demo.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Controller
@ResponseBody
@CrossOrigin
public class ExcelToDB {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    HashMap<String,String> TableValueTypeLog=new HashMap<>();

    String directory=this.getClass().getResource("/").getPath() + "/Excel/";

    String TypeLogDir="%s_value_type";
    //模板

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelToDB.class);
    @RequestMapping("/printxlsx")
    public void way() throws IOException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        String dir = this.getClass().getResource("/").getPath() + "/Excel/" + "1.xlsx";
        Workbook workbook = WorkbookFactory.create(new File(dir));
        //获取一张表
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {//跳过第一行，取得其他行数据
            Row row = sheet.getRow(i);//取得第i行数据
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);//取得第j列数据
                /*
                int CELL_TYPE_NUMERIC = 0;
                int CELL_TYPE_STRING = 1;
                int CELL_TYPE_FORMULA = 2;
                int CELL_TYPE_BLANK = 3;
                int CELL_TYPE_BOOLEAN = 4;
                int CELL_TYPE_ERROR = 5;
                 */
                cell.setCellType(1);
                String value = cell.getStringCellValue();
                System.out.print("row:"+i + " line:" + j + " value:" + value);

            }
            System.out.println();
        }
    }

    //读取所有this.this.getClass().getResource("/").getPath() + "/Excel/“中的文件并且存入excel
    @RequestMapping("/sae")//Save_All_Excel
    public String SaveAllExcel() throws IOException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        File dir=new File(directory);
        if(!dir.exists())
            return String.format("文件夹%s不存在",dir.getAbsolutePath());

        String[] child= dir.list();

        for(int i=0;i<child.length;i++) {
            //System.out.print(child[i]);
            try {
                SaveToDB(child[i]);
            }
            catch(ExportFailException e)
            {
                return e.toString();
            }
        }


        return "已成功保存";
    }

    private void SaveToDB(String s) throws ExportFailException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

       InputStream input = new FileInputStream(new File(directory+s));
        //Workbook workbook = WorkbookFactory.create(new File(directory+s));
        if((directory+s).indexOf(".xlsx")!=-1) {
            XSSFWorkbook wb = new XSSFWorkbook(input);
            Sheet sheet = wb.getSheetAt(0);
            CreateTabel(directory,s.substring(0,s.indexOf('.')),sheet);
            InsertIntoTable(directory,s.substring(0,s.indexOf('.')),sheet);
        }

    }

private void InsertIntoTable(String directory, String s, Sheet sheet) {
    for (int i = 1; i <= sheet.getLastRowNum(); i++) {//跳过第一行，取得其他行数据
        String values = "";
        Row row = sheet.getRow(i);//取得第i行数据
        String VTypes=ReadTypeAndConvertForm(s);
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = row.getCell(j);//取得第j列数据
            cell.setCellType(1);
            String value = cell.getStringCellValue();
            values=values+ValuesModification(VTypes,j,value)+",";
        }
        this.jdbcTemplate.execute(String.format("insert into %s values (%s)", s, values.substring(0,values.length()-1)));
    }
}

    private String ValuesModification(String vTypes, int j, String value) {
        if(vTypes.charAt(j)-'0'!=0)
        {
            return String.format("\"%s\",",value);
        }else
            return value;
    }

    private String ReadTypeAndConvertForm(String fName) {
        String mydir=directory+String.format(TypeLogDir,fName);
        String VTypes="";

        try{
            BufferedReader in = new BufferedReader(new FileReader(mydir));
            VTypes=in.readLine();
            VTypes=VTypes.substring(VTypes.indexOf('!')+1,VTypes.indexOf('.'));
            in.close();
        }
        catch (Exception e){
            System.out.println(String.format("读取%s时出错啦",mydir));
        }


        return VTypes;
    }

    /*
        Excel建表方式
        domain名前加入形如1_/2_/3_的记号
        表示
        int CELL_TYPE_NUMERIC = 0;
        int CELL_TYPE_STRING = 1;
        int CELL_TYPE_BOOLEAN=2;
        ********************************
        主键设置为3；
        ******************************
        该记号遵循org.apache.poi中excel单元格类型标准
        便于型转换
     */
    private void CreateTabel(String dir,String filename,Sheet st){

        Row fistRow=st.getRow(0);

        String execution="CREATE TABLE %s(%s);";

        String Body="";

        if(!TableValueTypeLog.containsKey(filename))
            TableValueTypeLog.put(filename,"");

        for(int i=0;i< fistRow.getLastCellNum();i++)
        {
            Cell cell=fistRow.getCell(i);
            cell.setCellType(1);
            String domainName=cell.getStringCellValue();
            Body=Body+Disintergrate(domainName,filename);
        }
        try {
            this.jdbcTemplate.execute(String.format("drop table %s;", filename));
        }catch(Exception e){}
        LOGGER.info(String.format(execution,filename,Body.substring(0,Body.length()-1)));
        this.jdbcTemplate.execute(String.format(execution,filename,Body.substring(0,Body.length()-1)));
        SaveValueTypeLog(TypeLogDir,filename);
    }

    private void SaveValueTypeLog(String typeLogDir,String FileName) {
        File f=new File(this.directory+String.format(typeLogDir,FileName));
        try{f.createNewFile();}
        catch (Exception e){System.out.println("保存数据类型MAP出错啦");}
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(f.getAbsoluteFile()));
            Iterator iter = TableValueTypeLog.entrySet().iterator();
            while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            out.write(entry.getKey().toString()+"!");
            out.write(entry.getValue().toString()+".");
            out.close();
            }
        }
        catch (Exception e){}

    }

    private String Disintergrate(String domainName,String FileName) {
        String tmp="";
        String pk="PRIMARY KEY(%s),";
        String typelog1="";
        switch (domainName.charAt(0)-'0')
        {
            case 0:
                tmp=String.format("%s %s,",domainName.substring(2,domainName.length()),"INTEGER");
                typelog1=TableValueTypeLog.get(FileName)+"0";
                TableValueTypeLog.remove(FileName);
                TableValueTypeLog.put(FileName,typelog1);
                break;
            case 1:
                tmp=String.format("%s %s,",domainName.substring(2,domainName.length()),"CHAR(30)");
                typelog1=TableValueTypeLog.get(FileName)+"1";
                TableValueTypeLog.remove(FileName);
                TableValueTypeLog.put(FileName,typelog1);
                break;
            case 2:
                tmp=String.format("%s %s,",domainName.substring(2,domainName.length()),"BOOLEAN");
                typelog1=TableValueTypeLog.get(FileName)+"0";
                TableValueTypeLog.remove(FileName);
                TableValueTypeLog.put(FileName,typelog1);
                break;
            case 3:
                tmp=String.format("%s %s,",domainName.substring(2,domainName.length()),"INTEGER");
                tmp=tmp+String.format(pk,domainName.substring(2,domainName.length()));
                typelog1=TableValueTypeLog.get(FileName)+"0";
                TableValueTypeLog.remove(FileName);
                TableValueTypeLog.put(FileName,typelog1);
                break;
        }
        //System.out.println(tmp);
        return tmp;
    }


}

class ExportFailException extends Exception{
    ExportFailException(){super();}
    ExportFailException(String s){super(s);}
}
