package org.plasea.generator.util;

public class Test {

	
	public String getCurrentPath(){  
	       //取得根目录路径  
	       String rootPath=getClass().getResource("/").getFile().toString();  
	       System.out.println("rootPath:"+rootPath);
	       //当前目录路径  
	       String currentPath1=getClass().getResource(".").getFile().toString();  
	       System.out.println("currentPath1:"+currentPath1);
	       String currentPath2=getClass().getResource("").getFile().toString();  
	       System.out.println("currentPath2:"+currentPath2);
	       //当前目录的上级目录路径  
	       String parentPath=getClass().getResource("../").getFile().toString();  
	       System.out.println("parentPath:"+parentPath);
	          
	       return rootPath;         
	   
	   }
	
	
	public static void main(String[] args) {
		 Test t = new Test();
		 t.getCurrentPath();
	}

}
