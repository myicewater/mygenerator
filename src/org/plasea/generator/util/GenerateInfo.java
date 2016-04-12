package org.plasea.generator.util;

/**
 * 生成信息类
 * @author 朱素海
 *
 */
public class GenerateInfo {

	/**
	 * 项目绝对路径
	 */
	private String projectPath;
	/**
	 * 实体包名
	 */
	private String modelPackage;
	
	/**
	 * basedao包名
	 */
	private String baseDaoPackage;
	/**
	 * dao接口包名
	 */
	private String daoBasePackage;
	/**
	 * dao实现包名
	 */
	private String daoImpPackage;
	/**
	 * 业务层接口包名
	 */
	private String serviceBasePackage;
	/**
	 * 业务层实现包名
	 */
	private String serviceImpPackage;
	
	/**
	 * 控制层层实现包名
	 */
	private String controllerPackage;

	/**
	 * 控制层url前缀
	 */
	private String urlPrefix;
	
	
	
	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getBaseDaoPackage() {
		return baseDaoPackage;
	}

	public void setBaseDaoPackage(String baseDaoPackage) {
		this.baseDaoPackage = baseDaoPackage;
	}

	public String getDaoBasePackage() {
		return daoBasePackage;
	}

	public void setDaoBasePackage(String daoBasePackage) {
		this.daoBasePackage = daoBasePackage;
	}

	public String getDaoImpPackage() {
		return daoImpPackage;
	}

	public void setDaoImpPackage(String daoImpPackage) {
		this.daoImpPackage = daoImpPackage;
	}

	public String getServiceBasePackage() {
		return serviceBasePackage;
	}

	public void setServiceBasePackage(String serviceBasePackage) {
		this.serviceBasePackage = serviceBasePackage;
	}

	public String getServiceImpPackage() {
		return serviceImpPackage;
	}

	public void setServiceImpPackage(String serviceImpPackage) {
		this.serviceImpPackage = serviceImpPackage;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}

	
	
	
	
	
}
