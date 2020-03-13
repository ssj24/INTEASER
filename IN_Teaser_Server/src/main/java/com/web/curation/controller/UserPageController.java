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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Pagination;
import com.web.curation.model.Project;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;
import com.web.curation.service.IAboutMeService;
import com.web.curation.service.IFileUploadService;
import com.web.curation.service.IFollowListService;

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
public class UserPageController {
	public static final Logger logger = LoggerFactory.getLogger(UserPageController.class);

	@Autowired
	private IAboutMeService accountService;
	@Autowired
	private IFollowListService followService;
	@Autowired
	private IFileUploadService fileUploadService;

	@GetMapping("/users/{user_seq}/profile")
	@ApiOperation(value = "프로필")
	public Object profile(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 유저의 프로필 정보  <<<<<<<<<<" + new Date());
		User user = new User();

		user.setUser_seq(user_seq);

		UserProfile profile = new UserProfile();

		profile = accountService.getMyAccount(user_seq);

		profile.setFollower_count(followService.followercnt(user));
		profile.setFollowing_count(followService.followingcnt(user));
		profile.setLike_count(accountService.getlikecnt(user_seq));

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";
		result.object = profile;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{login_user_seq}/{user_seq}/uploadfeed")
	@ApiOperation(value = "업로드한글")
	public Object uploadfeed(@PathVariable int login_user_seq, @PathVariable int user_seq, @RequestParam long since_id,
			@RequestParam long max_id, @RequestParam long final_id) {
		logger.info(">>>>>>>>>>>> 업로드 한 글  <<<<<<<<<<" + new Date());
		Pagination criteria = new Pagination(user_seq, since_id, max_id, final_id, 10);

		final BasicResponse result = new BasicResponse();

		Pagination uploadfeed = accountService.uploadfeed(login_user_seq, criteria);

		if (uploadfeed == null) {
			result.data = "피드 전부다 읽었다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		result.object = uploadfeed;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/likefeed")
	@ApiOperation(value = "내가좋아한글")
	public Object likefeed(@PathVariable int user_seq, @RequestParam long since_id, @RequestParam long max_id,
			@RequestParam long final_id) {
		logger.info(">>>>>>>>>>>> 내가 좋아한 글  <<<<<<<<<<" + new Date());
		Pagination criteria = new Pagination(user_seq, since_id, max_id, final_id, 10);

		final BasicResponse result = new BasicResponse();

		Pagination likefeed = accountService.likefeed(criteria);

		if (likefeed == null) {
			result.data = "피드 전부다 읽었다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		result.object = likefeed;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/likeproject")
	@ApiOperation(value = "내가좋아한프로젝트")
	public Object likeproject(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 내가 좋아한 프로젝트  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();

		List<Project> likepjt = accountService.likepjt(user_seq);

		result.object = likepjt;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/scrapfeed")
	@ApiOperation(value = "내가스크랩한글")
	public Object scrapfeed(@PathVariable int user_seq, @RequestParam long since_id, @RequestParam long max_id,
			@RequestParam long final_id) {
		logger.info(">>>>>>>>>>>> 내가 스크랩한 글  <<<<<<<<<<" + new Date());
		Pagination criteria = new Pagination(user_seq, since_id, max_id, final_id, 10);

		final BasicResponse result = new BasicResponse();

		Pagination scrapfeed = accountService.scrapfeed(criteria);

		if (scrapfeed == null) {
			result.data = "피드 전부다 읽었다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		result.object = scrapfeed;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/hidefeed")
	@ApiOperation(value = "내가 숨긴 글")
	public Object hidefeed(@PathVariable int user_seq, @RequestParam long since_id, @RequestParam long max_id,
			@RequestParam long final_id) {
		logger.info(">>>>>>>>>>>> 내가 숨긴 글  <<<<<<<<<<" + new Date());
		Pagination criteria = new Pagination(user_seq, since_id, max_id, final_id, 10);

		final BasicResponse result = new BasicResponse();

		Pagination hidefeed = accountService.hidefeed(criteria);

		if (hidefeed == null) {
			result.data = "피드 전부다 읽었다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		result.object = hidefeed;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/setting")
	@ApiOperation(value = "기본설정 조회")
	public Object readsetting(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 계정설정 > 기본설정 조회 <<<<<<<<<<" + new Date());
		// 조회: 닉네임, 이메일, 이메일onoff,이름, 이름 onoff, 관심분야, intro, intro onoff, contact,
		// contact onoff, 사진, 탈퇴여부

		UserProfile setting = accountService.readsetting(user_seq);
		User user = new User(user_seq);
		setting.setFollower_count(followService.followercnt(user));
		setting.setFollowing_count(followService.followingcnt(user));
		setting.setLike_count(accountService.getlikecnt(user_seq));

		final BasicResponse result = new BasicResponse();

		result.object = setting;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/users/{user_seq}/setting")
	@ApiOperation(value = "기본설정 + 프사 저장")
	public Object updatesettingwithphoto(@PathVariable int user_seq, @RequestPart("file") MultipartFile file,
			@ModelAttribute UserProfile setting) {
		logger.info(">>>>>>>>>>>> 계정설정 > 기본설정 +프사 저장<<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();

		setting.setUser_seq(user_seq);
		accountService.updatesetting(setting);

		result.status = true;
		result.data = "success";
		if (file == null)
			return new ResponseEntity<>(result, HttpStatus.OK);
		String url = fileUploadService.restoreprofile(user_seq, file);

		result.object = url;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/users/{user_seq}/setting")
	@ApiOperation(value = "기본설정 저장")
	public Object updatesetting(@PathVariable int user_seq,	@ModelAttribute UserProfile setting) {
		logger.info(">>>>>>>>>>>> 계정설정 > 기본설정 저장<<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();

		setting.setUser_seq(user_seq);
		accountService.updatesetting(setting);

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/users/{user_seq}/secession")
	@ApiOperation(value = "회원탈퇴")
	public void secession(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 계정설정 > 회원 탈퇴 <<<<<<<<<<" + new Date());

		accountService.secession(user_seq);

	}

	@PutMapping("/users/{user_seq}/remove-photo")
	@ApiOperation(value = "사진삭제")
	public void removephoto(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 계정설정 > 사진 삭제 <<<<<<<<<<" + new Date());

		accountService.removephoto(user_seq);

	}

}