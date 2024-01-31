<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<%@ include file="../common/head.jsp" %>
   	
   	<script>
   		function modifySubmit(e) {
   			e.loginPw.value = e.loginPw.value.trim();
   			e.name.value = e.name.value.trim();
   			e.nickname.value = e.nickname.value.trim();
   			e.cellphoneNum.value = e.cellphoneNum.value.trim();
   			e.email.value = e.email.value.trim();
   			
   			if(e.loginPw.value.length == 0){
   				alert('비밀번호를 입력해주세요');
   				e.loginPw.focus();
   				return;
   			}
   			if(e.name.value.length == 0){
   				alert('이름을 입력해주세요');
   				e.name.focus();
   				return;
   			}
   			if(e.nickname.value.length == 0){
   				alert('닉네임을 입력해주세요');
   				e.nickname.focus();
   				return;
   			}
   			//수정 필요
   			if(e.cellphoneNum.value.length == 0){
   				alert('전화번호를 입력해주세요');
   				e.cellphoneNum.focus();
   				return;
   			}
   			if(e.email.value.length == 0){
   				alert('이메일을 입력해주세요');
   				e.email.focus();
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
   	
	<section class="flex justify-center relative w-[400px] mx-auto mt-[200px]">
		<form action="doModify" onsubmit="modifySubmit(this); return false;" method="post">
			<table class="table text-center bg-gray-100 overflow-hidden">
				<tr>
					<th>아이디</th>
					<td>
						${member.loginId }
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input class="input input-bordered w-full" type="password" name="loginPw" />
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input class="input input-bordered w-full" type="text" name="name" autocomplete="off" value="${member.name }"/>
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>
						<input class="input input-bordered w-full" type="text" name="nickname" autocomplete="off" value="${member.nickname }"/>
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input class="input input-bordered w-full" type="tel" name="cellphoneNum" autocomplete="off" value="${member.cellphoneNum }"/>
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input class="input input-bordered w-full" type="email" name="email" autocomplete="off" value="${member.email }"/>
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="p-0 hover:bg-gray-200">
						<button class="w-full text-center p-4 font-bold">수정완료</button>
					</td>
				</tr>
			</table>
			
		</form>
	</section>
	
	<%@ include file="../common/foot.jsp" %>