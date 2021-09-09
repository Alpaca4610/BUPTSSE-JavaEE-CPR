//package com.buptcpr.demo.service;
//
//import com.buptcpr.demo.common.Result;
//import com.buptcpr.demo.controller.RankQuery;
//import com.itextpdf.text.*;
//import com.itextpdf.text.log.Logger;
//import com.itextpdf.text.log.LoggerFactory;
//import com.itextpdf.text.pdf.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.FileOutputStream;
//import java.util.HashMap;
//
//@ResponseBody
//@CrossOrigin
//@Controller
//public class PDFGenerator {
//    private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);
//    @Autowired
//    JdbcTemplate j1;
//
//    HashMap<String,String> mapping;
//    PDFGenerator()
//    {
//        mapping=new HashMap<>();
//        mapping.put("id","学号");
//        mapping.put("name","姓名");
//        mapping.put("score","分数");
//        mapping.put("rank","排名");
//
//    }
//
//
//    @PostMapping("/pdfgenerate")
//    public @ResponseBody Result pdfownload(@RequestParam Info info) throws Exception {
//        FillBody(info.Domains.split("\\.",0),info.Values.split("\\.",0),info.FileName);
//        return Result.success(null);
//
//    }
//
//    @GetMapping("/pdftest")
//    public @ResponseBody String pdfgenerate() throws Exception {
//        String d="id.name.score.rank";
//        String v="114514.李田所.120.114514";
//        FillBody(d.split("\\.",0),v.split("\\.",0),"test");
//        return "1";
//    }
//
//
//    public void FillBody (String[] Doamins,String[] values,String fname)
//    {
//        Boolean chongkainie=false;
//        Document doc = new Document(PageSize.A4);
//        System.out.println("width:"+PageSize.A4.getWidth());
//        System.out.println("height:"+PageSize.A4.getHeight());
//
//        try{
//            PdfWriter w = PdfWriter.getInstance(doc, new FileOutputStream(this.getClass().getResource("/").getPath()+String.format("%s.pdf", fname)));
//            doc.open();
//            PdfContentByte pcb=w.getDirectContent();
//
//            String title="我的志愿推荐报告";
//            Font TitleFont= FontFactory.getFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK);
//
//            Paragraph Title= new Paragraph(title,TitleFont);
//            TitleFont.setSize(20);
//            Title.setFont(TitleFont);
//            Title.setAlignment(Element.ALIGN_CENTER);
//            doc.add(Title);
//
//            PdfPTable t = new PdfPTable(2);
//            t.setHorizontalAlignment(Element.ALIGN_CENTER);
//            t.setTotalWidth(250);
//
//            Font font = new Font(BaseFont.createFont("C:/Windows/Fonts/simkai.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED));
//            PdfPCell cell;
//
//
//            int CellHeight=20;
//            for(int i=0;i<Doamins.length;i++)
//            {
//                cell = new PdfPCell(new Paragraph(mapping.get(Doamins[i]),
//                        FontFactory.getFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK)));
//                cell.setFixedHeight(CellHeight);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                t.addCell(cell);
//
//                cell = new PdfPCell(new Paragraph(values[i],
//                        FontFactory.getFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK)));
//                cell.setFixedHeight(CellHeight);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                t.addCell(cell);
//                if(Doamins[i].equals("score")&&Integer.parseInt(values[i])<180) {
//                    chongkainie = true;
//                }
//            }
//            t.writeSelectedRows(0,4, 150,750,pcb);
//
//            if(chongkainie==true)
//            {
//                Image png = Image.getInstance(this.getClass().getResource("/").getPath()+"imgs/chongkai.png");
//
//                png.scaleToFit(300,300);
//                png.setAbsolutePosition(0,0);
//                doc.add(png);
//            }
//            String RecSchoo="";
//            RecSchoo= RankQuery.SchoolRecommand(j1,Integer.parseInt(values[2]));
//            Paragraph RecommendSchool=new Paragraph(RecSchoo
//                    ,FontFactory.getFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED,10f, Font.NORMAL, BaseColor.BLACK));
//
//            doc.add(RecommendSchool);
//            doc.close();
//        }
//        catch (Exception e){e.printStackTrace();}
//    }
//
//
//}
//
//class Info
//{
//    String FileName="TempPDF";
//    String Domains="None";
//    String Values="None";
//}
//
