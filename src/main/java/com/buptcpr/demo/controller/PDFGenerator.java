package com.buptcpr.demo.controller;


import com.buptcpr.demo.DAO.QueryAPI;
import com.buptcpr.demo.common.Result;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.itextpdf.text.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseBody
@CrossOrigin
@Controller
public class PDFGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);
    @Autowired
    JdbcTemplate j1;

    HashMap<String,String> mapping;
    PDFGenerator()
    {
        mapping=new HashMap<>();
        mapping.put("studentid","学号");
        mapping.put("name","姓名");
        mapping.put("score","分数");
        mapping.put("my_rank","排名");
        mapping.put("crank","学校排名");
        mapping.put("score","投档线");
    }

    @Autowired
    JdbcTemplate j;
    @GetMapping("/pdfgenerate")
    public Result pdfownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id=Integer.parseInt(request.getParameter("id"));
        String FileName=request.getParameter("Filename");
        String[] Domains={"studentid","name","score","my_rank"};
        String[] DomainRange={"studentid"};
        String[] DRV={id.toString()};
        java.util.List<java.util.Map<String,Object>> res=new QueryAPI(j).Result("student",Domains,DomainRange,DRV);
        FillBody(Domains, Decompose(res) ,FileName);

        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + (FileName+".PDF"));// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        File file=new File(this.getClass().getResource("/").getPath()+String.format("%s.pdf", FileName));
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success(null);

    }


    private String[] Decompose(List<Map<String, Object>> res) {
        String[] values = new String[4];
        int count=0;
        for(java.util.Map<String,Object> map:res) {
            for (String s : map.keySet()) {
                values[count]=map.get(s).toString();
                count++;
            }
        }
        return values;
    }

    @GetMapping("/pdftest")
    public @ResponseBody String pdfgenerate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String d="id.name.score.rank";
        String v="114514.李田所.120.114514";
        String FileName="Report";
        FillBody(d.split("\\.",0),v.split("\\.",0),FileName);
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + (FileName+".PDF"));// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        File file=new File(this.getClass().getResource("/").getPath()+String.format("%s.pdf", FileName));
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            return "下载成功";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "1";
    }


    public void FillBody (String[] Doamins,String[] values,String fname)
    {
        Boolean chongkainie=false;
        Document doc = new Document(PageSize.A4);
        System.out.println("width:"+PageSize.A4.getWidth());
        System.out.println("height:"+PageSize.A4.getHeight());

        values[4]=RankQuery.getActualRank(Integer.parseInt(values[2]),j.queryForList(String.format("select * from test where score>=%d", values[2]))).toString();

        try{
            PdfWriter w = PdfWriter.getInstance(doc, new FileOutputStream(this.getClass().getResource("/").getPath()+String.format("%s.pdf", fname)));
            doc.open();
            PdfContentByte pcb=w.getDirectContent();

            String title="我的志愿推荐报告";
            Font TitleFont= FontFactory.getFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK);

            Paragraph Title= new Paragraph(title,TitleFont);
            TitleFont.setSize(20);
            Title.setFont(TitleFont);
            Title.setAlignment(Element.ALIGN_CENTER);
            doc.add(Title);

            PdfPTable t = new PdfPTable(2);
            t.setHorizontalAlignment(Element.ALIGN_CENTER);
            t.setTotalWidth(250);

            Font font = new Font(BaseFont.createFont("C:/Windows/Fonts/simkai.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED));
            PdfPCell cell;


            int CellHeight=20;
            for(int i=0;i<Doamins.length;i++)
            {
                cell = new PdfPCell(new Paragraph(mapping.get(Doamins[i]),
                        FontFactory.getFont("C:\\Windows\\Fonts\\simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK)));
                cell.setFixedHeight(CellHeight);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                t.addCell(cell);

                cell = new PdfPCell(new Paragraph(values[i],
                        FontFactory.getFont("C:\\Windows\\Fonts\\simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK)));
                cell.setFixedHeight(CellHeight);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                t.addCell(cell);
                LOGGER.info(values[i]);
                if(Doamins[i].equals("score")&&Integer.parseInt(values[i])<180) {
                    chongkainie = true;
                }
            }
            t.writeSelectedRows(0,4, 150,750,pcb);

            if(chongkainie==true)
            {
                Image png = Image.getInstance("src\\main\\resources\\static\\imgs\\"+"chongkai.png");

                png.scaleToFit(300,300);
                png.setAbsolutePosition(0,0);
                doc.add(png);
            }


            t=new PdfPTable(1);

            //
            RecommendSchool(new RankQuery().SchoolRecommand(j1,Integer.parseInt(values[2])),pcb);

            //
            doc.close();
        }
        catch (Exception e){e.printStackTrace();}
    }

    private PdfPTable RecommendSchool(String[] results,PdfContentByte pcb)
    {
        PdfPTable Container=new PdfPTable(1);
        int CellHeight=20;
        PdfPCell cell;
        cell=new PdfPCell(new Paragraph("推荐学校名单:",
                FontFactory.getFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK)));
        Container.addCell(cell);
        for(String s:results)
        {
            Paragraph p=new Paragraph(s,
                    FontFactory.getFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK));
            p.setAlignment(Element.ALIGN_CENTER);
            cell=new PdfPCell(p);
            Container.addCell(cell);
        }
        Container.setTotalWidth(500);
        Container.writeSelectedRows(0,30, 50,650,pcb);
        return Container;
    }

}

