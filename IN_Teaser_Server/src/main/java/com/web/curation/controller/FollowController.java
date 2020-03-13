package com.web.curation.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Communication;
import com.web.curation.model.FollowList;
import com.web.curation.model.Notice;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;
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
public class FollowController {
	public static final Logger logger = LoggerFactory.getLogger(FollowController.class);

	@Autowired
	private IFollowListService followService;

	@GetMapping("/users/{user_seq}/cnt-of-following")
	@ApiOperation(value = "팔로잉수")
	public Object followingcnt(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 팔로잉 수  <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		// 팔로잉 수
		int following_cnt = followService.followingcnt(user);

		final BasicResponse result = new BasicResponse();
		if (following_cnt < 1) {
			result.status = false;
			result.data = "fail";
			result.object = following_cnt;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		result.status = true;
		result.data = "success";
		result.object = following_cnt;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/list-of-following")
	@ApiOperation(value = "팔로잉리스트")
	public Object followinglist(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 팔로잉 리스트  <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		// 팔로잉 수
		int following_cnt = followService.followingcnt(user);

		final BasicResponse result = new BasicResponse();
		if (following_cnt < 1) {
			result.status = false;
			result.data = "fail";
			result.object = following_cnt;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		List<FollowList> following = followService.following(user);
		result.status = true;
		result.data = "success";
		result.object = following;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/cnt-of-follower")
	@ApiOperation(value = "팔로워수")
	public Object followercnt(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 팔로워 수  <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		// 팔로워 수
		int follower_cnt = followService.followercnt(user);

		final BasicResponse result = new BasicResponse();
		if (follower_cnt < 1) {
			result.status = false;
			result.data = "fail";
			result.object = follower_cnt;
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = true;
			result.data = "success";
			result.object = follower_cnt;

			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@GetMapping("/users/{user_seq}/list-of-follower")
	@ApiOperation(value = "팔로워리스트")
	public Object followerlist(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 팔로워 리스트  <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		// 팔로워 수
		int follower_cnt = followService.followercnt(user);

		final BasicResponse result = new BasicResponse();
		if (follower_cnt < 1) {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		List<FollowList> follower = followService.follower(user);
		result.status = true;
		result.data = "success";
		result.object = follower;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/cnt-of-request")
	@ApiOperation(value = "신규요청탭수")
	public Object requestcnt(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 신규 요청 탭 수 <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		// 요청 수
		int request_cnt = followService.requestcnt(user);

		final BasicResponse result = new BasicResponse();
		if (request_cnt < 1) {
			result.status = false;
			result.data = "fail";
			result.object = request_cnt;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		result.status = true;
		result.data = "success";
		result.object = request_cnt;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/list-of-request")
	@ApiOperation(value = "요청탭")
	public Object requestlist(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 요청 탭  <<<<<<<<<<" + new Date());
		// 요청탭에서 나에게 팔로우 요청을 한 사람들을 뿌려달래!
		User user = new User();
		user.setUser_seq(user_seq);


		// 신규+구 요청 수
		int all_request_cnt = followService.allrequestcnt(user);

		final BasicResponse result = new BasicResponse();
		if (all_request_cnt < 1) {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		List<FollowList> req = followService.requestfollower(user);
		result.status = true;
		result.data = "success";
		result.object = req;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/cnt-of-notice")
	@ApiOperation(value = "신규알림탭수")
	public Object noticecnt(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 신규 알림 탭 수 <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		// 신규 알림탭의 수
		int notice_cnt = followService.noticecnt(user);
		final BasicResponse result = new BasicResponse();
		if (notice_cnt < 1) {
			result.status = false;
			result.data = "fail";
			result.object = notice_cnt;

			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		result.status = true;
		result.data = "success";
		result.object = notice_cnt;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/list-of-notice")
	@ApiOperation(value = "알림탭")
	public Object notice(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 알림 탭  <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		// 모든 알림탭의 수(신, 구는 new_contentYN으로 구분해주세요)
		int all_notice_cnt = followService.allnoticecnt(user);

		final BasicResponse result = new BasicResponse();
		if (all_notice_cnt < 1) {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		List<Notice> notice = followService.noticelist(user);
		result.status = true;
		result.data = "success";
		result.object = notice;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/users/{user_seq}/notice-center")
	@ApiOperation(value = "알림센터")
	public Object noticecenter(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 알림센터에 뜨는 5개의 내용(신+구 최신내용)  <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		final BasicResponse result = new BasicResponse();

		List<Notice> notice_center = followService.noticecenterlist(user);
		result.status = true;
		result.data = "success";
		result.object = notice_center;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/users/{user_seq}/notices/{notice_seq}")
	@ApiOperation(value = "알림삭제")
	public Object removenotice(@PathVariable int user_seq, @PathVariable int notice_seq) {
		logger.info(">>>>>>>>>>>> 알림 삭제  <<<<<<<<<<" + new Date());
		Notice notice = new Notice(user_seq, notice_seq);

		followService.deletenotification(notice);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/users/{user_seq}/check-new-request")
	@ApiOperation(value = "더보기누르면")
	public void checknewrequest(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> (임시)요청탭 신규내용 옛날내용으로 (더보기 누르면) <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		followService.checknewrequest1(user); // 알림센터 요청탭 처리
		followService.checknewrequest2(user); // 요청탭 처리

	}

	@PutMapping("/users/{user_seq}/leave-request-tab")
	@ApiOperation(value = "요청탭벗어나면")
	public void leaverequesttab(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 요청탭 신규내용 옛날내용으로 (요청탭 제외한 버튼 누르면) <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		followService.newCheck1(user); // followList관리
		followService.deleteReject(user);
	}

	@PutMapping("/users/{user_seq}/check-new-notice")
	@ApiOperation(value = "알림탭누르면")
	public void checknewnotice(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> (임시)알림탭 신규내용 옛날내용으로 (알림탭 누르면) <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		followService.checknewnotice(user);
	}

	@PutMapping("/users/{user_seq}/leave-notice-tab")
	@ApiOperation(value = "알림탭벗어나면")
	public void leavenoticetab(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 알림탭 신규내용 옛날내용으로 (알림탭 누르면) <<<<<<<<<<" + new Date());
		User user = new User();
		user.setUser_seq(user_seq);


		followService.newCheck2(user); // notice 관리
	}

	@PostMapping("/users/{user_seq}/try-follow")
	@ApiOperation(value = "팔로우가능여부")
	public Object canfollow(@PathVariable int user_seq, @RequestBody Communication comm) {
		logger.info(">>>>>>>>>>>> 내가 이미 팔로잉한 사람인지  <<<<<<<<<<" + new Date());


		String nickname = comm.getUser_nickname();
		int user_auth = followService.getauth(nickname);
		comm.setUser_auth(user_auth);
		nickname = comm.getOpponent_nickname();
		int opponent_auth = followService.getauth(nickname);
		comm.setOpponent_auth(opponent_auth);

		// 내가 나한테 팔로우신청, 관리자가 누구한테, 누가 관리자한테 팔로우신청은 다 fail
		final BasicResponse result = new BasicResponse();
		if (comm.getUser_nickname().equals(comm.getOpponent_nickname()) || user_auth == 0 || opponent_auth == 0) {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		result.status = true;
		// 팔로우 신청 가능한 경우 = 모르는사람, 거절당한 경우
		if (followService.canFollow1(comm) < 1) {
			result.data = "object를 봐주세요[0: 팔로우하지않은||거절당한 경우(팔로우 가능)]";
			result.object = 0;
		} else {
			String status = followService.canFollow2(comm);
			if (status.equals("I")) {// 팔로우 신청중인 경우
				result.data = "object를 봐주세요[1: 팔로우 신청중(팔로우/언팔로우 불가능)]";
				result.object = 1;
			} else if (status.equals("Y")) {// 이미 팔로우한 경우
				result.data = "object를 봐주세요[2: 이미 팔로우한 경우(언팔로우 가능)]";
				result.object = 2;
			}
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/users/{user_seq}/request-follow")
	@ApiOperation(value = "(언)팔로우요청")
	public Object requestfollow(@PathVariable int user_seq, @RequestBody Communication comm) {
		logger.info(">>>>>>>>>>>> 팔로우/언팔로우 요청  <<<<<<<<<<" + new Date());

		comm.setUser_seq(user_seq);

		String nickname = comm.getUser_nickname();
		int user_auth = followService.getauth(nickname);
		comm.setUser_auth(user_auth);
		nickname = comm.getOpponent_nickname();
		int opponent_auth = followService.getauth(nickname);
		comm.setOpponent_auth(opponent_auth);


		// 내가 나한테 팔로우신청, 관리자가 누구한테, 누가 관리자한테 팔로우신청은 다 fail
		final BasicResponse result = new BasicResponse();
		if (comm.getUser_nickname().equals(comm.getOpponent_nickname()) || user_auth == 0 || opponent_auth == 0) {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		if (followService.canFollow1(comm) < 1) {
			System.out.println("모르는 사람 또는 거절당한 사람: 팔로우버튼 노출");
			int role = comm.getUser_auth();
			if (role == 2) { // 내가 채용담당자
				comm.setTemp("채용담당자 <b>" + comm.getUser_nickname() + "</b>님이 팔로우 신청을 보냈습니다.");
			} else if (role == 1) {
				comm.setTemp("<b>" + comm.getUser_nickname() + "</b>님이 팔로우 신청을 보냈습니다.");
			}

			followService.requestfollowing1(comm);
			followService.requestfollowing2(comm);
			followService.requestfollowing3(comm);

			comm.setTemp("<b>" + comm.getOpponent_nickname() + "</b>님에게 팔로우 신청을 보냈습니다.");
			followService.requestfollowing4(comm);

			result.status = true;
			result.data = "success";
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else { // 언팔
			String status = followService.canFollow2(comm);
			if (status.equals("I")) {// 팔로우 신청중인 경우
				result.status = false;
				result.data = "fail";
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else if (status.equals("Y")) {// 이미 팔로우한 경우
				int role = comm.getUser_auth();
				if (role == 2) { // 내가 채용담당자
					comm.setTemp("채용담당자 <b>" + comm.getUser_nickname() + "</b>님이 회원님을 언팔로우 했습니다.");
				} else if (role == 1) {
					comm.setTemp("<b>" + comm.getUser_nickname() + "</b>님이 회원님을 언팔로우 했습니다.");
				}
				followService.requestunfollowing1(comm);
				followService.requestunfollowing2(comm);
				followService.requestfollowing3(comm);

				comm.setTemp("<b>" + comm.getOpponent_nickname() + "</b>님을 언팔로우 했습니다.");
				followService.requestfollowing4(comm);
				result.status = false;
				result.data = "success";
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@PutMapping("/users/{user_seq}/answer")
	@ApiOperation(value = "팔로우요청대답")
	public Object answer(@PathVariable int user_seq, @RequestBody FollowList follow) {
		logger.info(">>>>>>>>>>>> 팔로우 요청 수락/거절  <<<<<<<<<<" + new Date());
		follow.setUser_seq(user_seq);


		Communication comm = new Communication();
		comm.setUser_nickname(follow.getUser_nickname());
		comm.setOpponent_nickname(follow.getOpponent_nickname());

		if (follow.getAcceptYN().equals("Y")) {
			// state가 1인 나의 acceptYN=Y로, new_result를 Y로
			// state가 2인 너의 acceptYN=Y로, new_result를 Y로
			followService.answer1(follow);
			followService.answer2(follow);
			comm.setTemp("<b>" + follow.getUser_nickname() + "</b>님이 팔로우 요청을 승인했습니다.");
			followService.answer3(comm);
		} else {
			// state가 1인 나의 acceptYN=N로, new_result를 Y로 U
			// state가 2인 너의 acceptYN=N로, new_result를 Y로 U
			followService.answer1(follow);
			followService.answer2(follow);
			comm.setTemp("<b>" + follow.getUser_nickname() + "</b>님이 팔로우 요청을 거절했습니다.");
			followService.answer3(comm);
		}
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/random")
	@ApiOperation(value = "랜덤친구추천")
	public Object random(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 랜덤친구추천  <<<<<<<<<<" + new Date());

		List<UserProfile> random = followService.random(user_seq);

		final BasicResponse result = new BasicResponse();
		if (random.isEmpty()) {
			result.data = "fail";
			result.object = null;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		result.data = "success";
		result.object = random;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}