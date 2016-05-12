package com.sys.base.dto;

import java.util.List;

/**
 * @author chenchuan
 * @date 2016年1月22日
 * @param <T>
 * 分页查询返回结果
 */
public class PageResult<T> {
	private Integer totalPages;
	private int page;
	private List<T> content;
	private List<T> totalReslt;
	
	public PageResult() {
		super();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public PageResult(PageResult entityResult) {
		this.totalPages = entityResult.getTotalPages();
		this.page = entityResult.getPage();
	}

	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPage) {
		this.totalPages = totalPage;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> contents) {
		this.content = contents;
	}

	public List<T> getTotalReslt() {
		return totalReslt;
	}

	public void setTotalReslt(List<T> totalReslt) {
		this.totalReslt = totalReslt;
	}
	
}
