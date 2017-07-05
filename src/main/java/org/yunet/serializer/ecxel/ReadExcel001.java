package org.yunet.serializer.ecxel;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel001 {  
    public static void main(String[] args) {  
        read("E:/JavaUtils/test.xlsx");
    }
    /**
     * 解析ecxel表
     * */
    public static void read(String fileName){
        List<List<String>> objs = new ArrayList<List<String>>();//用于后面转对象使用
        Workbook book = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);//取到文件
            book = new XSSFWorkbook(fis);
            fis.close();//及时关闭流
            Sheet sheet = book.getSheetAt(0);//取到工作谱（每一页是一个工作谱）
            Iterator<Row> rows = sheet.iterator();
            List<String> obj = null;
            int i = 0;
            while (rows.hasNext()){
                Row row = rows.next();
                Iterator<Cell> cells = row.iterator();
                obj = new ArrayList<String>();
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
            System.out.println(objs);
            serializerFile(objs);
        }catch (Exception e){
        }finally {
            try {
                book.close();
            }catch (Exception e){}
        }
    }
    private static void serializerFile(List<List<String>> file){
        List<Map<String,String>> objMap = new ArrayList<Map<String, String>>();
        Map<String,String> objs = null;
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
        Object beanObj = null;
        String clazzName = "User";
        try {
            clazz = Class.forName("org.yunet.serializer.ecxel.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("-------找不到-------"+clazzName);
        }

        try {
            beanObj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(""+clazzName);
        }
        for (Map.Entry<String,String> entry: objs.get(0).entrySet()) {
            try {
                Object value = null;
                Method m = writeMethod(beanObj, entry.getKey());
                Class<?> [] setMethod = m.getParameterTypes();
                //判断属性类型并赋值
                for (Class<?> c:setMethod) {
                   if(c == int.class)
                       value = Integer.parseInt(entry.getValue());
                   else value = value = entry.getValue();
                }
                m.invoke(beanObj, value);
            } catch (Exception e) {
            }
        }
        System.out.println((User)beanObj);
    }
    public static Method writeMethod(Object beanObj, String name) {
        //得到属性的set方法用于注入
        Method m = null;
        Field ff = null;
        String methodName = "set" + name.substring(0, 1).toUpperCase()
                + name.substring(1);
        try {
            //获取该类的字段
            ff = beanObj.getClass().getDeclaredField(name);
            ff.setAccessible(true);
        } catch (Exception e1) {
            throw new RuntimeException(beanObj.getClass()+"没有"+name+"这个属性");
        }
        try {
            m = beanObj.getClass().getMethod(methodName,ff.getType());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(beanObj.getClass()+"没有"+methodName+"这个方法");
        }
        return m;
    }
}  