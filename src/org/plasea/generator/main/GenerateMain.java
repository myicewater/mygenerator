package org.plasea.generator.main;

import org.plasea.generator.controller.ControllerGenerator;
import org.plasea.generator.dao.hibernate.dao.DaoBaseGenerator;
import org.plasea.generator.dao.hibernate.imp.DaoImpGenerator;
import org.plasea.generator.database.mysql.Table;
import org.plasea.generator.database.mysql.TableFactory;
import org.plasea.generator.model.ModelGenerator;
import org.plasea.generator.service.imp.ServiceImpGenerator;
import org.plasea.generator.service.service.ServiceBaseGenerator;
import org.plasea.generator.util.GenerateInfo;
import org.plasea.generator.util.PropUtil;

/**
 * 代码生成器主类，入口
 * @author 朱素海
 *
 */
public class GenerateMain {

	//代码环境
	public static String environment ;
	//项目名称
	public static String projectName;
	
	//实体包名
	public static String modelPackage;
	
	//表名
	public static String tableName;
	
	//项目根路径
	public static String projectPath;
	
	//basedao包名
	public static String baseDaoPackage;
	
	//dao层接口包名
	public static String daoBasePackage;
	
	//dao层接口实现包名
	public static String daoImpPackage;
	
	//业务层接口包名
	public static String serviceBasePackage;
	
	//业务层接口实现包名
	public static String serviceImpPackage;
	//控制层包名
	public static String controllerPackage;
	
	/**
	 * 初始化系统变量
	 */
	public  void init(){
		PropUtil.getInstatance();
		environment = PropUtil.getValue("environment");
		projectName = PropUtil.getValue("projectName");
		
		modelPackage = PropUtil.getValue("modelPackage");
		tableName = PropUtil.getValue("tableName");
		baseDaoPackage = PropUtil.getValue("baseDaoPackage");
		daoBasePackage = PropUtil.getValue("daoBasePackage");
		daoImpPackage = PropUtil.getValue("daoImpPackage");
		serviceBasePackage = PropUtil.getValue("serviceBasePackage");
		serviceImpPackage = PropUtil.getValue("serviceImpPackage");
		controllerPackage = PropUtil.getValue("controllerPackage");
		
		String temp = PropUtil.getValue("projectPath");
		
		if(temp != null && !temp.equals("")){//如果配置了项目绝对路径
			projectPath = temp;
		}else{//获取项目路径
			String rootPath = getClass().getResource("/").getFile().toString();
			rootPath = rootPath.substring(1);
			System.out.println("rootPath:"+rootPath);
		    String[] pathes = rootPath.split("/");
		    String newPath = "";
		    for(int i=0;i<pathes.length-2;i++){//省去bin和项目路径
		    	newPath+= pathes[i]+"/";
		    }
		    System.out.println("newPath:"+newPath);
		    newPath += projectName+"/src/";
		    projectPath = newPath;
		    System.out.println("项目路径："+projectPath);
		}
		
		
	}
	/**
	 * 生成各种文件
	 */
	public static void generate(){
		//读取配置文件，初始化变量
		new GenerateMain().init();
		//读取响应表，生成java实体
		String tables[] = tableName.split(",");
		Table table = null;
		GenerateInfo generateInfo = null;
		//多个表生成
		for(int i=0;i<tables.length;i++){
			table= TableFactory.generateTable(tables[i].trim());
			
			generateInfo = new GenerateInfo();
			//生成包名最后一层
			String tablePackageName = table.getTableName().toLowerCase();
			
			generateInfo.setProjectPath(projectPath);
			generateInfo.setBaseDaoPackage(baseDaoPackage);
			generateInfo.setDaoBasePackage(daoBasePackage+"."+tablePackageName);
			generateInfo.setDaoImpPackage(daoImpPackage+"."+tablePackageName);
			generateInfo.setServiceBasePackage(serviceBasePackage+"."+tablePackageName);
			generateInfo.setServiceImpPackage(serviceImpPackage+"."+tablePackageName);
			generateInfo.setModelPackage(modelPackage+"."+tablePackageName);
			generateInfo.setControllerPackage(controllerPackage+"."+tablePackageName);
			generateInfo.setUrlPrefix(tablePackageName);
			
			//生成各种文件
			ModelGenerator.generateModel( generateInfo,table);
			DaoBaseGenerator.generateBaseDao( generateInfo,table);
			DaoImpGenerator.generateBaseDao(generateInfo, table);
			ServiceBaseGenerator.generateServiceBase(generateInfo, table);
			ServiceImpGenerator.generateServiceBase(generateInfo, table);
			ControllerGenerator.generateController(generateInfo, table);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		generate();
		
		
		
	}

}
