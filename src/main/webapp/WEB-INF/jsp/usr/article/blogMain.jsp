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
   		<div class="flex">
   			<%@ include file="../common/sideMenu.jsp" %>
   		</div>
   	</section>
	
<%@ include file="../common/foot.jsp" %>