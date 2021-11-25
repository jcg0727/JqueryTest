/**
 * 공통 기능 모음 - 많이 쓰는 함수들
 */

function getParamByLocation(p){
	var lo = decodeURI(window.location.href);
//	var lo = location.search.substr(location.search);
//	lo = decodeURI(lo);
	
	var param = lo.split("?")[1];
	var getEach = param.split("&");
	var valName = "";
	for(var i=0;i<getEach.length;i++){
		var TagName = getEach[i].split("=");
		if(p==TagName[0]){
			if(i>=3){
				valName+=TagName[1]+", ";
			}else{
				return TagName[1];
			}
		}
	}
	valName=valName.substr(0,valName.length-2);
	return valName;
}

function isEmpty(p){
	var chk = p.trim();
	if(chk==null || chk=="" || chk==undefined){
		return true;
	}
	
	//교수님
//	if(p == undefined) return true;
//	
//	if(val.trim().length == 0 || val == null)
//		return true;
}

function formatHpno(val){
	if(val == null || val == undefined){
		return val;
	}
	val=val.replaceAll("-", "");  //이미 포맷된 상태로 들어온경우 -없애기위해
	
	// 정규식
	//1)숫자로만 10자리, 11자리가 입력된게 맞는지 확인
	//2)맞다면 ***-****-**** 또는 ***-***-****로 변경
//	var regExp = /^(\d{3})(\d{3,4})(\d{4})$/;
	var regExp = /^(\d{10,11})$/;
	if(regExp.test(val)){
		return val.replace(/(\d{3})(\d{3,4})(\d{4})/, "$1-$2-$3");  //앞에것을 뒤에 $자리에 넣어줌...
	}else{
		return val;
	}
}

function formatDate(val){
	tmp = val.replaceAll("-", "");
	regExp = /^\d{8}$/;
	
	if(regExp.test(tmp)){
		tmp = tmp.replace(/(\d{4})(\d{2})(\d{2})/, "$1-$2-$3");
		return tmp;
	}else{
		return val;
	}
}