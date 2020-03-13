package com.web.curation.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.CommentMapper;
import com.web.curation.model.Comment;
import com.web.curation.model.Feed;

@Repository
public class CommentDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	public void insertComment(Comment comment) {
		sqlSession.getMapper(CommentMapper.class).insertComment(comment);
	}

	public void insertreComment(Comment comment) {
		sqlSession.getMapper(CommentMapper.class).insertreComment(comment);
	}

	public void deleteComment(Comment comment) {
		sqlSession.getMapper(CommentMapper.class).deleteComment(comment);
		
	}

	public void deletereComment(Comment comment) {
		sqlSession.getMapper(CommentMapper.class).deletereComment(comment);
		
	}

	public List<Comment> feedComment(Feed feed) {
		return sqlSession.getMapper(CommentMapper.class).feedComment(feed);
	}

	public void updateGroupid(Comment comment) {
		sqlSession.getMapper(CommentMapper.class).updateGroupid(comment);
		
	}

	public List<Comment> feedComment2(Feed feed) {
		return sqlSession.getMapper(CommentMapper.class).feedComment2(feed);
	}

	public int feedCommentTotalCount(Feed feed) {
		return sqlSession.getMapper(CommentMapper.class).feedCommentTotalCount(feed);
	}

	public boolean compare12(Comment comment) {
		return sqlSession.getMapper(CommentMapper.class).compare12(comment);
	}

	public boolean compare23(Comment comment) {
		return sqlSession.getMapper(CommentMapper.class).compare23(comment);

	}

	public boolean recompare12(Comment comment) {
		return sqlSession.getMapper(CommentMapper.class).recompare12(comment);
	}
	
	


}
