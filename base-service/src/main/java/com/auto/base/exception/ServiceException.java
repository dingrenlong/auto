package com.auto.base.exception;

/**
 * 循环中断异常
 * @author drl
 *
 */
public class ServiceException extends Exception {

	/**
	 *序列化编号
	 */
	private static final long serialVersionUID = 202828532718619773L;
	
	/**
	 * 错误消息
	 */
	private String msg;

	public ServiceException(String msg, Throwable t) {
		super(t);
		this.msg = msg;
	}
	
	public ServiceException(Throwable t) {
		super(t);
		this.msg = t.getMessage();
	}
	
	public ServiceException(String msg) {
		super(new Throwable(msg));
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
