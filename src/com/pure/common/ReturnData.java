package com.pure.common;

/**
 * 客户端访问返回的基础类
 * 
 * @author alex
 *
 */
public class ReturnData {

	/**
	 * 返回代码
	 */
	private String code = null;
	/**
	 * 返回消息
	 */
	private String message = null;
	/**
	 * 返回的数据
	 */
	private Object data = null;

	public ReturnData() {
		this.code = SysCodeMsg.CODE_20000;
		this.message = SysCodeMsg.MSG_20000;
		this.data = null;
	}

	public ReturnData(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 讲返回数据设置为系统错误状态
	 */
	public void change2SysError() {
		this.code = SysCodeMsg.CODE_10000;
		this.message = SysCodeMsg.MSG_10000;
	}

}
