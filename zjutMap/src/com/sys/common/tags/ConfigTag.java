package com.sys.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.sys.common.util.ConfigUtil;
import com.sys.common.util.StringUtil;
/**
 * @author chenchuan
 * @date 2016��1��25��
 * DateFormatTag.java
 * ҳ��ϵͳ������Դ��ȡ����
 */
public class ConfigTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String key;
	@Override
	public int doStartTag() throws JspException {
		String configKey = this.key;
		boolean funSwitch = false;
		if(StringUtil.isNotNull(configKey)){
			funSwitch = ConfigUtil.isConfigSwitchOn(key);
		}
		if(funSwitch){
			return Tag.EVAL_BODY_INCLUDE;			
		}else{
			return Tag.SKIP_BODY;
		}
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
