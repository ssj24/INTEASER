package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.SearchKeyword;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;

public interface IUserService {
	
	int login(User user);
	
	User login_data(User user);
	
	User SNSlogin(User user);
	
	void signup(User user);

	int EmailDuplicateCheck(User user);

	int NicknameDuplicateCheck(User user);

	void saveCertificationCode(User user);

	int Certification_Code_Check(User user);

	String getSalt(String user_email);

	List<User> findAll_user();

	int snsUserCheck(User user);

	User MyAccountInfo(User user);

	int MyAccountInfoUpdate(User user);

	UserProfile MyProfile(UserProfile userprofile);

	int Follower_count(UserProfile userprofile);

	int Following_count(UserProfile userprofile);

	List<User> serch_nickname(User user);

	List<SearchKeyword> search_keyword_list(SearchKeyword s_keyword);

	void add_search_keyword(SearchKeyword s_keyword);

	void delete_search_keyword(SearchKeyword s_keyword);

	void update_pwd(User user);

	void update_saltpwd(User user);

	int check_search_keyword(SearchKeyword s_keyword);

	void update_search_keyword(SearchKeyword s_keyword);

	int Certification_Code_deadline(User user);

	int certification_check(User user);

	void SNSsignup(User user);

	User getUserseq(User user);

	void fillaboutme(User filluser);

	void fillportfolio(User filluser);

	FeedDetail writer(User user);

	int serch_nickname_getuserseq(String search_keyword);

	List<Feed> serchHashtag(Feed feed);






	
	
}
