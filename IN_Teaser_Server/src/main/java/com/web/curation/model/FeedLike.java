package com.web.curation.model;

import java.util.Date;

public class FeedLike {
	private int feedlike_seq;
	private int user_seq;
	private String user_nickname;
	private int feed_seq;
	private Date insert_date;
	public FeedLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeedLike(int feedlike_seq, int user_seq, String user_nickname, int feed_seq, Date insert_date) {
		super();
		this.feedlike_seq = feedlike_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.feed_seq = feed_seq;
		this.insert_date = insert_date;
	}
	@Override
	public String toString() {
		return "FeedLike [feedlike_seq=" + feedlike_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", feed_seq=" + feed_seq + ", insert_date=" + insert_date + "]";
	}
	public int getFeedlike_seq() {
		return feedlike_seq;
	}
	public void setFeedlike_seq(int feedlike_seq) {
		this.feedlike_seq = feedlike_seq;
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
