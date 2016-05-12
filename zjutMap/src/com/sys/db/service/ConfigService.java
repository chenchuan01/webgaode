package com.sys.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sys.base.BaseService;
import com.sys.db.dao.ConfigDao;
import com.sys.db.entity.Config;

/**
 * @author chenchuan
 * @date 2016Äê1ÔÂ22ÈÕ 
 * ConfigService.java
 */
@Component
public class ConfigService extends BaseService<Config> {
	@Autowired
	ConfigDao configDao;

	public Config getConfig(String configKey){
		return configDao.findKey(configKey);
	}
}
