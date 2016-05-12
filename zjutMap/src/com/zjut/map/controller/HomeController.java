package com.zjut.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *HomeController.java
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	static final String VIEW_HOME="home";
	static final String DIR_CONTENT="content/";
	static final String VIEW_DESC="viewdesc";
	static final String VIEW_SHARE="routshare";
	static final String VIEW_NAV="navmap";
	/**
	 * @return
	 */
	@RequestMapping({"","/nav"})
	public String home(){
		return VIEW_HOME;
	}
	/**
	 * @return
	 */
	@RequestMapping({"/navmap"})
	public String navmap(){
		return DIR_CONTENT+VIEW_NAV;
	}
	/**
	 * @return
	 */
	@RequestMapping({"/viewdesc"})
	public String viewdesc(){
		return DIR_CONTENT+VIEW_DESC;
	}
	/**
	 * @return
	 */
	@RequestMapping({"/routshare"})
	public String routshare(){
		return DIR_CONTENT+VIEW_SHARE;
	}
}
