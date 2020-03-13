package com.web.curation.model;

import java.util.Date;

public class Feed {
	private int feed_seq;
	private int user_seq;
	private String user_nickname;
	private String feed_subject;
	private String feed_contents;
	private int feed_like_cnt;
	private int feed_scrap_cnt;
	private String feed_scope;
	private String feed_hashtag;
	private int feed_report_cnt;
	private Date insert_date;
	private String format_date;

	private int login_user_seq;

	public Feed() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feed(int feed_seq, int user_seq, String user_nickname, String feed_subject, String feed_contents,
			int feed_like_cnt, int feed_scrap_cnt, String feed_scope, String feed_hashtag, int feed_report_cnt,
			Date insert_date, String format_date, int login_user_seq) {
		super();
		this.feed_seq = feed_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.feed_subject = feed_subject;
		this.feed_contents = feed_contents;
		this.feed_like_cnt = feed_like_cnt;
		this.feed_scrap_cnt = feed_scrap_cnt;
		this.feed_scope = feed_scope;
		this.feed_hashtag = feed_hashtag;
		this.feed_report_cnt = feed_report_cnt;
		this.insert_date = insert_date;
		this.format_date = format_date;
		this.login_user_seq = login_user_seq;
	}

	public Feed(int user_seq) {
		super();
		this.user_seq = user_seq;
	}

	@Override
	public String toString() {
		return "Feed [feed_seq=" + feed_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", feed_subject=" + feed_subject + ", feed_contents=" + feed_contents + ", feed_like_cnt="
				+ feed_like_cnt + ", feed_scrap_cnt=" + feed_scrap_cnt + ", feed_scope=" + feed_scope
				+ ", feed_hashtag=" + feed_hashtag + ", feed_report_cnt=" + feed_report_cnt + ", insert_date="
				+ insert_date + "]";
	}

	public int getLogin_user_seq() {
		return login_user_seq;
	}

	public void setLogin_user_seq(int login_user_seq) {
		this.login_user_seq = login_user_seq;
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

	public String getFeed_subject() {
		return feed_subject;
	}

	public void setFeed_subject(String feed_subject) {
		this.feed_subject = feed_subject;
	}

	public String getFeed_contents() {
		return feed_contents;
	}

	public void setFeed_contents(String feed_contents) {
		this.feed_contents = feed_contents;
	}

	public int getFeed_like_cnt() {
		return feed_like_cnt;
	}

	public void setFeed_like_cnt(int feed_like_cnt) {
		this.feed_like_cnt = feed_like_cnt;
	}

	public int getFeed_scrap_cnt() {
		return feed_scrap_cnt;
	}

	public void setFeed_scrap_cnt(int feed_scrap_cnt) {
		this.feed_scrap_cnt = feed_scrap_cnt;
	}

	public String getFeed_scope() {
		return feed_scope;
	}

	public void setFeed_scope(String feed_scope) {
		this.feed_scope = feed_scope;
	}

	public String getFeed_hashtag() {
		return feed_hashtag;
	}

	public void setFeed_hashtag(String feed_hashtag) {
		this.feed_hashtag = feed_hashtag;
	}

	public int getFeed_report_cnt() {
		return feed_report_cnt;
	}

	public void setFeed_report_cnt(int feed_report_cnt) {
		this.feed_report_cnt = feed_report_cnt;
	}

	public Date getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}

	public String getFormat_date() {
		return format_date;
	}

	public void setFormat_date(String format_date) {
		this.format_date = format_date;
	}

}
