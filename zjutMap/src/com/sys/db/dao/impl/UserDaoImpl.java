package com.sys.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.base.impl.BaseDaoImpl;
import com.sys.common.util.MD5Util;
import com.sys.db.dao.UserDao;
import com.sys.db.entity.User;

/**
 * UserDaoImpl.java
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User verify(User user) {
//		encryption(user);
		List<User> users =find(user);
		return users==null||users.isEmpty()?null:users.get(0);
	}

	public User regist(User user) {
//		encryption(user);
		return save(user).get(0);
	}
	/**
	 * ”√ªß√‹¬Îº”√‹
	 * @param user
	 */
	public void encryption(User user) {
		String pwd = user.getPassword();
		user.setPassword(MD5Util.MD5(pwd));
	}
}
