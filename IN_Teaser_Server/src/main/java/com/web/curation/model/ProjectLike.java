package com.web.curation.model;

import java.util.Date;

public class ProjectLike {
	private int project_like_seq;
	private int user_seq;
	private String user_nickname;
	private int project_seq;
	private Date insert_date;
	public ProjectLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectLike(int project_like_seq, int user_seq, String user_nickname, int project_seq, Date insert_date) {
		super();
		this.project_like_seq = project_like_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.project_seq = project_seq;
		this.insert_date = insert_date;
	}
	@Override
	public String toString() {
		return "ProjectLike [project_like_seq=" + project_like_seq + ", user_seq=" + user_seq + ", user_nickname="
				+ user_nickname + ", project_seq=" + project_seq + ", insert_date=" + insert_date + "]";
	}
	public int getProject_like_seq() {
		return project_like_seq;
	}
	public void setProject_like_seq(int project_like_seq) {
		this.project_like_seq = project_like_seq;
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
	public int getProject_seq() {
		return project_seq;
	}
	public void setProject_seq(int project_seq) {
		this.project_seq = project_seq;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}

	
	
}
