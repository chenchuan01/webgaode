package com.sys.common.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.sys.common.util.DateUtil;
import com.sys.common.util.LogUtil;
/**
 * @author chenchuan
 * @date 2016年1月25日
 * DateFormatTag.java
 * 页面系统实现格式转换工具
 */
public class DateFormatTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dateStr;
	private String formatStr;
	@Override
	public int doStartTag() throws JspException {
		String dateStr = this.dateStr;
		String formatStr = this.formatStr;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			Date date = DateUtil.parsTime(dateStr);
			sdf = new SimpleDateFormat(formatStr);
			dateStr = sdf.format(date);
			pageContext.getOut().write(dateStr);
		} catch (IOException e) {
			LogUtil.error(getClass(), "DateFormatTag.doStartTag()", e);
		} 
		return super.doStartTag();
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getFormatStr() {
		return formatStr;
	}
	public void setFormatStr(String formatStr) {
		this.formatStr = formatStr;
	}
	
	
	
}
