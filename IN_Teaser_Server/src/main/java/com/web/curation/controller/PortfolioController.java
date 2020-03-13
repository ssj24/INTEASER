package com.web.curation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
import com.web.curation.model.FeedLike;
import com.web.curation.model.Portfolio;
import com.web.curation.model.Project;
import com.web.curation.model.ProjectDetail;
import com.web.curation.model.ProjectLike;
import com.web.curation.model.UserLink;
import com.web.curation.service.IPortfolioService;

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
public class PortfolioController {
	public static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

	@Autowired
	private IPortfolioService portfolioService;

	@GetMapping("/portfolio-info/{user_seq}")
	@ApiOperation(value = "전체 포트폴리오 (프로젝트) 노출 여부")
	public Object portfolioInfo(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 전체 포트폴리오 (프로젝트) 노출 여부  <<<<<<<<<<" + new Date());

		Portfolio portfolio = new Portfolio();

		portfolio.setUser_seq(user_seq);

		Portfolio portfolioResult = portfolioService.portfolioInfo(portfolio);
		final BasicResponse result = new BasicResponse();

		if (portfolioResult != null) {
			result.status = true;
			result.data = "success";
			result.object = portfolioResult;
		} else {
			result.status = false;
			result.data = "fail";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PutMapping("/portfolio/{user_seq}")
	@ApiOperation(value = "전체 포트폴리오 (프로젝트) 노출 여부 설정")
	public Object insertPortfolio(@PathVariable int user_seq, @RequestBody Portfolio portfolio) {
		logger.info(">>>>>>>>>>>> 전체 포트폴리오 (프로젝트) 노출 여부 설정  <<<<<<<<<<" + new Date());

		portfolio.setUser_seq(user_seq);
		portfolio.setPortfolio_onoff(portfolio.isPortfolio_onoff());

		portfolioService.updateonoff(portfolio);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping("/link/{user_seq}")
	@ApiOperation(value = "링크 목록 조회")
	public Object userLink(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 링크 목록 조회 <<<<<<<<<<" + new Date());

		UserLink userlink = new UserLink();

		userlink.setUser_seq(user_seq);

		List<UserLink> linkResult = new ArrayList<UserLink>();
		linkResult = portfolioService.selectUserlink(userlink);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";
		result.object = linkResult;

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/link/{user_seq}")
	@ApiOperation(value = "새 링크 등록")
	public Object insertUserlink(@PathVariable int user_seq, @RequestBody UserLink userlink) {
		logger.info(">>>>>>>>>>>> 새 링크 등록  <<<<<<<<<<" + new Date());

		userlink.setUser_seq(user_seq);

		portfolioService.insertUserlink(userlink);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PutMapping("/link/{user_seq}/{userlink_seq}")
	@ApiOperation(value = "링크 수정")
	public Object updateUserlink(@PathVariable int user_seq, @PathVariable int userlink_seq,
			@RequestBody UserLink userlink) {
		logger.info(">>>>>>>>>>>> 링크 수정  <<<<<<<<<<" + new Date());

		userlink.setUser_seq(user_seq);
		userlink.setUserlink_seq(userlink_seq);

		portfolioService.updateUserlink(userlink);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@DeleteMapping("/link/{user_seq}/{userlink_seq}")
	@ApiOperation(value = "링크 삭제")
	public Object deleteUserlink(@PathVariable int user_seq, @PathVariable int userlink_seq) {
		logger.info(">>>>>>>>>>>> 링크 삭제 <<<<<<<<<<" + new Date());

		UserLink userlink = new UserLink();

		userlink.setUser_seq(user_seq);
		userlink.setUserlink_seq(userlink_seq);

		portfolioService.deleteUserlink(userlink);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/project/{user_seq}/step1")
	@ApiOperation(value = "새 프로젝트 등록")
	public Object insertProject1(@PathVariable int user_seq, @RequestBody Project project) {
		logger.info(">>>>>>>>>>>> 새 프로젝트 1차 등록  <<<<<<<<<<" + new Date());

		project.setUser_seq(user_seq);

		portfolioService.insertProject1(project);
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";
		result.object = project;

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PutMapping("/project/{user_seq}/step2/{project_seq}")
	@ApiOperation(value = "새 프로젝트 2차 등록")
	public Object insertProject2(@PathVariable int user_seq, @PathVariable int project_seq,
			@RequestBody Project project) {
		logger.info(">>>>>>>>>>>> 새 프로젝트 2차 등록   <<<<<<<<<<" + new Date());

		project.setUser_seq(user_seq);
		project.setProject_seq(project_seq);
		if (project.getProject_dev_setting() != null || project.getProject_dev_setting() != "") {
			String replace_characters = project.getProject_dev_setting().replaceAll("c++", "cplusplus");
			replace_characters = project.getProject_dev_setting().replaceAll("c#", "cshop");
			replace_characters = project.getProject_dev_setting().replaceAll("c", "ccccc");
			replace_characters = project.getProject_dev_setting().replaceAll("r", "rrrrr");
			project.setProject_dev_setting(replace_characters);
		}

		portfolioService.insertProject2(project);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PutMapping("/project/{user_seq}/{project_seq}")
	@ApiOperation(value = "프로젝트 수정")
	public Object updateProject(@PathVariable int user_seq, @PathVariable int project_seq,
			@RequestBody Project project) {
		logger.info(">>>>>>>>>>>> 프로젝트 수정   <<<<<<<<<<" + new Date());

		project.setUser_seq(user_seq);
		project.setProject_seq(project_seq);

		if (project.getProject_dev_setting() != null || project.getProject_dev_setting() != "") {
			String replace_characters = project.getProject_dev_setting().replaceAll("c++", "cplusplus");
			replace_characters = project.getProject_dev_setting().replaceAll("c#", "cshop");
			replace_characters = project.getProject_dev_setting().replaceAll("c", "ccccc");
			replace_characters = project.getProject_dev_setting().replaceAll("r", "rrrrr");
			project.setProject_dev_setting(replace_characters);
		}
		portfolioService.updateProject(project);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@DeleteMapping("/project/{user_seq}/{project_seq}")
	@ApiOperation(value = "프로젝트 삭제")
	public Object deleteProject(@PathVariable int user_seq, @PathVariable int project_seq) {
		logger.info(">>>>>>>>>>>> 프로젝트 수정   <<<<<<<<<<" + new Date());
		Project project = new Project();
		project.setUser_seq(user_seq);
		project.setProject_seq(project_seq);

		portfolioService.deleteProject(project);

		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping("/project/{user_seq}/{project_seq}")
	@ApiOperation(value = "프로젝트 상세")
	public Object detailProject(@PathVariable int user_seq, @PathVariable int project_seq) {
		logger.info(">>>>>>>>>>>> 프로젝트 상세   <<<<<<<<<<" + new Date());
		Project project = new Project();
		project.setUser_seq(user_seq);
		project.setProject_seq(project_seq);
		ProjectDetail projectResult = portfolioService.detailProject(project);
		if (project.getProject_dev_setting() != "") {
			String replace_characters = projectResult.getProject_dev_setting().replaceAll("cplusplus", "c++");
			replace_characters = replace_characters.replaceAll("cshop", "c#");
			replace_characters = replace_characters.replaceAll("ccccc", "c");
			replace_characters = replace_characters.replaceAll("rrrrr", "r");
			projectResult.setProject_dev_setting(replace_characters);
		}
		try {
			ProjectLike usercheck = new ProjectLike();
			usercheck.setUser_seq(user_seq);
			usercheck.setProject_seq(project_seq);
			ProjectLike porjectlike = portfolioService.loginUserLike(usercheck);
			if (porjectlike != null) {
				projectResult.setLogin_user_like(true);
				projectResult.setProject_like_seq(porjectlike.getProject_like_seq());
			} else {
				projectResult.setLogin_user_like(false);
			}
		} catch (Exception e) {
			projectResult.setLogin_user_like(false);
		}
		
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = projectResult;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/project/{user_seq}")
	@ApiOperation(value = "프로젝트 목록")
	public Object projectList(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 프로젝트 목록 <<<<<<<<<<" + new Date());
		Project project = new Project();
		project.setUser_seq(user_seq);
		List<Project> projectList = new ArrayList<Project>();
		projectList = portfolioService.projectList(project);

		for (int i = 0; i < projectList.size(); i++) {
			Project project2 = projectList.get(i);

			String tempdev_setting = projectList.get(i).getProject_dev_setting();
			if (tempdev_setting != null) {
				tempdev_setting = tempdev_setting.replaceAll("plusplus", "++");
				tempdev_setting = tempdev_setting.replaceAll("shop", "#");
				tempdev_setting = tempdev_setting.replaceAll("ccccc", "c");
				tempdev_setting = tempdev_setting.replaceAll("rrrrr", "r");
				project2.setProject_dev_setting(tempdev_setting);

			}

		}

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";
		result.object = projectList;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/project/{user_seq}/like/{project_seq}")
	@ApiOperation(value = "프로젝트 좋아요")
	public Object projectLike(@PathVariable int user_seq, @PathVariable int project_seq,
			@RequestParam("user_nickname") String user_nickname) {
		logger.info(">>>>>>>>>>>> 프로젝트 좋아요  <<<<<<<<<<" + new Date());

		ProjectLike projectLike = new ProjectLike();
		projectLike.setUser_seq(user_seq);
		projectLike.setUser_nickname(user_nickname);
		projectLike.setProject_seq(project_seq);

		portfolioService.projectLike(projectLike);

		Project project = new Project();
		project.setProject_seq(project_seq);
		portfolioService.projectLikeAdd(project);
		
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";
		result.object = projectLike;

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@DeleteMapping("/project/{user_seq}/like-cancel/{projectlike_seq}")
	@ApiOperation(value = "프로젝트 좋아요 취소")
	public Object projectLike_cancel(@PathVariable int user_seq, @PathVariable int projectlike_seq , @RequestBody ProjectLike projectlike) {
		logger.info(">>>>>>>>>>>> 프로젝트 좋아요 취소  <<<<<<<<<<" + new Date());

		ProjectLike projectLike = new ProjectLike();
		projectLike.setUser_seq(user_seq);
		projectLike.setProject_like_seq(projectlike_seq);
		projectLike.setProject_seq(projectlike.getProject_seq());
		
		portfolioService.projectLikeCancel(projectLike);
		
		Project project = new Project();
		project.setProject_seq(projectLike.getProject_seq());
		portfolioService.projectLikeSubtract(project);
		
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/project/{user_seq}/search-dev-setting")
	@ApiOperation(value = "프로젝트 기술 스택")
	public Object projectSearchDevSetting(@PathVariable int user_seq) {
		logger.info(">>>>>>>>>>>> 프로젝트 기술 스택  <<<<<<<<<<" + new Date());
		Project project = new Project();
		project.setUser_seq(user_seq);

		List<Project> projectResult = new ArrayList<Project>();
		projectResult = portfolioService.projectDevSettingList(user_seq);

		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < projectResult.size(); i++) {
			String tempdev_setting = projectResult.get(i).getProject_dev_setting();
			String[] tempdev_settingArray = tempdev_setting.trim().split(",");
			for (int j = 0; j < tempdev_settingArray.length; j++) {
				String dev_set = tempdev_settingArray[j];

				if (dev_set.contains("ccccc")) {
					dev_set = dev_set.replaceAll("ccccc", "c");
				}
				if (dev_set.contains("cplusplus")) {
					dev_set = dev_set.replaceAll("cplusplus", "c++");
				}
				if (dev_set.contains("cshop")) {
					dev_set = dev_set.replaceAll("cshop", "c#");
				}
				if (dev_set.contains("rrrrr")) {
					dev_set = dev_set.replaceAll("rrrrr", "r");
				}

				if (map.containsKey(dev_set)) {
					int count = map.get(dev_set);
					map.put(dev_set, count + 1);
				} else {
					map.put(dev_set, 1);
				}

			}
		}

		Map<String, Integer> test = Common.sortByValue(map);
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";
		result.object = test;

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/project/{user_seq}/search")
	@ApiOperation(value = "프로젝트 검색") // ,@RequestParam(value = "technical_stack", required = false) List<String>
										// technical_stack
	public Object projectSearch(@PathVariable int user_seq, @RequestBody Project project) {
		logger.info(">>>>>>>>>>>> 프로젝트 검색 <<<<<<<<<<" + new Date());

		project.setUser_seq(user_seq);
		if (project.getProject_dev_setting() != "") {
			String tempdev_setting = project.getProject_dev_setting();
			tempdev_setting = tempdev_setting.replaceAll("[+][+]", "plusplus");
			tempdev_setting = tempdev_setting.replaceAll("[#]", "shop");
			tempdev_setting = tempdev_setting.replaceAll("c", "ccccc");
			tempdev_setting = tempdev_setting.replaceAll("r", "rrrrr");

			// String tempdev_setting2 = tempdev_setting.substring(1,
			// tempdev_setting.length() - 1);
			String tempdev_settingArray = "+" + tempdev_setting.replaceAll(",", "+");
			project.setProject_dev_setting(tempdev_settingArray);
		}
		List<ProjectDetail> projectResult = new ArrayList<ProjectDetail>();
		projectResult = portfolioService.projectSearch(project);
		for (int i = 0; i < projectResult.size(); i++) {
			ProjectDetail project2 = projectResult.get(i);

			String tempdev_setting = projectResult.get(i).getProject_dev_setting();
			if (tempdev_setting != null) {
				tempdev_setting = tempdev_setting.replaceAll("plusplus", "++");
				tempdev_setting = tempdev_setting.replaceAll("shop", "#");
				tempdev_setting = tempdev_setting.replaceAll("ccccc", "c");
				tempdev_setting = tempdev_setting.replaceAll("rrrrr", "r");
				project2.setProject_dev_setting(tempdev_setting);
			}
			
			//사용자 프로필 검색
			int userSeq = projectResult.get(i).getUser_seq();
			String userProfileRoute = portfolioService.getuserProfileRoute(userSeq);
			project2.setUser_profile_route(userProfileRoute);
			
			try {
				ProjectLike usercheck = new ProjectLike();
				usercheck.setUser_seq(user_seq);
				usercheck.setProject_seq(projectResult.get(i).getProject_seq());
				ProjectLike porjectlike = portfolioService.loginUserLike(usercheck);
				if (porjectlike != null) {
					project2.setLogin_user_like(true);
					project2.setProject_like_seq(porjectlike.getProject_like_seq());
				} else {
					project2.setLogin_user_like(false);
				}
			} catch (Exception e) {
				project2.setLogin_user_like(false);
			}

		}
		System.out.println("projectResult : " + projectResult);
		final BasicResponse result = new BasicResponse();

		result.status = true;
		result.data = "success";
		result.object = projectResult;

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
