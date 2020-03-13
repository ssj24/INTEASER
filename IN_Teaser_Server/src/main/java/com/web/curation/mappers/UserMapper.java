package com.web.curation.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.SearchKeyword;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;
@Mapper
public interface UserMapper {

	public int login(User user);
	
	public User login_data(User user);
	
	public User SNSlogin(User user);

	public int EmailDuplicateCheck(User user);

	public int NicknameDuplicateCheck(User user);

	public void signup(User user);

	public void saveCertificationCode(User user);

	public int Certification_Code_Check(User user);

	public String getSalt(String user_email);

	public List<User> findAll_user();

	public int snsUserCheck(User user);

	public User MyAccountInfo(User user);

	public int MyAccountInfoUpdate(User user);

	public UserProfile MyProfile(UserProfile userprofile);

	public int Follower_count(UserProfile userprofile);

	public int Following_count(UserProfile userprofile);

	public List<User> serch_nickname(User user);

	public List<SearchKeyword> search_keyword_list(SearchKeyword s_keyword);

	public void add_search_keyword(SearchKeyword s_keyword);

	public void delete_search_keyword(SearchKeyword s_keyword);

	public void update_pwd(User user);

	public void update_saltpwd(User user);

	public int check_search_keyword(SearchKeyword s_keyword);

	public void update_search_keyword(SearchKeyword s_keyword);

	public int Certification_Code_deadline(User user);

	public int certification_check(User user);

	public void SNSsignup(User user);

	public void fillaboutme(User filluser);

	public User getUserseq(User user);

	public void fillportfolio(User filluser);

	public FeedDetail writer(User user);

	public int serch_nickname_getuserseq(String search_keyword);

	public List<Feed> serchHashtag(Feed feed);


	
}
