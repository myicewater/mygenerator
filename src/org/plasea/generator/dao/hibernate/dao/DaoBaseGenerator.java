package org.plasea.generator.dao.hibernate.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.plasea.generator.database.mysql.Table;
import org.plasea.generator.main.GenerateMain;
import org.plasea.generator.model.ModelGenerator;
import org.plasea.generator.util.GenerateInfo;
import org.plasea.generator.util.PathUtil;
import org.plasea.generator.util.UpperFirstCharacter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * dao层接口生成器
 * @author 朱素海
 *
 */
public class DaoBaseGenerator {

	/**
	 * 生成dao层接口
	 * @param projectPath
	 * @param baseDaoPackage
	 * @param table
	 */
	public static void generateBaseDao(GenerateInfo generateInfo,Table table){
		String packagePath = PathUtil.getPackagePath(generateInfo.getProjectPath(), generateInfo.getDaoBasePackage());
		
		Configuration cfg = new Configuration();  
        try {  
            cfg.setClassForTemplateLoading(DaoBaseGenerator.class, "/org/plasea/generator/dao/hibernate/dao");//指定模板所在的classpath目录  
            cfg.setSharedVariable("upperFC", new UpperFirstCharacter());//添加一个"宏"共享变量用来将属性名首字母大写  
            Template t = cfg.getTemplate("base-dao.html");//指定模板  
            String className= table.getTableName().substring(0, 1).toUpperCase()+table.getTableName().substring(1);
            String fileName = className+"Dao.java";
            System.out.println("文件名称："+fileName);
            File file = new File(packagePath);
            if(!file.exists()){
           	 file.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(new File(packagePath+fileName));//java文件的生成目录  
              
            //数据源  
            Map data = new HashMap();  
            data.put("generateInfo", generateInfo);
            data.put("className", className); //类名
            data.put("table", table);
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
