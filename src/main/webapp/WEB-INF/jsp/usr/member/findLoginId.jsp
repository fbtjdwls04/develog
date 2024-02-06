<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
  	
  	<script>
   		function findLoginIdSubmit(e) {
   			e.name.value = e.loginId.value.trim();
   			e.email.value = e.loginPw.value.trim();
   			e.cellphoneNum.value = e.loginPw.value.trim();
   			
   			if(e.name.value.length == 0){
   				alert('이름을 입력해주세요');
   				e.name.focus();
   				return;
   			}

   			if(e.email.value.length == 0){
   				alert('이메일을 입력해주세요');
   				e.email.focus();
   				return;
   			}
   			
   			if(e.cellphoneNum.value.length == 0){
   				alert('전화번호를 입력해주세요');
   				e.cellphoneNum.focus();
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
   	
	<section class="flex flex-col justify-center items-center mt-[200px]">
		<form action="doFindLoginId" onsubmit="findLoginIdSubmit(this); return false;" method="post">
			<table class="table text-center bg-gray-200 overflow-hidden" >
				<tr>
					<th>이름</th>
					<td>
						<input class="input input-bordered w-full max-w-xs" type="text" name="name" autocomplete="off"/>
						<div id="chkMsg" class="h-2 w-[265px]"></div>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input class="input input-bordered w-full max-w-xs" type="email" name="email" /></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input class="input input-bordered w-full max-w-xs" type="text" name="cellphoneNum" /></td>
				</tr>
				<tr>
					<th colspan="2" class="p-0 hover:bg-base-200">
						<button class="w-full text-center p-4">찾기</button>
					</th>
				</tr>
			</table>
		</form>
	</section>

<%@ include file="../common/foot.jsp" %>