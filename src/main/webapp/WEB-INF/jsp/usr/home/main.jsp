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
   	
   	<div class="container mx-auto relative mt-[200px] text-[white]">
   		<h1 class="text-center text-[100px] font-bold select-none">
			DEVELOG
   		</h1>
   		<nav class="mt-20 flex justify-center h-[250px]">
   			<ul class="flex flex-col items-center border p-4 rounded-[20px] bg-gray-700 overflow-y-scroll">
	   			<c:forEach var="article" items="${articles }">
	   				<li class="text-center border bg-[rgb(20,20,20)] my-2 rounded-[20px]">
	   					<a href="/usr/article/detail?id=${article.id }" class="flex flex-col justify-center p-2 w-[800px] h-[100px]">
	   						<div class="text-2xl text-bold truncate">${article.title }</div>
	   						<div class="max-h-[50px] min-h-[50px] truncate">${article.body }</div>
	   					</a>
	   				</li>
	   			</c:forEach>
   			</ul>
   		</nav>
   	</div>
	
	<%@ include file="../common/foot.jsp" %>
	
