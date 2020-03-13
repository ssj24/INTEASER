package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.common.Common;
import com.web.curation.dao.AboutMeDaoImpl;
import com.web.curation.dao.CommentDaoImpl;
import com.web.curation.dao.FeedDaoImpl;
import com.web.curation.model.Comment;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedDetail;
import com.web.curation.model.Pagination;
import com.web.curation.model.Project;
import com.web.curation.model.UserProfile;
@Transactional
@Service
public class AboutMeServiceImpl implements IAboutMeService {

	@Autowired
	private AboutMeDaoImpl accountdao;
	@Autowired
	private CommentDaoImpl commentdao;
	@Autowired
	private FeedDaoImpl feeddao;

	@Override
	public UserProfile getMyAccount(int user_seq) {
		return accountdao.getMyAccount(user_seq);
	}

	@Override
	public Pagination uploadfeed(int login_user_seq, Pagination criteria) {
		// Init()
		criteria = init_paging(criteria);

		List<Feed> seq = accountdao.uploadfeed(login_user_seq, criteria);

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
	public Pagination likefeed(Pagination criteria) {
		// Init()
		criteria = init_paging(criteria);

		List<Feed> seq = accountdao.likefeed(criteria);

		if (seq.isEmpty()) {
			System.out.println("여기는 오나요");
			return null;
		}

		int size = seq.size();
		List<FeedDetail> ans = get_detail(seq, size);

		criteria.setMax_id(feeddao.getmaxid(seq.get(size - 1).getFeed_seq()));
		criteria.setFeed(ans);

		return criteria;
	}

	@Override
	public Pagination scrapfeed(Pagination criteria) {
		// Init()
		criteria = init_paging(criteria);

		List<Feed> seq = accountdao.scrapfeed(criteria);
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
	public Pagination hidefeed(Pagination criteria) {
		// Init()
		criteria = init_paging(criteria);

		List<Feed> seq = accountdao.hidefeed(criteria);
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
	public List<Project> likepjt(int user_seq) {
		// 내가 좋아한 project_seq만 뽑아보자
		List<Project> likepjt = accountdao.likepjt(user_seq);
		return likepjt;
	}

	@Override
	public UserProfile readsetting(int user_seq) {
		UserProfile profile = accountdao.readsetting(user_seq);
		return profile;
	}

	@Override
	public void updatesetting(UserProfile setting) {
		accountdao.updateusersetting(setting);
		accountdao.updateaboutmesetting(setting);
	}

	@Override
	public void secession(int user_seq) {
		accountdao.secession(user_seq);
	}

	@Override
	public void removephoto(int user_seq) {
		accountdao.removephoto(user_seq);
	}

	@Override
	public int getlikecnt(int user_seq) {
		return accountdao.getlikecnt(user_seq);
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
