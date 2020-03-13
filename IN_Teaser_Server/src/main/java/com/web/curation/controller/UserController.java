package com.web.curation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
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

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Comment;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.FeedLike;
import com.web.curation.model.FeedScrap;
import com.web.curation.model.SearchKeyword;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;
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
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IFeedService feedService;
	@Autowired
	private ICommentService commentService;

	@GetMapping("/user/{user_seq}/search-keyword-list")
	@ApiOperation(value = "사용자가 검색헀던 키워드목록")
	public Object search_keyword_list(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 사용자가 검색헀던 키워드목록  <<<<<<<<<<" + new Date());

		SearchKeyword s_keyword = new SearchKeyword();
		// s_keyword.setUser_email(user_email);
		s_keyword.setUser_seq(user_seq);
		List<SearchKeyword> keyword_list = userService.search_keyword_list(s_keyword);
		for (int i = 0; i < keyword_list.size(); i++) {
			String isShop = keyword_list.get(i).getSearch_keyword().substring(0, 1);
			if (!isShop.equals("#")) {
				SearchKeyword searchKeyword = keyword_list.get(i);
				String searchuser = keyword_list.get(i).getSearch_keyword();

				int search_keyword_user_seq = userService
						.serch_nickname_getuserseq(keyword_list.get(i).getSearch_keyword());
				searchKeyword.setSearch_keyword_user_seq(search_keyword_user_seq);
		}//if

		}
		final BasicResponse result = new BasicResponse();

		if (keyword_list.size() > 0) {
			result.status = true;
			result.data = "success";
			result.object = keyword_list;
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@PostMapping("/user/{user_seq}/add-search-keyword")
	@ApiOperation(value = "사용자가 검색헀던 키워드 저장")
	public Object add_search_keyword(@PathVariable int user_seq,
			@RequestParam("search_keyword") String search_keyword) {
		logger.info(">>>>>>>>>>>> 사용자가 검색헀던 키워드 저장  <<<<<<<<<<" + new Date());

		SearchKeyword s_keyword = new SearchKeyword();
		s_keyword.setUser_seq(user_seq);
		s_keyword.setSearch_keyword(search_keyword);

		// 저장하기 전에 이미 저장중인 키워드 인지 검색
		int keywordcheck = userService.check_search_keyword(s_keyword);
		if (keywordcheck > 0) {
			userService.update_search_keyword(s_keyword);
		} else {
			userService.add_search_keyword(s_keyword);
		}

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@DeleteMapping("/user/{keyword_seq}/remove-search-keyword")
	@ApiOperation(value = "사용자가 검색헀던 키워드 삭제")
	public Object delete_search_keyword(@PathVariable int keyword_seq) {
		logger.info(">>>>>>>>>>>> 사용자가 검색헀던 키워드 삭제  <<<<<<<<<<" + new Date());

		SearchKeyword s_keyword = new SearchKeyword();
		s_keyword.setKeyword_seq(keyword_seq);
		userService.delete_search_keyword(s_keyword);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping("/user/{user_nickname}")
	@ApiOperation(value = "유저 닉네임으로 검색")
	public Object serchNickname(@PathVariable String user_nickname) {
		logger.info(">>>>>>>>>>>> 유저 닉네임으로 검색  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_nickname(user_nickname);

		List<User> user_list = userService.serch_nickname(user);

		final BasicResponse result = new BasicResponse();

		if (user_list.size() > 0) {
			result.status = true;
			result.data = "success";
			result.object = user_list;
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@GetMapping("/user-hashtag/{hashtag}/{user_seq}") // 해시태그는 3글자 이상... 우선
	@ApiOperation(value = "해시태그 검색")
	public Object serchHashtag(@PathVariable String hashtag, @PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 해시태그 검색  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();
		List<FeedDetail> feedList = new ArrayList<FeedDetail>();
		try {
			Feed feed = new Feed();
			hashtag = hashtag.replaceAll("#", "[#]");
			feed.setFeed_hashtag("+" + hashtag);

			List<Feed> hashtag_list = userService.serchHashtag(feed);

			for (int i = 0; i < hashtag_list.size(); i++) {

				FeedDetail feedDetail = new FeedDetail();

				feedDetail.setFeed_seq(hashtag_list.get(i).getFeed_seq());
				feedDetail.setFeed_subject(hashtag_list.get(i).getFeed_subject());
				feedDetail.setFeed_contents(hashtag_list.get(i).getFeed_contents());
				feedDetail.setFeed_hashtag(hashtag_list.get(i).getFeed_hashtag());
				feedDetail.setFeed_scope(hashtag_list.get(i).getFeed_scope());
				// feedDetail.setInsert_date(feedResult.getInsert_date());
				// feedResult.getInsert_date().toString()) 여기에 가공된 데이터 들어가야 함
				feedDetail.setPost_date(hashtag_list.get(i).getInsert_date().toString());

				// 피드 작성자
				User user = new User();
				user.setUser_seq(hashtag_list.get(i).getUser_seq());

				FeedDetail userResult = userService.writer(user);
				feedDetail.setUser_seq(userResult.getUser_seq());
				feedDetail.setUser_auth(userResult.getUser_auth());
				feedDetail.setUser_nickname(userResult.getUser_nickname());
				feedDetail.setUser_profile_route(userResult.getUser_profile_route());

				// 로그인 한 사용자
				try {

					//boolean login_user_like = feedService.loginUserLike(feed);
					FeedLike feedlike = feedService.loginUserLike(feed);
//					if (login_user_like) {
					if (feedlike!= null) {
						feedDetail.setLogin_user_like(true);
						feedDetail.setFeedlike_seq(feedlike.getFeedlike_seq());
					} else {
						feedDetail.setLogin_user_like(false);
					}
				} catch (Exception e) {
					feedDetail.setLogin_user_like(false);
				}

				try {

					//Feed feed_user = new Feed();
					//feed_user.setUser_seq(user_seq);

					//boolean login_user_scrap = feedService.loginUserScrap(feed);
					FeedScrap feedscrap = feedService.loginUserScrap(feed);
//					if (login_user_scrap) {
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
				feedDetail.setComment(commentResult);

				feedList.add(feedDetail);

			}

			result.status = true;
			result.data = "success";
			result.object = feedList;
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

}
