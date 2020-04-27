package com.auto.base.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础枚举类型定义
 * 
 * @author zhiziyun.zw
 * @version 1.0.0
 * @since JDK 8.0
 */
public interface EnumType {

	/**
	 * 获取枚举值
	 * 
	 * @return	枚举值
	 */
	public int getTypeValue();
	
	/**
	 * 获取枚举名称
	 * 
	 * @return	枚举名称
	 */
	public String getTypeName();
	
	/**
	 * 通过数据库值获取枚举值
	 * 
	 * @param clazz
	 * 			枚举类型
	 * @param value
	 * 			第三方值
	 * 
	 * @return	枚举类型
	 */
	public static EnumType getEnumTypeByValue(Class<? extends EnumType> clazz, int value) {
		
		EnumType[] enumConstants = clazz.getEnumConstants();
		
		if (enumConstants != null) {
			for (EnumType enumType : enumConstants) {
				if (enumType.getTypeValue() == value) {
					return enumType;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 获取允许的枚举类型
	 * 
	 * @param enumClazz 枚举类型
	 * @param clude     排除类型/包含类型
	 * @param flag      true包含，false排除，
	 * @return 选项
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static List<? extends Enum<?>> getEnumType(Class<? extends Enum<?>> enumClazz, List<? extends EnumType> clude, boolean flag) {

		List<Enum<?>> result = new ArrayList<>();
		Enum<?>[] enumConstants = enumClazz.getEnumConstants();

		for (Enum<?> enumItem : enumConstants) {
			if (clude != null && !clude.isEmpty()) {
				if (flag) {
					// 包含
					if (clude.contains(enumItem)) {
						result.add(enumItem);
					}
				} else {
					// 排除
					if (!clude.contains(enumItem)) {
						result.add(enumItem);
					}
				}
			} else {
				result.add(enumItem);
			}
		}
		
		return result;
	}
	
}
