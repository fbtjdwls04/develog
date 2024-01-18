<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   	
<c:set var="pageTitle" value="develog" />
   	
<%@ include file="../common/head.jsp" %>
   	<!-- 배경화면  -->
   	<div class="fixed left-0 right-0 top-0">
   		<img src="https://t1.daumcdn.net/cfile/tistory/2250EA5054FF120605" 
   		alt=""
   		class="w-full opacity-80" />
   	</div>
   	
   	<section class="flex container min-h-[1000px] mx-auto border relative mt-[200px] text-[white] bg-[rgb(60,64,67)]">
   		<div class="flex flex-col w-[300px]">
			<div class="border h-[400px] break-words">
				${nickname} 의 블로그
			</div>		
			<nav class="flex-grow">
				<ul class="border h-full p-2">
					<li class="mb-4"><a href="list?memberId=${memberId}">전체 글 보기</a></li>
					<c:forEach var="board" items="${boards }">
						<li><a href="list?memberId=${board.memberId }&boardId=${board.id } ">${board.name }</a></li>
					</c:forEach>
				</ul>
			</nav>
   		</div>
   		<div class="break-words p-10 flex-grow" >
   			<div class="p-4 border-b">
				<p class="text-bold text-3xl" >${article.title }</p>
				<br />
				<span class="text-base font-bold">
					<i class="fa-regular fa-user"></i>
					${nickname}
				</span>
				&nbsp;
				<span>${article.updateDate.substring(2,16) }</span>
				&nbsp;
				<span>조회 ${article.hitCount }</span>
				&nbsp;
				<span>추천 ${article.point}</span>
   			</div>
					
			<div id="viewer" class="p-[50px]">
				${article.body }
			</div>
		</div>
   	</section>
	
<%@ include file="../common/toast_ui_init.jsp" %>
<%@ include file="../common/viewer_init.jsp" %>
<%@ include file="../common/foot.jsp" %>