package com.web.curation.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.FeedMapper;
import com.web.curation.model.Communication;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.FeedHide;
import com.web.curation.model.FeedLike;
import com.web.curation.model.FeedScrap;
import com.web.curation.model.Pagination;
import com.web.curation.model.ReportList;
import com.web.curation.model.UserHide;

@Repository
public class FeedDaoImpl {

	@Autowired
	private SqlSession sqlSession;


	public void insertFeed(Feed feed) {
		sqlSession.getMapper(FeedMapper.class).insertFeed(feed);
		
	}


	public void updateFeed(Feed feed) {
		sqlSession.getMapper(FeedMapper.class).updateFeed(feed);
		
	}


	public void deleteFeed(Feed feed) {
		sqlSession.getMapper(FeedMapper.class).deleteFeed(feed);
		
	}


	public Feed detailFeed(Feed feed) {
		return sqlSession.getMapper(FeedMapper.class).detailFeed(feed);
	}


	public void feedLike(FeedLike feedlike) {
		sqlSession.getMapper(FeedMapper.class).feedLike(feedlike);
		
	}


	public void feedLikeAdd(Feed feed) {
		sqlSession.getMapper(FeedMapper.class).feedLikeAdd(feed);
		
	}


	public void feedLikeCancel(FeedLike feedlike) {
		sqlSession.getMapper(FeedMapper.class).feedLikeCancel(feedlike);
		
	}


	public void feedLikeSubtract(Feed feed) {
		sqlSession.getMapper(FeedMapper.class).feedLikeSubtract(feed);
		
	}

	public void feedScrap(FeedScrap feedScrap) {
		sqlSession.getMapper(FeedMapper.class).feedScrap(feedScrap);
		
	}


	public void feedScrapAdd(Feed feed) {
		sqlSession.getMapper(FeedMapper.class).feedScrapAdd(feed);
	}


	public void feedScrapCancel(FeedScrap feedscrap) {
		sqlSession.getMapper(FeedMapper.class).feedScrapCancel(feedscrap);
	}


	public void feedScrapSubtract(Feed feed) {
		sqlSession.getMapper(FeedMapper.class).feedScrapSubtract(feed);
		
	}


	public void feedReport(ReportList reportList) {
		sqlSession.getMapper(FeedMapper.class).feedReport(reportList);
		
	}


	public void feedReportAdd(Feed feed) {
		sqlSession.getMapper(FeedMapper.class).feedReportAdd(feed);
		
	}


	public void feedHide(FeedHide feedHide) {
		sqlSession.getMapper(FeedMapper.class).feedHide(feedHide);
		
	}


	public void userHide(UserHide userHide) {
		sqlSession.getMapper(FeedMapper.class).userHide(userHide);
		
	}


	public void userHideCancel(UserHide userHide) {
		sqlSession.getMapper(FeedMapper.class).userHideCancel(userHide);
	}


//	public boolean loginUserLike(Feed feed) {
//		return sqlSession.getMapper(FeedMapper.class).loginUserLike(feed);
//	}
	public FeedLike loginUserLike(Feed feed) {
		return sqlSession.getMapper(FeedMapper.class).loginUserLike(feed);
	}

//	public boolean loginUserScrap(Feed feed) {
//		return sqlSession.getMapper(FeedMapper.class).loginUserScrap(feed);
//	}
	public FeedScrap loginUserScrap(Feed feed) {
		return sqlSession.getMapper(FeedMapper.class).loginUserScrap(feed);
	}
	

	public List<Feed> feedlisttest(Feed feed) {
		return sqlSession.getMapper(FeedMapper.class).feedlisttest(feed);
	}


	public Boolean userReportCheck(ReportList reportList) {
		if( sqlSession.getMapper(FeedMapper.class).userReportCheck(reportList) == null)
			return false;
		else 
			return true;
	}


	public List<Feed> newFeedList(Feed feed) {
		return sqlSession.getMapper(FeedMapper.class).newFeedList(feed);

	}


	public List<Feed> followFeedList(Feed feed) {
		return sqlSession.getMapper(FeedMapper.class).followFeedList(feed);
	}


	public Communication setinfo(ReportList reportList) {
		return sqlSession.getMapper(FeedMapper.class).setinfo(reportList);
	}


	public void reportnotice(Communication comm) {
		sqlSession.getMapper(FeedMapper.class).reportnotice(comm);
	}


	public long getnewid() {
		return sqlSession.getMapper(FeedMapper.class).getnewid();
	}


	public long getoldid() {
		return sqlSession.getMapper(FeedMapper.class).getoldid(); 
	}


	public List<Feed> hometimelineseq(Pagination criteria) {
		return sqlSession.getMapper(FeedMapper.class).hometimelineseq(criteria);
	}


	public long getmaxid(int feed_seq) {
		return sqlSession.getMapper(FeedMapper.class).getmaxid(feed_seq);
	}


	public FeedDetail getdetail(Feed feed) {
		return sqlSession.getMapper(FeedMapper.class).getdetail(feed);
	}


	public List<Feed> followtimelineseq(Pagination criteria) {
		return sqlSession.getMapper(FeedMapper.class).followtimelineseq(criteria);
	}


	public List<Feed> questionfeed(Pagination criteria) {
		return sqlSession.getMapper(FeedMapper.class).questionfeed(criteria);
	}


	public List<Feed> questionfollowfeed(Pagination criteria) {
		return sqlSession.getMapper(FeedMapper.class).questionfollowfeed(criteria);
	}


	public boolean isme(FeedLike feedlike) {
		return sqlSession.getMapper(FeedMapper.class).isme(feedlike);
	}


	public List<Feed> hometimeline(int user_seq, Pagination criteria) {
		// TODO Auto-generated method stub
		return null;
	}


}
