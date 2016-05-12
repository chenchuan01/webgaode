/**customValid*/
/*************验证封装***************/
var regs = {
	email:/^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
	mobile:/^((\+?86)|(\(\+86\)))?(1[3,5,7,8]{1}[0-9]{9}|147[0-9]{8}|1349[0-9]{7})$/,
	phone: /^([0-9]{3,4}-)?[0-9]{7,8}$/,
	digits:/^\d+$/,
	day:/^\d{4}-\d{2}-\d{2}$/,
	dayTime:/^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/,
};
var messages={
		required:"请填写信息",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		day: "请输入合法的日期(eg:2016-01-01)",
		dayTime: "请输入合法的时间(eg:2016-01-01 09:00)",
		digits: "只能输入整数",
		equalTo: "请再次输入相同的值",
		length:"需输入{0}位字符",
		tel:"请输入正确格式的座机或手机号"
	};
var valid=function(formObjId,subFunc){
	var formObj = $(formObjId);
	if(formObj){
		clearValid(formObjId);
		var formFiledSelector = formObjId+' input[valid],'+
								formObjId+' select[valid],'+
								formObjId+' textarea[valid]';
		
		var textInputs =$(formFiledSelector);
		var validRslt=new Array();
		var textValid = true;
		if(textInputs&&textInputs.length>0){
			var index = 0;
			textInputs.each(function(){
				$(this).css('display','inline');
				textValid = matchText($(this));
				validRslt[index]=textValid;
				index++;
			});
		}
		var rslt = true;
		for(var i=0;i<validRslt.length;i++){
			if(!validRslt[i]){
				rslt =  false;
			}
		}
		if(!rslt){
			$('.validNoBorder:first').focus();
		}
		if(rslt&&typeof(subFunc)=='function'){
			subFunc(formObjId);
		}
		return rslt;
	}
};

var matchText = function(input){
	var value=input.val()?input.val().trim():"";
	var valid = input.attr('valid');
	if(valid){
		var valid_reg = input.attr('valid-reg');
		var valid_msg = input.attr('valid-msg');
		var msg = valid_msg?valid_msg:messages[valid]; 
		msg = msg?msg:"";
		if('required'==valid){
			if(!value||""==value){
				setValidClass(input,msg,false);
				return false;
			}
		}
		if('email'==valid){
			var rslt = regs['email'].test(value);
			if(!rslt){
				setValidClass(input,msg,false);
				return false;
			}
		}
		if('tel'==valid){
			var rslt = regs['mobile'].test(value)||regs['phone'].test(value);
			if(!rslt){
				setValidClass(input,msg,false);
				return false;
			}
		}
		if('digits'==valid){
			var rslt = regs['digits'].test(value);
			if(!rslt){
				setValidClass(input,msg,false);
				return false;
			}
		}
		if('day'==valid){
			var rslt = regs['day'].test(value);
			if(!rslt){
				setValidClass(input,msg,false);
				return false;
			}
		}
		if('dayTime'==valid){
			var rslt = regs['dayTime'].test(value);
			if(!rslt){
				setValidClass(input,msg,false);
				return false;
			}
		}
		if('equalTo'==valid){
			var rslt = value!=""&&value==$(valid_reg).val();
			if(!rslt){
				setValidClass(input,msg,false);
				return false;
			}
		}
		if('length'==valid){
			var length = parseInt(valid_reg);
			var rslt = value.length==length;
			if(!rslt){
				msg = msg.replace('{0}',length);
				setValidClass(input,msg,false);
				return false;
			}
		}else if(''==valid&&valid_reg){
			valid_reg = eval(valid_reg);
			var rslt =valid_reg.test(value);
			if(!rslt){
				setValidClass(input,msg,false);
				return false;
			}
		}else if('empty-or-reg'==valid){
			valid_reg = eval(valid_reg);
			var rslt =valid_reg.test(value);
			if(''!=value&&!rslt){
				return false;
			}
		}
	}
	setValidClass(input,"",true);
	return true;
};
var tips_msg='<span class="validMsg">{0}</span>';
var setValidClass=function(obj,msg,flag){
	var objId = obj.attr('id');
	objId = '#'+objId;
	if(flag){
		obj.addClass('validYesBorder');
	}else{
		obj.after(tips_msg.replace('{0}', msg));
		obj.addClass('validNoBorder');
	};
};
var clearValid=function(objId){
	$('.validYesBorder').each(function(){$(this).removeClass('validYesBorder');});
	$('.validNoBorder').each(function(){$(this).removeClass('validNoBorder');});
	$('.validMsg').each(function(){$(this).remove();});
};
