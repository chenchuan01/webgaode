package com.sys.base.dto;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 封装多个文件同时上传
 *
 */
public class UploadItem {
	private Integer id;
	private List<CommonsMultipartFile> files;

	public List<CommonsMultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<CommonsMultipartFile> files) {
		this.files = files;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
