package com.web.curation.model;

import java.util.Date;

public class Comment {
	private int comment_seq;
	private int user_seq;
	private String user_nickname;
	private int feed_seq;
	private String comment;
	private int group_id;
	private int order_num;
	private String insert_date;
	private String post_date;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(int comment_seq, int user_seq, String user_nickname, int feed_seq, String comment, int group_id,
			int order_num, String insert_date, String post_date) {
		super();
		this.comment_seq = comment_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.feed_seq = feed_seq;
		this.comment = comment;
		this.group_id = group_id;
		this.order_num = order_num;
		this.insert_date = insert_date;
		this.post_date = post_date;
	}
	@Override
	public String toString() {
		return "Comment [comment_seq=" + comment_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", feed_seq=" + feed_seq + ", comment=" + comment + ", group_id=" + group_id + ", order_num="
				+ order_num + ", insert_date=" + insert_date + ", post_date=" + post_date + "]";
	}
	public int getComment_seq() {
		return comment_seq;
	}
	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	
	
	
	
	
}
