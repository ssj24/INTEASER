package com.web.curation.testcontroller;

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


import com.web.curation.controller.AccountController;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.SearchKeyword;
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
public class testController {
	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private IUserService userService;
	

			

	@PostMapping("/test/post1saveCertificationCode")
	@ApiOperation(value = "인증코드생성")
	public Object PostsaveCertificationCode( SearchKeyword SearchKeyword) {//@RequestBody User user
		logger.info(">>>>>>>>>>>> 인증코드생성  <<<<<<<<<<" + new Date());
		
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/test/post2saveCertificationCode/{user_email}")
	@ApiOperation(value = "인증코드생성")
	public Object PostsaveCertificationCode(@PathVariable String user_email , SearchKeyword SearchKeyword) {//@RequestBody User user
		logger.info(">>>>>>>>>>>> 인증코드생성  <<<<<<<<<<" + new Date());
		
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@GetMapping("/test/getsaveCertificationCode/{user_email}")
	@ApiOperation(value = "인증코드생성")
	public Object getsaveCertificationCode(SearchKeyword SearchKeyword) {//@RequestBody User user
		logger.info(">>>>>>>>>>>> 인증코드생성  <<<<<<<<<<" + new Date());
		
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@PutMapping("/test/putsaveCertificationCode/{user_email}")
	@ApiOperation(value = "인증코드생성")
	public Object putsaveCertificationCode(SearchKeyword SearchKeyword) {//@RequestBody User user
		logger.info(">>>>>>>>>>>> 인증코드생성  <<<<<<<<<<" + new Date());
		
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/test/deletesaveCertificationCode/{user_email}")
	@ApiOperation(value = "인증코드생성")
	public Object deletesaveCertificationCode(SearchKeyword SearchKeyword) {//@RequestBody User user
		logger.info(">>>>>>>>>>>> 인증코드생성  <<<<<<<<<<" + new Date());
		
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}

}
