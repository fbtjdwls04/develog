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
   	</script>
   	
   	<section class="flex container min-h-[1000px] mx-auto relative mt-[200px]">
 	
 	 	<!-- 사이드 메뉴 -->
   		<%@ include file="../common/sideBoardList.jsp" %>  
   		<!-- 사이드 메뉴 끝 -->
   		<div class="break-words flex-grow" >
   			<div class="border-b px-10 py-20 bg-gray-200">
				<a href="list?memberId=${article.memberId}&boardId=${article.boardId }">[${nowBoard.name }]</a>
				<p class="text-bold text-3xl" >${article.title }</p>
				<br />
				<span class="text-base font-bold">
					<i class="fa-regular fa-user"></i>
					${nickname}
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
				<span>추천 ${article.point}</span>
   			</div>
			<div class="h-[50px]">
				<hr />
			</div>
			<div class="bg-gray-100 p-10">
				<div id="viewer" class="p-[50px]">
					${article.body }
				</div>
			</div>
			<div class="m-4">
				<a href="modify?id=${article.id }" class="btn btn-sm btn-outline btn-accent">수정</a>
				<button class="btn btn-sm btn-outline btn-accent" onclick="if(confirm('게시글을 삭제하시겠습니까?')) location.replace('doDelete?id=${article.id}');">
					삭제
				</button>
			</div>					
			<hr />
			<!-- 댓글창 -->
			<div class="m-4 mb-20">
				<p class="m-4">댓글 ${replies.size() }</p>
				<c:forEach var="reply" items="${replies }">
					<div class="m-4">
						<div id="reply${reply.id }" class="flex items-center reply">
							<div class="w-[100px] font-bold">${reply.writerName }</div>
							<div>
								<p>
									${reply.body }
								</p>
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
								  	<i class="fa-solid fa-ellipsis-vertical p-2"></i>
								  </div>
								  <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box text-black w-20">
								    <li><button onclick="replyModify_getForm(${reply.id})">수정</button></li>
								    <li><a href="/usr/reply/doDelete?id=${reply.id }" onclick="if(confirm('정말 삭제하시겠습니까?')== false) return false;">삭제</a></li>
								  </ul>
								</div>
							</c:if>
						</div>
						<!-- 댓글 수정창 -->
						<form id="replyModifyForm${reply.id }" action="/usr/reply/doModify" onsubmit="replyModifySubmit(this); return false;" class="mt-4 hidden replyModifyForm">
							<input type="hidden" name="id" value="${reply.id}"/>
							<input name="body" type="text" placeholder="댓글을 입력해주세요" class="input input-outline flex-grow text-black" value="${reply.body }"/>
							<button class="btn btn-accent">수정</button>
							<button class="btn btn-error bg-[pink]" type="button" onclick="replyModifyForm_cancle(${reply.id})">취소</button>
						</form>
					</div>
				</c:forEach>
				<!-- 댓글 입력창 -->
				<c:if test="${rq.getLoginedMemberId() != 0 }">
					<form action="/usr/reply/doWrite" onsubmit="replySubmit(this); return false;" class="flex m-4">
						<input type="hidden" name="articleId" value="${article.id}"/>
						<input name="body" type="text" placeholder="댓글을 입력해주세요" class="input input-outline flex-grow text-black"/>
						<button class="btn btn-accent">작성</button>
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