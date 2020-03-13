package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.dao.CommentDaoImpl;
import com.web.curation.dao.NoticeDaoImpl;
import com.web.curation.model.Comment;
import com.web.curation.model.Communication;
import com.web.curation.model.Feed;
import com.web.curation.model.User;
@Transactional
@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentDaoImpl commentdao;

	@Autowired
	private NoticeDaoImpl noticedao;

	@Override
	public void insertComment(Comment comment) {
		commentdao.insertComment(comment);
		
		if (commentdao.compare12(comment))
			return;
		int user_auth = noticedao.getuserauth(comment.getUser_seq());
		String temp = "";
		if (user_auth == 2)
			temp = "채용담당자<b>" + comment.getUser_nickname() + "</b>님이 회원님의 글에 댓글을 남겼습니다.";
		else
			temp = "<b>" + comment.getUser_nickname() + "</b>님이 회원님의 글에 댓글을 남겼습니다.";

		Communication comm = new Communication(comment.getFeed_seq(), comment.getUser_nickname(), user_auth, temp);
		noticedao.pushnoticebyfeed(comm);
	}

	@Override
	public void insertreComment(Comment comment) {
		commentdao.insertreComment(comment);

		int user_auth = noticedao.getuserauth(comment.getUser_seq());
		String temp = "";
		if (user_auth == 2)
			temp = "채용담당자<b>" + comment.getUser_nickname() + "</b>님이 회원님의 글에 댓글을 남겼습니다.";
		else
			temp = "<b>" + comment.getUser_nickname() + "</b>님이 회원님의 글에 댓글을 남겼습니다.";

		// 작성자에게 알림
		Communication comm1 = new Communication(comment.getFeed_seq(), comment.getUser_nickname(), user_auth, temp);
		// 댓글쓴이에게
		Communication comm2 = new Communication(comment.getGroup_id(), comment.getUser_nickname(), user_auth, temp);

		if (commentdao.recompare12(comment)) {
			if (commentdao.compare23(comment)) {
				return;
			} else {
				// 1한테만 push
				noticedao.pushnoticebyfeed(comm1);
			}
		} else {
			if (commentdao.compare12(comment)) {
				// 2한테만 push
				noticedao.pushnoticebycomment(comm2);
			} else if (commentdao.compare23(comment)) {
				// 1한테만 push
				noticedao.pushnoticebyfeed(comm1);
			} else {
				// 둘다한테 push
				noticedao.pushnoticebyfeed(comm1);
				noticedao.pushnoticebycomment(comm2);
			}
		}
	}

	@Override
	public void deleteComment(Comment comment) {
		commentdao.deleteComment(comment);

	}

	@Override
	public void deletereComment(Comment comment) {
		commentdao.deletereComment(comment);
	}

	@Override
	public List<Comment> feedComment(Feed feed) {
		return commentdao.feedComment(feed);
	}

	@Override
	public void updateGroupid(Comment comment) {
		commentdao.updateGroupid(comment);

	}

	@Override
	public List<Comment> feedComment2(Feed feed) {
		return commentdao.feedComment2(feed);
	}

	@Override
	public int feedCommentTotalCount(Feed feed) {
		return commentdao.feedCommentTotalCount(feed);
	}

}
