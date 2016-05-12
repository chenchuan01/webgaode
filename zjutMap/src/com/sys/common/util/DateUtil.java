package com.sys.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author chenchuan
 * @date 2016年1月25日
 * DateUtil.java
 */
public class DateUtil {
	/**
	 * 统一时间保持格式
	 */
	public static final String TIMEFORMAT="yyyy-MM-dd HH:mm:ss";
	/**
	 * 统一日期保持格式
	 */
	public static final String DATEFORMAT="yyyy-MM-dd";

	/**
	 * 时间目录格式
	 */
	public static final String DATEDIR = "yyMMdd";
	/**
	 * 时间单位:年
	 */
	public static final String METRIC_Y = "y";
	/**
	 * 时间单位:月
	 */
	public static final String METRIC_M = "M";
	/**
	 * 时间单位:天
	 */
	public static final String METRIC_D = "d";
	
	/**
	 * 时间format
	 */
	private static SimpleDateFormat timeSdf = new SimpleDateFormat(TIMEFORMAT);
	/**
	 * 日期format
	 */
	private static SimpleDateFormat dateSdf = new SimpleDateFormat(DATEFORMAT);
	
	/**
	 * 日历
	 */
	private static Calendar calendar = Calendar.getInstance();
	/**
	 * 获得现在时间
	 * @return string
	 */
	public static String getNow(){
		return timeSdf.format(new Date());
	}
	/**
	 * 获得今天日期
	 * @return string
	 */
	public static String getToday(){
		return dateSdf.format(new Date());
	}
	
	/**
	 * 小时时间计算
	 */
	public static long countHourTime(Date setDate,int hours){
		calendar.setTime(setDate);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTimeInMillis();
	}
	/**
	 * 天计算
	 */
	public static String countDayTime(Date setDate,int day){
		calendar.setTime(setDate);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return timeSdf.format(calendar.getTime());
	}
	/**
	 * 转换成datetime
	 * @param planTimeStr
	 * @return
	 */
	public static Date parsTime(String timeStr){
		try {
			return timeSdf.parse(timeStr);
		} catch (ParseException e) {
			LogUtil.error(DateUtil.class, "时间转换出错", e);
		}
		return null;
	}
	/**
	 * 转换成date
	 * @param planTimeStr
	 * @return
	 */
	public static Date parsDate(String dateStr){
		try {
			return dateSdf.parse(dateStr);
		} catch (ParseException e) {
			LogUtil.error(DateUtil.class, "时间转换出错", e);
		}
		return null;
	}
	
	/**
	 * 转换成Str
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date){
		return timeSdf.format(date);
	}
	/**
	 * 转换成Str
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		return dateSdf.format(date);
	}

	public static Object formatDate(Date date, String formatStr) {
		return new SimpleDateFormat(formatStr).format(date);
	}

	/**
	 * 与当前比较是否到期
	 * @param effend
	 * @return 到期返回true 未到期返回false
	 * @throws ParseException 
	 */
	public static boolean compareDueDate(String effend) {
		Date effEndDate ;
		Date now = new Date();
		try {
			effEndDate = timeSdf.parse(effend);
		} catch (ParseException e) {
			try {
				effEndDate = dateSdf.parse(effend);
			} catch (ParseException e1) {
				return effend.compareTo(getToday())<=0;
			}
		}
		
		long effTime = effEndDate.getTime();
		long nowTime = now.getTime();
		return effTime-nowTime<=0;
	}
	/**
	 * 有效期是否合法
	 * @param effstart
	 * @param effend
	 * @return
	 */
	public static boolean isValidEffs(String effstart, String effend) {
		try {
			Date start = dateSdf.parse(effstart);
			Date end = dateSdf.parse(effend);
			return start.getTime()-end.getTime()<0;
		} catch (ParseException e) {
			return effstart.compareTo(effend)<0;
		}
	}
	/**
	 * 计算最终到期
	 * @param activedate
	 * @param duedate
	 * @param givedate
	 * @param givemetric
	 * @return
	 */
	public static String calculateDate(String dueDateStr,Integer range, String metric) {
		Date end = parsDate(dueDateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(end);
		int calculatemetric = Calendar.DAY_OF_YEAR;
		if(METRIC_Y.equalsIgnoreCase(metric)){
			calculatemetric = Calendar.YEAR;
		}else if(METRIC_M.equalsIgnoreCase(metric)){
			calculatemetric = Calendar.MONTH;
		}else if(METRIC_D.equalsIgnoreCase(metric)){
			calculatemetric = Calendar.DAY_OF_YEAR;
		}
		calendar.add(calculatemetric, range);
		
		return formatDate(calendar.getTime());
	}

}
