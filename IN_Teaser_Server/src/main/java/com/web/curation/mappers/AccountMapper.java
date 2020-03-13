package com.web.curation.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Feed;
import com.web.curation.model.FollowList;
import com.web.curation.model.Pagination;
import com.web.curation.model.Project;
import com.web.curation.model.UserProfile;

@Mapper
public interface AccountMapper {

	UserProfile getMyAccount(int user_seq);

	int loginUserLike(Feed feed);

	int loginUserScrap(Feed feed);

	List<Feed> uploadfeed(Pagination criteria);

	List<Feed> uploadfeedbyfriend(Pagination criteria);

	List<Feed> uploadfeedbyothers(Pagination criteria);

	List<Feed> likefeed(Pagination criteria);

	List<Feed> scrapfeed(Pagination criteria);

	List<Feed> hidefeed(Pagination criteria);

	List<Project> likepjt(int user_seq);

	UserProfile readsetting(int user_seq);

	void updateusersetting(UserProfile setting);

	void updateaboutmesetting(UserProfile setting);

	void secession(int user_seq);

	int getlikecnt(int user_seq);

	void removephoto(int user_seq);

	int isfollowing(FollowList user);

}
