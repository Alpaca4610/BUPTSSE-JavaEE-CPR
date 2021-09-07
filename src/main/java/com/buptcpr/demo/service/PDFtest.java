package com.buptcpr.demo.service;

import com.buptcpr.demo.controller.UploadController;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFtest{

        private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

        public void createPDF() {
        //设置纸张大小
        Document document = new Document(PageSize.A4);
        try {
        //设置PDF存放路径
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(this.getClass().getResource("/").getPath()+"test.pdf"));
                LOGGER.info(this.getClass().getResource("/").getPath());
                document.open();
                //生成一个表格
                PdfPTable table = this.createTable();
                //将表格添加PDF进去
                document.add(table);

            /*
                图片插入去
             */
                //生成一张图片
                //Image png = this.createImage();
                //将图片添加到PDF
                //document.add(png);
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (DocumentException e) {
        e.printStackTrace();
        } catch (Exception e) {
        e.printStackTrace();
        } finally {
        document.close();
        }
        }
        //生成表格方法
        public static PdfPTable createTable() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(2);//生成一个两列的表格
        //渎系统中的字体
        Font font = new Font(BaseFont.createFont("C:/Windows/Fonts/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED));
        PdfPCell cell;
        //设置每列高度
        int size = 50;
        //按顺序向表格中添加值（输入汉字要加font）
        cell = new PdfPCell(new Phrase("我不管你是谁我爱国无罪",font));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("two"));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("three"));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("four"));
        cell.setFixedHeight(size);
        table.addCell(cell);
        //最后一行给合并起来
        cell = new PdfPCell(new Phrase("five"));
        cell.setColspan(2);//设置所占列数
        cell.setFixedHeight(size*2);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);

        return table;
        }

        public Image createImage() throws Exception{
        //寻找图片路径
        Image png = Image.getInstance("C:\\Users\\13577\\Desktop\\za\\img\\唐可可.png");
        //图片大小
        png.scaleToFit(500,500);
        //图片位置
        png.setAbsolutePosition(50,300);
        return png;
        }
}