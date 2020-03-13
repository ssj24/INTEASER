package com.web.curation.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.AdminMapper;
import com.web.curation.model.Communication;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedManagement;
import com.web.curation.model.Posting;
import com.web.curation.model.Reporter;
import com.web.curation.model.UserManagement;

@Repository
public class AdminPageDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	public List<Integer> effectiveseq() {
		return sqlSession.getMapper(AdminMapper.class).effectiveseq();
	}

	public List<Integer> effectiveseqnickname(String user_nickname) {
		return sqlSession.getMapper(AdminMapper.class).effectiveseqnickname(user_nickname);
	}

	public UserManagement searchuser(int user_seq) {
		return sqlSession.getMapper(AdminMapper.class).searchuser(user_seq);
	}

	public int getlikecnt(int user_seq) {
		return sqlSession.getMapper(AdminMapper.class).getlikecnt(user_seq);
	}

	public int getpostcnt(int user_seq) {
		return sqlSession.getMapper(AdminMapper.class).getpostcnt(user_seq);
	}

	public List<Integer> getreported(int user_seq) {
		return sqlSession.getMapper(AdminMapper.class).getreported(user_seq);
	}

	public int getreportcnt(int user_seq) {
		return sqlSession.getMapper(AdminMapper.class).getreportcnt(user_seq);
	}

	public String hasUtility(int user_seq) {
		return sqlSession.getMapper(AdminMapper.class).hasUtility(user_seq);
	}

	public void suspension(int user_seq) {
		sqlSession.getMapper(AdminMapper.class).suspension(user_seq);
	}

	public void activation(int user_seq) {
		sqlSession.getMapper(AdminMapper.class).activation(user_seq);
	}

	public List<Feed> search_all_feed() {
		return sqlSession.getMapper(AdminMapper.class).search_all_feed();
	}

	public List<Feed> search_all_feed(String user_nickname) {
		return sqlSession.getMapper(AdminMapper.class).search_all_feed_by_nickname(user_nickname);
	}

	public List<FeedManagement> search_reported_feed() {
		return sqlSession.getMapper(AdminMapper.class).search_reported_feed();
	}

	public List<FeedManagement> search_reported_feed(String user_nickname) {
		return sqlSession.getMapper(AdminMapper.class).search_reported_feed_by_nickname(user_nickname);
	}

	public List<Integer> effectivenormalseq() {
		return sqlSession.getMapper(AdminMapper.class).effectivenormalseq();
	}

	public List<Integer> effectivenormalseqnickname(String user_nickname) {
		return sqlSession.getMapper(AdminMapper.class).effectivenormalseqnickname(user_nickname);
	}

	public List<Integer> effectiverecruiterseq() {
		return sqlSession.getMapper(AdminMapper.class).effectiverecruiterseq();
	}

	public List<Integer> effectiverecruiterseqnickname(String nickname) {
		return sqlSession.getMapper(AdminMapper.class).effectiverecruiterseqnickname(nickname);
	}

	public List<Reporter> getreporter(int feed_seq) {
		return sqlSession.getMapper(AdminMapper.class).getreporter(feed_seq);
	}

	public boolean isadmin(int user_seq) {
		int user_auth = sqlSession.getMapper(AdminMapper.class).isadmin(user_seq);
		if (user_auth == 0)
			return true;
		else
			return false;
	}

	public void feeddelete(int feed_seq) {
		sqlSession.getMapper(AdminMapper.class).feeddelete(feed_seq);
	}

	public void admincheck(int feed_seq) {
		sqlSession.getMapper(AdminMapper.class).admincheck(feed_seq);
	}

	public int getusers() {
		return sqlSession.getMapper(AdminMapper.class).getusers();
	}

	public int getrecruiters() {
		return sqlSession.getMapper(AdminMapper.class).getrecruiters();
	}

	public Date startposting() {
		return sqlSession.getMapper(AdminMapper.class).poststart();
//		int pjt_start = sqlSession.getMapper(AdminMapper.class).pjtstart();

//		return Math.min(feed_start, pjt_start);
	}

	public int term() {
		return sqlSession.getMapper(AdminMapper.class).term();
	}

	public Posting getpostingcnt(String date) {
		return sqlSession.getMapper(AdminMapper.class).getpostingcnt(date);
	}
	
	public List<Integer> effectiverejectedseq() {
		return sqlSession.getMapper(AdminMapper.class).effectiverejectedseq();
	}

	public List<Integer> effectiverejectedseqnickname(String user_nickname) {
		return sqlSession.getMapper(AdminMapper.class).effectiverejectedseqnickname(user_nickname);
	}

	public void pushnotice(Communication comm) {
		sqlSession.getMapper(AdminMapper.class).pushnotice(comm);
	}

	public int getfeedreportcnt(int feed_seq) {
		return sqlSession.getMapper(AdminMapper.class).getfeedreportcnt(feed_seq);
	}

}
