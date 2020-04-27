//package com.auto.base.util.config;
//
//import java.util.HashMap;
//
//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.auto.base.util.string.StringUtil;
//
///**
// * 获取项目中配置的方法
// *@version 2.0
// */
//public class NacosConfig{
//
//	private static HashMap<String,String> properties = new HashMap<>();
//
//	static {
//		BootStrapConfig instance = BootStrapConfig.Instance();
//		try {
//            String fileNamesStr = instance.getProperty("spring.cloud.nacos.config.file-name");
//            String[] files = fileNamesStr.split(",");
//            for(String file:files){
//                String fileConfig = NacosFactory.createConfigService(instance.getProperty("spring.cloud.nacos.config.server-addr"))
//                        .getConfig(file,
//                                instance.getProperty("spring.cloud.nacos.config.group","DEFAULT_GROUP")
//                                , 5000);
//                String[] configs = fileConfig.split("\r\n");
//                for(String property:configs){
//                    String[] kv = property.split("=");
//                    if(kv.length==2){
//                        properties.put(kv[0].trim(),kv[1].trim());
//                    }
//
//                }
//            }
//
//		} catch (NacosException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static String getProperty(String propertyName)
//	{
//		String result="";
//		try{
//			result=properties.get(propertyName);
//		}
//		catch(Exception e){
//			result="";
//		}
//		return result;
//	}
//
//	public static  String getProperty(String propertyName, String defaultValue)
//	{
//		String result = "";
//		try{
//			result = properties.get(propertyName);
//		}
//		catch(Exception e){
//			result = "";
//		}
//		if(StringUtil.isEmpty(result)){
//			result = defaultValue;
//		}
//
//		return result;
//	}
//
//
//}
