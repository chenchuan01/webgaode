package com.sys.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author chenchuan
 * @date 2016��1��25��
 * DateUtil.java
 */
public class DateUtil {
	/**
	 * ͳһʱ�䱣�ָ�ʽ
	 */
	public static final String TIMEFORMAT="yyyy-MM-dd HH:mm:ss";
	/**
	 * ͳһ���ڱ��ָ�ʽ
	 */
	public static final String DATEFORMAT="yyyy-MM-dd";

	/**
	 * ʱ��Ŀ¼��ʽ
	 */
	public static final String DATEDIR = "yyMMdd";
	/**
	 * ʱ�䵥λ:��
	 */
	public static final String METRIC_Y = "y";
	/**
	 * ʱ�䵥λ:��
	 */
	public static final String METRIC_M = "M";
	/**
	 * ʱ�䵥λ:��
	 */
	public static final String METRIC_D = "d";
	
	/**
	 * ʱ��format
	 */
	private static SimpleDateFormat timeSdf = new SimpleDateFormat(TIMEFORMAT);
	/**
	 * ����format
	 */
	private static SimpleDateFormat dateSdf = new SimpleDateFormat(DATEFORMAT);
	
	/**
	 * ����
	 */
	private static Calendar calendar = Calendar.getInstance();
	/**
	 * �������ʱ��
	 * @return string
	 */
	public static String getNow(){
		return timeSdf.format(new Date());
	}
	/**
	 * ��ý�������
	 * @return string
	 */
	public static String getToday(){
		return dateSdf.format(new Date());
	}
	
	/**
	 * Сʱʱ�����
	 */
	public static long countHourTime(Date setDate,int hours){
		calendar.setTime(setDate);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTimeInMillis();
	}
	/**
	 * �����
	 */
	public static String countDayTime(Date setDate,int day){
		calendar.setTime(setDate);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return timeSdf.format(calendar.getTime());
	}
	/**
	 * ת����datetime
	 * @param planTimeStr
	 * @return
	 */
	public static Date parsTime(String timeStr){
		try {
			return timeSdf.parse(timeStr);
		} catch (ParseException e) {
			LogUtil.error(DateUtil.class, "ʱ��ת������", e);
		}
		return null;
	}
	/**
	 * ת����date
	 * @param planTimeStr
	 * @return
	 */
	public static Date parsDate(String dateStr){
		try {
			return dateSdf.parse(dateStr);
		} catch (ParseException e) {
			LogUtil.error(DateUtil.class, "ʱ��ת������", e);
		}
		return null;
	}
	
	/**
	 * ת����Str
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date){
		return timeSdf.format(date);
	}
	/**
	 * ת����Str
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
	 * �뵱ǰ�Ƚ��Ƿ���
	 * @param effend
	 * @return ���ڷ���true δ���ڷ���false
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
	 * ��Ч���Ƿ�Ϸ�
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
	 * �������յ���
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
