<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   	
<%@ include file="../common/head.jsp" %>
   	<!-- 배경화면  -->
   	<div class="fixed left-0 right-0 top-0">
   		<img src="https://t1.daumcdn.net/cfile/tistory/2250EA5054FF120605" 
   		alt=""
   		class="w-full opacity-80" />
   	</div>
   	
   	<section class="flex container min-h-[1000px] mx-auto border relative mt-[200px] text-[white] bg-[rgb(60,64,67)]">
   	
   		<!-- 사이드 메뉴 -->
		<%@ include file="../common/sideBoardList.jsp" %>   		
		
   		<nav class="flex-grow p-10">
   			<div class="flex items-center mb-4 text-2xl">
	   			<c:if test="${board == null}">
		   			<h1 class="p-2">전체글 리스트</h1>
	   			</c:if>
	   			<c:if test="${board.id != 0 }">
		   			<h1 class="p-2">${board.name }</h1>
	   			</c:if>
	   			<c:if test="${rq.getLoginedMemberId() == memberId }">
		   			<a href="write" class="btn btn-sm btn-outline ml-auto mr-[20px] btn-accent">글쓰기</a>
	   			</c:if>
   			</div>
	   		<table class="table text-center">
		   		<c:forEach var="article" items="${articles }">
		   			<tr class="border-b m-2 hover:bg-gray-500">
		   				<td>
		   					[${article.boardName }]
		   				</td>
		   				<td>
				   			<a href="detail?id=${article.id }" class="flex p-2">
				   				<span class="ml-[20px]">
				   					${article.title }
				   				</span>
				   			</a>
		   				</td>
		   				<td>
		   					${article.regDate }
		   				</td>
		   				<td>
		   					추천수 : ${article.hitCount }
		   				</td>
		   			</tr>
		   		</c:forEach>
	  	 	</table>
	  	 	
	  	 	<ul class="flex mt-[20px] justify-center items-center">
	  	 		<!-- 페이지 처음 화살표 -->
 				<c:if test="${beginPage > pageSize}">
					<a class="text-[20px] mx-6"
						href="list?memberId=${memberId }&boardId=${board.id}"> 
						<i class="fa-solid fa-backward flex items-center"></i>
					</a>
				</c:if>
	  	 	
	  	 		<!-- 이전 화살표 -->
	  	 		<c:if test="${beginPage > 1}">
					<a class="flex justify-center items-center"
						href="list?memberId=${memberId }&boardId=${board.id}&boardPage=${beginPage-pageSize}">
						<i class="fa-solid fa-caret-left text-2xl"></i> <span>이전 | </span>
					</a>
				</c:if>
				
				<!-- 페이지 번호 -->
	  	 		<c:forEach begin="${beginPage }" end="${endPage }" step="1" var="i">
	  	 			<c:if test="${i <= totalPage }">
		  	 			<li class="mx-2 ${i == boardPage ? 'text-2xl' : ''}">
		  	 				<a href="list?memberId=${memberId }&boardId=${board.id}&boardPage=${i}">
		  	 					${i }
		  	 				</a>
		  	 			</li>
	  	 			</c:if>
	  	 		</c:forEach>
	  	 		
	  	 		<!-- 다음 화살표 -->
	  	 		<c:if test="${endPage < totalPage }">
					<a class="flex justify-center items-center"
						href="list?memberId=${memberId }&boardId=${board.id}&boardPage=${beginPage+pageSize}">
						<span> | 다음</span> <i class="fa-solid fa-caret-right text-2xl"></i>
					</a>
				</c:if>
				
				<!-- 페이지 끝 화살표 -->
				<c:if test="${beginPage + pageSize <= totalPage }">
					<a class="text-[20px] mx-6"
						href="list?memberId=${memberId }&boardId=${board.id}&boardPage=${totalPage}">
						<i class="fa-solid fa-forward flex items-center"></i>
					</a>
				</c:if>
	  	 	</ul>
   		</nav>
   	</section>
	
<%@ include file="../common/foot.jsp" %>