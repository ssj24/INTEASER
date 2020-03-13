package com.web.curation.model;

public class DmList {

	private int server_seq;
	private int user_seq;
	private int opponent_seq;
	private String opponent_nickname;
	private String content;
	private int new_content_cnt;
	private String time;

	public DmList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DmList(int server_seq, int user_seq, int opponent_seq, String opponent_nickname, String content,
			int new_content_cnt, String time) {
		super();
		this.server_seq = server_seq;
		this.user_seq = user_seq;
		this.opponent_seq = opponent_seq;
		this.opponent_nickname = opponent_nickname;
		this.content = content;
		this.new_content_cnt = new_content_cnt;
		this.time = time;
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

	public String getOpponent_nickname() {
		return opponent_nickname;
	}

	public void setOpponent_nickname(String opponent_nickname) {
		this.opponent_nickname = opponent_nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNew_content_cnt() {
		return new_content_cnt;
	}

	public void setNew_content_cnt(int new_content_cnt) {
		this.new_content_cnt = new_content_cnt;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}