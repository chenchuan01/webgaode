package com.sys.common.util;

import java.io.File;

import com.sys.SysConstants;

/**
 *@author chenchuan
 *@date 2016Äê2ÔÂ2ÈÕ
 *ImgUtil.java
 */
public class ImgUtil {
	public static StringBuilder replaceFileSplit(String dirs) {
		String[] tempDirs = dirs.split(SysConstants.PATH_SPLIT);
		StringBuilder tagDir = new StringBuilder();
		int i = 0;
		for (String dir : tempDirs) {
			if(StringUtil.isNotNull(dir)){
				tagDir.append(dir);
				if(i!=tempDirs.length-1){
					tagDir.append(File.separator);
				}
			}
			i++;
		}
		return tagDir;
	}
}
