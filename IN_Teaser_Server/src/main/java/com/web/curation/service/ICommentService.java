package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Comment;
import com.web.curation.model.Feed;

public interface ICommentService {

	void insertComment(Comment comment);

	void insertreComment(Comment comment);

	void deleteComment(Comment comment);

	void deletereComment(Comment comment);

	List<Comment> feedComment(Feed feed);

	void updateGroupid(Comment comment);

	List<Comment> feedComment2(Feed feed);

	int feedCommentTotalCount(Feed feed);
	
}
