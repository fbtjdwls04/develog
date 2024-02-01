<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	const createBoardSubmit = function(e) {
		e.boardName.value = e.boardName.value.trim();
		
		if(e.boardName.value.length == 0){
			alert("게시판 이름을 입력해주세요")
			return;
		}
		
		e.submit();
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
					<form action="/usr/board/create" class="flex items-center" onsubmit="createBoardSubmit(this); return false;">
						<input name="boardName" type="text" class="input input-sm input-outlined input-bordered"/>
						<button class="btn btn-sm btn-outline">게시판 추가</button>
					</form>
				</c:if>
			</li>
			<c:forEach var="board" items="${boards }">
				<li class="hover:underline ${nowBoard.id == board.id ? 'font-bold' : '' }">
					<a href="list?memberId=${board.memberId }&boardId=${board.id } ">${board.name }</a>
				</li>
			</c:forEach>
		</ul>
	</nav>
</div>