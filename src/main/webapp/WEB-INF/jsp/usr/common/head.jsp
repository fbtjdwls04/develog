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
		<button class="ml-[20px] p-2"><a class="btn btn-sm btn-outline btn-accent" href="/">develog</a></button>
		<a href="/usr/home/test" class="ml-[20px] mr-auto text-white">
	    	<i class="fa-solid fa-flask p-2"></i>
	    </a>
		<ul class="flex">
			<c:if test="${rq.getLoginedMemberId() == 0}">
				<li><a class="p-2 btn btn-sm btn-outline btn-accent" href="/usr/member/login">로그인</a></li>
				<li><a class="p-2 btn btn-sm btn-outline btn-accent" href="/usr/member/join">회원가입</a></li>
			</c:if>
			<c:if test="${rq.getLoginedMemberId() != 0}">
				<li><a class="p-2 btn btn-sm btn-outline btn-accent" href="/usr/article/list?memberId=${rq.getLoginedMemberId() }">내 블로그</a></li>
				<li><a class="p-2 btn btn-sm btn-outline btn-accent" href="/usr/member/doLogout">로그아웃</a></li>
				<li class="mx-4">
					<div class="avatar">
					  	<div class="w-[35px] rounded-full">			
							<c:if test="${member.profillImg == null }">
									<a href="/usr/member/myPage">
								    	<img src="https://mblogthumb-phinf.pstatic.net/MjAyMDExMDFfMTgy/MDAxNjA0MjI4ODc1NDMw.Ex906Mv9nnPEZGCh4SREknadZvzMO8LyDzGOHMKPdwAg.ZAmE6pU5lhEdeOUsPdxg8-gOuZrq_ipJ5VhqaViubI4g.JPEG.gambasg/%EC%9C%A0%ED%8A%9C%EB%B8%8C_%EA%B8%B0%EB%B3%B8%ED%94%84%EB%A1%9C%ED%95%84_%ED%95%98%EB%8A%98%EC%83%89.jpg?type=w800" alt="" />
									</a>
						  	</c:if>
						  	<c:if test="${member.profillImg != null }">
									<a href="/usr/member/myPage">
								    	<img src="${member.profillImg }"/>	
									</a>
						  	</c:if>
				  		</div>
				  	</div>
				</li>
			</c:if>
		</ul>
	</header>
	
	<!-- 배경화면  -->
   	<div class="fixed left-0 right-0 top-0">
   		<img src="https://t1.daumcdn.net/cfile/tistory/2250EA5054FF120605" 
   		alt=""
   		class="w-full opacity-80" />
   	</div>
	
	
