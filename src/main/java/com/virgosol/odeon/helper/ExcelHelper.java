package com.virgosol.odeon.helper;

import com.sun.source.tree.AssertTree;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.*;

public class ExcelHelper {

    File file;
    XSSFWorkbook wb;
    XSSFSheet sheet;

    public ExcelHelper(String excelPath){
        try{
            file = new File(excelPath);
            if (!file.exists()){
                Assert.fail("Belirtilen dosya bulunamadı.");
            }

            FileInputStream fileInputStream = new FileInputStream(file);

            wb = new XSSFWorkbook(fileInputStream);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getData(int sheetNumber, int row, int column) throws IOException {

        sheet = wb.getSheetAt(sheetNumber);

        String data = sheet.getRow(row).getCell(column).getStringCellValue();

        wb.close();

        return data;
    }

    public int getYCount(int sheetNumber) throws IOException {

        sheet = wb.getSheetAt(sheetNumber);
        int rowCount = sheet.getFirstRowNum();

        wb.close();

        return rowCount;
    }

    public int getXCount(int sheetNumber) throws IOException {

        sheet = wb.getSheetAt(sheetNumber);

        int rowCount = sheet.getLastRowNum();

        wb.close();

        return rowCount;
    }

    public void deleteExcel(){

        System.out.println("Silinecek Excel File: "+file.getName());
        if (file.delete()) {
            System.out.println("File " + file.getName() + " is deleted.");
        } else {
            Assert.fail("File " + file.getName() + " not found.");
        }
    }


    public void setData(int sheetNumber, int row, int column, String value) throws IOException {

        try{
            sheet = wb.getSheetAt(sheetNumber);

            sheet.getRow(row).createCell(column).setCellValue(value);

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            wb.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
