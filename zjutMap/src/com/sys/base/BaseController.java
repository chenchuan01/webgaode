package com.sys.base;

import org.springframework.ui.Model;

import com.sys.common.AppExpection;

public class BaseController {
	public String forwordExpPage(Model m,AppExpection busExp,String page) {
		m.addAttribute("exp",busExp);
		return page;
	}
}
