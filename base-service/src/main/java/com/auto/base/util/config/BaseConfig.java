package com.auto.base.util.config;

import java.util.ResourceBundle;

import com.auto.base.util.string.StringUtil;


public abstract class BaseConfig {
	
	private ResourceBundle bundle;
	
	/**
	 * 配置文件resources目录下，文件名为：configName.properties
	 * @param configName 
	 */
	protected void init(String configName)
	{
		try {
			bundle = ResourceBundle.getBundle(configName);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public String getProperty(String propertyName)
	{
		String result="";
		try{
			result=bundle.getString(propertyName);
		}
		catch(Exception e){
			result="";
		}
		return result;
	}
	
	public String getProperty(String propertyName, String defaultValue)
	{
		String result = "";
		try{
			result = bundle.getString(propertyName);
		}
		catch(Exception e){
			result = "";
		}
		if(StringUtil.isEmpty(result)){
			result = defaultValue;
		}
		
		return result;
	}
}
