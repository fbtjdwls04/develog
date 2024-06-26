<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	const boardSubmit = function(e) {
		e.boardName.value = e.boardName.value.trim();
		
		if(e.boardName.value.length == 0){
			alert("게시판 이름을 입력해주세요");
			return;
		};
		
		if(e.boardName.value.length > 20){
			alert("게시판 이름은 최대 20글자까지 가능합니다");
			return;
		};
		
		e.submit();
	};
	
	const boardModify_getForm = function(id) {
			/* 해당 게시판을 제외한 나머지는 게시판은 보이게 (수정 입력칸 여러개 안켜지게) */
			
		$('.board').css("display", "flex");
		$('.boardModifyForm').hide();
		/*  */
		
		$('#board' + id).hide();
		$('#boardModifyForm' + id).css("display", "flex");
		
		let textarea = document.getElementById('textarea' + id);
		resizeItd(textarea);
	};
		
		/* 수정 입력칸 닫기 */
	const boardModifyForm_cancle = function(id) {
		$('#board' + id).css("display", "flex");
		$('#boardModifyForm' + id).hide();
	};
		
	$(document).ready(function() {
		$("#itdBtn").click(function() {
			$("#itdForm").show();
			$("#itdBtn").hide();
			$("#itd").hide();
			
			let itdTextarea = document.getElementById('itdTextarea');
			resizeItd(itdTextarea);
		});
		
		$("#itdFormCancleBtn").click(function() {
			$("#itdForm").hide();
			$("#itdBtn").show();
			$("#itd").show();
		});
	})
	;
	const resizeItd = function(e) {
   			e.style.height = 'auto';
   			e.style.height = (20 + e.scrollHeight) + 'px';
   		};
		
	const itdSubmit = function(e) {
		e.itd.value = e.itd.value.trim();
		
		if(e.itd.value.length == 0 ){
			alert("소개글을 입력해주세요");
			return;
		}
		
		if(e.itd.value.length > 100 ){
			alert("소개글은 100자 이하만 가능합니다")
			return;
		}
		
		e.submit();
	};
</script>

<div class="flex flex-col min-w-[300px] max-w-[300px]">
	<div class="border min-h-[400px] flex flex-col items-center relative">
		<!-- 프로필 화면 -->
		<div class="avatar mt-20">
		  <div class="w-[100px] rounded-full">
			  	<c:if test="${member.profillImg == null }">
				  	<a href="https://mblogthumb-phinf.pstatic.net/MjAyMDExMDFfMTgy/MDAxNjA0MjI4ODc1NDMw.Ex906Mv9nnPEZGCh4SREknadZvzMO8LyDzGOHMKPdwAg.ZAmE6pU5lhEdeOUsPdxg8-gOuZrq_ipJ5VhqaViubI4g.JPEG.gambasg/%EC%9C%A0%ED%8A%9C%EB%B8%8C_%EA%B8%B0%EB%B3%B8%ED%94%84%EB%A1%9C%ED%95%84_%ED%95%98%EB%8A%98%EC%83%89.jpg?type=w800"
				  	target="_blank">
				    	<img src="https://mblogthumb-phinf.pstatic.net/MjAyMDExMDFfMTgy/MDAxNjA0MjI4ODc1NDMw.Ex906Mv9nnPEZGCh4SREknadZvzMO8LyDzGOHMKPdwAg.ZAmE6pU5lhEdeOUsPdxg8-gOuZrq_ipJ5VhqaViubI4g.JPEG.gambasg/%EC%9C%A0%ED%8A%9C%EB%B8%8C_%EA%B8%B0%EB%B3%B8%ED%94%84%EB%A1%9C%ED%95%84_%ED%95%98%EB%8A%98%EC%83%89.jpg?type=w800" alt="" />
				  	</a>
			  	</c:if>
			  	<c:if test="${member.profillImg != null }">
			  		<a href="${member.profillImg }" target="_blank">
						<img src="${member.profillImg }"/>		  	
			  		</a>
			  	</c:if>
		  </div>
		</div>
		<p class="m-4 text-2xl">${member.nickname} 의 블로그</p>
		<p id="itd" class="p-4 whitespace-pre-wrap">${member.introduction }</p>
		
		<!-- 프로필 수정 버튼 -->
		<c:if test="${member.id == rq.getLoginedMemberId() }">
			<button id="itdBtn" class="absolute right-[20px] top-[20px] hover:text-[gray]"><i class="fa-solid fa-pen"></i></button>
			<form id="itdForm" action="/usr/member/doModifyIntroduct" class="hidden" onsubmit="itdSubmit(this); return false">
				<textarea id="itdTextarea" name="itd" cols="30" rows="2" onkeydown="resizeItd(this);">${member.introduction }</textarea>
				<div class="flex justify-end">
					<button class="btn btn-sm">수정</button>
					<button class="btn btn-sm" id="itdFormCancleBtn" type="button">취소</button>
				</div>
			</form>
		</c:if>	
	</div>
	
	<!-- 게시판 리스트 -->
	<nav class="flex-grow p-4 border">
		<ul class="h-full p-2">
			<li class="mb-4 flex justify-between">
				<a href="list?memberId=${member.id}" class="hover:underline ${nowBoard == null ? 'font-bold' : '' }">전체 글 보기</a>
			</li>
			<li class="mb-4">
				<c:if test="${member.id == rq.getLoginedMemberId() }">
					<form action="/usr/board/create" class="flex items-center justify-center" onsubmit="boardSubmit(this); return false;">
						<input name="boardName" type="text" class="input input-sm input-outlined input-bordered" placeholder="게시판 이름은 최대 20글자까지 가능합니다"/>
						<button class="btn btn-sm btn-outline">게시판 추가</button>
					</form>
				</c:if>
			</li>
			<c:forEach var="board" items="${boards }">
				<li id="board${board.id }" class="flex board items-center">
					<a href="list?memberId=${board.memberId }&boardId=${board.id }" class="hover:underline ${nowBoard.id == board.id ? 'font-bold' : '' }">${board.name }</a>
					<span class="text-[11px] ml-2">${board.articleCnt }</span>
					<!-- 게시판 수정, 삭제 버튼 -->
					<c:if test="${member.id == rq.getLoginedMemberId() }">
						<div class="dropdown dropdown-end ml-auto">
							<div tabindex="0" role="button">
								<i class="fa-solid fa-ellipsis-vertical p-2 text-[gray]"></i>
							</div>
							<ul tabindex="0"
								class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box text-black w-20">
								<li><button onclick="boardModify_getForm(${board.id})">수정</button></li>
								<li><a href="/usr/board/doDelete?id=${board.id }"
									onclick="if(confirm('해당 게시판과' 
									 + '\n 게시판에 존재하는 모든 게시물들이 삭제됩니다.'
									 + '\n 정말 [${board.name}] 게시판을 삭제하는게 맞습니까?') == false) return false;">삭제</a></li>
							</ul>
						</div>
					</c:if>
				</li>
				<!-- 보드 수정창 -->
				<li>
					<form id="boardModifyForm${board.id }" action="/usr/board/doModify"
						onsubmit="boardSubmit(this); return false;"
						class="hidden boardModifyForm flex justify-center items-center">
						<input type="hidden" name="id" value="${board.id}" />
						<input type="text" name="boardName" value="${board.name }" class="input input-sm input-bordered" placeholder="게시판 이름은 최대 20글자까지 가능합니다"/>
						<button type="button" onclick="boardModifyForm_cancle(${board.id})" class="btn btn-sm btn-error">취소</button>
						<button class="btn-sm btn-accent btn">수정</button>
					</form>
				</li>
			</c:forEach>
		</ul>
	</nav>
</div>