package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Communication;
import com.web.curation.model.FollowList;
import com.web.curation.model.Notice;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;

public interface IFollowListService {

	int followingcnt(User user);

	int followercnt(User user);

	List<FollowList> following(User user);

	List<FollowList> follower(User user);

	int requestcnt(User user);

	List<FollowList> requestfollower(User user);

	int noticecnt(User user);

	List<Notice> noticelist(User user);

	void newCheck1(User user);

	void newCheck2(User user);

	int canFollow1(Communication comm);

	String canFollow2(Communication comm);

	void requestfollowing1(Communication comm);

	void requestfollowing2(Communication comm);

	void requestfollowing3(Communication comm);

	void requestfollowing4(Communication comm);

	void answer1(FollowList follow);

	void answer2(FollowList follow);

	void deleteReject(User user);

	int allrequestcnt(User user);

	int allnoticecnt(User user);

	List<Notice> noticecenterlist(User user);

	void checknewrequest1(User user);

	void checknewrequest2(User user);

	void checknewnotice(User user);

	int checknotice(User user);

	void answer3(Communication comm);

	int getauth(String nickname);

	void deletenotification(Notice notice);

	void requestunfollowing1(Communication comm);

	void requestunfollowing2(Communication comm);

	List<UserProfile> random(int user_seq);

}
