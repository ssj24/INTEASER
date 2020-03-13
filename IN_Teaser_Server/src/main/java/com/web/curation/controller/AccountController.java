package com.web.curation.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.common.MailUtil;
import com.web.curation.common.SHA256Util;
import com.web.curation.jwt.JwtService;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.User;
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
public class AccountController {
	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	// private static final String IP = "http://"+"address.localhost.ipconfig";

//	@Value("${address.localhost.ipconfig}")
//	private String IP;
	@Value("${address.aws.ipconfig}")
	private String IP;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private IUserService userService;

	// @GetMapping("/account/{user_email}/login")
	@PostMapping("/account/{user_email}/login")
	@ApiOperation(value = "로그인")
	public Object login(@PathVariable String user_email, @RequestParam("user_pwd") String user_pwd,
			HttpServletResponse res) {// @RequestBody User user , @PathVariable String user_pwd
		logger.info(">>>>>>>>>>>> 로그인  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_email(user_email);
		user.setUser_pwd(user_pwd);

		final BasicResponse result = new BasicResponse();
		try {

			String salttext = userService.getSalt(user.getUser_email());
			String pwd = user.getUser_pwd();
			user.setUser_pwd(SHA256Util.getEncrypt(pwd, salttext));

			int certification_result = userService.certification_check(user);
			if (certification_result > 0) {
				User isMember = userService.login_data(user);
				if (isMember != null) {
					
					if( isMember.getuser_utilityYN() == "I"
							|| isMember.getuser_utilityYN().equals("I") ) {
						result.status = false;
						result.data = "관리자에 의해 계정이 정지되었습니다.";
						// status = HttpStatus.INTERNAL_SERVER_ERROR;
						return new ResponseEntity<>(result, HttpStatus.OK);
					}else {
					
						result.status = true;
						result.data = "success";
	
						// 로그인 성공했다면 토큰을 생성한다.
						String token = jwtService.create(isMember.getUser_nickname());
						// 토큰 정보는 request의 헤더로 보내고 나머지는 Map에 담아주자.
						res.setHeader("jwt-auth-token", token);
						// resultMap.put("auth_token", token);
						// 사용자에게 전달할 정보 + 너스로 토큰에 담긴 정보도 전달해보자. 서버에서 토큰을 사용하는 방법임을 알 수 있다.
						// jwtService.get(req.getHeader("jwt-auth-token"))
						result.object = isMember;
	
						// 사용자의 salt정보 수정 + 비밀번호 변경
						user = new User();
						user.setUser_email(user_email);
	
						String salttext_change = SHA256Util.generateSalt();
						user.setUser_salt(salttext_change);
	
						String pwd_change = user_pwd;
						pwd_change = SHA256Util.getEncrypt(pwd_change, salttext_change);
						user.setUser_pwd(pwd_change);
	
						userService.update_saltpwd(user);
	
						// status = HttpStatus.ACCEPTED;
						return new ResponseEntity<>(result, HttpStatus.OK);
					}
				} else {
					result.status = false;
					result.data = "fail";
					// status = HttpStatus.INTERNAL_SERVER_ERROR;
					return new ResponseEntity<>(result, HttpStatus.OK);
				}
			} else {
				result.status = false;
				result.data = "인증되지 않은 사용자 입니다.";
				// status = HttpStatus.INTERNAL_SERVER_ERROR;
				return new ResponseEntity<>(result, HttpStatus.OK);
			}

		} catch (Exception e) {
			result.status = false;
			result.data = "가입되지 않은 사용자 입니다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@GetMapping("/account/{user_email}/sns-login")
	@ApiOperation(value = "SNS로그인 시 토큰 발행")
	public Object SNSlogin(@PathVariable String user_email, HttpServletResponse res) {// @RequestBody User user
		logger.info(">>>>>>>>>>>> 로그인  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_email(user_email);

		User isMember = userService.SNSlogin(user);

		final BasicResponse result = new BasicResponse();
		//if (!isMember.getUser_nickname().isEmpty()) {
		if(isMember != null) {
			if(isMember.getuser_utilityYN() == "I" 
					|| isMember.getuser_utilityYN().equals("I")) {
				result.status = false;
				result.data = "관리자에 의해 계정이 정지되었습니다.";
				return new ResponseEntity<>(result, HttpStatus.OK);
			}else {
			result.status = true;
			result.data = "success";

			// 로그인 성공했다면 토큰을 생성한다.
			String token = jwtService.create(isMember.getUser_nickname());
			// 토큰 정보는 request의 헤더로 보내고 나머지는 Map에 담아주자.
			res.setHeader("jwt-auth-token", token);
			// resultMap.put("auth_token", token);
			// 사용자에게 전달할 정보 + 너스로 토큰에 담긴 정보도 전달해보자. 서버에서 토큰을 사용하는 방법임을 알 수 있다.
			// jwtService.get(req.getHeader("jwt-auth-token"))
			result.object = isMember;

			// status = HttpStatus.ACCEPTED;
			return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} else {
			result.status = false;
			result.data = "fail";
			// status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@RequestBody User user) {
		logger.info(">>>>>>>>>>>> 가입하기  <<<<<<<<<<" + new Date());

		String salttext = SHA256Util.generateSalt();
		user.setUser_salt(salttext);

		String pwd = user.getUser_pwd();
		pwd = SHA256Util.getEncrypt(pwd, salttext);
		user.setUser_pwd(pwd);

		userService.signup(user);

		// flag_type
		// auth : 회원가입 인증메일
		// password : 비밀번호 변경 인증메일

		// 인증번호 생성
		String confirmCode = SHA256Util.getCertificationCode();

		// DB에 저장
		user.setUser_certification_code(confirmCode);
		userService.saveCertificationCode(user);

		// aboutMe 테이블에도 회원가입시 입력한 내용 저장
		if (user.getUser_auth() == 1 || user.getUser_auth() == 2) {
			User filluser = userService.getUserseq(user);
			userService.fillaboutme(filluser);
		}

		if (user.getUser_auth() == 1) {
			User filluser = userService.getUserseq(user);
			userService.fillportfolio(filluser);
		}

		// 인증메일발송
		String userEmail = user.getUser_email();
		String subject = "꽃구름 SNS 아이디 인증메일입니다.";
		String mag = null;
		mag = new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<p>(크롬에서 열어주세요.)</p>").append("<a href=\'" + IP + "/mail/").append(confirmCode).append('/')
				.append(userEmail).append("/auth").append("\' target='_blenk'>이메일 인증 확인</a>").toString();
		try {
			MailUtil.sendMail(userEmail, subject, mag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/account/{user_email}/resend-signup-link")
	@ApiOperation(value = "가입인증 이메일 재전송")
	public Object re_send_link(@PathVariable String user_email) { // @RequestBody User user
		logger.info(">>>>>>>>>>>> 가입인증 이메일 재전송  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_email(user_email);

		// 인증번호 생성
		String confirmCode = SHA256Util.getCertificationCode();

		// DB에 저장
		user.setUser_certification_code(confirmCode);
		userService.saveCertificationCode(user);

		// 인증메일발송
		String userEmail = user.getUser_email();
		String subject = "꽃구름 SNS 아이디 인증메일입니다.";
		String mag = null;
		mag = new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<p>(크롬에서 열어주세요.)</p>").append("<a href=\\'\" + IP + \"/mail/").append(confirmCode)
				.append('/').append(userEmail).append("/auth").append("\' target='_blenk'>이메일 인증 확인</a>").toString();
		try {
			MailUtil.sendMail(userEmail, subject, mag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping("/account/{user_email}/email-duplicate")
	@ApiOperation(value = "이메일 중복체크")
	public Object EmailDuplicateCheck(@PathVariable String user_email) {// @RequestBody User user
		logger.info(">>>>>>>>>>>> 이메일 중복체크  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_email(user_email);

		int checkResult = userService.EmailDuplicateCheck(user);

		final BasicResponse result = new BasicResponse();
		if (checkResult == 0) {
			result.status = true;
			result.data = "success";
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@GetMapping("/account/{user_nickname}/nickname-duplicate")
	@ApiOperation(value = "닉네임 중복체크")
	public Object NicknameDuplicateCheck(@PathVariable String user_nickname) { // @RequestBody User user
		logger.info(">>>>>>>>>>>> 닉네임 중복체크  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_nickname(user_nickname);

		int checkResult = userService.NicknameDuplicateCheck(user);
		final BasicResponse result = new BasicResponse();
		if (checkResult == 0) {
			result.status = true;
			result.data = "success";
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@PostMapping("/account/{user_email}/pwd-link")
	@ApiOperation(value = "비밀번호 변경링크 전송")
	public Object update_pwd_link(@PathVariable String user_email) { // @RequestBody User user
		logger.info(">>>>>>>>>>>> 비밀번호 변경링크 전송  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_email(user_email);
		// 이메일있는 사용자인지 확인필요
		int checkResult = userService.EmailDuplicateCheck(user);

		if (checkResult == 0) {
			final BasicResponse result = new BasicResponse();
			result.status = false;
			// result.data = "fail";
			result.data = "가입되지 않은 이메일입니다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		// 인증번호 생성
		String confirmCode = SHA256Util.getCertificationCode();

		// DB에 저장
		user.setUser_certification_code(confirmCode);
		userService.saveCertificationCode(user);

		// 인증메일발송
		String userEmail = user.getUser_email();
		String subject = "꽃구름 SNS 비밀번호 변경 메일입니다.";
		String mag = null;
		mag = new StringBuffer().append("<h1>[비밀번호 변경 이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 비밀번호 변경링크로 이동합니다.</p>")
				.append("<p>(크롬에서 열어주세요.)</p>").append("<a href=\'" + IP + "/mail/").append(confirmCode).append('/')
				.append(userEmail).append("/password").append("'' target='_blenk'>이메일 인증 확인</a>").toString();

		try {
			MailUtil.sendMail(userEmail, subject, mag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/account/{user_email}/resend-pwd-link")
	@ApiOperation(value = "비밀번호 변경 이메일 재전송")
	public Object resend_pwd_link(@PathVariable String user_email) { // @RequestBody User user
		logger.info(">>>>>>>>>>>> 비밀번호 변경 이메일 재전송  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_email(user_email);
		// 이메일있는 사용자인지 확인필요
		int checkResult = userService.EmailDuplicateCheck(user);

		if (checkResult == 0) {
			final BasicResponse result = new BasicResponse();
			result.status = false;
			// result.data = "fail";
			result.data = "가입되지 않은 이메일입니다.";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		// 인증번호 생성
		String confirmCode = SHA256Util.getCertificationCode();

		// DB에 저장
		user.setUser_certification_code(confirmCode);
		userService.saveCertificationCode(user);

		// 인증메일발송
		String userEmail = user.getUser_email();
		String subject = "꽃구름 SNS 비밀번호 변경 메일입니다.";
		String mag = null;
		mag = new StringBuffer().append("<h1>[비밀번호 변경 이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 비밀번호 변경링크로 이동합니다.</p>")
				.append("<p>(크롬에서 열어주세요.)</p>").append("<a href=\'" + IP + "/mail/").append(confirmCode).append('/')
				.append(userEmail).append("/password").append("'' target='_blenk'>이메일 인증 확인</a>").toString();

		try {
			MailUtil.sendMail(userEmail, subject, mag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	// @PutMapping("/account/{user_email}/pwd")
	@PostMapping("/account/{user_email}/pwd") // 수정필요
	@ApiOperation(value = "비밀번호 변경하기")
	public Object update_pwd(@PathVariable String user_email, @RequestParam("user_pwd") String user_pwd,
			@RequestParam("user_certification_code") String user_certification_code) {
		logger.info(">>>>>>>>>>>> 비밀번호 변경하기  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_email(user_email);
		user.setUser_pwd(user_pwd);
		user.setUser_certification_code(user_certification_code);

		// String salttext = SHA256Util.generateSalt();
		String salttext = userService.getSalt(user.getUser_email());
		user.setUser_salt(salttext);

		String pwd = user.getUser_pwd();
		pwd = SHA256Util.getEncrypt(pwd, salttext);
		user.setUser_pwd(pwd);

		userService.update_pwd(user);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/account/{user_email}/signup-certification-code-check")
	@ApiOperation(value = "인증코드확인")
	public Object signup_Certification_Code_Check(@PathVariable String user_email,
			@RequestParam("user_certification_code") String user_certification_code) { // @RequestBody User user
		logger.info(">>>>>>>>>>>> 인증코드확인  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_email(user_email);
		user.setUser_certification_code(user_certification_code);

		int deadline_result = userService.Certification_Code_deadline(user);
		final BasicResponse result = new BasicResponse();
		if (deadline_result <= 30) {
			int checkResult = userService.Certification_Code_Check(user);

			if (checkResult == 1) {
				result.status = true;
				result.data = "success";
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				result.status = false;
				result.data = "fail";
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} else {
			result.status = false;
			result.data = "인증시간 만료";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@GetMapping("/account/{user_email}/pwd-certification-code-check")
	@ApiOperation(value = "비밀번호 변경 인증코드확인")
	public Object Certification_Code_Check(@PathVariable String user_email,
			@RequestParam("user_certification_code") String user_certification_code) { // @RequestBody User user
		logger.info(">>>>>>>>>>>> 비밀번호 변경 인증코드확인  <<<<<<<<<<" + new Date());

		User user = new User();
		user.setUser_email(user_email);
		user.setUser_certification_code(user_certification_code);

		int deadline_result = userService.Certification_Code_deadline(user);
		final BasicResponse result = new BasicResponse();

		if (deadline_result <= 30) {
			int checkResult = userService.Certification_Code_Check(user);

			if (checkResult == 1) {
				result.status = true;
				result.data = "success";
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				result.status = false;
				result.data = "fail";
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} else {
			result.status = false;
			result.data = "인증시간 만료";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@PostMapping("/account/sns-signup")
	@ApiOperation(value = "sns계정으로 가입하기")
	public Object SNSsignup(@RequestBody User user) {
		logger.info(">>>>>>>>>>>> sns계정으로 가입하기  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();
		try {

			if (user.getLogin_type().equals("google")) { // 이름 이메일주소 프로필사진
				String email = user.getUser_email();
				int idx = email.indexOf("@");
				String sns_account = email.substring(0, idx);
				user.setUser_nickname(sns_account);
				user.setUser_name(sns_account);
			}
			if (user.getLogin_type().equals("github")) { // 이메일
				String email = user.getUser_email();
				int idx = email.indexOf("@");
				String sns_account = email.substring(0, idx);
				user.setUser_nickname(sns_account);
				user.setUser_name(sns_account);
			}

			user.setUser_pwd("-");
			user.setUser_email_certification("Y");
			user.setUser_salt("-");

			userService.SNSsignup(user);

			// aboutMe 테이블에도 회원가입시 입력한 내용 저장
			if (user.getUser_auth() == 1 || user.getUser_auth() == 2) {
				User filluser = userService.getUserseq(user);
				userService.fillaboutme(filluser);
			}

			if (user.getUser_auth() == 1) {
				User filluser = userService.getUserseq(user);
				userService.fillportfolio(filluser);
			}

			result.status = true;
			result.data = "success";
			
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			
			result.status = false;
			result.data = "오류로 검색되지 않음";

			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@GetMapping("/account/{user_email}/snsUserCheck")
	@ApiOperation(value = "sns계정으로 로그인 시 회원가입된 유저인지 확인")
	public Object snsUserCheck(@PathVariable String user_email, @RequestParam("login_type") String login_type) { // @RequestBody
																													// User
																													// user
		// @RequestParam("user_auth") String user_auth
		logger.info(">>>>>>>>>>>> sns계정으로 로그인 시 회원가입된 유저인지 확인  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();
		User user = new User();
		user.setUser_email(user_email);
		user.setLogin_type(login_type);
		// user.setUser_auth(Integer.parseInt(user_auth));
		try {

			if (user.getLogin_type().equals("google")) { // 이름 이메일주소 프로필사진
				String email = user.getUser_email();
				int idx = email.indexOf("@");
				String sns_account = email.substring(0, idx);
			}
			if (user.getLogin_type().equals("github")) { // 이메일
				String email = user.getUser_email();
				int idx = email.indexOf("@");
				String sns_account = email.substring(0, idx);
				user.setUser_nickname(sns_account);
				user.setUser_name(sns_account);
			}

			int isMember = userService.snsUserCheck(user);

			if (isMember == 1) {
				result.status = true;
				result.data = "sns계정으로 가입된 유저";
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				result.status = false;
				result.data = "sns계정으로 가입되지 않은 유저";
				return new ResponseEntity<>(result, HttpStatus.OK);
			}

		} catch (Exception e) {
			result.status = false;
			result.data = "오류로 검색되지 않음";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@GetMapping("/info") // 요청이 들어올때 마다 새로은 키를 발급하자. 새로 발행 할 때마다 30분 . 준호님 요청 2020.02.04
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) { // @RequestBody User user
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			// 사용자에게 전달할 정보이다.
			// 보너스로 토큰에 담긴 정보도 전달해보자. 서버에서 토큰을 사용하는 방법임을 알 수 있다.
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			System.out.println(e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/account/findAll_user")
	@ApiOperation(value = "전체 사용자 목록")
	public Object findAll_user() { // @RequestBody User user
		logger.info(">>>>>>>>>>>> 전체 사용자 목록  <<<<<<<<<<" + new Date());

		List<User> user_list = userService.findAll_user();

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

}