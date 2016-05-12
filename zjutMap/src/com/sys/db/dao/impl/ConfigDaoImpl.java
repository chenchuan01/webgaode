package com.sys.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.base.impl.BaseDaoImpl;
import com.sys.db.dao.ConfigDao;
import com.sys.db.entity.Config;

/**
 * @author chenchuan
 * @date 2016Äê1ÔÂ22ÈÕ 
 * ConfigDaoImpl.java
 */
@Repository
public class ConfigDaoImpl extends BaseDaoImpl<Config> implements ConfigDao {

	public Config findKey(String configKey) {
		Config config = new Config(configKey);
		List<Config> list = find(config);
		return list!=null&&!list.isEmpty()?list.get(0):null;
	}

}
