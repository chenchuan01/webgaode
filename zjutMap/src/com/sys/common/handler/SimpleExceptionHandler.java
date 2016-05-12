package com.sys.common.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.sys.common.AppExpection;

import com.sys.common.util.LogUtil;
@Component
public class SimpleExceptionHandler implements HandlerExceptionResolver{
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		AppExpection busExpection = e2AppExpection(ex);
		//处理Ajax异常
		if(isAjaxRequest(request)){
			handleAjaxResponse(busExpection,request,response);
			return new ModelAndView();
		}
		return null;
	}

	private AppExpection e2AppExpection(Exception ex) {
		AppExpection be = getCausedBy(ex, AppExpection.class);
		if(null!=be){
			return be;
		}
		
		return new AppExpection("System.method()", "系统出现异常请稍后重试！", ex);
	}
	@SuppressWarnings("unchecked")
	public static <T extends Throwable> T getCausedBy(Exception ex, Class<T> causeExceptionClazz) {
		if (null == ex || null == causeExceptionClazz) {
			return null;
		}
		Throwable cause = ex;
		while (cause != null) {
			if (causeExceptionClazz.isInstance(cause)) {
				return (T)cause;
			}
			cause = cause.getCause();
		}
		return null;
	}
	/**
	 * Ajax请求响应
	 * @param errorVo
	 * @param response
	 */
	protected void handleAjaxResponse(AppExpection errorVo,HttpServletRequest request,HttpServletResponse response){
			try {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				
				response.getWriter().write(busExpToJson(errorVo));
				
				
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				LogUtil.error(SimpleExceptionHandler.class, 
						"SimpleExceptionResolver.handleAjaxResponse", 
						"Ajax回写发生异常:URL->{0}", 
						request.getRequestURL());
			}
	}
	/**
	 * 判断当前请求是否是ajax请求
	 * @param request
	 * @return
	 */
	protected boolean isAjaxRequest(HttpServletRequest request){
		String accept = (request.getHeader("accept") == null ? "":request.getHeader("accept"));
		String requestedWith = (request.getHeader("X-Requested-With") == null ? "":request.getHeader("X-Requested-With"));
		if(accept.indexOf("application/json") > -1 
				|| requestedWith.indexOf("XMLHttpRequest") > -1){
			return true;
		}
		return false;
	}
	private String busExpToJson(AppExpection ex) {
		StringBuilder json = new StringBuilder("{");
		json.append("\"methodName\":\""+ex.getMethodName()+"\"");
		json.append(",");
		json.append("\"msg\":\""+ex.getMsg()+"\"");
		if(null!=ex.getE()){
			json.append(",");
			json.append("\"e\":{\"message\":\""+ex.getE().getMessage()+"\"}");
		}
		
		json.append("}");
		return json.toString();
	}
}
