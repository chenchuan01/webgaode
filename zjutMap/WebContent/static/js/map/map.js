var MapApp = function(contentId,panelId,initZoom){
	var map;
	var init = function(){
		var mapObj =new AMap.Map(contentId, {
	        resizeEnable: true,
	        center: [120.038201,30.226134],//鍦板浘涓績鐐�
	        zoom: initZoom //鍦板浘鏄剧ず鐨勭缉鏀剧骇鍒�
	    });
		this.map = mapObj;
	};
	//姝ヨ瀵艰埅
	function walking(from,to) {
		loadToggle();
		clearMap();
	    //姝ヨ瀵艰埅
	    AMap.service(["AMap.Walking"], function() {
	        var MWalk = new AMap.Walking({
	            map: this.map,
	            panel: panelId
	        }); //鏋勯�璺嚎瀵艰埅绫�
	        //鏍规嵁璧风粓鐐瑰潗鏍囪鍒掓琛岃矾绾�
	        MWalk.search([
	            {keyword: from,city:MapUtil.getCity()},
	            {keyword: to,city:MapUtil.getCity()}
	        ], function(status, result) {
	        	loadToggle();
	        	commonError(status);
	        });
	    });
	    
	}
	//鍏氦瀵艰埅
	function bus(from,to) {
		loadToggle();
		clearMap();
		 AMap.service(["AMap.Transfer"], function() {
		        var transOptions = {
		            map: this.map,
		            city:MapUtil.getCity(),
		            panel:panelId,                            //鍏氦鍩庡競
		            policy: AMap.TransferPolicy.LEAST_TIME //涔樿溅绛栫暐
		        };
		        //鏋勯�鍏氦鎹箻绫�
		        var trans = new AMap.Transfer(transOptions);
		        //鏍规嵁璧枫�缁堢偣鍧愭爣鏌ヨ鍏氦鎹箻璺嚎
		        trans.search([
				              {keyword: from},
				              {keyword: to}
				              ], function(status, result) {
					loadToggle();
					commonError(status);
				});
		    });
		
	}
	//椹捐溅瀵艰埅
	function drive(from,to) {
		loadToggle();
		clearMap();
		AMap.service(["AMap.Driving"], function() {
	        var driving = new AMap.Driving({
	        	map: this.map,
				panel: panelId
	        }); //鏋勯�璺嚎瀵艰埅绫�
	        // 鏍规嵁璧风粓鐐瑰潗鏍囪鍒掓琛岃矾绾�
	        driving.search([
				              {keyword: from,city:MapUtil.getCity()},
				              {keyword: to,city:MapUtil.getCity()}
				              ], function(status, result) {
					loadToggle();
					commonError(status);
				});
	    });
	}
	//娓呯┖鍦板浘
	function clearMap(){
	    this.map.clearMap();
	}
	function commonError(status){
    	if(status==0||status=='error'){
    		info("未找到关键字相关地点，请重新输入！");
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
