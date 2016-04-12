package org.plasea.generator.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plasea.generator.database.mysql.Table;
import org.plasea.generator.util.GenerateInfo;
import org.plasea.generator.util.PathUtil;
import org.plasea.generator.util.UpperFirstCharacter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 实体类生成器
 * 
 * @author 朱素海
 *
 */
public class ModelGenerator {

	/**
	 * 
	 *  
	 * @param args
	 */
    public static void main(String[] args) {  
        Configuration cfg = new Configuration();  
        try {  
            cfg.setClassForTemplateLoading(ModelGenerator.class, "/org/plasea/generator/model");//指定模板所在的classpath目录  
            cfg.setSharedVariable("upperFC", new UpperFirstCharacter());//添加一个"宏"共享变量用来将属性名首字母大写  
            Template t = cfg.getTemplate("model.html");//指定模板  
            FileOutputStream fos = new FileOutputStream(new File("d:/Student.java"));//java文件的生成目录  
              
            //模拟数据源  
            Map data = new HashMap();  
            data.put("package", "edu");//包名  
            data.put("className", "Student");  
              
            List pros = new ArrayList();  
            Map pro_1 = new HashMap();  
            pro_1.put("proType", String.class.getSimpleName());  
            pro_1.put("proName", "name");  
            pros.add(pro_1);  
              
            Map pro_2 = new HashMap();  
            pro_2.put("proType", String.class.getSimpleName());  
            pro_2.put("proName", "sex");  
            pros.add(pro_2);  
              
            Map pro_3 = new HashMap();  
            pro_3.put("proType", Integer.class.getSimpleName());  
            pro_3.put("proName", "age");  
            pros.add(pro_3);  
              
            data.put("properties", pros);  
            t.process(data, new OutputStreamWriter(fos,"utf-8"));//  
            fos.flush();  
            fos.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
    }
    
    /**
     * 生成实体类
     * @param generateInfo
     * @param table
     */
    public static void generateModel(GenerateInfo generateInfo,Table table){
    	
    	 String modelPath = PathUtil.getPackagePath(generateInfo.getProjectPath(), generateInfo.getModelPackage());
    	
    	 Configuration cfg = new Configuration();  
         try {  
             cfg.setClassForTemplateLoading(ModelGenerator.class, "/org/plasea/generator/model");//指定模板所在的classpath目录  
             cfg.setSharedVariable("upperFC", new UpperFirstCharacter());//添加一个"宏"共享变量用来将属性名首字母大写  
             Template t = cfg.getTemplate("model.html");//指定模板  
             String className= table.getTableName().substring(0, 1).toUpperCase()+table.getTableName().substring(1);
             String fileName = className+".java";
             System.out.println("文件名称："+fileName);
             File file = new File(modelPath);
             if(!file.exists()){
            	 file.mkdir();
             }
             FileOutputStream fos = new FileOutputStream(new File(modelPath+fileName));//java文件的生成目录  
               
             //数据源  
             Map data = new HashMap();  
             data.put("generateInfo", generateInfo);//生成信息类
             data.put("className", className); //类名
             data.put("table", table);//表对象
             data.put("generateTime", new Date());//生成时间
             t.process(data, new OutputStreamWriter(fos,"utf-8"));//  
             fos.flush();  
             fos.close();  
         } catch (IOException e) {  
             e.printStackTrace();  
         } catch (TemplateException e) {  
             e.printStackTrace();  
         }  
    	
    }
    
}
