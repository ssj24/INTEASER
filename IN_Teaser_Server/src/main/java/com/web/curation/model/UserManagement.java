package com.web.curation.model;

public class UserManagement {

	private int user_seq;
	private String user_nickname;
	private String user_email;
	private int post_cnt; // 게시글
	private int like_cnt;
	private int follower_cnt;
	private int following_cnt;
	private int reported_post_cnt;
	private int reported_cnt;
	private int report_cnt;
	private String user_utilityYN;

	public UserManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserManagement(int user_seq, String user_nickname, String user_email, int post_cnt, int like_cnt,
			int follower_cnt, int following_cnt, int reported_post_cnt, int reported_cnt, int report_cnt,
			String user_utilityYN) {
		super();
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.user_email = user_email;
		this.post_cnt = post_cnt;
		this.like_cnt = like_cnt;
		this.follower_cnt = follower_cnt;
		this.following_cnt = following_cnt;
		this.reported_post_cnt = reported_post_cnt;
		this.reported_cnt = reported_cnt;
		this.report_cnt = report_cnt;
		this.user_utilityYN = user_utilityYN;
	}

	@Override
	public String toString() {
		return "UserManagement [user_seq=" + user_seq + ", user_nickname=" + user_nickname + ", user_email="
				+ user_email + ", post_cnt=" + post_cnt + ", like_cnt=" + like_cnt + ", follower_cnt=" + follower_cnt
				+ ", following_cnt=" + following_cnt + ", reported_post_cnt=" + reported_post_cnt + ", reported_cnt="
				+ reported_cnt + ", report_cnt=" + report_cnt + ", user_utilityYN=" + user_utilityYN + "]";
	}
	
	public UserManagement(String user_nickname) {
		super();
		this.user_nickname = user_nickname;
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

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getPost_cnt() {
		return post_cnt;
	}

	public void setPost_cnt(int post_cnt) {
		this.post_cnt = post_cnt;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}

	public int getFollower_cnt() {
		return follower_cnt;
	}

	public void setFollower_cnt(int follower_cnt) {
		this.follower_cnt = follower_cnt;
	}

	public int getFollowing_cnt() {
		return following_cnt;
	}

	public void setFollowing_cnt(int following_cnt) {
		this.following_cnt = following_cnt;
	}

	public int getReported_post_cnt() {
		return reported_post_cnt;
	}

	public void setReported_post_cnt(int reported_post_cnt) {
		this.reported_post_cnt = reported_post_cnt;
	}

	public int getReported_cnt() {
		return reported_cnt;
	}

	public void setReported_cnt(int reported_cnt) {
		this.reported_cnt = reported_cnt;
	}

	public int getReport_cnt() {
		return report_cnt;
	}

	public void setReport_cnt(int report_cnt) {
		this.report_cnt = report_cnt;
	}

	public String getUser_utilityYN() {
		return user_utilityYN;
	}

	public void setUser_utilityYN(String user_utilityYN) {
		this.user_utilityYN = user_utilityYN;
	}

}