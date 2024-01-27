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
   		const replySubmit = function(e){
   			
			e.body.value = e.body.value.trim();
			
			if(e.body.value.length == 0){
				alert("내용을 입력해주세요");
				return;
			}
			
			e.submit();
   		}
   		
   		const replyModifySubmit = function(e) {
   			
			e.body.value = e.body.value.trim();
			
			if(e.body.value.length == 0){
				alert("내용을 입력해주세요");
				return;
			}
			
			e.submit();
		}
   		
   		const replyModify_getForm = function(id) {
   			/* 해당 댓글을 제외한 나머지는 댓글만 보이게 (수정 입력칸 여러개 안켜지게) */
   			
			$('.reply').css("display", "flex");
			$('.replyModifyForm').hide();
			/*  */
			
			$('#reply' + id).hide();
			$('#replyModifyForm' + id).css("display", "flex");
		}
   		
   		/* 수정 입력칸 닫기 */
		const replyModifyForm_cancle = function(id) {
			$('#reply' + id).css("display", "flex");
			$('#replyModifyForm' + id).hide();
		}
   		
   		const resize = function(e) {
   			e.style.height = 'auto';
   			e.style.height = (20 + e.scrollHeight) + 'px';
   		}
   	</script>
   	
   	<section class="flex container min-h-[1000px] mx-auto relative mt-[200px]">
 	
 	 	<!-- 사이드 메뉴 -->
   		<%@ include file="../common/sideBoardList.jsp" %>  
   		<!-- 사이드 메뉴 끝 -->
   		<!-- 게시물 -->
   		<div class="w-full">
   			<div class="border-b p-20 pb-10 bg-gray-200">
				<a href="list?memberId=${article.memberId}&boardId=${article.boardId }">[${nowBoard.name }]</a>
				<p class="text-bold text-3xl" >${article.title }</p>
				<br />
				<span class="text-base font-bold">
					<i class="fa-regular fa-user"></i>
					${member.nickname}
				</span>
				&nbsp;
				<span>${article.updateDate.substring(2,16) }</span>
				<c:if test="${article.regDate != article.updateDate }">
					<span class="text-[gray]">
						(수정됨)
					</span>
				</c:if>
				&nbsp;
				<span>조회 ${article.hitCount }</span>
				&nbsp;
				<span>추천 ${article.hitCount}</span>
   			</div>
   			
			<div id="viewer" class="p-20 w-[800px] bg-gray-100">
				${article.body }
			</div>
			<c:if test="${article.memberId == rq.getLoginedMemberId() }">
				<div class="m-4">
					<a href="modify?id=${article.id }" class="btn btn-sm btn-outline btn-accent">수정</a>
					<button class="btn btn-sm btn-outline btn-accent" onclick="if(confirm('게시글을 삭제하시겠습니까?')) location.replace('doDelete?id=${article.id}');">
						삭제
					</button>
				</div>					
			</c:if>
			<hr />
			<!-- 댓글창 -->
			<div class="m-4 mb-20">
				<p class="m-4">댓글 ${replies.size() }</p>
				<c:forEach var="reply" items="${replies }">
					<div class="m-4">
						<div id="reply${reply.id }" class="flex items-center reply">
							<div class="avatar mr-4">
							  <div class="w-[50px] rounded-full">
							  	<a href="list?memberId=${reply.memberId }">
									<c:if test="${reply.profillImg == null }">
								    	<img src="https://mblogthumb-phinf.pstatic.net/MjAyMDExMDFfMTgy/MDAxNjA0MjI4ODc1NDMw.Ex906Mv9nnPEZGCh4SREknadZvzMO8LyDzGOHMKPdwAg.ZAmE6pU5lhEdeOUsPdxg8-gOuZrq_ipJ5VhqaViubI4g.JPEG.gambasg/%EC%9C%A0%ED%8A%9C%EB%B8%8C_%EA%B8%B0%EB%B3%B8%ED%94%84%EB%A1%9C%ED%95%84_%ED%95%98%EB%8A%98%EC%83%89.jpg?type=w800" alt="" />
								  	</c:if>
								  	<c:if test="${reply.profillImg != null }">
										<img src="${reply.profillImg }"/>		  	
								  	</c:if>		  	
							  	</a>
							  </div>
							</div>
							<div class="mr-4">
								<p class="min-w-[100px] font-bold">
									<a href="list?memberId=${reply.memberId }">
										${reply.writerName }
									</a>
								</p>
								<p class="break-words">${reply.body }</p>
								<p class="text-[14px] text-[gray]">
									${reply.updateDate }
									<c:if test="${article.regDate != article.updateDate }">
										<span>
											(수정됨)
										</span>
									</c:if>
								</p>
							</div>
							<!-- 댓글 수정 삭제 버튼 -->
							<c:if test="${reply.memberId == rq.getLoginedMemberId() }">
								<div class="dropdown dropdown-end ml-auto">
								  <div tabindex="0" role="button">
								  	<i class="fa-solid fa-ellipsis-vertical p-2 text-[gray]"></i>
								  </div>
								  <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box text-black w-20">
								    <li><button onclick="replyModify_getForm(${reply.id})">수정</button></li>
								    <li><a href="/usr/reply/doDelete?id=${reply.id }" onclick="if(confirm('정말 삭제하시겠습니까?')== false) return false;">삭제</a></li>
								  </ul>
								</div>
							</c:if>
						</div>
						<!-- 댓글 수정창 -->
						<form id="replyModifyForm${reply.id }" action="/usr/reply/doModify" onsubmit="replyModifySubmit(this); return false;" class="mt-4 hidden replyModifyForm bg-white border-2 rounded-2xl flex flex-col p-4">
							<input type="hidden" name="id" value="${reply.id}"/>
							<textarea name="body" placeholder="댓글을 입력해주세요" class="text-black resize-none overflow-hidden" onkeydown="resize(this)">${reply.body}</textarea>
							<div class="mt-2 ml-auto">
								<button type="button" onclick="if(confirm('댓글 수정을 취소하시겠습니까?')) replyModifyForm_cancle(${reply.id})">취소</button>
								<button class="ml-2 btn-sm btn-accent btn">수정</button>
							</div>
						</form>
					</div>
					<br />
				</c:forEach>
				<!-- 댓글 입력창 -->
				<c:if test="${rq.getLoginedMemberId() != 0 }">
					<form action="/usr/reply/doWrite" onsubmit="replySubmit(this); return false;" class="m-4 bg-white border-2 rounded-2xl flex flex-col p-4">
						<input type="hidden" name="articleId" value="${article.id}"/>
						<p></p>
						<textarea name="body" placeholder="댓글을 입력해주세요" class="text-black resize-none overflow-hidden" onkeydown="resize(this)"></textarea>
						<button class="ml-auto mt-2 btn-sm btn-accent btn">작성</button>
					</form>
				</c:if>
				<c:if test="${rq.getLoginedMemberId() == 0 }">
					<div class="m-4">댓글은 로그인 후 이용이 가능합니다</div>
				</c:if>
			</div>
		</div>
   	</section>
   	
	
<%@ include file="../common/toast_ui_init.jsp" %>
<%@ include file="../common/viewer_init.jsp" %>
<%@ include file="../common/foot.jsp" %>