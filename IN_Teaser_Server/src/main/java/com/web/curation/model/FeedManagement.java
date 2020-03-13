package com.web.curation.model;

import java.util.List;

public class FeedManagement {

	private int feed_seq;
	private int user_seq;
	private String user_nickname;
	private int feed_report_cnt;
	private List<Reporter> reporter;
	private String admin_confirmYN;
	private String deleteYN;

	public FeedManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeedManagement(int feed_seq, int user_seq, String user_nickname, int feed_report_cnt,
			List<Reporter> reporter, String admin_confirmYN, String deleteYN) {
		super();
		this.feed_seq = feed_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.feed_report_cnt = feed_report_cnt;
		this.reporter = reporter;
		this.admin_confirmYN = admin_confirmYN;
		this.deleteYN = deleteYN;
	}

	@Override
	public String toString() {
		return "FeedManagement [feed_seq=" + feed_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", feed_report_cnt=" + feed_report_cnt + ", reporter=" + reporter + ", admin_confirmYN="
				+ admin_confirmYN + ", deleteYN=" + deleteYN + "]";
	}

	public int getFeed_seq() {
		return feed_seq;
	}

	public void setFeed_seq(int feed_seq) {
		this.feed_seq = feed_seq;
	}

	public int getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public int getFeed_report_cnt() {
		return feed_report_cnt;
	}

	public void setFeed_report_cnt(int feed_report_cnt) {
		this.feed_report_cnt = feed_report_cnt;
	}

	public List<Reporter> getReporter() {
		return reporter;
	}

	public void setReporter(List<Reporter> reporter) {
		this.reporter = reporter;
	}

	public String getAdmin_confirmYN() {
		return admin_confirmYN;
	}

	public void setAdmin_confirmYN(String admin_confirmYN) {
		this.admin_confirmYN = admin_confirmYN;
	}

	public String getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	
}