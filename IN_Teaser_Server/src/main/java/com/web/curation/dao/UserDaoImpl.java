package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.UserMapper;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.SearchKeyword;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;

@Repository
public class UserDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	public int login(User user) {
		return sqlSession.getMapper(UserMapper.class).login(user);
	}

	public User login_data(User user) {
		return sqlSession.getMapper(UserMapper.class).login_data(user);
	}

	public User SNSlogin(User user) {
		return sqlSession.getMapper(UserMapper.class).SNSlogin(user);
	}

	public int EmailDuplicateCheck(User user) {
		return sqlSession.getMapper(UserMapper.class).EmailDuplicateCheck(user);
	}

	public int NicknameDuplicateCheck(User user) {
		return sqlSession.getMapper(UserMapper.class).NicknameDuplicateCheck(user);
	}

	public void signup(User user) {
		sqlSession.getMapper(UserMapper.class).signup(user);
	}

	public void saveCertificationCode(User user) {
		sqlSession.getMapper(UserMapper.class).saveCertificationCode(user);
	}

	public int Certification_Code_Check(User user) {
		return sqlSession.getMapper(UserMapper.class).Certification_Code_Check(user);
	}

	public String getSalt(String user_email) {
		return sqlSession.getMapper(UserMapper.class).getSalt(user_email);
	}

	public List<User> findAll_user() {
		return sqlSession.getMapper(UserMapper.class).findAll_user();
	}

	public int snsUserCheck(User user) {
		return sqlSession.getMapper(UserMapper.class).snsUserCheck(user);
	}

	public User MyAccountInfo(User user) {
		return sqlSession.getMapper(UserMapper.class).MyAccountInfo(user);
	}

	public int MyAccountInfoUpdate(User user) {
		return sqlSession.getMapper(UserMapper.class).MyAccountInfoUpdate(user);
	}

	public UserProfile MyProfile(UserProfile userprofile) {
		return sqlSession.getMapper(UserMapper.class).MyProfile(userprofile);
	}

	public int Follower_count(UserProfile userprofile) {
		return sqlSession.getMapper(UserMapper.class).Follower_count(userprofile);
	}

	public int Following_count(UserProfile userprofile) {
		return sqlSession.getMapper(UserMapper.class).Following_count(userprofile);
	}

	public List<User> serch_nickname(User user) {
		return sqlSession.getMapper(UserMapper.class).serch_nickname(user);
	}

	public List<SearchKeyword> search_keyword_list(SearchKeyword s_keyword) {
		return sqlSession.getMapper(UserMapper.class).search_keyword_list(s_keyword);
	}

	public void add_search_keyword(SearchKeyword s_keyword) {
		sqlSession.getMapper(UserMapper.class).add_search_keyword(s_keyword);

	}

	public void delete_search_keyword(SearchKeyword s_keyword) {
		sqlSession.getMapper(UserMapper.class).delete_search_keyword(s_keyword);

	}

	public void update_pwd(User user) {
		sqlSession.getMapper(UserMapper.class).update_pwd(user);

	}

	public void update_saltpwd(User user) {
		sqlSession.getMapper(UserMapper.class).update_saltpwd(user);

	}

	public int check_search_keyword(SearchKeyword s_keyword) {
		return sqlSession.getMapper(UserMapper.class).check_search_keyword(s_keyword);
	}

	public void update_search_keyword(SearchKeyword s_keyword) {
		sqlSession.getMapper(UserMapper.class).update_search_keyword(s_keyword);

	}

	public int Certification_Code_deadline(User user) {
		return sqlSession.getMapper(UserMapper.class).Certification_Code_deadline(user);
	}

	public int certification_check(User user) {
		return sqlSession.getMapper(UserMapper.class).certification_check(user);
	}

	public void SNSsignup(User user) {
		sqlSession.getMapper(UserMapper.class).SNSsignup(user);

	}

	public User getUserseq(User user) {
		return sqlSession.getMapper(UserMapper.class).getUserseq(user);
	}

	public void fillaboutme(User filluser) {
		sqlSession.getMapper(UserMapper.class).fillaboutme(filluser);
	}

	public void fillportfolio(User filluser) {
		sqlSession.getMapper(UserMapper.class).fillportfolio(filluser);

	}

	public FeedDetail writer(User user) {
		return sqlSession.getMapper(UserMapper.class).writer(user);
	}

	public int serch_nickname_getuserseq(String search_keyword) {
		return sqlSession.getMapper(UserMapper.class).serch_nickname_getuserseq(search_keyword);
	}

	public List<Feed> serchHashtag(Feed feed) {
		return sqlSession.getMapper(UserMapper.class).serchHashtag(feed);
	}

	
}
