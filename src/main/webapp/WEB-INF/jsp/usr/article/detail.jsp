<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   	
<%@ include file="../common/head.jsp" %>
   	<!-- 배경화면  -->
   	<div class="fixed left-0 right-0 top-0">
   		<img src="https://t1.daumcdn.net/cfile/tistory/2250EA5054FF120605" 
   		alt=""
   		class="w-full opacity-80" />
   	</div>
   	
   	<script>
   		function replySubmit(e){
   			
			e.body.value = e.body.value.trim();
			
			if(e.body.value.length == 0){
				alert("내용을 입력해주세요");
				return;
			}
			
			e.submit();
   		}
   	</script>
   	
   	<section class="flex container min-h-[1000px] mx-auto border relative mt-[200px] text-[white] bg-[rgb(60,64,67)]">
 	
 	 	<!-- 사이드 메뉴 -->
   		<%@ include file="../common/sideBoardList.jsp" %>  
   		
   		<div class="break-words p-10 flex-grow" >
   			<div class="p-4 border-b">
				<a href="list?memberId=${article.memberId}&boardId=${article.boardId }">[${boardName }]</a>
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
			<div class="my-4">
				<a href="modify?id=${article.id }" class="btn btn-sm btn-outline btn-accent">수정</a>
				<button class="btn btn-sm btn-outline btn-accent" onclick="if(confirm('게시글을 삭제하시겠습니까?')) location.replace('doDelete?id=${article.id}');">
					삭제
				</button>
			</div>
			<hr />
			<div class="my-4">
				<p class="my-4">댓글 ${replies.size() }</p>
				<div>
					<c:forEach var="reply" items="${replies }">
						<div class="flex items-center">
							<div class="w-[100px]">${reply.writerName }</div>
							<div>
								${reply.body }
							</div>
							<c:if test="${reply.memberId == rq.getLoginedMemberId() }">
								<div class="dropdown dropdown-end ml-auto">
								  <div tabindex="0" role="button">
								  	<i class="fa-solid fa-ellipsis-vertical p-2"></i>
								  </div>
								  <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box text-black w-20">
								    <li><a>수정</a></li>
								    <li><a href="/usr/reply/doDelete?id=${reply.id }" onclick="if(confirm('정말 삭제하시겠습니까?')== false) return false;">삭제</a></li>
								  </ul>
								</div>
							</c:if>
						</div>
					</c:forEach>
				</div>
				<form action="/usr/reply/doWrite" onsubmit="replySubmit(this); return false;" class="flex mt-4">
					<input type="hidden" name="articleId" value="${article.id}"/>
					<input name="body" type="text" placeholder="댓글을 입력해주세요" class="input input-outline flex-grow text-black"/>
					<button class="btn btn-accent">작성</button>
				</form>
			</div>
		</div>
   	</section>
   	
	
<%@ include file="../common/toast_ui_init.jsp" %>
<%@ include file="../common/viewer_init.jsp" %>
<%@ include file="../common/foot.jsp" %>