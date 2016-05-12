package com.sys.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sys.SysConstants;
import com.sys.common.util.LogUtil;
import com.sys.db.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String[] IGNORE_URI = {"login","verify","img" };

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = false;
		String url = request.getRequestURL().toString();
		LogUtil.infoReq(this.getClass(), url);
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				flag = true;
				break;
			}
		}
		if(!flag){
			User user = (User) request.getSession().getAttribute(SysConstants.SYSUSER);
			if (user != null){
				flag = true;
				
			}else{
				response.sendRedirect(request.getContextPath()+"/login.do");
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
