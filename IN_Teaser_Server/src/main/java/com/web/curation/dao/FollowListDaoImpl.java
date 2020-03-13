package com.web.curation.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.FollowMapper;
import com.web.curation.model.Communication;
import com.web.curation.model.FollowList;
import com.web.curation.model.Notice;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;

@Repository
public class FollowListDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	public int followingcnt(User user) {
		return sqlSession.getMapper(FollowMapper.class).followingcnt(user);
	}

	public int followercnt(User user) {
		return sqlSession.getMapper(FollowMapper.class).followercnt(user);
	}

	public List<FollowList> following(User user) {
		return sqlSession.getMapper(FollowMapper.class).following(user);
	}

	public List<FollowList> follower(User user) {
		return sqlSession.getMapper(FollowMapper.class).follower(user);
	}

	public int requestcnt(User user) {
		return sqlSession.getMapper(FollowMapper.class).requestcnt(user);
	}

	public List<FollowList> requestfollower(User user) {
		return sqlSession.getMapper(FollowMapper.class).requestfollower(user);
	}

	public int noticecnt(User user) {
		return sqlSession.getMapper(FollowMapper.class).noticecnt(user);
	}

	public List<Notice> noticelist(User user) {
		return sqlSession.getMapper(FollowMapper.class).noticelist(user);
	}

	public void newCheck1(User user) {
		sqlSession.getMapper(FollowMapper.class).newCheck1(user);
	}
	
	public void newCheck2(User user) {
		sqlSession.getMapper(FollowMapper.class).newCheck2(user);
	}
	
	public int canFollow1(Communication comm) {
		return sqlSession.getMapper(FollowMapper.class).canFollow1(comm);
	}

	public String canFollow2(Communication comm) {
		return sqlSession.getMapper(FollowMapper.class).canFollow2(comm);
	}

	public void requestfollowing1(Communication comm) {
		sqlSession.getMapper(FollowMapper.class).requestfollowing1(comm);
	}

	public void requestfollowing2(Communication comm) {
		sqlSession.getMapper(FollowMapper.class).requestfollowing2(comm);
	}

	public void answer1(FollowList follow) {
		sqlSession.getMapper(FollowMapper.class).answer1(follow);
	}

	public void answer2(FollowList follow) {
		sqlSession.getMapper(FollowMapper.class).answer2(follow);
	}

	public void deleteReject(User user) {
		sqlSession.getMapper(FollowMapper.class).deleteReject(user);
	}

	public int allrequestcnt(User user) {
		return sqlSession.getMapper(FollowMapper.class).allrequestcnt(user);
	}

	public int allnoticecnt(User user) {
		return sqlSession.getMapper(FollowMapper.class).allnoticecnt(user);
	}

	public List<Notice> noticecenterlist(User user) {
		return sqlSession.getMapper(FollowMapper.class).noticecenterlist(user);
	}

	public void checknewrequest1(User user) {
		sqlSession.getMapper(FollowMapper.class).checknewrequest1(user);
	}

	public void checknewrequest2(User user) {
		sqlSession.getMapper(FollowMapper.class).checknewrequest2(user);
	}
	
	public void checknewnotice(User user) {
		sqlSession.getMapper(FollowMapper.class).checknewnotice(user);
	}

	public int checknotice(User user) {
		return sqlSession.getMapper(FollowMapper.class).checknotice(user);
	}

	public void requestfollowing3(Communication comm) {
		sqlSession.getMapper(FollowMapper.class).requestfollowing3(comm);
	}

	public void answer3(Communication comm) {
		sqlSession.getMapper(FollowMapper.class).answer3(comm);
	}

	public int getauth(String nickname) {
		return sqlSession.getMapper(FollowMapper.class).getauth(nickname);
	}

	public void requestfollowing4(Communication comm) {
		sqlSession.getMapper(FollowMapper.class).requestfollowing4(comm);
	}

	public void deletenotification(Notice notice) {
		sqlSession.getMapper(FollowMapper.class).deletenotification(notice);
	}

	public void requestunfollowing1(Communication comm) {
		sqlSession.getMapper(FollowMapper.class).requestunfollowing1(comm);
	}

	public void requestunfollowing2(Communication comm) {
		sqlSession.getMapper(FollowMapper.class).requestunfollowing2(comm);
	}

	public String getinterest(int user_seq) {
		return sqlSession.getMapper(FollowMapper.class).getinterest(user_seq);
	}

	public List<UserProfile> getrandom(UserProfile user) {
		return sqlSession.getMapper(FollowMapper.class).getrandom(user);
	}

}
