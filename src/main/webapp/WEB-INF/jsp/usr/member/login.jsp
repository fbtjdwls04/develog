<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
  	
  	<script>
   		function loginSubmit(e) {
   			e.loginId.value = e.loginId.value.trim();
   			e.loginPw.value = e.loginPw.value.trim();
   			let chkMsg = $('#chkMsg');
   			
   			if(e.loginId.value.length == 0){
   				alert('아이디를 입력해주세요');
   				e.loginId.focus();
   				return;
   			}

   			if(e.loginPw.value.length == 0){
   				alert('비밀번호를 입력해주세요');
   				e.loginPw.focus();
   				return;
   			}
   			
   			$.ajax({
   				url: "loginInfoCheck",
				method: "get",
				data: {
						"loginId" : e.loginId.value,
						"loginPw" : e.loginPw.value
					},
				dataType: "json",
				success: function(data) {
					if(data.success){
			   			e.submit();
					}else{
						chkMsg.addClass('text-red-500');
						chkMsg.html(`<span>\${data.msg}</span>`);
						vaildLoginId = e.value;
						return;
					}
				},
				error: function(xhr, status, error) {
					console.error("ERROR : " + status + " - " + error);
				}
   			})
		}
   	</script>
   	
   	<!-- 배경화면  -->
   	<div class="fixed left-0 right-0 top-0">
   		<img src="https://t1.daumcdn.net/cfile/tistory/2250EA5054FF120605" 
   		alt=""
   		class="w-full opacity-80" />
   	</div>
   	
	<section class="flex flex-col justify-center items-center mt-[200px]">
		<form action="doLogin" onsubmit="loginSubmit(this); return false;" method="post">
			<table class="table text-center bg-gray-200 overflow-hidden" >
				<tr>
					<th>아이디</th>
					<td>
						<input class="input input-bordered w-full max-w-xs" type="text" name="loginId" autocomplete="off"/>
						<div id="chkMsg" class="h-2 w-[265px]"></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input class="input input-bordered w-full max-w-xs" type="password" name="loginPw" /></td>
				</tr>
				<tr>
					<th colspan="2" class="p-0 hover:bg-base-200">
						<button class="w-full text-center p-4">로그인</button>
					</th>
				</tr>
			</table>
		</form>
		<div class="mt-4 relative">
			<button class="btn btn-xs btn-outline mr-4">
				<a href="findLoginId">아이디 찾기</a>
			</button>
			<button class="btn btn-xs btn-outline">
				<a href="findLoginPw">비밀번호 찾기</a>
			</button>
		</div>
	</section>

<%@ include file="../common/foot.jsp" %>