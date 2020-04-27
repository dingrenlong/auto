package com.auto.base.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.castor.core.util.Base64Decoder;
import org.castor.core.util.Base64Encoder;

import com.auto.base.util.error.ErrorUtil;

public class RSAUtil {
	
    public static final String KEY_ALGORITHM = "RSA";  
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";  
  
    private static final String PUBLIC_KEY = "RSAPublicKey";  
    private static final String PRIVATE_KEY = "RSAPrivateKey";  
  
    /** 
     * 用私钥对信息生成数字签名 
     *  
     * @param data 
     *            加密数据 
     * @param privateKey 
     *            私钥 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String sign(byte[] data, String privateKey) throws Exception {  
      
    	// 解密由base64编码的私钥  
        byte[] keyBytes = Base64Decoder.decode(privateKey);  
  
        // 构造PKCS8EncodedKeySpec对象  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
  
        // KEY_ALGORITHM 指定的加密算法  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
  
        // 取私钥匙对象  
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);  
  
        // 用私钥对信息生成数字签名  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initSign(priKey);  
        signature.update(data);  
  
        return new String(Base64Encoder.encode(signature.sign()));  
    }  
  
    /** 
     * 校验数字签名 
     *  
     * @param data 
     *            加密数据 
     * @param publicKey 
     *            公钥 
     * @param sign 
     *            数字签名 
     *  
     * @return 校验成功返回true 失败返回false 
     * @throws Exception 
     *  
     */  
    public static boolean verify(byte[] data, String publicKey, String sign)  
            throws Exception {  
  
        // 解密由base64编码的公钥  
        byte[] keyBytes = Base64Decoder.decode(publicKey);  
  
        // 构造X509EncodedKeySpec对象  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
  
        // KEY_ALGORITHM 指定的加密算法  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
  
        // 取公钥匙对象  
        PublicKey pubKey = keyFactory.generatePublic(keySpec);  
  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initVerify(pubKey);  
        signature.update(data);  
  
        // 验证签名是否正常  
        return signature.verify(Base64Decoder.decode(sign));  
    }  
  
    /** 
     * 解密<br> 
     * 用私钥解密 
     *  
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String decryptByPrivateKey(String data, String key)  
            throws Exception {  
        // 对密钥解密  
        byte[] keyBytes = Base64Decoder.decode(key);  
        byte[] dataBytes = Base64Decoder.decode(data);
        // 取得私钥  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
  
        // 对数据解密  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
  
        return new String(cipher.doFinal(dataBytes));  
    }  
  
    /** 
     * 解密<br> 
     * 用公钥解密 
     *  
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String decryptByPublicKey(String data, String key)  
            throws Exception {  
        // 对密钥解密  
        byte[] keyBytes = Base64Decoder.decode(key);  
        byte[] dataBytes = Base64Decoder.decode(data);
        // 取得公钥  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicKey = keyFactory.generatePublic(x509KeySpec);  
  
        // 对数据解密  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);  

        return new String(cipher.doFinal(dataBytes));
    }  
  
    /** 
     * 加密<br> 
     * 用公钥加密 
     *  
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByPublicKey(String data, String key)  
            throws Exception {  
        // 对公钥解密  
        byte[] keyBytes = Base64Decoder.decode(key);  
  
        // 取得公钥  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicKey = keyFactory.generatePublic(x509KeySpec);  
  
        // 对数据加密  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
  
        return new String(Base64Encoder.encode(cipher.doFinal(data.getBytes())));  
    }  
  
    /** 
     * 加密<br> 
     * 用私钥加密 
     *  
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByPrivateKey(String data, String key)  
            throws Exception {  
        // 对密钥解密  
        byte[] keyBytes = Base64Decoder.decode(key);  
  
        // 取得私钥  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
  
        // 对数据加密  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
  
        return new String(Base64Encoder.encode(cipher.doFinal(data.getBytes())));  
    }  
  
    /** 
     * 取得私钥 
     *  
     * @param keyMap 
     * @return 
     * @throws Exception 
     */  
    public static String getPrivateKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PRIVATE_KEY);  
  
        return new String(Base64Encoder.encode(key.getEncoded()));  
    }  
  
    /** 
     * 取得公钥 
     *  
     * @param keyMap 
     * @return 
     * @throws Exception 
     */  
    public static String getPublicKey(Map<String, Object> keyMap)  
            throws Exception {  
        Key key = (Key) keyMap.get(PUBLIC_KEY);  
  
        return new String(Base64Encoder.encode(key.getEncoded()));  
    }  
  
    /** 
     * 初始化密钥 
     *  
     * @return 
     * @throws Exception 
     */  
    public static Map<String, Object> init1024Key() throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator  
                .getInstance(KEY_ALGORITHM);  
        keyPairGen.initialize(1024);  
  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
  
        // 公钥  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
  
        // 私钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
  
        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
  
        keyMap.put(PUBLIC_KEY, publicKey);  
        keyMap.put(PRIVATE_KEY, privateKey);  
        return keyMap;  
    }  
    
    /** 
     * 初始化密钥 
     *  
     * @return 
     * @throws Exception 
     */  
    public static Map<String, Object> init2048Key() throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator  
                .getInstance(KEY_ALGORITHM);  
        keyPairGen.initialize(2048);  
  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
  
        // 公钥  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
  
        // 私钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
  
        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
  
        keyMap.put(PUBLIC_KEY, publicKey);  
        keyMap.put(PRIVATE_KEY, privateKey);  
        return keyMap;  
    }
    
    /** 
     * 初始化密钥 
     *  
     * @return 
     * @throws Exception 
     */  
    public static Map<String, Object> init4096Key() throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator  
                .getInstance(KEY_ALGORITHM);  
        keyPairGen.initialize(4096);  
  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
  
        // 公钥  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
  
        // 私钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
  
        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
  
        keyMap.put(PUBLIC_KEY, publicKey);  
        keyMap.put(PRIVATE_KEY, privateKey);  
        return keyMap;  
    }
    
    /**
     * 格式化Linux公钥
     * 
     * @param publicKey
     * 			公钥字符串
     * 
     * @return Linux公钥
     */
    public static String formatPublicKeyForLinux(String publicKey) {  
    	
    	// 开始内容
    	StringBuilder newKey = new StringBuilder("-----BEGIN PUBLIC KEY-----\\n");
    	
    	try {
	    	if (publicKey != null) {
	    		int index = 0;
	    		int times = 0;
	    		if (publicKey.length()%64 == 0) {
	    			times = publicKey.length()/64 - 1;
	    		} else {
	    			times = publicKey.length()/64;
	    		}
	        	for (; index < times; index++) {
	        		newKey.append(publicKey.substring(index*64, (index+1)*64)).append("\\n");
	        	}
	        	newKey.append(publicKey.substring(index*64, publicKey.length())).append("\\n");
	    	}
    	} catch (Exception e) {
    		ErrorUtil.loggingErrorMsg("Linux公钥格式化错误", e);
    	}
    	
    	newKey.append("-----END PUBLIC KEY-----\\n");
    	
    	// 公钥返回
    	return newKey.toString();
    }
    
    /**
     * 格式化Linux私钥
     * 
     * @param privateKey
     * 			私钥字符串
     * 
     * @return Linux私钥
     */
    public static String formatPrivateKeyForLinux(String privateKey) {  
    	
    	// 开始内容
    	StringBuilder newKey = new StringBuilder("-----BEGIN RSA PRIVATE KEY-----\\n");
    	
    	try {
	    	if (privateKey != null) {
	    		int index = 0;
	    		int times = 0;
	    		if (privateKey.length()%64 == 0) {
	    			times = privateKey.length()/64 - 1;
	    		} else {
	    			times = privateKey.length()/64;
	    		}
	        	for (; index < times; index++) {
	        		newKey.append(privateKey.substring(index*64, (index+1)*64)).append("\\n");
	        	}
	        	newKey.append(privateKey.substring(index*64, privateKey.length())).append("\\n");
	    	}
		} catch (Exception e) {
			ErrorUtil.loggingErrorMsg("Linux私钥格式化错误", e);
		}
    	
    	newKey.append("-----END RSA PRIVATE KEY-----\\n");
    	
    	// 私钥返回
    	return newKey.toString();
    }
    
}
