/**ajax*/
/*************Ajax封装***************/
var ajaxData = function(url,dataArry,sucfun,errFun){
	$.ajax({
		type:"POST",
		url:url,
		data:dataArry,
		dataType:"json",
		success:function(data){
			commonSuc(data,sucfun);
		},
		error:function(data){
			commonErr(data, errFun);
		}
	});
};
var ajaxLoad= function(url,dataArry,sucfun,errFun){
	loadToggle();
	$.ajax({
		type:"POST",
		url:url,
		data:dataArry,
		dataType:"json",
		success:function(data){
			loadToggle();
			commonSuc(data,sucfun);
		},
		error:function(data){
			loadToggle();
			commonErr(data, errFun);
		}
	});
};
var ajaxHtml= function(url,dataArry,sucfun,errFun){
	loadToggle();
	$.ajax({
		type:"POST",
		url:url,
		data:dataArry,
		dataType:"html",
		success:function(data){
			loadToggle();
			commonSuc(data,sucfun);
		},
		error:function(data){
			loadToggle();
			commonErr(data, errFun);
		}
	});
};
var commonSuc=function(data,sucfun){
	if(data.msg){
		error(data.msg);
		return;
	}
	if(typeof(sucfun)=="function"){
		sucfun(data);
	}
};
var commonErr=function(data,errFun){
	if(data.msg){
		error(data.msg);
		return;
	}
	if(typeof(errFun)=="function"){
		errFun(data);
		return;
	}
};
function getUrl(formId){
	if(formId){
		return $(formId).attr('action');
	}
	return $('form[action]').first().attr('action');
}
var loadToggle=function(){
	$('#load').toggle(50);
};