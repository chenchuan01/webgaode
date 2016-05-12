/**dialogHintWin*/
/*************窗口封装***************/


var smallSize=['300px','200px'];
var smallLangSize=['300px','500px'];
var middleSize=['600px','520px'];
var midlangSize=['700px','710px'];
var largeSize=['1200px','650px'];
var autoWin = function(title,url,w,h){
	if(w){
		win(title, url, w+'px');
	}else if(h){
		win(title, url, h+'px');
	}else if (w&&h){
		win(title, url, w+'px', h+'px');
	}else{
		win(title, url);
	}
	
};
var smallWin=function(title,url){
	win(title, url, smallSize[0], smallSize[1]);
};
var smallLangWin=function(title,url){
	win(title, url, smallLangSize[0], smallLangSize[1]);
};

var middleWin=function(title,url){
	win(title, url, middleSize[0], middleSize[1]);
};
var midlangWin=function(title,url){
	win(title, url, midlangSize[0], midlangSize[1]);
};
var largeWin=function(title,url){
	win(title, url, largeSize[0], largeSize[1]);
};
var winDialog=function(title,url,width,height){
	if(!title){
		title = "";
	}
	$.dialog({
        title: title,
        content: 'url:'+basePath+url,
        lock: true,
		width:width,
		height:height
	});
};
var win = function(title,url,width,height){
	if(!title){
		title = "";
	}
	var modalId = "#formModal";
	//title
	$(modalId+' .modal-title').text(title);
	//size
	if(width){
		$(modalId+' .modal-dialog').css('width',width);
	}
	if(height){
		$(modalId+' .modal-dialog').css('min-height',height);
	}
	
	//content
	ajaxHtml(url, {}, function(html){
		$(modalId+' .modal-body').html(html);
		$(modalId).modal('toggle');
	});
};
var info = function(msgStr){
	msg('提示信息',msgStr);
};
var error = function(errMsg){
	msg('系统错误',errMsg);
};
var cfm = function(msgStr,suc,err){
	msg('确认信息',msgStr,suc,err);
};
var msg= function(title,msgStr,success,cancle){
	if(!title){
		title = "";
	}
	var modalId = "#msgModal";
	//title
	$(modalId+' .modal-title').text(title);
	$(modalId+' .modal-body').html('');
	$(modalId+' .modal-body').text(msgStr);
	$(modalId+' .modal-footer').html('');
	if(typeof(success)=='function'){
		$(modalId+' .modal-footer').append('<a id="sucBtn" class="btn btn-primary">确定</a>');
	}
	if(typeof(cancle)=='function'){
		$(modalId+' .modal-footer').append('<a id="errBtn" class="btn">取消</a>');
	}
	if(typeof(success)!='function'&&typeof(cancle)=='function'){
		$(modalId+' .modal-footer').append('<a id="closeBtn" class="btn">关闭</a>');
	}
	
	$(modalId).modal('toggle');
	
	if(typeof(success)=='function'){
		$('#sucBtn').click(function(){success();});
	}
	if(typeof(cancle)=='function'){
		$('#errBtn').click(function(){cancle();});
	}
	if(typeof(success)!='function'&&typeof(cancle)=='function'){
		$('#closeBtn').click(closeInfo);
	}
};
var closeMsg=function(){
	var modalId = "#formModal";
	$(modalId).modal('hide');
};
var closeInfo=function(){
	$('#msgModal').modal('hide');
};