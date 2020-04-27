package com.auto.base.exception;

/**
 * 循环中断异常
 * @author drl
 *
 */
public class CyclicInterruptException extends Exception {

	/**
	 *序列化编号
	 */
	private static final long serialVersionUID = 202828532718619773L;
	
	/**
	 * 错误消息
	 */
	private String msg;

	public CyclicInterruptException(String msg, Throwable t) {
		super(t);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
