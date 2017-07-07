package org.yunet.serializer.ecxel;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ExcelUtils {
    /**
     *
     */
    private static Logger logger;

    static {
        logger = Logger.getLogger(ExcelUtils.class);
    }

    public static void main(String[] args) {

        //read("/test.xlsx");
        read("E:/JavaUtils/test.xlsx");
    }

    /**
     * 解析ecxel表
     * */
    private static void read(String fileName){
        List<List<String>> objs;//用于后面转对象使用
        objs = new ArrayList<List<String>>();
        Workbook book = null;
        FileInputStream fis = null;
        try {
            logger.info("开始读取"+fileName+"文件的内容");
            fis = new FileInputStream(fileName);//取到文件
            book = new XSSFWorkbook(fis);
            fis.close();//及时关闭流
            logger.info("开始读取"+fileName+"工作谱的内容");
            Sheet sheet = book.getSheetAt(0);//取到工作谱（每一页是一个工作谱）
            Iterator<Row> rows = sheet.iterator();
            List<String> obj = null;
            int i = 0;
            logger.info("开始读取"+fileName+"行的内容");
            while (rows.hasNext()){
                Row row = rows.next();
                Iterator<Cell> cells = row.iterator();
                obj = new ArrayList<String>();
                logger.info("开始读取"+fileName+"列的内容");
                while (cells.hasNext()){
                    Cell cell = cells.next();
                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            cell.setCellType(Cell.CELL_TYPE_STRING);//全部转换成string类型
                            break;
                    }
                    obj.add(cell.getStringCellValue());
                }
                objs.add(obj);
            }
            serializerFile(objs);
        }catch (Exception e){
            logger.error("读取"+fileName+"错误");
        }finally {
            try {
                book.close();
            }catch (Exception e){
                logger.error("关闭book流错误");
            }
        }
    }
    private static void serializerFile(List<List<String>> file){
        List<Map<String,String>> objMap = new ArrayList<Map<String, String>>();
        Map<String,String> objs;
        logger.info("开始转换成map");
        for (int i = 1;i<file.size();i++){
            objs = new HashMap<String, String>();
            for (int j = 0;j<file.get(i).size();j++){
                objs.put(file.get(0).get(j),file.get(i).get(j));
            }
            objMap.add(objs);
        }
        System.out.println(objMap);
        serializerObj(objMap);
    }
    private static void serializerObj(List<Map<String,String>> objs){
        Class clazz = null;
        Object beanObj;
        String clazzName = "org.yunet.serializer.ecxel.User";
        try {
            logger.info("反射");
            clazz = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error(clazzName+"类反射错误");
            throw new RuntimeException("-------找不到-------"+clazzName);
        }

        try {
            logger.info("开始实例化反射类");
            beanObj = clazz.newInstance();
        } catch (Exception e) {
            logger.error(clazzName+"类反射实例化错误");
            throw new RuntimeException(""+clazzName);
        }
        for (Map.Entry<String,String> entry: objs.get(0).entrySet()) {
            try {
                Object value = null;
                logger.info("开始实例化反射属性");
                Method m = writeMethod(beanObj, entry.getKey());

                Class<?> [] setMethod = m.getParameterTypes();
                //判断属性类型并赋值
                for (Class<?> c:setMethod) {
                   if(c == int.class) value = Integer.parseInt(entry.getValue());
                   else if(c == float.class) value  = Float.parseFloat(entry.getValue());
                   else if(c == double.class) value = Double.parseDouble(entry.getValue());
                   else if (c == long.class) value = Long.parseLong(entry.getValue());
                   else if(c == short.class) value = Short.parseShort(entry.getValue());
                   else value = entry.getValue();
                }
                m.invoke(beanObj, value);
            } catch (Exception e) {
                logger.error("类注入属性错误");
            }
        }
        System.out.println((User)beanObj);
    }
    private static Method writeMethod(Object beanObj, String name) {
        //得到属性的set方法用于注入
        Method m;
        Field ff;
        logger.info("开始拼接set方法");
        String methodName = "set" + name.substring(0, 1).toUpperCase()
                + name.substring(1);
        try {
            logger.info("反射字段");
            //获取该类的字段
            ff = beanObj.getClass().getDeclaredField(name);
            ff.setAccessible(true);
        } catch (Exception e1) {
            logger.error(name+"反射类没有这个字段");
            throw new RuntimeException(beanObj.getClass()+"没有"+name+"这个属性");
        }
        try {
            m = beanObj.getClass().getMethod(methodName,ff.getType());
        } catch (Exception e) {
            logger.error(methodName+"反射类没有这个方法");
            throw new RuntimeException(beanObj.getClass()+"没有"+methodName+"这个方法");
        }
        return m;
    }
}  