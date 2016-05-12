/**loadPage*/
$(function(){
	loadPage();
});
var loadPage=function(){
	initOther();
};
var initOther=function(){
	/**清空表单*/
	cleanForm();
    /**bootstrap mual init*/
   bootstrapInit();
   $(".datepickerInput").datepicker(
			{ 
			language: 'zh-CN',
	        autoclose: true,
	        todayHighlight: true,
	        format: 'yyyy-mm-dd'
	       });
};
var cleanForm=function(){
	$('.cancel').click(function(){
		$("textarea,select").each(
			function(){$(this).val('');});
		$("input[name]").each(function(){
			if($(this).attr('type')=='text'||
					$(this).attr('type')=='hidden'){
				$(this).val('');
			}
			
		});
		refresh();
	});
};
var bootstrapInit=function(){
	 /*$('[data-toggle="popover"]').popover();
	 $('[data-toggle="tooltip"]').tooltip();*/
	 $('#formModal').on('hidden.bs.modal', function (e) {
		 $('#formModal'+' .modal-title').text("");
		 $('#formModal'+' .modal-body').html("");
	 });
};

/*************分页封装***************/
var pageHtml='<li  id="page_%page%" class="pages"><a href="javascript:;" onclick="page(%page%)">%page%</a></li>';
function getPageUrl(){
	return $('#search').attr('action');
}
function getTotal(){
	return parseInt($('#totalPages').text());
};
function setTotal(pages){
	$('#totalPages').text("");
	$('#totalPages').text(pages);
};
function getPage(){
	return parseInt($('#page').text());
};
function setPage(page){
	$('.pages').removeClass('active');
	$('#page').text(page);
	var pageId="#page_"+page;
	$(pageId).addClass('active');
};
var setPageItems=function(itemHtml){
	$('#pagesItems').html(itemHtml);
};
var setTableData=function(dataHtml){
	$('#tableData').html(dataHtml);
	
};
var page=function(num){
	if(getPageUrl()=='undefined'||getParams()=='undefined'){
		return;
	}
	var params = getParams('#search');
	params['page']=num;
	var url = getPageUrl();
	ajaxData(url,params,pageFun);
};
var pageFun=function(data){
	if(data){
		jspLoadPageDatas(data.content);
		loadPagesItem(data.totalPages);
		setTotal(data.totalPages);
		setPage(data.page);
	}else{
		loadPagesItem(0);
		setTotal(0);
		setPage(0);
	}
};
var loadPagesItem=function(total){
	total = total=='undefined'?0:total;
	if(total>0){
		var itemHtml='<li><a href="javascript:;" onclick="prePage()">Prev</a></li>';
		for(var i =1;i<=total;i++){
			itemHtml = itemHtml+pageHtml.replace('%page%', i).replace('%page%', i).replace('%page%', i);
		}
		itemHtml = itemHtml+'<li><a href="javascript:;" onclick="nextPage()">Next</a></li>';
		setPageItems(itemHtml);
	};
};
var jspLoadPageDatas=function(data){
	var trStr='<tr class="">%content%</tr>';
	var tdStr='<td item="%item%">%content%</td>';
	var trObj=$('#template');
	var tdObjs=trObj.children('td');
	var trStrs = "";
	//行列遍历生成html字符串
	for(var row=0;row<data.length;row++){
		var jsonData = data[row];
		var tempTr=trStr;
		var tdStrs="";
		for(var col=0;col<tdObjs.length;col++){
			var dataMap = $jsonObjToMap(jsonData);
			var tempTd = tdStr;
			var tdObj = $(tdObjs[col]);
			var item = tdObj.attr('item');
			var content = "";
			if('index'==item){
				content = ''+(row+1);
			}else if('oprea'==item){
				var itemFiled = tdObj.attr('itemFiled');
				var itemId = $getDataFiled(dataMap, itemFiled);
				content = genOpreaHtml($(tdObjs[col]).html(),itemId);
			}else if('checkBox'==item){
				var itemFiled = tdObj.attr('itemFiled');
				var itemId = $getDataFiled(dataMap, itemFiled);
				content = genCheckHtml($(tdObjs[col]).html(),itemId);
			}else{
				content = $getDataFiled(dataMap,item);
			}
			tempTd = tempTd.replace("%item%", item).replace("%content%", content);
			tdStrs = tdStrs+tempTd;
		}
		tempTr = tempTr.replace('%content%',tdStrs);
		trStrs=trStrs+tempTr;
	}
		
	if(trStrs.indexOf('%content%')<=-1){
		clearTable();
		setTable(trStrs);
	}
	
};

var genOpreaHtml = function(aStrs,itemId){
	var opStrs = aStrs.split('id=');
	var newOpStrs = opStrs[0];
	for(var i=1;i<opStrs.length;i++){
		newOpStrs=newOpStrs+"id="+itemId+opStrs[i].substring(opStrs[i].indexOf('\''),opStrs[i].length);
	}
	return newOpStrs;
};
var genCheckHtml = function(checkStrs,itemId){
	return checkStrs.replace('#itemId',itemId);
};

var clearTable=function(){
	$("#tableData").html('');
};
var setTable=function(trStrs){
	$("#tableData").html(trStrs);
};
var nextPage=function(){
	var total = getTotal();
	var pageNum = getPage()+1;
	if(pageNum>total){
		return;
	}
	page(pageNum);
};

var prePage=function(){
	var pageNum = getPage()-1;
	if(pageNum<1){
		return;
	}
	page(pageNum);
};
var fristPage=function(){
	page(1);
};
var lastPage=function(){
	page(getTotal());
};
var refresh=function(){
	var formFileds = $('#search input[name],#search select[name]');
	if(formFileds){
		formFileds.each(function(){$(this).val('');});
		page(1);
	}
};