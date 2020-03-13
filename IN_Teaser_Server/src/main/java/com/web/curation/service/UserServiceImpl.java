package com.web.curation.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.dao.UserDaoImpl;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.SearchKeyword;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;
@Transactional
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDaoImpl userdao;

	@Override
	public int login(User user) {
		return userdao.login(user);
	}

	@Override
	public User login_data(User user) {
		return userdao.login_data(user);
	}

	@Override
	public User SNSlogin(User user) {
		return userdao.SNSlogin(user);
	}
	
	@Override
	public void signup(User user) {
		userdao.signup(user);
	}

	@Override
	public int EmailDuplicateCheck(User user) {
		return userdao.EmailDuplicateCheck(user);
	}

	@Override
	public int NicknameDuplicateCheck(User user) {
		return userdao.NicknameDuplicateCheck(user);
	}

	@Override
	public void saveCertificationCode(User user) {
		userdao.saveCertificationCode(user);
		
	}

	@Override
	public int Certification_Code_Check(User user) {
		return userdao.Certification_Code_Check(user);
	}

	@Override
	public String getSalt(String user_email) {
		return userdao.getSalt(user_email);
	}

	@Override
	public List<User> findAll_user() {
		return userdao.findAll_user();
	}

	@Override
	public int snsUserCheck(User user) {
		return userdao.snsUserCheck(user);
	}

	@Override
	public User MyAccountInfo(User user) {
		return userdao.MyAccountInfo(user);
	}

	@Override
	public int MyAccountInfoUpdate(User user) {
		return userdao.MyAccountInfoUpdate(user);
	}

	@Override
	public UserProfile MyProfile(UserProfile userprofile) {
		return userdao.MyProfile(userprofile);
	}

	@Override
	public int Follower_count(UserProfile userprofile) {
		return userdao.Follower_count(userprofile);
	}

	@Override
	public int Following_count(UserProfile userprofile) {
		return userdao.Following_count(userprofile);
	}

	@Override
	public List<User> serch_nickname(User user) {
		return userdao.serch_nickname(user);
	}

	@Override
	public List<SearchKeyword> search_keyword_list(SearchKeyword s_keyword) {
		return userdao.search_keyword_list(s_keyword);
	}

	@Override
	public void add_search_keyword(SearchKeyword s_keyword) {
		userdao.add_search_keyword(s_keyword);
		
	}

	@Override
	public void delete_search_keyword(SearchKeyword s_keyword) {
		userdao.delete_search_keyword(s_keyword);
		
	}

	@Override
	public void update_pwd(User user) {
		userdao.update_pwd(user);
		
	}

	@Override
	public void update_saltpwd(User user) {
		userdao.update_saltpwd(user);
		
	}

	@Override
	public int check_search_keyword(SearchKeyword s_keyword) {
		return userdao.check_search_keyword(s_keyword);
	}

	@Override
	public void update_search_keyword(SearchKeyword s_keyword) {
		userdao.update_search_keyword(s_keyword);
		
	}

	@Override
	public int Certification_Code_deadline(User user) {
		return userdao.Certification_Code_deadline(user);
		
	}

	@Override
	public int certification_check(User user) {
		int result = userdao.certification_check(user);
		return result;
	}

	@Override
	public void SNSsignup(User user) {
		userdao.SNSsignup(user);
		
	}

	@Override
	public void fillaboutme(User filluser) {
		userdao.fillaboutme(filluser);
	}

	@Override
	public void fillportfolio(User filluser) {
		userdao.fillportfolio(filluser);
	}

	@Override
	public User getUserseq(User user) {
		return userdao.getUserseq(user);
	}

	@Override
	public FeedDetail writer(User user) {
		return userdao.writer(user);
	}

	@Override
	public int serch_nickname_getuserseq(String search_keyword) {
		return userdao.serch_nickname_getuserseq(search_keyword);
	}

	@Override
	public List<Feed> serchHashtag(Feed feed) {
		return userdao.serchHashtag(feed);
	}



}
