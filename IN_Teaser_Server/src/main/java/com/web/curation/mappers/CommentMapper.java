package com.web.curation.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Comment;
import com.web.curation.model.Feed;

@Mapper
public interface CommentMapper {

	public void insertComment(Comment comment);

	public void insertreComment(Comment comment);

	public void deleteComment(Comment comment);

	public void deletereComment(Comment comment);

	public List<Comment> feedComment(Feed feed);

	public void updateGroupid(Comment comment);

	public List<Comment> feedComment2(Feed feed);

	public int feedCommentTotalCount(Feed feed);

	public boolean compare12(Comment comment);

	public boolean compare23(Comment comment);

	public boolean recompare12(Comment comment);

}
