package com.sys.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sys.common.AppExpection;
import com.sys.db.DBConstants;



/**
 *
 *CommonUtil.java
 *系统功能工具方法
 */
public class CommonUtil {
	/**
	 * 空格分隔
	 */
	public static final String SPACE_SPLIT=" ";
	/**
	 * 过滤属性
	 */
	public static final String[] PASS_FILEDS={"id","delflag"};
	/**
	 * 字符串包含
	 * @param items
	 * @param str
	 * @param splitRegex
	 * @return
	 */
	public static boolean isContains(String items,String str,String splitRegex){
		if(StringUtil.isNotNull(items)&&
				StringUtil.isNotNull(str)){
			for(String item:items.split(splitRegex)){
				if(str.equalsIgnoreCase(item)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * List包含
	 * @param items
	 * @param str
	 * @return
	 */
	public static boolean isCotains(List<String> items,String str){
		if(isListNotNull(items)&&
				StringUtil.isNotNull(str)){
			for (String item : items) {
				if(str.equalsIgnoreCase(item)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 判断list是否为空
	 * @param list
	 * @return
	 */
	public static boolean isListNotNull(List<? extends Object> list){
		if(list!=null&&!list.isEmpty()){
			return true;
		}
		return false;
	}
	/**
	 * @param obj
	 * @param _class
	 * @param fieldNames
	 * @param fieldValues
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class recursive(Object obj,Class _class,StringBuilder fieldNames,StringBuilder fieldValues){
        if(_class==null)
         return null;
         else{
            Field[] fields = _class.getDeclaredFields();
            // 遍历方法集合
            for (Field field : fields) {
            	if(isCotains(Arrays.asList(PASS_FILEDS), field.getName())){
            		continue;
            	}
                try {
                	String name = field.getName(); // 获取属性的名字
        			fieldNames.append(name + DBConstants.FILD_SPLIT);
        			name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
        			String type = field.getGenericType().toString(); // 获取属性的类型
                    String value = switchFieldValue(obj, name, type)+ DBConstants.FILD_SPLIT;
                    fieldValues.append(value);
                } catch (IllegalArgumentException e) {
                LogUtil.error(CommonUtil.class, "反射属性获取属性值出错", new AppExpection(
						"CommonUtil.recursive(Object,Class, StringBulider, StringBulider)", 
						"反射属性获取属性值出错", e));
                }
            }
            return recursive(obj,_class.getSuperclass(),fieldNames,fieldValues);
         }
    }
	private static String switchFieldValue(Object obj, String name, String type){
		String fidldValue = "";
		try {
			Method m = obj.getClass().getMethod("get" + name);
			if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
				
				String value = (String) m.invoke(obj); // 调用getter方法获取属性值
				if (value != null&&StringUtil.isNotNull(value)) {
					fidldValue = "'" + value + "'";
				}
			}
			if (type.equals("class java.lang.Integer")) {
				Integer value = (Integer) m.invoke(obj);
				if (value != null) {
					fidldValue = StringUtil.isNotNull(value.toString())?value.toString():"0";
				}
			}
			if (type.equals("class java.lang.Short")) {
				Short value = (Short) m.invoke(obj);
				if (value != null) {
					fidldValue = StringUtil.isNotNull(value.toString())?value.toString():"0";
				}
			}
			if (type.equals("class java.lang.Double")) {
				Double value = (Double) m.invoke(obj);
				if (value != null) {
					fidldValue = StringUtil.isNotNull(value.toString())?value.toString():"0";
				}
			}
			if (type.equals("class java.lang.Boolean")) {
				Boolean value = (Boolean) m.invoke(obj);
				if (value != null) {
					fidldValue = null!=value&&value?"1":"0";
				}
			}
		} catch (Exception e) {
			LogUtil.error(CommonUtil.class, "",
					new AppExpection(
							"CommonUtil.switchFieldValue(T, String, String)", 
							"反射属性获取属性值出错", e));
		}
		
		return fidldValue;
	}
	public static String format(String msg ,Object... params) {
		if(params==null||params.length<=0){
			return msg;
		}else{
			String temp = msg;
			for (int i = 0; i < params.length; i++) {
				String holderStr = "{"+i+"}";
				temp = temp.replace(holderStr,params[i]+"");
			}
			return temp;
		}
	}
	public static List<String> clearList(List<String> studentIds) {
		List<String> list = new ArrayList<String>();
		for (String id : studentIds) {
			if(StringUtil.isNotNull(id)){
				list.add(id);
			}
		}
		return list;
	}
	private static void perm(String[] buf, int start, int end,List<String> combineWords,String tempWords) {
		if(start==end){
			return;
		}
		String temp = tempWords;
		if(start<end){
			for (int i = start; i < end;i++) {
				if(StringUtil.isNull(temp)){
					temp = temp+ buf[i];
				}else{
					temp = temp +"/"+ buf[i];
				}
				combineWords.add(temp);
			}
		}else{
			start++;
			perm(buf, start, end, combineWords, tempWords);
		}
		
    }
	public static List<String> combineWords(String[] keyWords) {
		List<String> combineWords = new ArrayList<String>();
		for (int i = 0; i < keyWords.length; i++) {
			perm(keyWords, i, keyWords.length, combineWords,"");
		}
		
		
		return combineWords;
	}
	public static void main(String[] args) {
		String[] like = {"讲","个","笑话"};
		System.out.println(combineWords(like));
	}
}
