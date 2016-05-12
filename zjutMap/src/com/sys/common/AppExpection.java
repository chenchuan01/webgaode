package com.sys.common;
/**
 * @author chenchuan
 * @date 2016年1月25日
 * 通用业务异常
 * AppExpection.java
 */
public class AppExpection extends Exception{
	private static final long serialVersionUID = 1L;
	private String methodName;
	private String msg;
	private Exception e;
	public AppExpection(){
		
	}
	public AppExpection(String name,String msg){
		this.methodName = name;
		this.msg = msg;
	}
	public AppExpection(String name,String msg,Exception e){
		this.methodName = name;
		this.msg = msg;
		this.e = e;
	}
	
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Exception getE() {
		return e;
	}
	public void setE(Exception e) {
		this.e = e;
	}

}
