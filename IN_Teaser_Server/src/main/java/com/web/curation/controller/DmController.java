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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.DM;
import com.web.curation.model.DmList;
import com.web.curation.service.IDmService;

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
public class DmController {
	public static final Logger logger = LoggerFactory.getLogger(DmController.class);

	@Autowired
	private IDmService dmService;

	@GetMapping("/users/{user_seq}/dm")
	@ApiOperation(value = "dm 하러가기")
	public Object readdm(@PathVariable int user_seq, @RequestParam("opponent_seq") int opponent_seq) {
		logger.info(">>>>>>>>>>>> dm 하러가기  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();
		DM dm = new DM(user_seq, opponent_seq);

		List<DM> comm = dmService.readdm(dm);
		
		result.object = comm;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/users/{user_seq}/dm")
	@ApiOperation(value = "dm 작성하기")
	public Object savedm(@PathVariable int user_seq, @RequestBody DM dm) {
		logger.info(">>>>>>>>>>>> dm 작성하기  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();
		dm.setUser_seq(user_seq);

		dmService.savedm(dm);

		result.status = true;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/users/{user_seq}/dm-time")
	@ApiOperation(value = "dm나가기")
	public Object closedm(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> dm나가기  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();

		dmService.closetime(user_seq);

		result.status = true;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{user_seq}/dm-list")
	@ApiOperation(value = "dm목록보기")
	public Object dmlist(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> dm목록보기  <<<<<<<<<<" + new Date());
		final BasicResponse result = new BasicResponse();

		List<DmList> list = dmService.dmlist(user_seq);

		result.object = list;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}