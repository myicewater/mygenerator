package org.plasea.generator.util;


/**
 * 路径工具类
 * @author 朱素海
 *
 */
public class PathUtil {

	/**
	 * 生成包的路径
	 * @param projectPath
	 * @param packageName
	 * @return
	 */
	public static String getPackagePath(String projectPath,String packageName ){
    	
    	String packagePath = packageName.replace(".", "/");//包路径
    	packagePath = projectPath +packagePath+"/";//最终实体类路径
    	System.out.println("包路径："+packagePath);
    	
    	return packagePath;
	}
}
