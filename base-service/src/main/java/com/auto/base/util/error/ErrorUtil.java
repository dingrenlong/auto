package com.auto.base.util.error;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.auto.base.exception.ServiceException;
import com.auto.base.util.string.StringUtil;

public class ErrorUtil {

	/**
	 * LOG
	 */
	private static Log log = LogFactory.getLog(ErrorUtil.class);

	/**
	 * 私有构造
	 */
	private ErrorUtil() {

	}

	/**
	 * Service异常抛出处理
	 * 
	 * @param msg 错误内容
	 * @param e   外部异常
	 * @throws ServiceException 服务异常
	 */
	public static void throwServiceException(String msg, Exception e) throws ServiceException {

		// Service异常生成
		ServiceException serviceException = new ServiceException(msg, e);

		// 日志记录
		loggingErrorMsg(msg, serviceException);

		// 异常抛出
		throw serviceException;
	}

	/**
	 * 错误消息记录
	 * 
	 * @param msg 错误内容
	 * @param e
	 */
	public static void loggingErrorMsg(String msg, Exception e) {
		// 日志记录
		log.error(msg, e);
	}

	/**
	 * 获取API错误异常
	 * 
	 * @param errorCode 错误编号
	 * @param t         栈异常信息
	 * @param msgParam  错误消息
	 * 
	 * @return 错误异常实例
	 */
	public static ServiceException getErrorException(String errorMsg, Throwable t, String... msgParam) {

		// API异常实例生成
		ServiceException exception = null;

		if (t != null) {
			exception = new ServiceException(t);
		}

		try {
			// 消息内容
			String msgContent = errorMsg;
			// 存在内容
			if (msgParam != null && msgParam.length > 0) {
				// 消息参数内容替换
				for (int i = 0; i < msgParam.length; i++) {
					if (!StringUtil.isEmpty(msgParam[i])) {
						msgContent = msgContent.replace("{" + i + "}", msgParam[i]);
					}
				}
			}

			// 错误信息设置
			exception = new ServiceException(msgContent);

		} catch (Exception e) {
			// 错误消息处理失败
			exception = new ServiceException("未知错误");
		}

		// 获取异常类
		return exception;
	}

}
