package com.web.curation.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedManagement;
import com.web.curation.model.Posting;
import com.web.curation.model.Ratio;
import com.web.curation.model.UserManagement;
import com.web.curation.service.IAdminPageService;

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
public class AdminPageController {
	public static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);

	@Autowired
	private IAdminPageService adminService;

	@GetMapping("/admin/user-management/search-all-user")
	@ApiOperation(value = "전체사용자조회")
	public Object searchalluser() {
		logger.info(">>>>>>>>>>>> 전체 사용자 조회  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();
		List<UserManagement> all_user = adminService.search_all_user();

		result.object = all_user;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/user-management/search-all-user-by-nickname")
	@ApiOperation(value = "전체사용자중닉네임검색")
	public Object searchalluserbynickname(@RequestParam("user_nickname") String user_nickname) {
		logger.info(">>>>>>>>>>>> 전체 사용자 중 닉네임 검색  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();
		List<UserManagement> all_user = adminService.search_all_user(user_nickname);

		result.object = all_user;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/user-management/search-normal")
	@ApiOperation(value = "일반사용자조회")
	public Object searchnormal() {
		logger.info(">>>>>>>>>>>> 일반 사용자 조회  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<UserManagement> all_user = adminService.search_normal();

		result.object = all_user;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/user-management/search-normal-by-nickname")
	@ApiOperation(value = "일반사용자중닉네임검색")
	public Object searchnormalbynickname(@RequestParam("user_nickname") String user_nickname) {
		logger.info(">>>>>>>>>>>> 일반사용자중닉네임검색  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<UserManagement> all_user = adminService.search_normal(user_nickname);

		result.object = all_user;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/user-management/search-recruiter")
	@ApiOperation(value = "채용담당자조회")
	public Object searchrecruiter() {
		logger.info(">>>>>>>>>>>> 채용담당자 조회  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<UserManagement> all_user = adminService.search_recruiter();

		result.object = all_user;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/user-management/search-recruiter-by-nickname")
	@ApiOperation(value = "채용담당자중닉네임검색")
	public Object searchrecruiterbynickname(@RequestParam("user_nickname") String user_nickname) {
		logger.info(">>>>>>>>>>>> 채용담당자중닉네임검색  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<UserManagement> all_user = adminService.search_recruiter(user_nickname);

		result.object = all_user;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/user-management/search-rejected")
	@ApiOperation(value = "계정정지된사용자조회")
	public Object searchrejected() {
		logger.info(">>>>>>>>>>>> 계정정지된 사용자 조회  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<UserManagement> all_user = adminService.search_rejected();

		result.object = all_user;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/admin/user-management/search-rejected-by-nickname")
	@ApiOperation(value = "계정정지된사용자중닉네임검색")
	public Object searchrejectedbynickname(@RequestParam("user_nickname") String user_nickname) {
		logger.info(">>>>>>>>>>>> 계정정지된 사용자중닉네임검색  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<UserManagement> all_user = adminService.search_rejected(user_nickname);

		result.object = all_user;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/admin/user-management/{user_seq}")
	@ApiOperation(value = "사용자계정상태")
	public Object canactivate(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 사용자 계정 상태 <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();
		if (!adminService.hasUtility(user_seq)) { // utility가 N이면 탈퇴한애
			result.data = "fail";
			return result;
		} else {
			result.data = "success";
			if (adminService.canactivate(user_seq)) {
				result.status = true;
			} else {
				result.status = false;
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@PutMapping("/admin/user-management/{user_seq}")
	@ApiOperation(value = "사용자계정정지/활성화")
	public Object activateonoff(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 사용자 계정 정지 및 활성화  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		boolean suspension = adminService.activateonoff(user_seq);

		if (suspension) {
			result.data = "정지시켰다.";
		} else {
			result.data = "활성화시켰다.";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/feed-management/search-all-feed")
	@ApiOperation(value = "전체피드조회")
	public Object searchallfeed() {
		logger.info(">>>>>>>>>>>> 전체피드조회  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<Feed> feedList = adminService.search_all_feed();

		result.status = true;
		result.data = "success";
		result.object = feedList;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/feed-management/search-all-feed-by-nickname")
	@ApiOperation(value = "전체피드중닉네임검색")
	public Object searchallfeedbynickname(@RequestParam("user_nickname") String user_nickname) {
		logger.info(">>>>>>>>>>>> 전체피드중닉네임검색  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<Feed> feedList = adminService.search_all_feed(user_nickname);

		result.status = true;
		result.data = "success";
		result.object = feedList;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/feed-management/search-all-reportfeed")
	@ApiOperation(value = "신고당한 피드조회")
	public Object searchallreportfeed() {
		logger.info(">>>>>>>>>>>> 신고당한 피드 조회  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<FeedManagement> feedList = adminService.search_reported_feed();

		result.status = true;
		result.data = "success";
		result.object = feedList;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/feed-management/search-all-reportfeed-by-nickname")
	@ApiOperation(value = "신고당한 피드중닉네임검색")
	public Object searchallreportfeedbynickname(@RequestParam("user_nickname") String user_nickname) {
		logger.info(">>>>>>>>>>>> 신고당한 피드 중 닉네임 검색  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<FeedManagement> feedList = adminService.search_reported_feed(user_nickname);

		result.status = true;
		result.data = "success";
		result.object = feedList;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/is-admin")
	@ApiOperation(value = "관리자인지검사")
	public Object isnickname(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 관리자인지 검사  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		boolean isAdmin = adminService.isadmin(user_seq);

		result.status = isAdmin;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/admin/feed-management/{feed_seq}/hide")
	@ApiOperation(value = "게시물관리자가숨기기")
	public Object feedhide(@PathVariable int feed_seq) {
		logger.info(">>>>>>>>>>>> 게시물 숨기기  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		adminService.feedhide(feed_seq);

		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/admin/feed-management/{feed_seq}/check")
	@ApiOperation(value = "게시물관리자가확인")
	public Object feedcheck(@PathVariable int feed_seq) {
		logger.info(">>>>>>>>>>>> 게시물 조취만  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		adminService.feedcheck(feed_seq);

		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/admin/main/ratio-of-user")
	@ApiOperation(value = "유저-채용담당자비율")
	public Object ratioofuser() {
		logger.info(">>>>>>>>>>>> 유저, 채용담당자 비율  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		Ratio ratio = adminService.ratio();

		result.object = ratio;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/admin/main/feed-per-day")
	@ApiOperation(value = "오늘글작성수")
	public Object feedperday() {
		logger.info(">>>>>>>>>>>> 오늘 글 작성 수  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<Posting> day = adminService.day();

		result.object = day;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}