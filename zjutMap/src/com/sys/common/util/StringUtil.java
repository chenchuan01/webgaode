package com.sys.common.util;


/**
 * ×Ö·û´®´¦Àí¹¤¾ßÀà
 * StringUtil.java
 */
public class StringUtil {
	
	/**
	 * ×Ö·û´®ÅÐ¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(null!=str){
			str = str.trim();
		}
		return str==null||"".equalsIgnoreCase(str);
	}
	/**
	 * ×Ö·û´®ÅÐ¶Ï·Ç¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str){
		if(null!=str){
			str = str.trim();
		}
		return str!=null&&!"".equalsIgnoreCase(str);
	}
	
}
