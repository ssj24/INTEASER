package com.web.curation.model;

import java.util.Date;

public class FeedHide {
	
	private int feedhide_seq;
	private int user_seq;
	private String user_nickname;
	private int feed_seq;
	private Date insert_date;
	public FeedHide() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeedHide(int feedhide_seq, int user_seq, String user_nickname, int feed_seq, Date insert_date) {
		super();
		this.feedhide_seq = feedhide_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.feed_seq = feed_seq;
		this.insert_date = insert_date;
	}
	@Override
	public String toString() {
		return "FeedHide [feedhide_seq=" + feedhide_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", feed_seq=" + feed_seq + ", insert_date=" + insert_date + "]";
	}
	public int getFeedhide_seq() {
		return feedhide_seq;
	}
	public void setFeedhide_seq(int feedhide_seq) {
		this.feedhide_seq = feedhide_seq;
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
	public int getFeed_seq() {
		return feed_seq;
	}
	public void setFeed_seq(int feed_seq) {
		this.feed_seq = feed_seq;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}

	
}
