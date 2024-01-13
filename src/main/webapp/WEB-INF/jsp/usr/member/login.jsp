<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
  	
  	<script>
		function loginSubmit(e) {
			
			e.loginId.value = e.loginId.value.trim();
			e.loginPw.value = e.loginPw.value.trim();
			
			if(e.loginId.value.length == 0){
				alert("아이디를 입력해주세요");
				return;
			}
			
			if(e.loginPw.value.length == 0){
				alert("비밀번호를 입력해주세요");
				return;
			}
			
			e.submit();
		}
  	</script>
  	
  	<!-- 배경화면  -->
   	<div class="fixed left-0 right-0 top-0">
   		<img src="https://t1.daumcdn.net/cfile/tistory/2250EA5054FF120605" 
   		alt=""
   		class="w-full opacity-80" />
   	</div>
   	
  	<section class="container mx-auto relative mt-[200px]">
  		<form action="doLogin" onsubmit="loginSubmit(this); return false;">
			<input type="text" name="loginId" class="input input-outline"/>  		
			<input type="text" name="loginPw" class="input input-outline"/>
			<button class="btn">로그인</button>  		
  		</form>
  	</section>

<%@ include file="../common/foot.jsp" %>