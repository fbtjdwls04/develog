<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   	
<c:set var="pageTitle" value="develog" />
   	
<%@ include file="../common/head.jsp" %>
   	
   	<section class="flex container min-h-[1000px] mx-auto relative mt-[200px] justify-center items-center">
   		<form action="doWrite" onsubmit="submitForm(this); return false;" class="flex flex-col">
   			<div class="flex">
	   			<select class="select max-w-xs" name="boardId">
				  <option selected disabled value="0">게시판을 선택해주세요</option>
				  <c:forEach var="board" items="${boards }">
				  	<option value="${board.id }">${board.name }</option>
				  </c:forEach>
				</select>
	   			<input type="text" name="title" class="input input-outline input-bordered flex-grow" placeholder="제목을 입력해주세요" value="${article.title }"/>
   			</div>
   			<input type="hidden" name="body"/>
   			<div id="editor" class="bg-white"></div>
   			<button class="btn btn-accent ml-auto">작성</button>
   		</form>
   	</section>
	
<%@ include file="../common/toast_ui_init.jsp" %>
<%@ include file="../common/editor_init.jsp" %>
<%@ include file="../common/foot.jsp" %>