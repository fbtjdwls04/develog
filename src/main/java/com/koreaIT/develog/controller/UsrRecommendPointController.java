package com.koreaIT.develog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.develog.service.ArticleService;
import com.koreaIT.develog.service.BoardService;
import com.koreaIT.develog.service.MemberService;
import com.koreaIT.develog.service.RecommendPointService;
import com.koreaIT.develog.service.ReplyService;
import com.koreaIT.develog.util.Util;
import com.koreaIT.develog.vo.Article;
import com.koreaIT.develog.vo.RecommendPoint;
import com.koreaIT.develog.vo.Reply;
import com.koreaIT.develog.vo.ResultData;
import com.koreaIT.develog.vo.Rq;

@Controller
public class UsrRecommendPointController {
	
	private RecommendPointService recommendPointService;
	private Rq rq;
	
	public UsrRecommendPointController(RecommendPointService recommendPointService,Rq rq) {
		this.recommendPointService = recommendPointService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/recommendPoint/getRecommendPoint")
	@ResponseBody
	public ResultData<RecommendPoint> getRecommendPoint(int articleId) {
		
		RecommendPoint recommendPoint = recommendPointService.getRecommendPoint(articleId, rq.getLoginedMemberId());
		
		if(recommendPoint == null) {
			return ResultData.from("F-1", "좋아요 기록 없음");
		}
	
		return ResultData.from("S-1", "좋아요 기록 있음", recommendPoint);
	}
	
	@RequestMapping("/usr/recommendPoint/doRecommendPoint")
	@ResponseBody
	public ResultData doRecommendPoint(int articleId, boolean recommendBtn) {
		
		if(recommendBtn) {
			recommendPointService.deleteRecommendPoint(articleId, rq.getLoginedMemberId());
			return ResultData.from("F-1", "좋아요 삭제", recommendPointService.getRecommendPointCnt(articleId));
		}
		
		recommendPointService.insertRecommendPoint(articleId, rq.getLoginedMemberId());
		return ResultData.from("S-1", "좋아요 추가", recommendPointService.getRecommendPointCnt(articleId));
	}	
}
