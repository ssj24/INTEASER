package com.web.curation.model;

import java.util.Arrays;
import java.util.Date;

public class ProjectDetail {
	private int project_seq;
	private int user_seq;
	private String user_nickname;
	private int portfolio_seq;
	private String project_name;
	private String project_intro;
	private String project_start;
	private String project_end;
	private String user_profile_route;
	private String project_dev_setting;
	private int project_personnel;
	private String project_position;
	private String project_content;
	private int project_like_cnt;
	private String project_link;
	private String project_hashtag;
	private Date insert_date;
	/*로그인 한 사용자*/
	private boolean login_user_like;
	private int project_like_seq;
	public ProjectDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectDetail(int project_seq, int user_seq, String user_nickname, int portfolio_seq, String project_name,
			String project_intro, String project_start, String project_end, String user_profile_route,
			String project_dev_setting, int project_personnel, String project_position, String project_content,
			int project_like_cnt, String project_link, String project_hashtag, Date insert_date,
			boolean login_user_like, int project_like_seq) {
		super();
		this.project_seq = project_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.portfolio_seq = portfolio_seq;
		this.project_name = project_name;
		this.project_intro = project_intro;
		this.project_start = project_start;
		this.project_end = project_end;
		this.user_profile_route = user_profile_route;
		this.project_dev_setting = project_dev_setting;
		this.project_personnel = project_personnel;
		this.project_position = project_position;
		this.project_content = project_content;
		this.project_like_cnt = project_like_cnt;
		this.project_link = project_link;
		this.project_hashtag = project_hashtag;
		this.insert_date = insert_date;
		this.login_user_like = login_user_like;
		this.project_like_seq = project_like_seq;
	}
	@Override
	public String toString() {
		return "ProjectDetail [project_seq=" + project_seq + ", user_seq=" + user_seq + ", user_nickname="
				+ user_nickname + ", portfolio_seq=" + portfolio_seq + ", project_name=" + project_name
				+ ", project_intro=" + project_intro + ", project_start=" + project_start + ", project_end="
				+ project_end + ", user_profile_route=" + user_profile_route + ", project_dev_setting="
				+ project_dev_setting + ", project_personnel=" + project_personnel + ", project_position="
				+ project_position + ", project_content=" + project_content + ", project_like_cnt=" + project_like_cnt
				+ ", project_link=" + project_link + ", project_hashtag=" + project_hashtag + ", insert_date="
				+ insert_date + ", login_user_like=" + login_user_like + ", project_like_seq=" + project_like_seq + "]";
	}
	public int getProject_seq() {
		return project_seq;
	}
	public void setProject_seq(int project_seq) {
		this.project_seq = project_seq;
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
	public int getPortfolio_seq() {
		return portfolio_seq;
	}
	public void setPortfolio_seq(int portfolio_seq) {
		this.portfolio_seq = portfolio_seq;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_intro() {
		return project_intro;
	}
	public void setProject_intro(String project_intro) {
		this.project_intro = project_intro;
	}
	public String getProject_start() {
		return project_start;
	}
	public void setProject_start(String project_start) {
		this.project_start = project_start;
	}
	public String getProject_end() {
		return project_end;
	}
	public void setProject_end(String project_end) {
		this.project_end = project_end;
	}
	public String getUser_profile_route() {
		return user_profile_route;
	}
	public void setUser_profile_route(String user_profile_route) {
		this.user_profile_route = user_profile_route;
	}
	public String getProject_dev_setting() {
		return project_dev_setting;
	}
	public void setProject_dev_setting(String project_dev_setting) {
		this.project_dev_setting = project_dev_setting;
	}
	public int getProject_personnel() {
		return project_personnel;
	}
	public void setProject_personnel(int project_personnel) {
		this.project_personnel = project_personnel;
	}
	public String getProject_position() {
		return project_position;
	}
	public void setProject_position(String project_position) {
		this.project_position = project_position;
	}
	public String getProject_content() {
		return project_content;
	}
	public void setProject_content(String project_content) {
		this.project_content = project_content;
	}
	public int getProject_like_cnt() {
		return project_like_cnt;
	}
	public void setProject_like_cnt(int project_like_cnt) {
		this.project_like_cnt = project_like_cnt;
	}
	public String getProject_link() {
		return project_link;
	}
	public void setProject_link(String project_link) {
		this.project_link = project_link;
	}
	public String getProject_hashtag() {
		return project_hashtag;
	}
	public void setProject_hashtag(String project_hashtag) {
		this.project_hashtag = project_hashtag;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}
	public boolean isLogin_user_like() {
		return login_user_like;
	}
	public void setLogin_user_like(boolean login_user_like) {
		this.login_user_like = login_user_like;
	}
	public int getProject_like_seq() {
		return project_like_seq;
	}
	public void setProject_like_seq(int project_like_seq) {
		this.project_like_seq = project_like_seq;
	}
	
	

}
