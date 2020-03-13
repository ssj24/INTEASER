package com.web.curation.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Communication;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedManagement;
import com.web.curation.model.Posting;
import com.web.curation.model.Reporter;
import com.web.curation.model.UserManagement;
@Mapper
public interface AdminMapper {

	List<Integer> effectiveseq();

	List<Integer> effectiveseqnickname(String user_nickname);
	
	List<Integer> effectivenormalseq();
	
	List<Integer> effectivenormalseqnickname(String user_nickname);

	List<Integer> effectiverecruiterseq();

	List<Integer> effectiverecruiterseqnickname(String user_nickname);

	UserManagement searchuser(int user_seq);

	int getlikecnt(int user_seq);

	int getpostcnt(int user_seq);

	List<Integer> getreported(int user_seq);

	int getreportcnt(int user_seq);

	String hasUtility(int user_seq);

	void suspension(int user_seq);

	void activation(int user_seq);

	List<Feed> search_all_feed();
	
	List<Feed> search_all_feed_by_nickname(String user_nickname);

	List<FeedManagement> search_reported_feed();

	List<FeedManagement> search_reported_feed_by_nickname(String user_nickname);

	List<Reporter> getreporter(int feed_seq);

	int isadmin(int user_seq);

	void feeddelete(int feed_seq);

	void admincheck(int feed_seq);

	int getusers();

	int getrecruiters();

	Date poststart();
	
	int pjtstart();

	int term();

	Posting getpostingcnt(String date);

	List<Integer> effectiverejectedseq();

	List<Integer> effectiverejectedseqnickname(String user_nickname);

	void pushnotice(Communication comm);

	int getfeedreportcnt(int feed_seq);

}
