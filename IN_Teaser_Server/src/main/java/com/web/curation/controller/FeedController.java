package com.web.curation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.common.Common;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Comment;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.FeedHide;
import com.web.curation.model.FeedLike;
import com.web.curation.model.FeedScrap;
import com.web.curation.model.Pagination;
import com.web.curation.model.ReportList;
import com.web.curation.model.User;
import com.web.curation.model.UserHide;
import com.web.curation.service.ICommentService;
import com.web.curation.service.IFeedService;
import com.web.curation.service.IUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
@RequestMapping("/api")
@RestController
public class FeedController {
	public static final Logger logger = LoggerFactory.getLogger(FeedController.class);

	@Autowired
	private IFeedService feedService;

	@Autowired
	private IUserService userService;

	@Autowired
	private ICommentService commentService;

	@PostMapping("/feed/{user_seq}")
	@ApiOperation(value = "피드 등록")
	public Object insertFeed(@PathVariable int user_seq, @RequestBody Feed feed) {
		logger.info(">>>>>>>>>>>> 피드 등록  <<<<<<<<<<" + new Date());

		feed.setUser_seq(user_seq);
		// feed.setUser_nickname(user_nickname);

		feedService.insertFeed(feed);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PutMapping("/feed/{user_seq}/{feed_seq}")
	@ApiOperation(value = "피드 수정")
	public Object updateFeed(@PathVariable int user_seq, @PathVariable int feed_seq, @RequestBody Feed feed) {
		logger.info(">>>>>>>>>>>> 피드 수정  <<<<<<<<<<" + new Date());

		feed.setFeed_seq(feed_seq);
		feed.setUser_seq(user_seq);

		feedService.updateFeed(feed);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@DeleteMapping("/feed/{user_seq}/{feed_seq}")
	@ApiOperation(value = "피드 삭제")
	public Object deleteFeed(@PathVariable int user_seq, @PathVariable int feed_seq) {
		logger.info(">>>>>>>>>>>> 피드 삭제 <<<<<<<<<<" + new Date());

		Feed feed = new Feed();
		feed.setFeed_seq(feed_seq);
		feed.setUser_seq(user_seq);

		feedService.deleteFeed(feed);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/feed/{user_seq}/like/{feed_seq}")
	@ApiOperation(value = "피드 좋아요")
	public Object feedLike(@PathVariable int user_seq, @PathVariable int feed_seq, @RequestBody FeedLike feedlike) {
		logger.info(">>>>>>>>>>>> 피드 좋아요 버튼  <<<<<<<<<<" + new Date());

		feedlike.setUser_seq(user_seq);
		feedlike.setFeed_seq(feed_seq);

		feedService.feedLike(feedlike);

		Feed feed = new Feed();
		feed.setFeed_seq(feed_seq);

		feedService.feedLikeAdd(feed);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = feedlike;

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@DeleteMapping("/feed/{user_seq}/like-cancel/{feedlike_seq}")
	@ApiOperation(value = "피드 좋아요 취소")
	public Object feedLikeCancel(@PathVariable int user_seq, @PathVariable int feedlike_seq,
			@RequestBody FeedLike feedlike) {
		logger.info(">>>>>>>>>>>> 피드 좋아요 버튼  <<<<<<<<<<" + new Date());

		feedlike.setUser_seq(user_seq);
		feedlike.setFeedlike_seq(feedlike_seq);

		feedService.feedLikeCancel(feedlike);

		Feed feed = new Feed();
		feed.setFeed_seq(feedlike.getFeed_seq()); // 피드

		feedService.feedLikeSubtract(feed);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/feed/{user_seq}/scrap/{feed_seq}")
	@ApiOperation(value = "피드 스크랩")
	public Object feedScrap(@PathVariable int user_seq, @PathVariable int feed_seq, @RequestBody FeedScrap feedScrap) {
		logger.info(">>>>>>>>>>>> 피드 스크랩 버튼  <<<<<<<<<<" + new Date());

		feedScrap.setUser_seq(user_seq);
		feedScrap.setFeed_seq(feed_seq);

		feedService.feedScrap(feedScrap);

		Feed feed = new Feed();
		feed.setFeed_seq(feed_seq);

		feedService.feedScrapAdd(feed);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = feedScrap;

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@DeleteMapping("/feed/{user_seq}/scrap-cancel/{feedscrap_seq}")
	@ApiOperation(value = "피드 스크랩 취소")
	public Object feedScrapCancel(@PathVariable int user_seq, @PathVariable int feedscrap_seq,
			@RequestBody FeedScrap feedscrap) {
		logger.info(">>>>>>>>>>>> 피드 스크랩 취소 버튼  <<<<<<<<<<" + new Date());

		feedscrap.setUser_seq(user_seq);
		feedscrap.setFeedscrap_seq(feedscrap_seq);

		feedService.feedScrapCancel(feedscrap);

		Feed feed = new Feed();
		feed.setFeed_seq(feedscrap.getFeed_seq()); // 피드

		feedService.feedScrapSubtract(feed);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/feed/{user_seq}/feed-report/{feed_seq}")
	@ApiOperation(value = "피드 신고")
	public Object feedReport(@PathVariable int user_seq, @PathVariable int feed_seq,
			@RequestBody ReportList reportList) {
		logger.info(">>>>>>>>>>>> 피드 신고 버튼  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();
		reportList.setUser_seq(user_seq);
		reportList.setFeed_seq(feed_seq);

		// 이미 신고한 사용자인지
		Boolean reportCheck = feedService.userReportCheck(reportList);
		if (reportCheck) {
			result.status = false;
			result.data = "이미 신고한 게시글 입니다.";
		} else {
			feedService.feedReport(reportList);

			Feed feed = new Feed();
			feed.setFeed_seq(feed_seq);

			feedService.feedReportAdd(feed);

			// 신고됐다고 알림을 보내보자
			feedService.reportnotice(reportList);
			result.status = true;
			result.data = "success";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/feed/{user_seq}/feed-hide/{feed_seq}")
	@ApiOperation(value = "피드 숨김")
	public Object feedHide(@PathVariable int user_seq, @PathVariable int feed_seq, @RequestBody FeedHide feedHide) {
		logger.info(">>>>>>>>>>>> 피드 숨김 버튼  <<<<<<<<<<" + new Date());

		feedHide.setUser_seq(user_seq);
		feedHide.setFeed_seq(feed_seq);

		feedService.feedHide(feedHide);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/feed/{user_seq}/user-hide/{opponent_seq}")
	@ApiOperation(value = "이 사용자 글 안보기")
	public Object userHide(@PathVariable int user_seq, @PathVariable int opponent_seq, @RequestBody UserHide userHide) {
		logger.info(">>>>>>>>>>>> 이 사용자 글 안보기 버튼  <<<<<<<<<<" + new Date());

		userHide.setUser_seq(user_seq);
		userHide.setOpponent_seq(opponent_seq);

		feedService.userHide(userHide);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@DeleteMapping("/feed/{user_seq}/user-hide-cancel/{userhide_seq}")
	@ApiOperation(value = "이 사용자 글 보기")
	public Object userHideCancel(@PathVariable int user_seq, @PathVariable int userhide_seq,
			@RequestBody UserHide userHide) {
		logger.info(">>>>>>>>>>>> 이 사용자 글 안보기 버튼  <<<<<<<<<<" + new Date());

		userHide.setUser_seq(user_seq);
		userHide.setUserhide_seq(userhide_seq);

		feedService.userHideCancel(userHide);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping("/feed/{user_seq}/detail/{feed_seq}")
	@ApiOperation(value = "피드 상세페이지")
	public Object detailFeed(@PathVariable int user_seq, @PathVariable int feed_seq) {
		logger.info(">>>>>>>>>>>> 피드 상세페이지  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();
		FeedDetail feedDetail = new FeedDetail();
		try {
			// 피드 상세
			Feed feed = new Feed();
			feed.setFeed_seq(feed_seq);
			feed.setUser_seq(user_seq);

			Feed feedResult = feedService.detailFeed(feed);

			feedDetail.setFeed_seq(feedResult.getFeed_seq());
			feedDetail.setFeed_subject(feedResult.getFeed_subject());
			feedDetail.setFeed_contents(feedResult.getFeed_contents());
			feedDetail.setFeed_hashtag(feedResult.getFeed_hashtag());
			feedDetail.setInsert_date(feedResult.getInsert_date());
			String DateCreated = Common.calculateTime(feedDetail.getInsert_date());
			feedDetail.setPost_date(DateCreated);
			feedDetail.setFeed_scope(feedResult.getFeed_scope());

			// 피드 작성자
			User user = new User();
			user.setUser_seq(feedResult.getUser_seq());

			FeedDetail userResult = userService.writer(user);

			feedDetail.setUser_seq(userResult.getUser_seq());
			feedDetail.setUser_auth(userResult.getUser_auth());
			feedDetail.setUser_nickname(userResult.getUser_nickname());
			feedDetail.setUser_profile_route(userResult.getUser_profile_route());

			// 로그인 한 사용자
			try {

				// boolean login_user_like = feedService.loginUserLike(feed);
				FeedLike feedlike = feedService.loginUserLike(feed);
//				if (login_user_like) {
				if (feedlike != null) {
					feedDetail.setLogin_user_like(true);
					feedDetail.setFeedlike_seq(feedlike.getFeedlike_seq());
				} else {
					feedDetail.setLogin_user_like(false);
				}
			} catch (Exception e) {
				feedDetail.setLogin_user_like(false);
			}

			try {

				// Feed feed_user = new Feed();
				// feed_user.setUser_seq(user_seq);

				// boolean login_user_scrap = feedService.loginUserScrap(feed);
				FeedScrap feedscrap = feedService.loginUserScrap(feed);
//				if (login_user_scrap) {
				if (feedscrap != null) {
					feedDetail.setLogin_user_scrap(true);
					feedDetail.setFeedscrap_seq(feedscrap.getFeedscrap_seq());
				} else {
					feedDetail.setLogin_user_scrap(false);
				}
			} catch (Exception e) {
				feedDetail.setLogin_user_scrap(false);
			}
			// 댓글
			List<Comment> commentResult = new ArrayList<Comment>();
			commentResult = commentService.feedComment(feed);
			int comment_totalCount = commentService.feedCommentTotalCount(feed);
			feedDetail.setTotalCount(comment_totalCount);

			feedDetail.setComment(commentResult);

			result.status = true;
			result.data = "success";
			result.object = feedDetail;
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

//	@GetMapping("/feed/{user_seq}/new-feed-list")
//	@ApiOperation(value = "최신 피드 목록 ")
//	public Object feedList(@PathVariable int user_seq) {
//		logger.info(">>>>>>>>>>>> 최신 피드 목록  <<<<<<<<<<" + new Date());
//		final BasicResponse result = new BasicResponse();
//
//		List<FeedDetail> feedList = new ArrayList<FeedDetail>();
//
//		try {
//			// 피드 상세
//			Feed feed = new Feed();
//			feed.setUser_seq(user_seq);
//
//			List<Feed> feedResult = new ArrayList<Feed>();
//			feedResult = feedService.newFeedList(feed);
//
//			for (int i = 0; i < feedResult.size(); i++) {
//				feed.setFeed_seq(feedResult.get(i).getFeed_seq());
//				FeedDetail feedDetail = new FeedDetail();
//
//				feedDetail.setFeed_seq(feedResult.get(i).getFeed_seq());
//				feedDetail.setFeed_subject(feedResult.get(i).getFeed_subject());
//				feedDetail.setFeed_contents(feedResult.get(i).getFeed_contents());
//				feedDetail.setFeed_hashtag(feedResult.get(i).getFeed_hashtag());
//				feedDetail.setFeed_scope(feedResult.get(i).getFeed_scope());
//				feedDetail.setInsert_date(feedResult.get(i).getInsert_date());
//				String DateCreated = Common.calculateTime(feedDetail.getInsert_date());
//				feedDetail.setPost_date(DateCreated);
//
//				// 피드 작성자
//				User user = new User();
//				user.setUser_seq(feedResult.get(i).getUser_seq());
//
//				FeedDetail userResult = userService.writer(user);
//				feedDetail.setUser_seq(userResult.getUser_seq());
//				feedDetail.setUser_auth(userResult.getUser_auth());
//				feedDetail.setUser_nickname(userResult.getUser_nickname());
//				feedDetail.setUser_profile_route(userResult.getUser_profile_route());
//				// 로그인 한 사용자
//				try {
//
//					//Feed feed_user = new Feed();
//					//feed_user.setUser_seq(user_seq);
//
////					boolean login_user_like = feedService.loginUserLike(feed);
//					FeedLike feedlike = feedService.loginUserLike(feed);
////					if (login_user_like) {
//					if (feedlike!= null) {
//						feedDetail.setLogin_user_like(true);
//						feedDetail.setFeedlike_seq(feedlike.getFeedlike_seq());
//					} else {
//						feedDetail.setLogin_user_like(false);
//					}
//				} catch (Exception e) {
//					feedDetail.setLogin_user_like(false);
//				}
//
//				try {
//
//					//Feed feed_user = new Feed();
//					//feed_user.setUser_seq(user_seq);
//
//					//boolean login_user_scrap = feedService.loginUserScrap(feed);
//					FeedScrap feedscrap = feedService.loginUserScrap(feed);
////					if (login_user_scrap) {
//					if (feedscrap != null) {
//						feedDetail.setLogin_user_scrap(true);
//						feedDetail.setFeedscrap_seq(feedscrap.getFeedscrap_seq());
//					} else {
//						feedDetail.setLogin_user_scrap(false);
//					}
//				} catch (Exception e) {
//					feedDetail.setLogin_user_scrap(false);
//				}
//				
//				try {
//					// 댓글
//					List<Comment> commentResult = new ArrayList<Comment>();
//					commentResult = commentService.feedComment2(feed);
//					feedDetail.setComment(commentResult);
//				}catch (Exception e) {
//					feedDetail.setComment(null);
//				}
//			
//				try {
//					int comment_totalCount = commentService.feedCommentTotalCount(feed);
//					feedDetail.setTotalCount(comment_totalCount);
//					
//				}catch (Exception e) {
//					feedDetail.setTotalCount(0);
//				}
//				
//				
//				feedList.add(feedDetail);
//			} // for
//			result.status = true;
//			result.data = "success";
//			result.object = feedList;
//			return new ResponseEntity<>(result, HttpStatus.OK);
//
//		} catch (Exception e) {
//			result.status = false;
//			result.data = "fail";
//			return new ResponseEntity<>(result, HttpStatus.OK);
//		}
//
//	}

//	@GetMapping("/feed/{user_seq}/follow-feed-list")
//	@ApiOperation(value = "친구 피드 목록")
//	public Object followfeedList(@PathVariable int user_seq) {
//		logger.info(">>>>>>>>>>>> 친구 피드 목록  <<<<<<<<<<" + new Date());
//		final BasicResponse result = new BasicResponse();
//
//		List<FeedDetail> feedList = new ArrayList<FeedDetail>();
//
//		try {
//			// 피드 상세
//			Feed feed = new Feed();
//			feed.setUser_seq(user_seq);
//
//			List<Feed> feedResult = new ArrayList<Feed>();
//			feedResult = feedService.followFeedList(feed);
//
//			for (int i = 0; i < feedResult.size(); i++) {
//				feed.setFeed_seq(feedResult.get(i).getFeed_seq());
//				FeedDetail feedDetail = new FeedDetail();
//
//				feedDetail.setFeed_seq(feedResult.get(i).getFeed_seq());
//				feedDetail.setFeed_subject(feedResult.get(i).getFeed_subject());
//				feedDetail.setFeed_contents(feedResult.get(i).getFeed_contents());
//				feedDetail.setFeed_hashtag(feedResult.get(i).getFeed_hashtag());
//				feedDetail.setFeed_scope(feedResult.get(i).getFeed_scope());
//				feedDetail.setInsert_date(feedResult.get(i).getInsert_date());
//				String DateCreated = Common.calculateTime(feedDetail.getInsert_date());
//				feedDetail.setPost_date(DateCreated);
//
//				// 피드 작성자
//				User user = new User();
//				user.setUser_seq(feedResult.get(i).getUser_seq());
//
//				FeedDetail userResult = userService.writer(user);
//				feedDetail.setUser_seq(userResult.getUser_seq());
//				feedDetail.setUser_auth(userResult.getUser_auth());
//				feedDetail.setUser_nickname(userResult.getUser_nickname());
//				feedDetail.setUser_profile_route(userResult.getUser_profile_route());
//				// 로그인 한 사용자
//				try {
//
//					// Feed feed_user = new Feed();
//					// feed_user.setUser_seq(user_seq);
//
//					// boolean login_user_like = feedService.loginUserLike(feed);
//					FeedLike feedlike = feedService.loginUserLike(feed);
//					// if (login_user_like) {
//					if (feedlike != null) {
//						feedDetail.setLogin_user_like(true);
//						feedDetail.setFeedlike_seq(feedlike.getFeedlike_seq());
//					} else {
//						feedDetail.setLogin_user_like(false);
//					}
//				} catch (Exception e) {
//					feedDetail.setLogin_user_like(false);
//				}
//
//				try {
//
//					// Feed feed_user = new Feed();
//					// feed_user.setUser_seq(user_seq);
//
//					// boolean login_user_scrap = feedService.loginUserScrap(feed);
//					FeedScrap feedscrap = feedService.loginUserScrap(feed);
////					if (login_user_scrap) {
//					if (feedscrap != null) {
//						feedDetail.setLogin_user_scrap(true);
//						feedDetail.setFeedscrap_seq(feedscrap.getFeedscrap_seq());
//					} else {
//						feedDetail.setLogin_user_scrap(false);
//					}
//				} catch (Exception e) {
//					feedDetail.setLogin_user_scrap(false);
//				}
//				// 댓글
//				List<Comment> commentResult = new ArrayList<Comment>();
//				commentResult = commentService.feedComment2(feed);
//				feedDetail.setComment(commentResult);
//				int comment_totalCount = commentService.feedCommentTotalCount(feed);
//				feedDetail.setTotalCount(comment_totalCount);
//
//				feedList.add(feedDetail);
//			} // for
//			result.status = true;
//			result.data = "success";
//			result.object = feedList;
//			return new ResponseEntity<>(result, HttpStatus.OK);
//
//		} catch (Exception e) {
//			result.status = false;
//			result.data = "fail";
//			return new ResponseEntity<>(result, HttpStatus.OK);
//		}
//
//	}

	@GetMapping("/feed/{user_seq}/new-feed-list")
	@ApiOperation(value = "최신 피드 목록 ")
	public Object feedList(@PathVariable int user_seq, @RequestParam long since_id, @RequestParam long max_id,
			@RequestParam long final_id) {

		Pagination criteria = new Pagination(user_seq, since_id, max_id, final_id, 10);

		final BasicResponse result = new BasicResponse();

		Pagination hometimeline = feedService.hometimeline(criteria);

		if (hometimeline == null) {
			result.data = "피드 전부다 읽었다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		result.object = hometimeline;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/feed/{user_seq}/follow-feed-list")
	@ApiOperation(value = "친구 피드 목록")
	public Object followfeedList(@PathVariable int user_seq, @RequestParam long since_id, @RequestParam long max_id,
			@RequestParam long final_id) {
		Pagination criteria = new Pagination(user_seq, since_id, max_id, final_id, 10);

		final BasicResponse result = new BasicResponse();

		Pagination followtimeline = feedService.followtimeline(criteria);

		if (followtimeline == null) {
			result.data = "피드 전부다 읽었다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		result.object = followtimeline;
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping("/feed/{user_seq}/question-feed-list")
	@ApiOperation(value = "최신 질문 목록 ")
	public Object questionfeedList(@PathVariable int user_seq, @RequestParam long since_id, @RequestParam long max_id,
			@RequestParam long final_id) {
		Pagination criteria = new Pagination(user_seq, since_id, max_id, final_id, 10);

		final BasicResponse result = new BasicResponse();

		Pagination questionfeed = feedService.questionfeed(criteria);

		if (questionfeed == null) {
			result.data = "피드 전부다 읽었다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		result.object = questionfeed;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/feed/{user_seq}/follow-question-feed-list")
	@ApiOperation(value = "친구 질문 피드 목록")
	public Object followquestionfeedList(@PathVariable int user_seq, @RequestParam long since_id,
			@RequestParam long max_id, @RequestParam long final_id) {
		Pagination criteria = new Pagination(user_seq, since_id, max_id, final_id, 10);

		final BasicResponse result = new BasicResponse();

		Pagination questionfollowfeed = feedService.questionfollowfeed(criteria);

		if (questionfollowfeed == null) {
			result.data = "피드 전부다 읽었다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		result.object = questionfollowfeed;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
