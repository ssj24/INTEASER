package com.web.curation.model;

import java.util.Date;

public class UserHide {

	private int userhide_seq;
	private int user_seq;
	private String user_nickname;
	private int opponent_seq;
	private String opponent_nickname;
	private Date insert_date;
	public UserHide() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserHide(int userhide_seq, int user_seq, String user_nickname, int opponent_seq, String opponent_nickname,
			Date insert_date) {
		super();
		this.userhide_seq = userhide_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.opponent_seq = opponent_seq;
		this.opponent_nickname = opponent_nickname;
		this.insert_date = insert_date;
	}
	@Override
	public String toString() {
		return "UserHide [userhide_seq=" + userhide_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", opponent_seq=" + opponent_seq + ", opponent_nickname=" + opponent_nickname + ", insert_date="
				+ insert_date + "]";
	}
	public int getUserhide_seq() {
		return userhide_seq;
	}
	public void setUserhide_seq(int userhide_seq) {
		this.userhide_seq = userhide_seq;
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
	public int getOpponent_seq() {
		return opponent_seq;
	}
	public void setOpponent_seq(int opponent_seq) {
		this.opponent_seq = opponent_seq;
	}
	public String getOpponent_nickname() {
		return opponent_nickname;
	}
	public void setOpponent_nickname(String opponent_nickname) {
		this.opponent_nickname = opponent_nickname;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}
	
	
}
