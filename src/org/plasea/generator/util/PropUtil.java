package org.plasea.generator.util;

import java.io.InputStream;
import java.util.Properties;

public class PropUtil
{

	private static PropUtil instance = null;
	private Properties props = null ;
	private static String FILEPATH = "/config.properties";
	
	public static synchronized PropUtil getInstatance(){
		if(instance == null){
			instance = new PropUtil();
		}
		return instance;
	}
	
	private PropUtil(){
		loadProps();
	}

	private void loadProps() {
		props = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream(FILEPATH);
			props.load(in);
		}
		catch (Exception e) {

			System.err.println("Error reading conf properties in PropertyManager.loadProps() " + e);
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getProp(String key) {
		String value = props.getProperty(key);
		return value == null ? "" : value.trim();
	}
	
	/**
	 * 鏍规嵁key鑾峰彇瀵瑰簲value
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		return getInstatance().getProp(key);
	}
	
	/**
	 * 鏍规嵁key鑾峰彇瀵瑰簲value锛屽鏋滀负绌哄垯杩斿洖榛樿鐨剉alue
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getValue(String key,String defaultValue){
		String value = getInstatance().getProp(key);
		return "".equals(value) ? defaultValue : value;
	}

}
