package com.auto.base.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.auto.base.util.string.StringUtil;

/**
 * XML内容解析工具类
 * 
 * @author zhiziyun.zw
 * @version 1.0.0
 * @since JDK 8.0
 */
public class XMLUtil {
	
	/**
	 * 本地文档
	 */
	private static final ThreadLocal<Element> localElement = new ThreadLocal<>();
	
	/**
	 * 私有构造
	 */
	private XMLUtil() {
		
	}
	
	/**
	 * 日志对象
	 */
	private static Log log = LogFactory.getLog(XMLUtil.class);
	
	/**
	 * 获取标签值
	 * 
	 * @param root
	 * 			根文件
	 * @param tagName
	 * 			标签名称
	 * 
	 * @return 标签值
	 */
	public static String getTagNameValue(Element root, String tagName) {
		
		// XML文件对象为空
		if(root == null) {
			return "";
		}
		// 标签名称取值
		NodeList tagNameNodelist = root.getElementsByTagName(tagName);
		// 标签内容为空
		if(tagNameNodelist == null || tagNameNodelist.getLength() < 1) {
			return "";
		}
		// 返回结果
		return tagNameNodelist.item(0).getTextContent();
	}
	
	/**
	 * 获取标签值
	 * 
	 * @param xmlStr
	 * 			XML字符串
	 * @param tagName
	 * 			标签名称
	 * 
	 * @return 标签值
	 */
	public static String getTagNameValue(String xmlStr, String tagName) {
		
		// XML文件对象为空
		if(StringUtil.isEmpty(xmlStr)) {
			return "";
		}
		
		// 获取XML根对象
		Element root = getXMLElementByStrValue(xmlStr);
		
		// 根对象为空
		if (root == null) {
			return "";
		}
		
		// 标签名称取值
		NodeList tagNameNodelist = root.getElementsByTagName(tagName);
		// 标签内容为空
		if(tagNameNodelist == null || tagNameNodelist.getLength() < 1) {
			return "";
		}
		
		// 返回结果
		return tagNameNodelist.item(0).getTextContent();
	}
	
	/**
	 * 通过XML字符串获取XML根对象
	 * 
	 * @param xmlStrValue
	 * 			XML内容字符串
	 * 	
	 * @return	XML根对象
	 */
	public static Element getXMLElementByStrValue(String xmlStrValue) {
		
		try {
			// XML字符串转对象
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmlStrValue);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);
			return document.getDocumentElement();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			log.error("XML字符串（" + xmlStrValue + "）对象转换失败", e);
		}
		
		return null;
	}
	
	/**
	 * 线程XML对象存储
	 * 
	 * @param xmlStrValue
	 * 			XML内容字符串
	 */
	public static void saveThreadLocalXMLElementByStrValue(String xmlStrValue) {
		
		try {
			// XML字符串转对象
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmlStrValue);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);
			localElement.set(document.getDocumentElement());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			log.error("XML字符串（" + xmlStrValue + "）对象转换失败", e);
		}
	}
	
	/**
	 * 线程XML对象值获取
	 * 
	 * @param key
	 * 			XML主键
	 */
	public static String getThreadLocalXMLElementValueByKey(String key) {
		return getTagNameValue(localElement.get(), key);
	}

}
