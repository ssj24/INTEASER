package com.web.curation.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Communication;
import com.web.curation.model.FollowList;
import com.web.curation.model.Notice;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;

@Mapper
public interface FollowMapper {

	public int followingcnt(User user);

	public int followercnt(User user);

	public List<FollowList> following(User user);

	public List<FollowList> follower(User user);

	public int requestcnt(User user);

	public List<FollowList> requestfollower(User user);

	public int noticecnt(User user);

	public List<Notice> noticelist(User user);

	public void newCheck1(User user);

	public void newCheck2(User user);

	public int canFollow1(Communication comm);

	public String canFollow2(Communication comm);

	public void requestfollowing1(Communication comm);

	public void requestfollowing2(Communication comm);

	public void requestfollowing3(Communication comm);

	public void requestfollowing4(Communication comm);

	public void answer1(FollowList follow);

	public void answer2(FollowList follow);

	public void answer3(Communication comm);

	public void deleteReject(User user);

	public int allrequestcnt(User user);

	public int allnoticecnt(User user);

	public List<Notice> noticecenterlist(User user);

	public void checknewrequest1(User user);

	public void checknewrequest2(User user);

	public void checknewnotice(User user);

	public int checknotice(User user);

	public int getauth(String nickname);

	public void deletenotification(Notice notice);

	public void requestunfollowing1(Communication comm);

	public void requestunfollowing2(Communication comm);

	public String getinterest(int user_seq);

	public List<UserProfile> getrandom(UserProfile user);

}
