package com.web.curation.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Communication;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.FeedHide;
import com.web.curation.model.FeedLike;
import com.web.curation.model.FeedScrap;
import com.web.curation.model.Pagination;
import com.web.curation.model.ReportList;
import com.web.curation.model.UserHide;
@Mapper
public interface FeedMapper {

	public void insertFeed(Feed feed);

	public void updateFeed(Feed feed);

	public void deleteFeed(Feed feed);

	public Feed detailFeed(Feed feed);

	public void feedLike(FeedLike feedlike);

	public void feedLikeAdd(Feed feed);

	public void feedLikeCancel(FeedLike feedlike);

	public void feedLikeSubtract(Feed feed);

	public void feedScrap(FeedScrap feedScrap);

	public void feedScrapAdd(Feed feed);

	public void feedScrapCancel(FeedScrap feedscrap);

	public void feedScrapSubtract(Feed feed);

	public void feedReport(ReportList reportList);

	public void feedReportAdd(Feed feed);

	public void feedHide(FeedHide feedHide);

	public void userHide(UserHide userHide);

	public void userHideCancel(UserHide userHide);

//	public boolean loginUserLike(Feed feed);
	public FeedLike loginUserLike(Feed feed);
//	public boolean loginUserScrap(Feed feed);
	public FeedScrap loginUserScrap(Feed feed);

	public List<Feed> feedlisttest(Feed feed);

	public Boolean userReportCheck(ReportList reportList);

	public List<Feed> newFeedList(Feed feed);

	public List<Feed> followFeedList(Feed feed);

	public Communication setinfo(ReportList reportList);

	public void reportnotice(Communication comm);

	public long getnewid();

	public long getoldid();

	public List<Feed> hometimelineseq(Pagination criteria);

	public long getmaxid(int feed_seq);

	public FeedDetail getdetail(Feed feed);

	public List<Feed> followtimelineseq(Pagination criteria);

	public List<Feed> questionfeed(Pagination criteria);

	public List<Feed> questionfollowfeed(Pagination criteria);

	public boolean isme(FeedLike feedlike);

	
}
