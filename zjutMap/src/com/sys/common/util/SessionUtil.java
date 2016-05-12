package com.sys.common.util;

import javax.servlet.http.HttpSession;

import com.sys.SysConstants;
import com.sys.db.entity.User;

/**
 *@author chenchuan
 *@date 2016Äê1ÔÂ31ÈÕ
 *SessionUtil.java
 */
public class SessionUtil {
	public static User sysUser(HttpSession session){
		return (User)session.getAttribute(SysConstants.SYSUSER);
	}
}
