package org.yunet.serializer.ecxel;

import java.lang.reflect.Field;
import java.util.Date;  
  
public class User  
{  
  
    private Integer id;  
    private String name;  
    private Double price;  
    private Date date;  
      
  
    public Integer getId()  
    {  
        return id;  
    }  
  
    public void setId(Integer id)  
    {  
        this.id = id;  
    }  
  
    public String getName()  
    {  
        return name;  
    }  
  
    public void setName(String name)  
    {  
        this.name = name;  
    }  
  
    public Double getPrice()  
    {  
        return price;  
    }  
  
    public void setPrice(Double price)  
    {  
        this.price = price;  
    }  
      
      
  
    public Date getDate()  
    {  
        return date;  
    }  
  
    public void setDate(Date date)  
    {  
        this.date = date;  
    }  
  
    public static void main(String[] args)  
    {  
        Class clz = User.class;  
  
        Field[] fa = clz.getDeclaredFields();  
  
        for (int i = 0; i < fa.length; i++) {  
            System.out.println(fa[i].getName());  
        }  
  
    }  
  
}  