<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="flex flex-col w-[300px]">
	<div class="border h-[400px] break-words">${nickname} 의 블로그</div>
	<nav class="flex-grow">
		<ul class="border h-full p-2">
			<li class="mb-4"><a href="list?memberId=${memberId}">전체 글 보기</a></li>
			<c:forEach var="board" items="${boards }">
				<li class="hover:underline"><a
					href="list?memberId=${board.memberId }&boardId=${board.id } ">${board.name }</a></li>
			</c:forEach>
		</ul>
	</nav>
</div>