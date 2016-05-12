package com.sys.common.util;

import java.security.MessageDigest;

import com.sys.common.AppExpection;

/**
 * 
 * @author chenchuan
 * @date 2016年1月22日 
 * MD5Util.java
 */
public class MD5Util {
	static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public final static String MD5(String orgString) {

		try {
			byte[] btInput = orgString.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			LogUtil.error(MD5Util.class, new AppExpection(
					"MD5Util.MD5(String)", "密码加密失败"));
			return orgString;
		}
	}
	public static void main(String[] args) {
		
		System.out.println(MD5("chenchuan"));
		System.out.println("9B6F32318D67ADBD18CFAA274BA71CA1".equals(MD5("chenchuan")));
	}
}
