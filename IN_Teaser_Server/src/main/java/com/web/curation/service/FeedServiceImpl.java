package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.common.Common;
import com.web.curation.dao.CommentDaoImpl;
import com.web.curation.dao.FeedDaoImpl;
import com.web.curation.dao.NoticeDaoImpl;
import com.web.curation.model.Comment;
import com.web.curation.model.Communication;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.FeedHide;
import com.web.curation.model.FeedLike;
import com.web.curation.model.FeedScrap;
import com.web.curation.model.Pagination;
import com.web.curation.model.ReportList;
import com.web.curation.model.UserHide;
@Transactional
@Service
public class FeedServiceImpl implements IFeedService {
	@Autowired
	private FeedDaoImpl feeddao;
	@Autowired
	private CommentDaoImpl commentdao;
	@Autowired
	private NoticeDaoImpl noticedao;

	@Override
	public void insertFeed(Feed feed) {
		feeddao.insertFeed(feed);

	}

	@Override
	public void updateFeed(Feed feed) {
		feeddao.updateFeed(feed);
	}

	@Override
	public void deleteFeed(Feed feed) {
		feeddao.deleteFeed(feed);
	}

	@Override
	public Feed detailFeed(Feed feed) {
		return feeddao.detailFeed(feed);
	}

	@Override
	public void feedLike(FeedLike feedlike) {
		feeddao.feedLike(feedlike);
		if (feeddao.isme(feedlike))
			return;
		int user_auth = noticedao.getuserauth(feedlike.getUser_seq());
		String temp = "";
		if (user_auth == 2)
			temp = "채용담당자<b>" + feedlike.getUser_nickname() + "</b>님이 회원님의 글에 좋아요를 눌렀습니다.";
		else
			temp = "<b>" + feedlike.getUser_nickname() + "</b>님이 회원님의 글에 좋아요를 눌렀습니다.";

		Communication comm = new Communication(feedlike.getFeed_seq(), feedlike.getUser_nickname(), user_auth, temp);
		noticedao.pushnoticebyfeed(comm);
	}

	@Override
	public void feedLikeAdd(Feed feed) {
		feeddao.feedLikeAdd(feed);

	}

	@Override
	public void feedLikeCancel(FeedLike feedlike) {
		feeddao.feedLikeCancel(feedlike);

	}

	@Override
	public void feedLikeSubtract(Feed feed) {
		feeddao.feedLikeSubtract(feed);

	}

	@Override
	public void feedScrap(FeedScrap feedScrap) {
		feeddao.feedScrap(feedScrap);

	}

	@Override
	public void feedScrapAdd(Feed feed) {
		feeddao.feedScrapAdd(feed);

	}

	@Override
	public void feedScrapCancel(FeedScrap feedscrap) {
		feeddao.feedScrapCancel(feedscrap);

	}

	@Override
	public void feedScrapSubtract(Feed feed) {
		feeddao.feedScrapSubtract(feed);

	}

	@Override
	public void feedReport(ReportList reportList) {
		feeddao.feedReport(reportList);

	}

	@Override
	public void feedReportAdd(Feed feed) {
		feeddao.feedReportAdd(feed);

	}

	@Override
	public void feedHide(FeedHide feedHide) {
		feeddao.feedHide(feedHide);

	}

	@Override
	public void userHide(UserHide userHide) {
		feeddao.userHide(userHide);
	}

	@Override
	public void userHideCancel(UserHide userHide) {
		feeddao.userHideCancel(userHide);

	}

	@Override
	public FeedLike loginUserLike(Feed feed) {
		return feeddao.loginUserLike(feed);
	}

	@Override
	public FeedScrap loginUserScrap(Feed feed) {
		return feeddao.loginUserScrap(feed);
	}

	@Override
	public List<Feed> feedlisttest(Feed feed) {
		return feeddao.feedlisttest(feed);
	}

	@Override
	public Boolean userReportCheck(ReportList reportList) {
		return feeddao.userReportCheck(reportList);
	}

	@Override
	public List<Feed> newFeedList(Feed feed) {
		return feeddao.newFeedList(feed);
	}

	@Override
	public List<Feed> followFeedList(Feed feed) {
		return feeddao.followFeedList(feed);
	}

	@Override
	public void reportnotice(ReportList reportList) {
		// feed_seq, user_seq
		Communication comm = feeddao.setinfo(reportList);
		comm.setTemp("회원님의 게시물이 신고됐습니다.");

		feeddao.reportnotice(comm);
	}

	@Override
	public long getnewid() {
		return feeddao.getnewid();
	}

	@Override
	public long getoldid() {
		return feeddao.getoldid();
	}

	@Override
	public long getmaxid(int feed_seq) {
		return feeddao.getmaxid(feed_seq);
	}

	@Override
	public FeedDetail getdetail(Feed feed) {
		return feeddao.getdetail(feed);
	}
	
	@Override
	public Pagination hometimeline(Pagination criteria) {
		// Init()
		criteria = init_paging(criteria);
		
		List<Feed> seq = feeddao.hometimelineseq(criteria);
		
		if (seq.isEmpty()) {
			return null;
		}
		
		int size = seq.size();
		List<FeedDetail> ans = get_detail(seq, size);
		
		criteria.setMax_id(feeddao.getmaxid(seq.get(size - 1).getFeed_seq()));
		criteria.setFeed(ans);
		
		return criteria;
	}

	@Override
	public Pagination followtimeline(Pagination criteria) {
		// Init()
		criteria = init_paging(criteria);

		List<Feed> seq = feeddao.followtimelineseq(criteria);

		if (seq.isEmpty()) {
			return null;
		}

		int size = seq.size();
		List<FeedDetail> ans = get_detail(seq, size);

		criteria.setMax_id(feeddao.getmaxid(seq.get(size - 1).getFeed_seq()));
		criteria.setFeed(ans);

		return criteria;
	}

	@Override
	public Pagination questionfeed(Pagination criteria) {
		// Init()
		criteria = init_paging(criteria);

		List<Feed> seq = feeddao.questionfeed(criteria);

		if (seq.isEmpty()) {
			return null;
		}

		int size = seq.size();
		List<FeedDetail> ans = get_detail(seq, size);

		criteria.setMax_id(feeddao.getmaxid(seq.get(size - 1).getFeed_seq()));
		criteria.setFeed(ans);

		return criteria;
	}

	@Override
	public Pagination questionfollowfeed(Pagination criteria) {
		// Init()
		criteria = init_paging(criteria);

		List<Feed> seq = feeddao.questionfollowfeed(criteria);

		if (seq.isEmpty()) {
			return null;
		}

		int size = seq.size();
		List<FeedDetail> ans = get_detail(seq, size);

		criteria.setMax_id(feeddao.getmaxid(seq.get(size - 1).getFeed_seq()));
		criteria.setFeed(ans);

		return criteria;
	}

	private Pagination init_paging(Pagination criteria) {
		if (criteria.getSince_id() == 0 && criteria.getMax_id() == 0) {
			// 가장 최신 피드의 cursor를 알아보자
			long newid = feeddao.getnewid();
			long oldid = feeddao.getoldid();
			criteria.setSince_id(newid);
			criteria.setMax_id(newid + 1);
			criteria.setFinal_id(oldid);
		}

		if (criteria.getMax_id() == criteria.getFinal_id()) {
			criteria.setFinal_id(criteria.getSince_id() + 1);
			long newid = feeddao.getnewid();
			criteria.setSince_id(newid);
			criteria.setMax_id(newid + 1);
		}
		return criteria;
	}

	private List<FeedDetail> get_detail(List<Feed> seq, int size) {
		List<FeedDetail> ans = new ArrayList<FeedDetail>();
		for (int i = 0; i < size; i++) {
			FeedDetail tmp = feeddao.getdetail(seq.get(i));
			List<Comment> comment = commentdao.feedComment2(seq.get(i));
			tmp.setComment(comment);
			tmp.setPost_date(Common.calculateTime(tmp.getInsert_date()));
			ans.add(tmp);
		}
		return ans;
	}

}
