package org.yunet.serializer.ecxel;

import java.io.FileInputStream;
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Iterator;  
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
   
public class ReadExcel001 {  
    public static void main(String[] args) {  
        read("E:/JavaUtils/test.xlsx");
        System.out.println("-------------");  
        //readXml("d:/test2.xls");
    }  
    public static void readXml(String fileName){  
        boolean isE2007 = false;    //判断是否是excel2007格式  
        if(fileName.endsWith("xlsx"))  
            isE2007 = true;  
        try {  
            InputStream input = new FileInputStream(fileName);  //建立输入流  
            Workbook wb  = null;  
            //根据文件格式(2003或者2007)来初始化  
            if(isE2007)  
                wb = new XSSFWorkbook(input);  
            else  
                wb = new HSSFWorkbook(input);  
            Sheet sheet = wb.getSheetAt(0);     //获得第一个表单  
            Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器  
            while (rows.hasNext()) {  
                Row row = rows.next();  //获得行数据  
              //  System.out.println("Row #" + row.getRowNum());  //获得行号从0开始
                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器  
                while (cells.hasNext()) {  
                    Cell cell = cells.next();  
                  //  System.out.println("Cell #" + cell.getColumnIndex());
                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据  
                    case HSSFCell.CELL_TYPE_NUMERIC:  
                        System.out.print(cell.getNumericCellValue());
                        break;  
                    case HSSFCell.CELL_TYPE_STRING:  
                        System.out.print(cell.getStringCellValue());
                        break;  
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;  
                    case HSSFCell.CELL_TYPE_FORMULA:  
                        System.out.println(cell.getCellFormula());  
                        break;  
                    default:  
                        System.out.println("unsuported sell type");  
                    break;  
                    }  
                }  
            }  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }
    public  static  void  read(String fileName){
        System.out.println("++++++++");
        try {
            FileInputStream fis = new FileInputStream(fileName);//取到文件
            Workbook book = new XSSFWorkbook(fis);
            Sheet sheet = book.getSheetAt(0);//取到工作pu
            Iterator<Row> rows = sheet.iterator();
            while (rows.hasNext()){
                Row row = rows.next();
                Iterator<Cell> cells = row.iterator();
                System.out.println();
                while (cells.hasNext()){
                    Cell cell = cells.next();
                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue()+"\t");
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue()+"\t");
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue()+"\t");
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            System.out.print(cell.getCellFormula()+"\t");
                            break;
                        default:
                            System.out.print("unsuported sell type"+"\t");
                            break;
                    }
                }
                System.out.println();
            }
        }catch (Exception e){}
    }
}  