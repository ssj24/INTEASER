package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.AccountMapper;
import com.web.curation.model.Feed;
import com.web.curation.model.FollowList;
import com.web.curation.model.Pagination;
import com.web.curation.model.Project;
import com.web.curation.model.UserProfile;

@Repository
public class AboutMeDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	public UserProfile getMyAccount(int user_seq) {
		return sqlSession.getMapper(AccountMapper.class).getMyAccount(user_seq);
	}

	public boolean loginUserLike(Feed feed) {
		int like = sqlSession.getMapper(AccountMapper.class).loginUserLike(feed);
		if (like < 1)
			return false;
		else
			return true;
	}

	public boolean loginUserScrap(Feed feed) {
		int scrap = sqlSession.getMapper(AccountMapper.class).loginUserScrap(feed);
		if (scrap < 1)
			return false;
		else
			return true;
	}

	public List<Feed> uploadfeed(int login_user_seq, Pagination criteria) {
		FollowList user= new FollowList(login_user_seq, criteria.getUser_seq());
		if (login_user_seq == criteria.getUser_seq())
			return sqlSession.getMapper(AccountMapper.class).uploadfeed(criteria);
		else {
			int following = sqlSession.getMapper(AccountMapper.class).isfollowing(user);
			if(following>0)
				return sqlSession.getMapper(AccountMapper.class).uploadfeedbyfriend(criteria);
			else
				return sqlSession.getMapper(AccountMapper.class).uploadfeedbyothers(criteria);
		}
	}

	public List<Feed> likefeed(Pagination criteria) {
		return sqlSession.getMapper(AccountMapper.class).likefeed(criteria);
	}

	public List<Feed> scrapfeed(Pagination criteria) {
		return sqlSession.getMapper(AccountMapper.class).scrapfeed(criteria);
	}

	public List<Feed> hidefeed(Pagination criteria) {
		return sqlSession.getMapper(AccountMapper.class).hidefeed(criteria);
	}

	public List<Project> likepjt(int user_seq) {
		return sqlSession.getMapper(AccountMapper.class).likepjt(user_seq);
	}

	public UserProfile readsetting(int user_seq) {
		return sqlSession.getMapper(AccountMapper.class).readsetting(user_seq);
	}

	public void updateusersetting(UserProfile setting) {
		sqlSession.getMapper(AccountMapper.class).updateusersetting(setting);
	}

	public void updateaboutmesetting(UserProfile setting) {
		sqlSession.getMapper(AccountMapper.class).updateaboutmesetting(setting);
	}

	public void secession(int user_seq) {
		sqlSession.getMapper(AccountMapper.class).secession(user_seq);
	}

	public int getlikecnt(int user_seq) {
		return sqlSession.getMapper(AccountMapper.class).getlikecnt(user_seq);
	}

	public void removephoto(int user_seq) {
		sqlSession.getMapper(AccountMapper.class).removephoto(user_seq);
	}

}
