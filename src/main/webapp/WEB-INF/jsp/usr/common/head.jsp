<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-theme="light">
<head>
<meta charset="UTF-8">
<!-- 테일 윈드, daisyUI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.0.8/dist/full.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
<!-- jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/resource/common.css" />

<script src="/resource/common.js" defer="defer"></script>
<!-- toast ui -->
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<!-- 사이트 아이콘 -->
<link rel="icon" href="/resource/funkinCat.png" />

<title>${pageTitle}</title>
</head>
<body>
	<header class="fixed w-full z-10 flex justify-end items-center top-4 right-2">
		<button class="btn btn-sm btn-outline btn-accent mr-auto ml-[20px]"><a href="/">develog</a></button>
		<ul class="flex">
			<c:if test="${rq.getLoginedMemberId() == 0}">
				<li class="p-2 btn btn-sm btn-outline btn-accent"><a href="/usr/member/login">로그인</a></li>
				<li class="p-2 btn btn-sm btn-outline btn-accent"><a href="/usr/member/join">회원가입</a></li>
			</c:if>
			<c:if test="${rq.getLoginedMemberId() != 0}">
				<li class="p-2 btn btn-sm btn-outline btn-accent"><a href="/usr/article/blogMain?memberId=${rq.getLoginedMemberId() }">내 블로그</a></li>
				<li class="p-2 btn btn-sm btn-outline btn-accent"><a href="/usr/member/doLogout">로그아웃</a></li>
			</c:if>
		</ul>
	</header>
	
	
