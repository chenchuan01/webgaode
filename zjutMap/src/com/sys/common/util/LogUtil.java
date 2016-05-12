package com.sys.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sys.SysConstants;
import com.sys.common.AppExpection;

/**
 * @author chenchuan
 * @date 2016��1��25�� ��־��¼������ LogUtil.java
 */
public class LogUtil {

	private static Logger logger;
	

	/**
	 * info����
	 * 
	 * @param clazz
	 * @param context
	 * @param params
	 */
	public static void info(Class<?> clazz, String context, Object... params) {
		logger = LoggerFactory.getLogger(clazz);
		context = CommonUtil.format(context,params);
		logger.info(context, params);
	}
	/**
	 * info����
	 * 
	 * @param clazz
	 * @param context
	 * @param params
	 */
	public static void infoSql(Class<?> clazz, String context, Object... params) {
			info(clazz, SysConstants.SQL_LOG+"=>"+context, params);
	}
	/**
	 * info����
	 * 
	 * @param clazz
	 * @param context
	 * @param params
	 */
	public static void infoReq(Class<?> clazz, String context, Object... params) {
			info(clazz, SysConstants.URL_LOG+"=>"+context, params);
	}

	/**
	 * info���𣨺���������
	 * 
	 * @param clazz
	 * @param methodName
	 * @param context
	 * @param params
	 */
	public static void info(Class<?> clazz, String methodName, String context,
			Object... params) {
		context = "methodName=>(" + methodName + ");info->" + context;
		info(clazz, context, params);
	}


	/**
	 * error����
	 * 
	 * @param clazz
	 * @param context
	 * @param params
	 */
	private static void error(Class<?> clazz, String context, Object... params) {
		logger = LoggerFactory.getLogger(clazz);
		logger.error(context, params);
	}

	/**
	 * error���𣨺���������
	 * 
	 * @param clazz
	 * @param methodName
	 * @param context
	 * @param params
	 */
	public static void error(Class<?> clazz, String methodName, String context,
			Object... params) {
		context = "methodName=>(" + methodName + ");error->" + context;
		error(clazz, context, params);
	}


	/**
	 * ��¼�쳣
	 * 
	 * @param clazz
	 * @param context
	 * @param e
	 */
	public static void error(Class<?> clazz, String context, Exception e) {
		logger = LoggerFactory.getLogger(clazz);
		logger.error(context, e);
	}

	/**
	 * ��¼ҵ���쳣
	 * 
	 * @param clazz
	 * @param context
	 * @param e
	 */
	public static void error(Class<?> clazz, AppExpection e) {
		String context = "BusExpection[method=" + e.getMethodName() + "msg="
				+ e.getMessage() + "]\n";
		error(clazz, context, e);
	}

}
