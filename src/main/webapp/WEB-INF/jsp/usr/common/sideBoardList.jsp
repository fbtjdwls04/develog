<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	const createBoardSubmit = function(e) {
		e.boardName.value = e.boardName.value.trim();
		
		if(e.boardName.value.length == 0){
			alert("게시판 이름을 입력해주세요");
			return;
		}
		
		e.submit();
	}
	
	const boardModify_getForm = function(id) {
			/* 해당 댓글을 제외한 나머지는 댓글만 보이게 (수정 입력칸 여러개 안켜지게) */
			
		$('.board').css("display", "flex");
		$('.boardModifyForm').hide();
		/*  */
		
		$('#board' + id).hide();
		$('#boardModifyForm' + id).css("display", "flex");
		
		let textarea = document.getElementById('textarea' + id);
		resize(textarea);
	}
		
		/* 수정 입력칸 닫기 */
	const boardModifyForm_cancle = function(id) {
		$('#board' + id).css("display", "flex");
		$('#boardModifyForm' + id).hide();
	}
		
</script>

<div class="flex flex-col min-w-[300px] max-w-[300px]">
	<div class="border min-h-[400px] flex flex-col items-center relative">
		<!-- 프로필 수정 버튼 -->
		<c:if test="${member.id == rq.getLoginedMemberId() }">
			<button class="absolute right-[20px] top-[20px] hover:text-[gray]"><i class="fa-solid fa-pen"></i></button>
		</c:if>	
		<!-- 프로필 화면 -->
		<div class="avatar mt-20">
		  <div class="w-[100px] rounded-full">
		  	<c:if test="${member.profillImg == null }">
		    	<img src="https://mblogthumb-phinf.pstatic.net/MjAyMDExMDFfMTgy/MDAxNjA0MjI4ODc1NDMw.Ex906Mv9nnPEZGCh4SREknadZvzMO8LyDzGOHMKPdwAg.ZAmE6pU5lhEdeOUsPdxg8-gOuZrq_ipJ5VhqaViubI4g.JPEG.gambasg/%EC%9C%A0%ED%8A%9C%EB%B8%8C_%EA%B8%B0%EB%B3%B8%ED%94%84%EB%A1%9C%ED%95%84_%ED%95%98%EB%8A%98%EC%83%89.jpg?type=w800" alt="" />
		  	</c:if>
		  	<c:if test="${member.profillImg != null }">
				<img src="${member.profillImg }"/>		  	
		  	</c:if>
		  </div>
		</div>
		<p class="m-4 text-2xl">${member.nickname} 의 블로그</p>
		<p class="p-4">안녕하세요 개발자가 꿈입니다</p>
	</div>
	
	<!-- 게시판 리스트 -->
	<nav class="flex-grow p-4 border">
		<ul class="h-full p-2">
			<li class="mb-4 flex justify-between">
				<a href="list?memberId=${member.id}" class="hover:underline ${nowBoard == null ? 'font-bold' : '' }">전체 글 보기</a>
			</li>
			<li class="mb-4">
				<c:if test="${member.id == rq.getLoginedMemberId() }">
					<form action="/usr/board/create" class="flex items-center justify-center" onsubmit="createBoardSubmit(this); return false;">
						<input name="boardName" type="text" class="input input-sm input-outlined input-bordered"/>
						<button class="btn btn-sm btn-outline">게시판 추가</button>
					</form>
				</c:if>
			</li>
			<c:forEach var="board" items="${boards }">
				<li id="board${board.id }" class="hover:underline ${nowBoard.id == board.id ? 'font-bold' : '' } flex board">
					<a href="list?memberId=${board.memberId }&boardId=${board.id } ">${board.name }</a>
					
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
						<input type="text" name="boardName" value="${board.name }" class="input input-sm input-bordered"/>
						<button type="button" onclick="boardModifyForm_cancle(${board.id})" class="btn btn-sm btn-error">취소</button>
						<button class="btn-sm btn-accent btn">수정</button>
					</form>
				</li>
			</c:forEach>
		</ul>
	</nav>
</div>