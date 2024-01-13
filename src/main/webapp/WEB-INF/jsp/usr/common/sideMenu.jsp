<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<div class="flex flex-col w-[300px] border">
		<div class="h-[400px]">
			${member.nickname} 의 블로그
		</div>		
		<nav class="flex-grow">
			<ul class="border h-full">
				<li>전체 글 보기</li>
				<li>테스트1</li>
				<li>테스트2</li>
			</ul>
		</nav>
	</div>
