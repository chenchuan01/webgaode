package com.sys.db.dao;

import com.sys.base.BaseDao;
import com.sys.db.entity.User;


/**
 * @author chenchuan
 * @date 2016年1月22日
 * UserDao.java
 */
public interface UserDao extends BaseDao<User>{
	/**
	 * 登录验证
	 * @param user
	 * @return
	 */
	User verify(User user);
	
	/**
	 * 添加新用户
	 * @param user
	 */
	User regist(User user);
	
	void encryption(User user);
}
