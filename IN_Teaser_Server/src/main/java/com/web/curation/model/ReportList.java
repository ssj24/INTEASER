package com.web.curation.model;

import java.util.Date;

public class ReportList {

	private int report_seq;
	private int user_seq;
	private String user_nickname;
	private int feed_seq;
	private int opponent_seq;
	private String opponent_nickname;
	private String admin_confirmYN;
	private Date insert_date;
	public ReportList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportList(int report_seq, int user_seq, String user_nickname, int feed_seq, int opponent_seq,
			String opponent_nickname, String admin_confirmYN, Date insert_date) {
		super();
		this.report_seq = report_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.feed_seq = feed_seq;
		this.opponent_seq = opponent_seq;
		this.opponent_nickname = opponent_nickname;
		this.admin_confirmYN = admin_confirmYN;
		this.insert_date = insert_date;
	}
	@Override
	public String toString() {
		return "ReportList [report_seq=" + report_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", feed_seq=" + feed_seq + ", opponent_seq=" + opponent_seq + ", opponent_nickname="
				+ opponent_nickname + ", admin_confirmYN=" + admin_confirmYN + ", insert_date=" + insert_date + "]";
	}
	public int getReport_seq() {
		return report_seq;
	}
	public void setReport_seq(int report_seq) {
		this.report_seq = report_seq;
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
	public String getAdmin_confirmYN() {
		return admin_confirmYN;
	}
	public void setAdmin_confirmYN(String admin_confirmYN) {
		this.admin_confirmYN = admin_confirmYN;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}

	
}
