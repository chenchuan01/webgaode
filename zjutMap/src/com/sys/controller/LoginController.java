package com.sys.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.SysConstants;
import com.sys.base.BaseController;
import com.sys.common.AppExpection;
import com.sys.common.util.SessionUtil;
import com.sys.common.util.StringUtil;
import com.sys.db.entity.User;
import com.sys.db.service.UserService;

/**
 * ��¼
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private static final String LOGINPAGE = "login/login";
	private static final String TO_INDEX = "redirect:/home.do";
	@Resource
	UserService userService;

	@RequestMapping(value = "")
	public String tologin(HttpSession session) throws InterruptedException {
		if (null != SessionUtil.sysUser(session)) {
			return TO_INDEX;
		}
		return LOGINPAGE;
	}

	@RequestMapping(value = "verify")
	public @ResponseBody User verifyLogin(Model m, HttpSession session, String userName,
			String password, String code) throws AppExpection {
		if (StringUtil.isNull(userName) || StringUtil.isNull(password)) {
			throw new AppExpection("LoginController.verifyLogin(User, Model, HttpSession)","�û���������Ϊ�գ�");
		}
		userName = userName.trim();
		password = password.trim();
		User loginUser = userService.userVerify(new User(userName, password));
		if (loginUser == null) {
			throw new AppExpection("LoginController.verifyLogin(User, Model, HttpSession)","�û�������������δע�ᣡ");
		}

		// ��¼��֤
		session.setAttribute(SysConstants.SYSUSER, loginUser);
		return loginUser;
	}


	@RequestMapping(value = "regist")
	public @ResponseBody User registUser(User user, Model m, String code,
			HttpSession session) throws AppExpection {
		
		if (user == null ||StringUtil.isNull(user.getUserName())|| StringUtil.isNull(user.getPassword())) {
			throw new AppExpection("LoginController.registUser(User,Model)",
							"ע���û�����Ϊ�գ��������룡");
		}
		user.setUserName(user.getUserName());
		boolean result = userService.userRegist(user) != null;
		if (!result) {
			throw  new AppExpection("LoginController.registUser(User,Model)","ע�����û������쳣������ϵ����Ա��");
		}
		return user;
	}
	
	@RequestMapping(value = "logout")
	public void logOut(HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		if (SessionUtil.sysUser(session) != null) {
			session.invalidate();
		}
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		response.sendRedirect(basePath);
	}
}
