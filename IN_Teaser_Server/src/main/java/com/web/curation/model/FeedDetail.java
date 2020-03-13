package com.web.curation.model;

import java.util.Date;
import java.util.List;

public class FeedDetail {
	
	/*게시글*/
	private int feed_seq;
	private String feed_subject;
	private String feed_contents;
	private String feed_hashtag;
	private Date insert_date;
	private String post_date;
	private String feed_scope;
	/*게시글 작성자*/
	private int user_seq;
	private int user_auth;//필요?
	private String user_nickname;
	private String user_profile_route;
	/*로그인 한 사용자*/
	private boolean login_user_like;
	private int feedlike_seq;
	private boolean login_user_scrap;
	private int feedscrap_seq;
	/*댓글*/
	private List<Comment> comment;
	private int totalCount;
	public FeedDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeedDetail(int feed_seq, String feed_subject, String feed_contents, String feed_hashtag, Date insert_date,
			String post_date, String feed_scope, int user_seq, int user_auth, String user_nickname,
			String user_profile_route, boolean login_user_like, int feedlike_seq, boolean login_user_scrap,
			int feedscrap_seq, List<Comment> comment, int totalCount) {
		super();
		this.feed_seq = feed_seq;
		this.feed_subject = feed_subject;
		this.feed_contents = feed_contents;
		this.feed_hashtag = feed_hashtag;
		this.insert_date = insert_date;
		this.post_date = post_date;
		this.feed_scope = feed_scope;
		this.user_seq = user_seq;
		this.user_auth = user_auth;
		this.user_nickname = user_nickname;
		this.user_profile_route = user_profile_route;
		this.login_user_like = login_user_like;
		this.feedlike_seq = feedlike_seq;
		this.login_user_scrap = login_user_scrap;
		this.feedscrap_seq = feedscrap_seq;
		this.comment = comment;
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "FeedDetail [feed_seq=" + feed_seq + ", feed_subject=" + feed_subject + ", feed_contents="
				+ feed_contents + ", feed_hashtag=" + feed_hashtag + ", insert_date=" + insert_date + ", post_date="
				+ post_date + ", feed_scope=" + feed_scope + ", user_seq=" + user_seq + ", user_auth=" + user_auth
				+ ", user_nickname=" + user_nickname + ", user_profile_route=" + user_profile_route
				+ ", login_user_like=" + login_user_like + ", feedlike_seq=" + feedlike_seq + ", login_user_scrap="
				+ login_user_scrap + ", feedscrap_seq=" + feedscrap_seq + ", comment=" + comment + ", totalCount="
				+ totalCount + "]";
	}
	public int getFeed_seq() {
		return feed_seq;
	}
	public void setFeed_seq(int feed_seq) {
		this.feed_seq = feed_seq;
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
	public String getFeed_hashtag() {
		return feed_hashtag;
	}
	public void setFeed_hashtag(String feed_hashtag) {
		this.feed_hashtag = feed_hashtag;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	public String getFeed_scope() {
		return feed_scope;
	}
	public void setFeed_scope(String feed_scope) {
		this.feed_scope = feed_scope;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public int getUser_auth() {
		return user_auth;
	}
	public void setUser_auth(int user_auth) {
		this.user_auth = user_auth;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_profile_route() {
		return user_profile_route;
	}
	public void setUser_profile_route(String user_profile_route) {
		this.user_profile_route = user_profile_route;
	}
	public boolean isLogin_user_like() {
		return login_user_like;
	}
	public void setLogin_user_like(boolean login_user_like) {
		this.login_user_like = login_user_like;
	}
	public int getFeedlike_seq() {
		return feedlike_seq;
	}
	public void setFeedlike_seq(int feedlike_seq) {
		this.feedlike_seq = feedlike_seq;
	}
	public boolean isLogin_user_scrap() {
		return login_user_scrap;
	}
	public void setLogin_user_scrap(boolean login_user_scrap) {
		this.login_user_scrap = login_user_scrap;
	}
	public int getFeedscrap_seq() {
		return feedscrap_seq;
	}
	public void setFeedscrap_seq(int feedscrap_seq) {
		this.feedscrap_seq = feedscrap_seq;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	

	
	
	
}
	