<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="flex flex-col w-[300px]">
	<div class="border h-[400px] break-words flex flex-col items-center">
		<div class="avatar mt-20">
		  <div class="w-[100px] rounded-full">
		  	<c:if test="${profillImg == null }">
		    	<img src="https://daisyui.com/images/stock/photo-1534528741775-53994a69daeb.jpg" />
		  	</c:if>
		  	<c:if test="${profillImg != null }">
				<<img src="${profillImg }"/>		  	
		  	</c:if>
		  </div>
		</div>
		<p class="m-4 text-2xl">${nickname} 의 블로그</p>
		<p>안녕하세요 개발자가 꿈입니다</p>
	</div>
	<nav class="flex-grow p-4 border">
		<ul class="h-full p-2">
			<li class="mb-4 flex justify-between">
				<a href="list?memberId=${memberId}" class="hover:underline ${nowBoard == null ? 'font-bold' : '' }">전체 글 보기</a>
				<button class="hover:text-[gray]"><i class="fa-solid fa-gear"></i></button>
			</li>
			<c:forEach var="board" items="${boards }">
				<li class="hover:underline ${nowBoard.id == board.id ? 'font-bold' : '' }"><a
					href="list?memberId=${board.memberId }&boardId=${board.id } ">${board.name }</a></li>
			</c:forEach>
		</ul>
	</nav>
</div>