package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.FeedHide;
import com.web.curation.model.FeedLike;
import com.web.curation.model.FeedScrap;
import com.web.curation.model.Pagination;
import com.web.curation.model.ReportList;
import com.web.curation.model.UserHide;

public interface IFeedService {

	void insertFeed(Feed feed);

	void updateFeed(Feed feed);

	void deleteFeed(Feed feed);

	Feed detailFeed(Feed feed);

	void feedLike(FeedLike feedlike);

	void feedLikeAdd(Feed feed);

	void feedLikeCancel(FeedLike feedlike);

	void feedLikeSubtract(Feed feed);

	void feedScrap(FeedScrap feedScrap);

	void feedScrapAdd(Feed feed);

	void feedScrapCancel(FeedScrap feedscrap);

	void feedScrapSubtract(Feed feed);

	void feedReport(ReportList reportList);

	void feedReportAdd(Feed feed);

	void feedHide(FeedHide feedHide);

	void userHide(UserHide userHide);

	void userHideCancel(UserHide userHide);

	// boolean loginUserLike(Feed feed);
	FeedLike loginUserLike(Feed feed);

//	boolean loginUserScrap(Feed feed);
	FeedScrap loginUserScrap(Feed feed);

	List<Feed> feedlisttest(Feed feed);

	Boolean userReportCheck(ReportList reportList);

	List<Feed> newFeedList(Feed feed);

	List<Feed> followFeedList(Feed feed);

	void reportnotice(ReportList reportList);

	long getnewid();

	long getoldid();

	long getmaxid(int feed_seq);

	FeedDetail getdetail(Feed feed);

	Pagination hometimeline(Pagination criteria);

	Pagination followtimeline(Pagination criteria);

	Pagination questionfeed(Pagination criteria);

	Pagination questionfollowfeed(Pagination criteria);

}
