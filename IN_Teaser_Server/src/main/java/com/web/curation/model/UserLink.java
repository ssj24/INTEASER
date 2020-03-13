package com.web.curation.model;

import java.util.Date;

public class UserLink {
	private int userlink_seq;
	private int user_seq;
	private String user_nickname;
	private String linkname;
	private String link;
	private Date insert_date;
	public UserLink() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserLink(int userlink_seq, int user_seq, String user_nickname, String linkname, String link,
			Date insert_date) {
		super();
		this.userlink_seq = userlink_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.linkname = linkname;
		this.link = link;
		this.insert_date = insert_date;
	}
	@Override
	public String toString() {
		return "UserLink [userlink_seq=" + userlink_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", linkname=" + linkname + ", link=" + link + ", insert_date=" + insert_date + "]";
	}
	public int getUserlink_seq() {
		return userlink_seq;
	}
	public void setUserlink_seq(int userlink_seq) {
		this.userlink_seq = userlink_seq;
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
	public String getLinkname() {
		return linkname;
	}
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}
	
	
}
