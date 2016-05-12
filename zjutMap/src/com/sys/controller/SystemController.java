package com.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.BaseController;
import com.sys.base.dto.PageResult;
import com.sys.base.dto.QueryParam;
import com.sys.common.AppExpection;
import com.sys.common.util.SessionUtil;
import com.sys.common.util.StringUtil;
import com.sys.db.DBConstants;
import com.sys.db.entity.Config;
import com.sys.db.entity.User;
import com.sys.db.service.ConfigService;
import com.sys.db.service.UserService;

/**
 *系统功能控制器
 */
@Controller
@RequestMapping("/sys")
public class SystemController extends BaseController {
	@Resource
	UserService userService;
	@Resource
	ConfigService configService;
	private static final String FORM_SPACE="form/";
	/**
	 * 用户列表
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userPage")
	public @ResponseBody PageResult<User> userListPage(QueryParam<User> params,
			Model m, User user) {
		if(user!=null&&StringUtil.isNotNull(user.getUserName())){
			user.setUserName(DBConstants.CHAR_LIKE+user.getUserName()+DBConstants.CHAR_LIKE);
		}
		params.setParam(user);
		PageResult<User> result = userService.pageQuery(params);
		return result;
	}

	/**
	 * 用户详情
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userForm")
	public String userForm(Integer id, Model m) {
		User user = userService.findById(id);
		m.addAttribute("user", user);
		return FORM_SPACE+"userForm";
	}
	/**
	 * 用户详情
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "delBatchUser")
	public @ResponseBody User delBatchUser(String delIds,HttpSession session) {
		if(StringUtil.isNotNull(delIds)){
			for(String id:delIds.split(",")){
				userService.deleteEntity(new User(Integer.valueOf(id)));
			}
		}
		return SessionUtil.sysUser(session);
	}

	/**
	 * 密码修改
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "pwdModify")
	public String pwdModify(Integer id, Model m) {
		User user = userService.findById(id);
		m.addAttribute("user", user);
		return FORM_SPACE+"pwdModify";
	}

	/**
	 * 用户修改
	 * 
	 * @param user
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userModify")
	public @ResponseBody User userModify(User user, HttpSession session)
			throws AppExpection {
		if(user!=null&&user.getId()==null){
			userService.userRegist(user);
		}else{
			userService.userUpdate(user);
		}
		
		return user;
	}

	/**
	 * 用户删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "userDelete")
	public @ResponseBody User userDelete(Integer id) {
		User user = userService.findById(id);
		userService.deleteEntity(user);
		return user;
	}
	/**
	 * 系统配置分页查询
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configPage")
	public @ResponseBody PageResult<Config> configListPage(
			QueryParam<Config> params, Model m, Config config)
			throws AppExpection {
		if(config!=null){
			if(StringUtil.isNotNull(config.getKey())){
				config.setKey(DBConstants.CHAR_LIKE+config.getKey()+DBConstants.CHAR_LIKE);
			}
			if(StringUtil.isNotNull(config.getValue())){
				config.setValue(DBConstants.CHAR_LIKE+config.getValue()+DBConstants.CHAR_LIKE);
			}
		}
		params.setParam(config);
		PageResult<Config> result = configService.pageQuery(params);
		return result;
	}

	/**
	 * 系统配置表单
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configForm")
	public String configForm(Integer id, Model m) throws AppExpection {
		Config config = configService.findById(id);
		m.addAttribute("config", config);
		return FORM_SPACE+"configForm";
	}

	/**
	 * 系统配置修改
	 * 
	 * @param config
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configModify")
	public @ResponseBody Config configModify(Config config, HttpSession session)
			throws AppExpection {
		if(config!=null&&config.getId()==null){
			configService.saveEntity(config);
		}else{
			configService.updateEntity(config);
		}
		
		return config;
	}
}
