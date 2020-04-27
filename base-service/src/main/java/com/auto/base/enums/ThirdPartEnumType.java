package com.auto.base.enums;

/**
 * 第三方枚举类型定义
 * 
 * @author zhiziyun.zw
 * @version 1.0.0
 * @since JDK 8.0
 */
public interface ThirdPartEnumType extends EnumType {

	/**
	 * 获取第三方枚举类型值
	 * 
	 * @return 第三方枚举类型值
	 */
	public String getThirdPartTypeValue();
	
	/**
	 * 通过第三方值获取枚举值
	 * 
	 * @param clazz
	 * 			枚举类型
	 * @param thirdPartyValue
	 * 			第三方值
	 * 
	 * @return	枚举类型
	 */
	public static EnumType getEnumTypeByThirdPartyValue(Class<? extends ThirdPartEnumType> clazz, String thirdPartyValue) {
		
		ThirdPartEnumType[] enumConstants = clazz.getEnumConstants();
		
		if (enumConstants != null) {
			for (ThirdPartEnumType enumType : enumConstants) {
				if (enumType.getThirdPartTypeValue().equals(thirdPartyValue)) {
					return enumType;
				}
			}
		}
		
		return null;
	}
}
