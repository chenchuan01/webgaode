package com.sys.common.util;

import java.security.MessageDigest;

import com.sys.common.AppExpection;

/**
 * 
 * @author chenchuan
 * @date 2016��1��22�� 
 * MD5Util.java
 */
public class MD5Util {
	static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public final static String MD5(String orgString) {

		try {
			byte[] btInput = orgString.getBytes();
			// ���MD5ժҪ�㷨�� MessageDigest ����
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽڸ���ժҪ
			mdInst.update(btInput);
			// �������
			byte[] md = mdInst.digest();
			// ������ת����ʮ�����Ƶ��ַ�����ʽ
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
					"MD5Util.MD5(String)", "�������ʧ��"));
			return orgString;
		}
	}
	public static void main(String[] args) {
		
		System.out.println(MD5("chenchuan"));
		System.out.println("9B6F32318D67ADBD18CFAA274BA71CA1".equals(MD5("chenchuan")));
	}
}
