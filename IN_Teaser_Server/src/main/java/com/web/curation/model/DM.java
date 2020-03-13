package com.web.curation.model;

import java.sql.Date;

public class DM {

	private int dm_seq;
	private int server_seq;
	private int user_seq;
	private int opponent_seq;
	private String content;
	private Date insert_date;
	private boolean speaker;

	public DM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DM(int dm_seq, int server_seq, int user_seq, int opponent_seq, String content, Date insert_date,
			boolean speaker) {
		super();
		this.dm_seq = dm_seq;
		this.server_seq = server_seq;
		this.user_seq = user_seq;
		this.opponent_seq = opponent_seq;
		this.content = content;
		this.insert_date = insert_date;
		this.speaker = speaker;
	}

	public DM(int user_seq, int opponent_seq) {
		super();
		this.user_seq = user_seq;
		this.opponent_seq = opponent_seq;
	}

	public DM(int server_seq, int user_seq, int opponent_seq, String conent) {
		this.server_seq = server_seq;
		this.user_seq = user_seq;
		this.opponent_seq = opponent_seq;
		this.content = conent;
	}

	@Override
	public String toString() {
		return "DM [dm_seq=" + dm_seq + ", server_seq=" + server_seq + ", user_seq=" + user_seq + ", opponent_seq="
				+ opponent_seq + ", content=" + content + ", insert_date=" + insert_date + ", speaker=" + speaker + "]";
	}

	public int getDm_seq() {
		return dm_seq;
	}

	public void setDm_seq(int dm_seq) {
		this.dm_seq = dm_seq;
	}

	public int getServer_seq() {
		return server_seq;
	}

	public void setServer_seq(int server_seq) {
		this.server_seq = server_seq;
	}

	public int getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}

	public int getOpponent_seq() {
		return opponent_seq;
	}

	public void setOpponent_seq(int opponent_seq) {
		this.opponent_seq = opponent_seq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}

	public boolean isSpeaker() {
		return speaker;
	}

	public void setSpeaker(boolean speaker) {
		this.speaker = speaker;
	}

}
