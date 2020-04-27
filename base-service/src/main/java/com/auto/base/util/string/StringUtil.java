package com.auto.base.util.string;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类（与1.1相比去除了前端应用比较广泛的一些方法，如string过长截断等）
 * 
 * @author LZH
 * @version 2.0
 * @since java 8.0
 */
public class StringUtil {

//	private final static String APPKEY = "1387088783";
//
//	private final static String SHORT_URL = "http://api.t.sina.com.cn/short_url/shorten.json";

	private StringUtil() {
	}

	/**
	 * 用"0"补足一个字符串到指定长度
	 * 
	 * @param str  - 源字符串
	 * @param size - 补足后应达到的长度
	 * @return - 补零后的结果
	 */
	public static String fillZero(String str, int size) {
		String result;
		if (str.length() < size) {
			char[] s = new char[size - str.length()];
			for (int i = 0; i < (size - str.length()); i++) {
				s[i] = '0';
			}
			result = new String(s) + str;
		} else {
			result = str;
		}
		return result;
	}

	/**
	 * 通过字符串转换成相应的整型，并返回。
	 * @param strValue String 待转换的字符串
	 * @return int 转换完成的整型
	 * */
	public static int getStrToInt(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		int iValue = 0;
		try
		{
			iValue = new java.lang.Integer(strValue.trim()).intValue();
		}
		catch (Exception ex)
		{
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 通过字符串转换成相应的DOUBLE，并返回。
	 * @param strValue String 待转换的字符串
	 * @return double 转换完成的DOUBLE
	 * */
	public static double getStrToDouble(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		double dValue = 0;
		try
		{
			dValue = Double.parseDouble(strValue.trim());
		}
		catch (Exception ex)
		{
			dValue = 0;
		}
		return dValue;
	}

	/**
	 * 转换字符串第一个字符为大写
	 * @param str String
	 * @return String
	 */
	public static String getStrByUpperFirstChar(String str)
	{
		try
		{
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		catch (Exception e)
		{
			return "";
		}

	}

	/**
	 * 判断一个字符串是否为 null字段，空字符串 或空
	 * 
	 * @param inStr 待判定字段
	 * @return boolean
	 */
	public static boolean isValid(String inStr) {
		if (inStr == null) {
			return false;
		} else if (inStr.equals("")) {
			return false;
		} else if (inStr.equals("null")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断一个字符串是否为 空或空字符串，为空返回False
	 * 
	 * @param str 待判定字段
	 * @return boolean
	 */
	public static boolean checkNotNull(String str) {
		boolean flag = false;

		if (str != null && str.trim().length() != 0)
			flag = true;
		return flag;
	}

	/**
	 * 判断一个字符串是否为 空或空字符串，为空返回True
	 * 
	 * @param str 待判定字段
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		return "".equals(str.trim());
	}

	/**
	 * 判断是否含有中文，如果含有中文返回ture
	 * 
	 * @param str
	 * @return 含有中文返回ture
	 */
	public static boolean containsChinese(String str) {
		if (str.length() < (str.getBytes()).length)
			return true;
		return false;
	}

	/**
	 * 是否由纯数字组成，12.3不是纯数字
	 * 
	 * @param cs the CharSequence to check, may be null
	 * @return 是纯数字返回true
	 */
	public static boolean isNumeric(CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 截取保留小数点后2位
	 * 
	 * @param num 数字字符串(非数字抛异常)
	 * @return
	 */
	public static String toFloatNumber(String num) {
		return toFloatNumber(num, 2);
	}

	/**
	 * 截取保留小数点后位数
	 * 
	 * @param num      数字字符串(非数字抛异常)
	 * @param accuracy 要保留的位数(该参数负默认保留整数)
	 * @return
	 */
	public static String toFloatNumber(String num, int accuracy) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(accuracy);
		nf.setMinimumFractionDigits(accuracy);
		return nf.format(Double.parseDouble(num));
	}

	/**
	 * 验证电子邮件
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		if (isEmpty(email))
			return false;
		Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}

	/**
	 * 验证手机号码，手机号如加86视为无效
	 * 
	 * @param phone
	 * @return 1开头手机号视为true，其余为false
	 */
	public static boolean isValidPhone(String phone) {
		if (isEmpty(phone))
			return false;
		Pattern pattern = Pattern.compile("^1\\d{10}$");
		Matcher matcher = pattern.matcher(phone);
		return matcher.find();
	}

	/**
	 * 转义正则特殊字符 （$()*+.[]?\^{},|） 例，转义字段:[a],转义后字段:\\[a\\]
	 * 
	 * @param keyword 需要转义的字符串
	 * @return 转义后的字符串
	 */
	public static String escapeExprSpecialWord(String keyword) {
		if (StringUtil.checkNotNull(keyword)) {
			String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
			for (String key : fbsArr) {
				if (keyword.contains(key)) {
					keyword = keyword.replace(key, "\\" + key);
				}
			}
		}
		return keyword;
	}
	
	/**
	 * 验证电话号码（手机及固话）,手机号如加86视为无效，固话不加区号视为无效，默认8位固话
	 * 
	 * @param phone
	 * @return 1开头手机号0开头固话号视为true，其余为false
	 */
	public static boolean isValidTelePhone(String phone) {
		if (isEmpty(phone))
			return false;
		Pattern pattern = Pattern.compile("^(1\\d{10})|(0\\d{10,11})$");
		Matcher matcher = pattern.matcher(phone);
		return matcher.find();
	}

//	/**
//	 * 获取短地址
//	 *
//	 * @param url 长地址
//	 */
//	public static String convertToShortUrl(String url) throws Exception {
//		String s = HttpClientUtil.httpGet(SHORT_URL+"?url_long="+url+"&source="+APPKEY);
//		JsonNode convert = JsonUtil.convert(s);
//		if (convert.get(0)!=null&&convert.get(0).get("url_short")!=null) {
//			return convert.get(0).get("url_short").asText();
//		} else {
//			throw new Exception("短链转化异常");
//		}
//	}

}
