package com.sys.db.dao;

import com.sys.base.BaseDao;
import com.sys.db.entity.Config;

/**
 * @author chenchuan
 * @date 2016��1��22��
 * ConfigDao.java
 */
public interface ConfigDao extends BaseDao<Config> {
	/**
	 * ��ѯconfig
	 * @param configKey
	 * @return
	 */
	Config findKey(String configKey);
}
