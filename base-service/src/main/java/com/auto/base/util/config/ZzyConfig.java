package com.auto.base.util.config;

/**
 * 获取项目中配置的方法
 *@version 2.0
 */
public class ZzyConfig extends BaseConfig{
	
	private static ZzyConfig m_Instance = new ZzyConfig();
	
	public static ZzyConfig Instance()
	{
		return m_Instance;
	}
	
	private ZzyConfig()
	{
		init("zhiziyun-config");
	}
}
