/**global_var*/
/**
 * 空值占位符
 */
var NONE_VAL_FLAG = '';
/**
 * 值间占位
 */
var VAL_FLAG='-';
/**
 * 属性中从属属性分割符‘.’
 */
var FILED_SPLIT = '.';
/**
 * 多个属性分割符‘,’
 */
var FILEDS_SPLIT = ',';
/**
 * 配置多个值之间分隔符‘;’
 */
var CONFIG_VAL_SPLIT = ';';
/**
 * 配置映射分割符‘:’
 */
var CONFIG_TO_SPLIT = ':';

/**
 * 注册
 * @param formId
 */
function registUser(formId){
	ajaxLoad(getUrl(formId), getParams(formId),
			function(){
		cfm('新用户注册成功！',function(){
			window.location = basePath+'login.do';
		});
	});
}
/**
 * 登录
 * @param formId
 */
function loginUser(formId){
	ajaxLoad(getUrl(formId), getParams(formId),
			function(){
			window.location = basePath+'home.do';
	});
}
/**
 * @param url
 */
function pageView(url){
	ajaxHtml(url, {}, setContent, function(){});
}
function setContent(html){
	$('#pageContent').html(html);
	page(1);
	bootstrapInit();
}
/************Parameter DATA**************/
function getParams(formId){
	var params={};
	if(formId){
		$(formId+' input[name],'+formId+' textarea[name],'+formId+' select[name]').each(function(){
			var name =$(this).attr('name');
			var value =$(this).val();
			if(name!='undefined'&&value!='undefined'){
				if($(this).attr('type')!='checkbox'){
					params[name]=value;
				}
				
			}
		});
		$(formId+' input[type="checkbox"]:checked,'+formId+' input[type="radio"]:checked').each(function(){
			params[$(this).attr('name')]=$(this).val();
		});
	}else{
		$('input[name],textarea[name],select[name]').each(function(){
			var name =$(this).attr('name');
			var value =$(this).val();
			if(name!='undefined'&&value!='undefined'){
				params[name]=value;
			}
		});
		$('input[type="checkbox"]:checked,input[type="radio"]:checked').each(function(){
			params[$(this).attr('name')]=$(this).val();
		});
	}
	return params;
}
/************Save DATA**************/
var saveInfo=function(formId,saveFun){
	valid(formId, saveFun);
};
var doSaveInfo =function(){
	var params = getParams();
	ajaxLoad(getUrl(), params);
};

var save=function(formId){
	var params = getParams(formId);
	ajaxData(getUrl(formId),params,function(){
		closeMsg();
		cfm('保存修改成功！是否关闭对话框？',function(){
			closeInfo();
			page(1);
		});
	});
};
/************DELETE DATA**************/
var deleteUrl;
var deleteItem=function(url){
	deleteUrl=url;
	cfm("确认删除此条信息？",doDelete);
};
var doDelete=function(){
	ajaxData(deleteUrl, {},
	function(){
		closeInfo();
		page(1);
	});
};

/**
 * import
 */
var $import=function(file){ 
	if ( file.match(/.js$/)){  
	    document.write('<script type="text/javascript" src="' + file + '"></script>'); 
	}else{ 
	    document.write('<style type="text/css">@import "' + file + '" ;</style>'); 
	}
};
var utilPath = basePath+'static/js/util/';
/**Ajax 封装*/
$import(utilPath+'ajax.js');
/**自定义验证 封装*/
$import(utilPath+'customValid.js');
/**对话框、提示框 封装*/
$import(utilPath+'dialogHintWin.js');
/**页面初始化 封装*/
$import(utilPath+'loadPage.js');
/**
 * 禁用按钮
 */
var $btnDisable=function(selector){
	if(!selector){
		selector='.submit';
	}
	$(selector).addClass('disabled');
	$(selector).attr('disabled','disabled');
	
};
var $btnEnable=function(selector){
	if(!selector){
		selector='.submit';
	}
	$(selector).removeClass('disabled');
	$(selector).removeAttr('disabled');
};
function cleanFormById(formId){
	if(formId){
		var selector=formId+' input[name],'+
		formId+' textare[name],'+
		formId+' select[name]';
		$(selector).each(function(){$(this).val('');});
	}
}
/**
 * jsonObjToMap
 */
var $jsonObjToMap=function(jsonObj){
	var map = {};
	for(var item in jsonObj){
		map[item]=jsonObj[item];
	}
	return map;
};
/**
 * isMapNull
 */
var $isMapNull = function(dataMap,filed){
	return (dataMap[filed]==null||dataMap[filed]==''||dataMap[filed]=='null')&&dataMap[filed]!=0;
};
/**
 * 通过处理item属性值，返回映射数据(包含情况xx;xx.xx;xx,xx.xx)
 */
var $getDataFiled=function(dataMap,filed){
	var fileds = filed.split(FILEDS_SPLIT);
	if(fileds.length<=1){
		return $singledData(dataMap,fileds[0]);
	}else{
		return $multipleData(dataMap,fileds);
	}
};
/**
 * 单个属性，返回数据(xx;xx.xx)
 */
var $singledData= function(dataMap,filed){
	//single filed
	
	//xx.xx
	if(filed!=null&&filed.indexOf(FILED_SPLIT)>0){
		var fileds = filed.split(FILED_SPLIT);
		var tempMap = dataMap;
		for(var i =0;i<fileds.length;i++){
			var tempfiled = fileds[i];
			if($isMapNull(tempMap,tempfiled)){
				return NONE_VAL_FLAG;
			}
			tempMap = $getDataVal(tempMap, tempfiled);
		}
		return tempMap;
	}
	//xx
	else{
		return $isMapNull(dataMap,filed)?NONE_VAL_FLAG:$getDataVal(dataMap,filed);
	}
	
}; 
/**
 * 获得属性值
 */
var $getDataVal=function(dataMap,filed){
	//特殊处理会员卡类型和赠送会籍单位
	if(filed!=null&&filed!=""){
		if('dep'==filed){
			var type = dataMap[filed];
			return depName[type];
		}
	}
	return dataMap[filed];
};
/**
 * 多个属性，返回组合数据(xx+xx;xx.xx+xx.xx)
 */
var $multipleData=function(dataMap,fileds){
	var infoArray = [];
	for(var index=0;index<fileds.length;index++){
		infoArray[index]=$singledData(dataMap, fileds[index]);
	}
	var groupInfo = "";
	for(var index=0;index< infoArray.length;index++){
		groupInfo = groupInfo +infoArray[index];
	}
	return groupInfo;
};
/**
 * 根据信息类型,回填信息数据
 */
var commFillInfo = function(dataMap,selector,type){
	$(selector).each(function(){
		var showFiled = $(this);
		var itemFileds = showFiled.attr('item');
		if(type==infoType.val){
			showFiled.val($getDataFiled(dataMap,itemFileds));
		}else if(type==infoType.text){
			showFiled.text($getDataFiled(dataMap,itemFileds));
		}else if(type==infoType.src){
			var orginalSrc=showFiled.attr('src');
			orginalSrc = orginalSrc.replace('#src',$singledData(dataMap,itemFileds));
			showFiled.attr('src',orginalSrc);
		}
	});
};