package com.sys.base.dto;

import java.util.List;

import com.sys.base.BaseEntity;
import com.sys.common.ConfigKeys;
import com.sys.common.util.ConfigUtil;
import com.sys.common.util.StringUtil;


/**
 * @author chenchuan
 * @date 2016��1��22��
 * @param <T>
 * ��ѯ����
 */
public class QueryParam<T>{
	//��ʼ����
	private String startDate;
	//��������
	private String endDate;
	//��ʼ����
	private String startDate2;
	//��������
	private String endDate2;
	//ҳ��
	private int page = 1;
	//ҳ������
	private int pageSize = 5;
	//��ѯ����
	private T param;
	//�����ֶ�
	private String orderFiled;
	//��������
	private String orderType;
	private List<String> queryTimeDates;
	public QueryParam() {
		this.pageSize = ConfigUtil.getIntVal(ConfigKeys.PAGESIZE);
	}
	public QueryParam(int page) {
		this.page = page;
		this.pageSize = ConfigUtil.getIntVal(ConfigKeys.PAGESIZE);
	}
	public QueryParam(QueryParam<? extends BaseEntity> param) {
		setPage(param.getPage());
		this.startDate = param.getStartDate();
		this.endDate = param.getEndDate();
		this.startDate2= param.getStartDate2();
		this.endDate2 = param.getEndDate2();
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		if(StringUtil.isNotNull(startDate)){
			this.startDate = startDate+" 00:00";
		}else{
			this.startDate = startDate;
		}
		
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		if(StringUtil.isNotNull(endDate)){
			this.endDate = endDate+" 23:59";
		}else{
			this.endDate = endDate;
		}
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderFiled() {
		return orderFiled;
	}
	public void setOrderFiled(String orderFiled) {
		this.orderFiled = orderFiled;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getStartDate2() {
		return startDate2;
	}
	public void setStartDate2(String startDate2) {
		if(StringUtil.isNotNull(startDate2)){
			this.startDate2 = startDate2+" 00:00";
		}else{
			this.startDate2 = startDate2;
		}
	}
	public String getEndDate2() {
		return endDate2;
	}
	public void setEndDate2(String endDate2) {
		if(StringUtil.isNotNull(endDate2)){
			this.endDate2 = endDate2+" 00:00";
		}else{
			this.endDate2 = endDate2;
		}
	}
	public List<String> getQueryTimeDates() {
		return queryTimeDates;
	}
	public void setQueryTimeDates(List<String> queryTimeDates) {
		this.queryTimeDates = queryTimeDates;
	}
	
	
}
