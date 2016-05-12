var MapApp = function(contentId,panelId,initZoom){
	var map;
	var init = function(){
		var mapObj =new AMap.Map(contentId, {
	        resizeEnable: true,
	        center: [120.165711, 30.293286],//地图中心点
	        zoom: initZoom //地图显示的缩放级别
	    });
		this.map = mapObj;
	};
	//步行导航
	function walking(from,to) {
		loadToggle();
		clearMap();
	    //步行导航
	    AMap.service(["AMap.Walking"], function() {
	        var MWalk = new AMap.Walking({
	            map: this.map,
	            panel: panelId
	        }); //构造路线导航类
	        //根据起终点坐标规划步行路线
	        MWalk.search([
	            {keyword: from,city:MapUtil.getCity()},
	            {keyword: to,city:MapUtil.getCity()}
	        ], function(status, result) {
	        	loadToggle();
	        	commonError(status);
	        });
	    });
	    
	}
	//公交导航
	function bus(from,to) {
		loadToggle();
		clearMap();
		 AMap.service(["AMap.Transfer"], function() {
		        var transOptions = {
		            map: this.map,
		            city:MapUtil.getCity(),
		            panel:panelId,                            //公交城市
		            policy: AMap.TransferPolicy.LEAST_TIME //乘车策略
		        };
		        //构造公交换乘类
		        var trans = new AMap.Transfer(transOptions);
		        //根据起、终点坐标查询公交换乘路线
		        trans.search([
				              {keyword: from},
				              {keyword: to}
				              ], function(status, result) {
					loadToggle();
					commonError(status);
				});
		    });
		
	}
	//驾车导航
	function drive(from,to) {
		loadToggle();
		clearMap();
		AMap.service(["AMap.Driving"], function() {
	        var driving = new AMap.Driving({
	        	map: this.map,
				panel: panelId
	        }); //构造路线导航类
	        // 根据起终点坐标规划步行路线
	        driving.search([
				              {keyword: from,city:MapUtil.getCity()},
				              {keyword: to,city:MapUtil.getCity()}
				              ], function(status, result) {
					loadToggle();
					commonError(status);
				});
	    });
	}
	//清空地图
	function clearMap(){
	    this.map.clearMap();
	}
	function commonError(status){
    	if(status==0||status=='error'){
    		info("未找到相关路线，请输入其他关键字！如：北京市地震局(公交站),亦庄文化园(地铁站)");
    	}
	}
	return {
		init:function(){
			init();
		},
		nav_walk:function(from,to){
			walking(from,to);
		},
		nav_bus:function(from,to){
			bus(from,to);
		},
		nav_drive:function(from,to){
			drive(from,to);
		}
	};
};
var MapUtil={
		switchOnNav:function(){
			$('#mapContent').removeClass('col-sm-12');
			$('#mapContent').addClass('col-sm-10');
			$('#navContent').show('slow');
			$('#navContent').attr('showFlag',1);
		},
		switchOffNav:function(){
			$('#navContent').hide('slow');
			$('#navContent').attr('showFlag',0);
			setTimeout(function(){
				$('#mapContent').removeClass('col-sm-10');
				$('#mapContent').addClass('col-sm-12');
			},1000);
			
		},
		getCity:function(){
			return $('#city').val();
		}
		
};
