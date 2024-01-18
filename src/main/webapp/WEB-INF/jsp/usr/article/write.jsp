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
   	
   	<section class="flex container min-h-[1000px] mx-auto border relative mt-[200px] bg-[rgb(60,64,67)]">
   		<form action="doWrite" onsubmit="submitForm(this); return false;">
   			<select class="select w-full max-w-xs" name="boardId">
			  <option disabled selected value="0">게시판을 선택해주세요</option>
			  <c:forEach var="board" items="${boards }">
			  	<option value="${board.id }">${board.name }</option>
			  </c:forEach>
			</select>
			<br />
   			<input type="text" name="title" class="input input-outline w-full" placeholder="제목을 입력해주세요"/>
   			<input type="hidden" name="body"/>
   			<div id="editor" class="text-[white]"></div>
   			<button class="btn ml-auto">작성</button>
   		</form>
   	</section>
	
<%@ include file="../common/toast_ui_init.jsp" %>
<%@ include file="../common/editor_init.jsp" %>
<%@ include file="../common/foot.jsp" %>