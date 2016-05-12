<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div id="content" class="col-lg-12">
	<!-- PAGE HEADER-->
	<div class="row">
		<div class="col-sm-12">
			<div class="page-header">
				<form class="form-inline" role="form" style="padding-top: 1%;">
				  <div class="form-group">
					<label class="sr-only" for="city">城市</label>
					<input type="text" style="width:80px;" class="form-control" id="city" placeholder="查询城市" value="杭州市">
				  </div>
				  <div class="form-group">
					<label class="sr-only" for="from">出发地</label>
					<input type="text" class="form-control" id="from" placeholder="出发地">
				  </div>
				  <i class="fa fa-arrow-right"></i>
				  <div class="form-group">
					<label class="sr-only" for="to">目的地</label>
					<input type="text" class="form-control" id="to" placeholder="目的地">
				  </div>
				  <button type="button" class="btn btn-info" onclick="serachNav()"><i class="fa fa-search"></i> 导航搜索</button>
				  <br/>
				  <div class="form-group">
					 <label class="radio-inline"> <input type="radio" class="uniform" name="navType" value="walk" checked="checked">步行</label> 
					 <label class="radio-inline"> <input type="radio" class="uniform" name="navType" value="bus">公交</label>
					 <label class="radio-inline"> <input type="radio" class="uniform" name="navType" value="drive">驾车</label>
				  </div>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<div id="mapContent" class="col-sm-12 viewAre"></div>
		<div id="navContent" class="col-sm-2 viewAre" style="display: none;background: #fff;overflow: auto;" showFlag=0></div>
	</div>
</div>
<script>
	var mapApp = new MapApp("mapContent","navContent",17);
	jQuery(document).ready(function() {		
		App.setPage("forms");  //Set current page
		App.init(); //Initialise plugins and elements
		mapApp.init();
	});
	function serachNav(){
		var type = $('input[name="navType"]:checked').val();
		if('walk'==type){
			walkNav();
		}else if('bus'==type){
			busNav();
		}else if('drive'){
			driveNav();
		}
		MapUtil.switchOnNav();
	}
	function walkNav(){
		mapApp.nav_walk($('#from').val(),$('#to').val());
	}
	function busNav(){
		mapApp.nav_bus($('#from').val(),$('#to').val());
	}
	function driveNav(){
		mapApp.nav_drive($('#from').val(),$('#to').val());
	}
</script>
