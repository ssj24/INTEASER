package com.web.curation.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.AdminPageDaoImpl;
import com.web.curation.model.Communication;
import com.web.curation.model.Feed;
import com.web.curation.model.FeedManagement;
import com.web.curation.model.Posting;
import com.web.curation.model.Ratio;
import com.web.curation.model.UserManagement;

@Service
public class AdminPageServiceImpl implements IAdminPageService {

	@Autowired
	private AdminPageDaoImpl admindao;
	private List<UserManagement> all_user;

	@Override
	public boolean hasUtility(int user_seq) {
		String utilityYN = admindao.hasUtility(user_seq);
		if (utilityYN.contentEquals("Y"))
			return false;
		else
			return true;
	}

	@Override
	public boolean canactivate(int user_seq) {
		String utilityYN = admindao.hasUtility(user_seq);
		if (utilityYN.contentEquals("N"))
			return true;
		else if (utilityYN.contentEquals("I"))
			return false;
		return false;
	}

	@Override
	public boolean activateonoff(int user_seq) {
		String utilityYN = admindao.hasUtility(user_seq);
		if (utilityYN.contentEquals("N")) {
			admindao.suspension(user_seq);
			return true;
		} else if (utilityYN.contentEquals("I")) {
			admindao.activation(user_seq);
			return false;
		}
		return false;
	}

	@Override
	public List<UserManagement> search_all_user() {
		all_user = null;
		all_user = new ArrayList<UserManagement>();
		List<Integer> effective_seq = admindao.effectiveseq(); // 유효한 seq들만 있어
		List<Integer> reported = null;
		for (int i = 0; i < effective_seq.size(); i++) {
			int user_seq = effective_seq.get(i);
			all_user.add(admindao.searchuser(user_seq));
			all_user.get(i).setLike_cnt(admindao.getlikecnt(user_seq));
			// 작성한 게시글 수
			all_user.get(i).setPost_cnt(admindao.getpostcnt(user_seq));
			// reported = user_seq가 신고받은 게시글의 신고수
			reported = admindao.getreported(user_seq);
			// user_seq가 신고받은 게시글의 수
			int size = reported.size();
			all_user.get(i).setReported_post_cnt(size);
			// ans = user_seq가 신고받은 total
			int ans = 0;
			for (int j = 0; j < size; j++) {
				ans += reported.get(j);
			}
			all_user.get(i).setReported_cnt(ans);
			// user_seq가 신고한 수
			int report_cnt = admindao.getreportcnt(user_seq);
			all_user.get(i).setReport_cnt(report_cnt);
		}
		return all_user;
	}

	@Override
	public List<UserManagement> search_all_user(String user_nickname) {
		all_user = null;
		all_user = new ArrayList<UserManagement>();
		List<Integer> effective_seq = admindao.effectiveseqnickname(user_nickname); // 유효한 seq들 + 내 닉네임만 있어
		List<Integer> reported = null;
		for (int i = 0; i < effective_seq.size(); i++) {
			int user_seq = effective_seq.get(i);
			all_user.add(admindao.searchuser(user_seq));
			all_user.get(i).setLike_cnt(admindao.getlikecnt(user_seq));
			// 작성한 게시글 수
			all_user.get(i).setPost_cnt(admindao.getpostcnt(user_seq));
			// reported = user_seq가 신고받은 게시글의 신고수
			reported = admindao.getreported(user_seq);
			// user_seq가 신고받은 게시글의 수
			int size = reported.size();
			all_user.get(i).setReported_post_cnt(size);
			// ans = user_seq가 신고받은 total
			int ans = 0;
			for (int j = 0; j < size; j++) {
				ans += reported.get(j);
			}
			all_user.get(i).setReported_cnt(ans);
			// user_seq가 신고한 수
			int report_cnt = admindao.getreportcnt(user_seq);
			all_user.get(i).setReport_cnt(report_cnt);
		}
		return all_user;
	}

	@Override
	public List<UserManagement> search_normal() {
		all_user = null;
		all_user = new ArrayList<UserManagement>();
		List<Integer> effective_seq = admindao.effectivenormalseq(); // 유효한 seq들만 있어
		List<Integer> reported = null;
		for (int i = 0; i < effective_seq.size(); i++) {
			int user_seq = effective_seq.get(i);
			all_user.add(admindao.searchuser(user_seq));
			all_user.get(i).setLike_cnt(admindao.getlikecnt(user_seq));
			// 작성한 게시글 수
			all_user.get(i).setPost_cnt(admindao.getpostcnt(user_seq));
			// reported = user_seq가 신고받은 게시글의 신고수
			reported = admindao.getreported(user_seq);
			// user_seq가 신고받은 게시글의 수
			int size = reported.size();
			all_user.get(i).setReported_post_cnt(size);
			// ans = user_seq가 신고받은 total
			int ans = 0;
			for (int j = 0; j < size; j++) {
				ans += reported.get(j);
			}
			all_user.get(i).setReported_cnt(ans);
			// user_seq가 신고한 수
			int report_cnt = admindao.getreportcnt(user_seq);
			all_user.get(i).setReport_cnt(report_cnt);
		}
		return all_user;
	}

	@Override
	public List<UserManagement> search_normal(String user_nickname) {
		all_user = null;
		all_user = new ArrayList<UserManagement>();
		List<Integer> effective_seq = admindao.effectivenormalseqnickname(user_nickname); // 유효한 seq들만 있어
		List<Integer> reported = null;
		for (int i = 0; i < effective_seq.size(); i++) {
			int user_seq = effective_seq.get(i);
			all_user.add(admindao.searchuser(user_seq));
			all_user.get(i).setLike_cnt(admindao.getlikecnt(user_seq));
			// 작성한 게시글 수
			all_user.get(i).setPost_cnt(admindao.getpostcnt(user_seq));
			// reported = user_seq가 신고받은 게시글의 신고수
			reported = admindao.getreported(user_seq);
			// user_seq가 신고받은 게시글의 수
			int size = reported.size();
			all_user.get(i).setReported_post_cnt(size);
			// ans = user_seq가 신고받은 total
			int ans = 0;
			for (int j = 0; j < size; j++) {
				ans += reported.get(j);
			}
			all_user.get(i).setReported_cnt(ans);
			// user_seq가 신고한 수
			int report_cnt = admindao.getreportcnt(user_seq);
			all_user.get(i).setReport_cnt(report_cnt);
		}
		return all_user;
	}

	@Override
	public List<UserManagement> search_recruiter() {
		all_user = null;
		all_user = new ArrayList<UserManagement>();
		List<Integer> effective_seq = admindao.effectiverecruiterseq(); // 유효한 seq들만 있어
		List<Integer> reported = null;
		for (int i = 0; i < effective_seq.size(); i++) {
			int user_seq = effective_seq.get(i);
			all_user.add(admindao.searchuser(user_seq));
			all_user.get(i).setLike_cnt(admindao.getlikecnt(user_seq));
			// 작성한 게시글 수
			all_user.get(i).setPost_cnt(admindao.getpostcnt(user_seq));
			// reported = user_seq가 신고받은 게시글의 신고수
			reported = admindao.getreported(user_seq);
			// user_seq가 신고받은 게시글의 수
			int size = reported.size();
			all_user.get(i).setReported_post_cnt(size);
			// ans = user_seq가 신고받은 total
			int ans = 0;
			for (int j = 0; j < size; j++) {
				ans += reported.get(j);
			}
			all_user.get(i).setReported_cnt(ans);
			// user_seq가 신고한 수
			int report_cnt = admindao.getreportcnt(user_seq);
			all_user.get(i).setReport_cnt(report_cnt);
		}
		return all_user;
	}

	@Override
	public List<UserManagement> search_recruiter(String user_nickname) {
		all_user = null;
		all_user = new ArrayList<UserManagement>();
		List<Integer> effective_seq = admindao.effectiverecruiterseqnickname(user_nickname); // 유효한 seq들만 있어
		List<Integer> reported = null;
		for (int i = 0; i < effective_seq.size(); i++) {
			int user_seq = effective_seq.get(i);
			all_user.add(admindao.searchuser(user_seq));
			all_user.get(i).setLike_cnt(admindao.getlikecnt(user_seq));
			// 작성한 게시글 수
			all_user.get(i).setPost_cnt(admindao.getpostcnt(user_seq));
			// reported = user_seq가 신고받은 게시글의 신고수
			reported = admindao.getreported(user_seq);
			// user_seq가 신고받은 게시글의 수
			int size = reported.size();
			all_user.get(i).setReported_post_cnt(size);
			// ans = user_seq가 신고받은 total
			int ans = 0;
			for (int j = 0; j < size; j++) {
				ans += reported.get(j);
			}
			all_user.get(i).setReported_cnt(ans);
			// user_seq가 신고한 수
			int report_cnt = admindao.getreportcnt(user_seq);
			all_user.get(i).setReport_cnt(report_cnt);
		}
		return all_user;
	}

	@Override
	public List<Feed> search_all_feed() {
		return admindao.search_all_feed();
	}

	@Override
	public List<Feed> search_all_feed(String user_nickname) {
		return admindao.search_all_feed(user_nickname);
	}

	@Override
	public List<FeedManagement> search_reported_feed() {
		List<FeedManagement> reported_feed = admindao.search_reported_feed();
		// feed_seq로 opponent 애들을 찾아보자
		for (int i = 0; i < reported_feed.size(); i++) {
			int feed_seq = reported_feed.get(i).getFeed_seq();
			reported_feed.get(i).setFeed_report_cnt(admindao.getfeedreportcnt(feed_seq));
			reported_feed.get(i).setReporter(admindao.getreporter(feed_seq));
		}
		return reported_feed;
	}

	@Override
	public List<FeedManagement> search_reported_feed(String user_nickname) {
		List<FeedManagement> reported_feed = admindao.search_reported_feed(user_nickname);
		for (int i = 0; i < reported_feed.size(); i++) {
			int feed_seq = reported_feed.get(i).getFeed_seq();
			reported_feed.get(i).setReporter(admindao.getreporter(feed_seq));
		}
		return reported_feed;
	}

	@Override
	public boolean isadmin(int user_seq) {
		return admindao.isadmin(user_seq);
	}

	@Override
	public void feedhide(int feed_seq) {
		admindao.feeddelete(feed_seq); // deleteYN을 I로
		admindao.admincheck(feed_seq); // report_list의 confirmYN을 Y로
		
		Communication comm = new Communication(feed_seq, "admin", 0,"회원님의 게시물이 부적절한 컨텐츠로 사료되어 관리자에 의해 숨김 처리 되었습니다.");
		
		admindao.pushnotice(comm);
	}

	@Override
	public void feedcheck(int feed_seq) {
		admindao.admincheck(feed_seq);
	}

	@Override
	public Ratio ratio() {
		Ratio ratio = new Ratio();
		int users = admindao.getusers();
		int recruiters = admindao.getrecruiters();

		double puser = Math.round(((double) users / (users + recruiters)) * 100 * 100 / 100.0);
		double precruiter = Math.round(((double) recruiters / (users + recruiters)) * 100 * 100 / 100.0);

		ratio.setUsers(users);
		ratio.setRecruiters(recruiters);
		ratio.setPuser(puser);
		ratio.setPrecruiter(precruiter);
		return ratio;
	}

	@Override
	public List<Posting> day() {
		// 시작일부터 오늘까지 해보자
		Date startday = admindao.startposting();
		int term = admindao.term();
		List<Posting> post = new ArrayList<Posting>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(startday);
		for (int i = 0; i < term; i++) {
			post.add(admindao.getpostingcnt(format.format(cal.getTime())));
			cal.add(Calendar.DATE, 1);
		}
		return post;
	}

	@Override
	public List<UserManagement> search_rejected() {
		all_user = null;
		all_user = new ArrayList<UserManagement>();
		List<Integer> effective_seq = admindao.effectiverejectedseq(); // 유효한 seq들만 있어
		List<Integer> reported = null;
		for (int i = 0; i < effective_seq.size(); i++) {
			int user_seq = effective_seq.get(i);
			all_user.add(admindao.searchuser(user_seq));
			all_user.get(i).setLike_cnt(admindao.getlikecnt(user_seq));
			// 작성한 게시글 수
			all_user.get(i).setPost_cnt(admindao.getpostcnt(user_seq));
			// reported = user_seq가 신고받은 게시글의 신고수
			reported = admindao.getreported(user_seq);
			// user_seq가 신고받은 게시글의 수
			int size = reported.size();
			all_user.get(i).setReported_post_cnt(size);
			// ans = user_seq가 신고받은 total
			int ans = 0;
			for (int j = 0; j < size; j++) {
				ans += reported.get(j);
			}
			all_user.get(i).setReported_cnt(ans);
			// user_seq가 신고한 수
			int report_cnt = admindao.getreportcnt(user_seq);
			all_user.get(i).setReport_cnt(report_cnt);
		}
		return all_user;
	}

	@Override
	public List<UserManagement> search_rejected(String user_nickname) {
		all_user = null;
		all_user = new ArrayList<UserManagement>();
		List<Integer> effective_seq = admindao.effectiverejectedseqnickname(user_nickname); // 유효한 seq들만 있어
		List<Integer> reported = null;
		for (int i = 0; i < effective_seq.size(); i++) {
			int user_seq = effective_seq.get(i);
			all_user.add(admindao.searchuser(user_seq));
			all_user.get(i).setLike_cnt(admindao.getlikecnt(user_seq));
			// 작성한 게시글 수
			all_user.get(i).setPost_cnt(admindao.getpostcnt(user_seq));
			// reported = user_seq가 신고받은 게시글의 신고수
			reported = admindao.getreported(user_seq);
			// user_seq가 신고받은 게시글의 수
			int size = reported.size();
			all_user.get(i).setReported_post_cnt(size);
			// ans = user_seq가 신고받은 total
			int ans = 0;
			for (int j = 0; j < size; j++) {
				ans += reported.get(j);
			}
			all_user.get(i).setReported_cnt(ans);
			// user_seq가 신고한 수
			int report_cnt = admindao.getreportcnt(user_seq);
			all_user.get(i).setReport_cnt(report_cnt);
		}
		return all_user;
	}

}
