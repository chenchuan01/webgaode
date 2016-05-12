package com.sys.common.util;

import net.sf.json.JSONArray;

/**
 *
 *@author chenchuan
 *@date 2016年2月3日
 *JsonUtil.java
 */
public class JsonUtil {
	/**
	 * 对象转换JSON字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		JSONArray arry = JSONArray.fromObject(obj);
		return arry!=null?arry.toString():"";
	}
}
