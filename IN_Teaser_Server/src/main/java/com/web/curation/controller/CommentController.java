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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Comment;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.User;
import com.web.curation.service.ICommentService;

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
public class CommentController {
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private ICommentService commentService;
	
	
	@PostMapping("/comment/{user_seq}")
	@ApiOperation(value = "댓글등록")
	public Object insertComment(@PathVariable int user_seq , @RequestBody Comment comment ) { 
		logger.info(">>>>>>>>>>>> 댓글등록  <<<<<<<<<<" + new Date());

		comment.setUser_seq(user_seq);
		
		commentService.insertComment(comment);
		commentService.updateGroupid(comment);
		final BasicResponse result = new BasicResponse();
		
		result.status = true;
		result.data = "success";
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
	@PostMapping("/recomment/{user_seq}")
	@ApiOperation(value = "대댓글 등록")
	public Object insertreComment(@PathVariable int user_seq , @RequestBody Comment comment ) { 
		logger.info(">>>>>>>>>>>> 대댓글 등록  <<<<<<<<<<" + new Date());

		comment.setUser_seq(user_seq);
		
		commentService.insertreComment(comment);
		
		final BasicResponse result = new BasicResponse();
		
		result.status = true;
		result.data = "success";
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@DeleteMapping("/comment/{user_seq}/{comment_seq}")
	@ApiOperation(value = "댓글삭제")
	public Object deleteComment(@PathVariable int user_seq , @PathVariable int comment_seq ) { 
		logger.info(">>>>>>>>>>>> 댓글삭제 <<<<<<<<<<" + new Date());

		Comment comment = new Comment();
		comment.setUser_seq(user_seq);
		comment.setComment_seq(comment_seq);
		
		commentService.deleteComment(comment);
		
		final BasicResponse result = new BasicResponse();
		
		result.status = true;
		result.data = "success";		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
	
	@DeleteMapping("/recomment/{user_seq}/{comment_seq}")
	@ApiOperation(value = "대댓글삭제")
	public Object deletereComment(@PathVariable int user_seq , @PathVariable int comment_seq ) { 
		logger.info(">>>>>>>>>>>> 대댓글삭제 <<<<<<<<<<" + new Date());

		Comment comment = new Comment();
		comment.setUser_seq(user_seq);
		comment.setComment_seq(comment_seq);
		
		commentService.deletereComment(comment);
		
		final BasicResponse result = new BasicResponse();
		
		result.status = true;
		result.data = "success";		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
	
	@GetMapping("/feed/{feed_seq}/comment-list")
	@ApiOperation(value = "피드 댓글목록")
	public Object detailFeed( @PathVariable int feed_seq) {
		logger.info(">>>>>>>>>>>> 피드 댓글목록  <<<<<<<<<<" + new Date());

		final BasicResponse result = new BasicResponse();
		
		try {
			// 피드 상세
			Feed feed = new Feed();
			feed.setFeed_seq(feed_seq);
			
			// 댓글
			List<Comment> commentResult = new ArrayList<Comment>();
			commentResult = commentService.feedComment(feed);

			result.status = true;
			result.data = "success";
			result.object = commentResult;
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.status = false;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}
}
