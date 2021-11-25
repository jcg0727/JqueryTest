/**
 * 회원 등록 화면
 */

$(document).ready(function(){
	initSelect();
	initCheckBox();
	initZipSido();
	
	//아이디 on클릭 이벤트 주기
	$("#chkId").click(function(){
		chkId();
	});
	
	$("#printZip tbody").on("dblclick", "tr", function(){
		appendAddr($(this).text());
	});
});

function makeBir(){
	var bir = $("#brithform").val();
	var birth = bir.replaceAll("-","");
	$("#birth").val(birth);
}

function makeTel(){
	var hp = $("#hp1").val() + $("#hp2").val() + $("#hp3").val();
	$("#hp").val(hp);
	console.log(hp);
}
function removeIdMsg(){
	$("#chkIdMsg").html("");
	$("#chkIdMsg").val("");
}

function chkEmail(){
 	var email = $("#email").val();
 	var msg = "";
 	if(chkEmailR(email)==false){
 		msg = '이메일 형식으로 입력하세요';
 	}
 	$("#emailMsg").html(msg);
 	$("#emailMsg").val(msg);
}

function changeBir(){
	var bir = $("#brith").val();
	bir = bir.replace("-","");
	$("#birth").val(bir);
}

function chkPassConfirm(){
	var memPass = $("#password").val();
	var memPassC = $("#passwordChk").val();
	var msg = "";
	if(memPass != memPassC){
		msg = '비밀번호가 다릅니다.';
	}
	$("#passConMsg").html(msg);
	$("#passConMsg").val(msg);
}

function chkPass(){
	var memPass = $("#password").val();
	$("#passwordChk").val('');
	var msg = "";
	if(chkPassR(memPass)==false){
		msg = '영문, 숫자 포함 8~12글자 입력하세요';
	}
	$("#passMsg").html(msg);
	$("#passMsg").val(msg);  //val()로 메세지 붙여야 입력있는지 체크할때 쓸 수 있음. 안그러면 값 안들어가있어서 못꺼냄
}

function chkName(){
	var memName = $("#name").val();
	var msg = "";
//	$("#nameMsg").remove();   remove쓰면 해당 태그자체가 사라짐. -> 자식 지우든지 append쓰지말고 html로 새로 붙이도록 해주기 
	if(memName.length<2){
		msg = '두글자 이상으로 입력하세요';
	}
	$("#nameMsg").html(msg);
	$("#nameMsg").val(msg);
}

function replaceZipcode(zipcode){
	var newZip = zipcode.replace("-","");
	console.log(newZip);
	return newZip;
}

function appendAddr(data){
	var zipcode = data.substr(0,7);
	zipcode = replaceZipcode(zipcode);
	var addr = data.substr(7);
	
	$("#zipcode").val(zipcode);
	$("#addr1").val(addr);
	$("#myModal").modal("hide");
	
}
function selectZip(){
	var sido = document.getElementById("zipSido").value;
	var gugun = document.getElementById("zipGugun").value;
	var dong = document.getElementById("zipDong").value;
	$.ajax({
		url : "/zipServlet"
		,type : "post"	
		,data : {
			"sido" : sido
			,"gugun" : gugun
			,"dong" : dong}	
		,dataType : "json"
		,success : function(data){
			makeZipCode(data);
		}	
		,error : function(xhr){
			console.log(xhr.status);
			alert("주소를 조회하는데 실패하였습니다. 관리자에게 문의 주세요.");
		}
	});
}


function chkId(){
	var memId = $("#id").val();
	if(memId.length < 5){
		alert("ID는 5~15글자 입니다.");
		$("#id").focus();
		return false;
	} else if(isEmpty(memId)){
		alert("ID를 입력하세요");
	}else if(!chkIdLower(memId)){
		alert("영어소문자, 숫자로 5~15글자 입력하세요.");
	}else{
		$.ajax({
			url : "/MemberCheckServlet"
			,type : "post"	
			,data : {
				"id" : memId}	
			,dataType : "json"
			,success : function(data){
				alert(data.msg);
				chkIdMsg(data);
			}	
			,error : function(xhr){
				console.log(xhr.status);
				alert("아이디 중복체크에 실패하였습니다. 관리자에게 문의 주세요.");
			}
		});
	}
}

function chkIdMsg(data){
	var html = "<span style='color:green;font-size:0.5em'>"+data.msg+"</span>";
	console.log(html);
	$("#chkIdMsg").html(html);
	$("#chkIdMsg").val(data.msg);
}

function makeZipCode(data){
	var html = "";
	for(var i=0;i<data.length;i++){
		html += '<tr><td>'+data[i].zipcode+'</td><td>'+data[i].sido +" "+ data[i].gugun +" "+ data[i].dong +" "+ checkBunji(data[i].bunji)+'</td></tr>'
	}
	$("#printZip tbody").html(html);
	
}

function checkBunji(data){
	var bunji = data;
	if(bunji == 'null'){
		bunji = "";
	}
	return bunji;
}

function setGugun(){
	$("#zipDong").removeAttr("disabled");
	var sido = document.getElementById("zipSido").value;
	var gugun = document.getElementById("zipGugun").value;
	
	$.ajax({
		url : "/zipServlet"
		,type : "post"	
		,data : {
			"flag" : "dong"
			,"sido" : sido
			,"gugun" : gugun}	
		,dataType : "json"
		,success : function(data){
			makeDong(data);
		}	
		,error : function(xhr){
			console.log(xhr.status);
			alert("주소를 조회하는데 실패하였습니다. 관리자에게 문의 주세요.");
		}
	});
}
function makeDong(data){
	var html = "<option>선택하세요</option>";
	for(var i=0;i<data.length;i++){
		html += '<option value="'+data[i].dong+'">'+data[i].dong+'</option>'
	}
	$("#zipDong").html(html);
}

function setSido(){
	var sido = document.getElementById("zipSido").value;
	$("#zipGugun").removeAttr("disabled");
	$.ajax({
		url : "/zipServlet"
		,type : "post"	
		,data : {
			"flag" : "gugun"
			,"sido" : sido}	
		,dataType : "json"
		,success : function(data){
			makeGugun(data);
		}	
		,error : function(xhr){
 			console.log(xhr.status);
 			alert("주소를 조회하는데 실패하였습니다. 관리자에게 문의 주세요.");
 		}
	});
}

function makeGugun(data){
	var html = "<option>선택하세요</option>";
	for(var i=0;i<data.length;i++){
		html += '<option value="'+data[i].gugun+'">'+data[i].gugun+'</option>'
	}
	$("#zipGugun").html(html);
}

function initZipSido(){
	$.ajax({
		url : "/zipServlet"
		,type : "post"	
		,data : {"flag" : "sido"}	
		,dataType : "json"
		,success : function(data){
			makeSido(data);
		}	
		,error : function(xhr){
 			console.log(xhr.status);
 			alert("주소를 조회하는데 실패하였습니다. 관리자에게 문의 주세요.");
 		}
	});
}

function makeSido(data){
	var html = "<option>선택하세요</option>";
	for(var i=0;i<data.length;i++){
		html += '<option value="'+data[i].sido+'">'+data[i].sido+'</option>'
	}
	$("#zipSido").html(html);
}

function initSelect(){
	$.ajax({
		url : "/CodeServlet"
		,type : "post"	
		,data : {
			"groupCode" : "A02"
			,"action" : "RC"
		}
		,dataType : "json"
		,success : function(data){
			//data 받아서 option에 넣어줄 것 테이블 만들었던 것처럼
			makeJobSelect(data);
		}	
		,error : function(xhr){
 			console.log(xhr);
 			alert("직업정보를 조회하는데 실패하였습니다. 관리자에게 문의 주세요.");
 		}
	});
} 

function makeJobSelect(data){
	//<select  class="form-control" id="memJob">
	//<option value="01">학생</option>
	var html = "";
	for(var i=0;i<data.length;i++){
		html += '<option value="'+data[i].id+'">'+data[i].name+'</option>'
	}
	$("#memJob").html(html);
}

function initCheckBox(){
	$.ajax({
		url : "/CodeServlet"
		,type : "post"	
		,data : {"groupCode" : "A01"}
		,dataType : "json"
		,success : function(data){
			//data 받아서 option에 넣어줄 것 테이블 만들었던 것처럼
			makeHobby(data);
		}	
		,error : function(xhr){
			console.log(xhr);
			alert("직업정보를 조회하는데 실패하였습니다. 관리자에게 문의 주세요.");
		}
	});
} 

function makeHobby(data){
	var html="";
	for(var i=0;i<data.length;i++){
		html += '<input type="checkbox" value='+data[i].id+'"> '+data[i].name+' ';
	}
	$("#hobby").html(html);
}

function chkCreate(){
	if(!validataData()){
		return;
	}
	var chk = confirm('가입하시겠습니까?');   //confirm?
	if(chk==true){
		createMem();
	}else{
		return;
	}
}

function validataData(){
	var memId = $("#id").val();
	if(isEmpty(memId)){
		alert('아이디는 필수 입력 입니다.');
		$("#id").focus();
		return false;
	}else if(memId.length < 5){
		alert("ID는 5글자 이상입니다.");
		$("#id").focus();
		return false;
	}
	var idchkMsg = $("#chkIdMsg").val();
	if(idchkMsg==""){
		alert('아이디 중복체크를 해주세요');
		return false;
	}
	var memPass = $("#password").val();
	if(isEmpty(memPass)){
		alert('비밀번호는 필수 입력 입니다.');
		$("#password").focus();
		return false;
	}else if($("#passMsg").val()!=""){
		alert('비밀번호를 확인해주세요');
		$("#password").focus();
		return false;
	}
	
	var passChk = $("#passwordChk").val();
	if(isEmpty(passChk)){
		alert('비밀번호 확인을 입력해주세요');
		$("#passwordChk").focus();
		return false;
	}
	var memPassC = $("#passConMsg").val();
	if(memPassC!=''){
		alert('비밀번호가 다릅니다.');
		$("#passwordChk").focus();
		return false;
	}
	
	var memName = $("#name").val();
	if(isEmpty(memName)){
		alert('이름은 필수 입력 입니다.');
		$("#name").focus();
		return false;
	}
	var nameMsg = $("#nameMsg").val();
	if(nameMsg!=""){
		alert('이름을 확인해주세요.');
		$("#name").focus();
		return false;
	}
	var emailMsg = $("#emailMsg").val();
	if(emailMsg!=""){
		alert('이메일 형식으로 입력하세요.');
		$("#email").focus();
		return false;
	}
	
	var memAddr1 = $("#addr1").val();
	if(isEmpty(memAddr1)){
		alert('주소는 필수 입력 입니다.');
		$("#addr1").focus();
		return false;
	}
	var memAddr2 = $("#addr2").val();
	if(isEmpty(memAddr2)){
		alert('상세주소를 입력해주세요.');
		$("#addr2").focus();
		return false;
	}
	return true;
}

function createMem(){
	$.ajax({
		url : "/MemberServlet"
		,type : "post"	
		,data : $("#mf").serialize()
		,dataType : "json"
		,success : function(data){
			//data 받아서 option에 넣어줄 것 테이블 만들었던 것처럼
			console.log(data);
			alert(data.resultMsg);
		}	
		,error : function(xhr){
			console.log(xhr);
			alert("회원등록에 실패하였습니다. 관리자에게 문의 주세요.");
		}
	});
}