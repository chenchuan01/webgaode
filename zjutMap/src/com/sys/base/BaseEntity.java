package com.sys.base;


/**
 * 
 * @author chenchuan
 * @date 2016��1��22�� ���ݶ������
 */
public class BaseEntity {

	private Integer id;// ����

	private String delflag = "0";// ɾ����־

	public BaseEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public boolean notNull(){
		return this!=null&&this.getId()!=null;
	}
}
