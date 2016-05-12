package com.sys.db.dao;

import com.sys.base.BaseDao;
import com.sys.db.entity.Config;

/**
 * @author chenchuan
 * @date 2016年1月22日
 * ConfigDao.java
 */
public interface ConfigDao extends BaseDao<Config> {
	/**
	 * 查询config
	 * @param configKey
	 * @return
	 */
	Config findKey(String configKey);
}
