package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Feed;
import com.web.curation.model.FeedManagement;
import com.web.curation.model.Posting;
import com.web.curation.model.Ratio;
import com.web.curation.model.UserManagement;

public interface IAdminPageService {

	boolean hasUtility(int user_seq);

	boolean canactivate(int user_seq);

	boolean activateonoff(int user_seq);
	
	//유저관리
	List<UserManagement> search_all_user();

	List<UserManagement> search_all_user(String user_nickname);

	List<UserManagement> search_normal();

	List<UserManagement> search_normal(String user_nickname);

	List<UserManagement> search_recruiter();
	
	List<UserManagement> search_recruiter(String user_nickname);
	
	//게시글관리
	List<Feed> search_all_feed();
	
	List<Feed> search_all_feed(String user_nickname);
	
	List<FeedManagement> search_reported_feed();
	
	List<FeedManagement> search_reported_feed(String user_nickname);

	boolean isadmin(int user_seq);

	void feedhide(int feed_seq);

	void feedcheck(int feed_seq);

	Ratio ratio();

	List<Posting> day();

	List<UserManagement> search_rejected();

	List<UserManagement> search_rejected(String user_nickname);
}
