package com.sys.common.util;

import javax.servlet.http.HttpSession;

import com.sys.SysConstants;
import com.sys.db.entity.User;

/**
 *@author chenchuan
 *@date 2016��1��31��
 *SessionUtil.java
 */
public class SessionUtil {
	public static User sysUser(HttpSession session){
		return (User)session.getAttribute(SysConstants.SYSUSER);
	}
}
