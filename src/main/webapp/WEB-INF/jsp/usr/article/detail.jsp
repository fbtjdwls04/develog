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
				<ul class="border h-full">
					<li>전체 글 보기</li>
					<li>테스트1</li>
					<li>테스트2</li>
				</ul>
			</nav>
   		</div>
   		<div>
   			${article.title }
   		</div>
   		<div>
   			${article.body }
   		</div>
   		<div>
   			${nickname }
   		</div>
   	</section>
	
<%@ include file="../common/foot.jsp" %>