/**
 * 회원가입 정규식
 */

function chkIdLower(memId){
	var regExp = /^[a-z0-9]{5,15}$/;
	if(regExp.test(memId)){
		return true;
	}else{
		return false;
	}
}

function chkPassR(memPass){
	var regExp = /^[a-z0-9]{8,13}$/;
	if(regExp.test(memPass)){
		return true;
	}else{
		return false;
	}
}

function chkEmailR(email){
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if(regExp.test(email)){
		return true;
	}else{
		return false;
	}
}