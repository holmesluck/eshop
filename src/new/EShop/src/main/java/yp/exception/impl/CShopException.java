package yp.exception.impl;

import yp.exception.CShopExceptionI;

/**
 * 自定义异常类
 * @author YP
 *
 */
public class CShopException extends Exception implements CShopExceptionI{
	/**
	 * 错误提示信息
	 */
	private String message;
	
	/**
	 * 错误类型
	 */
	private String exceptionType;
	
	/**
	 * 错误的功能模块
	 */
	private String exceptionComponent;
	
	/**
	 * 错误的位置.例：类名：方法名
	 */
	private String exceptionLocation;
	
	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getExceptionComponent() {
		return exceptionComponent;
	}

	public void setExceptionComponent(String exceptionComponent) {
		this.exceptionComponent = exceptionComponent;
	}

	public String getExceptionLocation() {
		return exceptionLocation;
	}

	public void setExceptionLocation(String exceptionLocation) {
		this.exceptionLocation = exceptionLocation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//构造器
	public CShopException(){
		super();
	}
	
	public CShopException(String message){
		this.message = message;
	}
	
}
