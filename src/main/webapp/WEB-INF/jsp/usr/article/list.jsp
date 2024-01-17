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
					<li class="mb-4">전체 글 보기</li>
					<c:forEach var="board" items="${boards }">
						<li>${board.name }</li>
					</c:forEach>
				</ul>
			</nav>
   		</div>
   		<nav class="flex-grow">
   			<h1 class="p-2">전체글 리스트</h1>
	   		<ul>
		   		<c:forEach var="article" items="${articles }">
		   			<a href="detail?id=${article.id }&memberId=${memberId}">
			   			<li class="border-b p-2 m-2">
			   			${article.title }
			   			</li>
		   			</a>
		   		</c:forEach>
	  	 	</ul>
   		</nav>
   	</section>
	
<%@ include file="../common/foot.jsp" %>