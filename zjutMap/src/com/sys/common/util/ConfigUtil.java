package com.sys.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.SpringContextHolder;
import com.sys.db.entity.Config;
import com.sys.db.service.ConfigService;

/**
 * @author chenchuan
 * @date 2016年1月22日
 * 系统配置工具类
 */
public class ConfigUtil {
	/**
	 * 配置值分割符--;
	 */
	public static final String VALUE_SPLIT=";";
	/**
	 * 配置项键-值分割符--:
	 */
	public static final String K_V_SPLIT=":";
	/**
	 * 配置项多个值分割符--,
	 */
	public static final String V_SPLIT=",";
	
	static ConfigService configService =SpringContextHolder.getBean("configService");
	
	
	
	/**
	 * 获得config对象
	 * @param key
	 * @return
	 */
	public static Config getConfig(String key){
		Config query = new Config(key);
		return configService.findEntity(query);
	}
	
	/**
	 * 获得系统配置值String
	 * @param key
	 * @return
	 */
	public static String getStrVal(String key){
		Config config = getConfig(key);
		if(config!=null&&StringUtil.isNotNull(config.getValue())){
			return config.getValue();
		}
		return "";
	}
	/**
	 * 获得系统配置值Int
	 * @param key
	 * @return
	 */
	public static int getIntVal(String key){
		Config config = getConfig(key);
		if(config!=null&&StringUtil.isNotNull(config.getValue())){
			return Integer.valueOf(config.getValue());
		}
		return 0;
	}
	/**
	 * 返回配置字符串划分的list
	 * @param key
	 * @return
	 */
	public static List<String> getListVal(String key){
		String value = getStrVal(key);
		List<String> list = converToList(value);
		return list;
	}

	public static List<String> converToList(String value) {
		List<String> list = new ArrayList<String>();
		for (String string : value.split(VALUE_SPLIT)) {
			if(StringUtil.isNotNull(string)){
				list.add(string);
			}
		}
		return list;
	}
	/**
	 * 数据库系统开关是否开启
	 * @param key
	 * @return
	 */
	public static boolean isConfigSwitchOn(String key){
		Config config = getConfig(key);
		if(config!=null&&StringUtil.isNotNull(config.getValue())){
			isSwitchOn(config.getValue());
		}
		return false;
	}
	/**
	 * 开关配置
	 * @param value
	 * @return
	 */
	public static boolean isSwitchOn(String value){
		int rslt = 0;
		if(StringUtil.isNotNull(value)){
			rslt = Integer.valueOf(value);
		}
		return rslt==0?false:true;
	}

	/**
	 * 获得键值映射配置
	 * @param cardType
	 * @return
	 */
	public static Map<Object, Object> getMapVal(String configKey) {
		List<String> configs = getListVal(configKey);
		Map<Object, Object> configMap = new HashMap<Object, Object>();
		for (String config : configs) {
			String[] items = config.split(K_V_SPLIT);
			configMap.put(items[0], items[1]);
		}
		return configMap;
	}

}
