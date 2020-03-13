package com.web.curation.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.BasicResponse;
import com.web.curation.service.IFileUploadService;

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
public class FileUploadController {
	public static final Logger logger = LoggerFactory.getLogger(FollowController.class);

	@Autowired
	private IFileUploadService fileUploadService;

//	@PostMapping("/users/{user_seq}/upload")
//	@ApiOperation(value = "프로필 사진 업로드")
//	public Object profileupload(@PathVariable int user_seq, @RequestBody MultipartFile file) {
//		logger.info(">>>>>>>>>>>> 프로필 사진 업로드" + new Date());
//
//		String url = fileUploadService.restore(user_seq, file);
//		final BasicResponse result = new BasicResponse();
//
//		result.status = true;
//		result.data = "success";
//		result.object = url;
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
	
	@PostMapping("/feed/{user_seq}/upload")
	@ApiOperation(value = "파일업로드")
	public Object upload(@PathVariable int user_seq, @RequestBody MultipartFile file) {
		logger.info(">>>>>>>>>>>> 게시글 > 이미지 업로드" + new Date());

		String url = fileUploadService.restorefile(file);
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";
		result.object = url;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}