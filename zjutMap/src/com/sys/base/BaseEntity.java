package com.sys.base;


/**
 * 
 * @author chenchuan
 * @date 2016年1月22日 数据对象基类
 */
public class BaseEntity {

	private Integer id;// 主键

	private String delflag = "0";// 删除标志

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
