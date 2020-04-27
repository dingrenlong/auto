package com.auto.base.util.config;

/**
 * 获取项目中配置的方法
 *@version 2.0
 */
public class BootStrapConfig extends BaseConfig{

	private static BootStrapConfig m_Instance = new BootStrapConfig();

	public static BootStrapConfig Instance()
	{
		return m_Instance;
	}

	private BootStrapConfig()
	{
		init("bootstrap");
	}
}
