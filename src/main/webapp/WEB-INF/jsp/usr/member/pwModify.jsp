<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
  	
  	<script>
   		function pwModifySubmit(e) {
   			e.password.value = e.password.value.trim();
   			e.pwChk.value = e.pwChk.value.trim();
   			let chkMsg = $('#chkMsg');

   			if(e.password.value.length == 0){
   				alert('비밀번호를 입력해주세요');
   				e.password.focus();
   				return;
   			}
   			
   			if(e.pwChk.value.length == 0){
   				alert('비밀번호 확인을 입력해주세요');
   				e.pwChk.focus();
   				return;
   			}
   			
   			if(e.pwChk.value != e.password.value){
   				alert('비밀번호와 비밀번호 확인이 일치하지 않습니다');
   				e.pwChk.focus();
   				return;
   			}
   			
   			e.submit();
		}
   	</script>
   	
	<section class="flex flex-col justify-center items-center mt-[200px]">
		<form action="doPwModify" onsubmit="pwModifySubmit(this); return false;" method="post">
			<table class="table text-center bg-gray-200 overflow-hidden" >
				<tr>
					<th colspan="2">비밀번호 수정</th>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input class="input input-bordered w-full max-w-xs" type="password" name="password" />
						<div id="chkMsg" class="h-2 w-[265px]"></div>						
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input class="input input-bordered w-full max-w-xs" type="password" name="pwChk"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" class="p-0 hover:bg-base-200">
						<button class="w-full text-center p-4">수정</button>
					</th>
				</tr>
			</table>
		</form>
	</section>

<%@ include file="../common/foot.jsp" %>