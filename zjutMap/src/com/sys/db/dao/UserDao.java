package com.sys.db.dao;

import com.sys.base.BaseDao;
import com.sys.db.entity.User;


/**
 * @author chenchuan
 * @date 2016��1��22��
 * UserDao.java
 */
public interface UserDao extends BaseDao<User>{
	/**
	 * ��¼��֤
	 * @param user
	 * @return
	 */
	User verify(User user);
	
	/**
	 * ������û�
	 * @param user
	 */
	User regist(User user);
	
	void encryption(User user);
}
