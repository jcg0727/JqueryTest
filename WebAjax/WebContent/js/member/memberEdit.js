/**
 * 회원정보 수정 화면 JS
 */
function updateMem(){
//	if(!conform('저장하시겠습니까?')){
//		
//	}
	
	$.ajax({
		url : "/MemberServlet"
		,type : "post"
		,dataType : "json"
		,data : $("#mf").serialize()
		,success : function(data){
			console.log(data);
			alert(data.resultMsg);
			goMemberView();
		}	
		,error : function(xhr){
			console.log(xhr);
			alert('실패');
		}
	});
}

function deleteMem(){
	$.ajax({
		url : "/MemberServlet"
		,type : "post"
		,dataType : "json"
		,data : $("#mf").serialize()
		,success : function(data){
			console.log(data.msg);
			alert(data.msg);
		}
		,error : function(xhr){
			alert(xhr.status);
		}
	});
}
function goList(){
	window.location.href="/html/member/member_list.html";
}
function goBack(){
	window.history.back();
}

function goMemberView(){
	var fm = document.getElementById("tmpFm");
	fm.method = "post";
	fm.action = "/MemberServlet";
	fm.submit();
}