package com.sys.common.util;

import net.sf.json.JSONArray;

/**
 *
 *@author chenchuan
 *@date 2016��2��3��
 *JsonUtil.java
 */
public class JsonUtil {
	/**
	 * ����ת��JSON�ַ���
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		JSONArray arry = JSONArray.fromObject(obj);
		return arry!=null?arry.toString():"";
	}
}
