package com.web.curation.model;

public class Communication {

	private int user_seq;
	private String user_nickname;
	private int opponent_seq;
	private String opponent_nickname;
	private int user_auth;
	private int opponent_auth;
	private String temp;

	public Communication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Communication(int user_seq, String user_nickname, int opponent_seq, String opponent_nickname, int user_auth,
			int opponent_auth, String temp) {
		super();
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.opponent_seq = opponent_seq;
		this.opponent_nickname = opponent_nickname;
		this.user_auth = user_auth;
		this.opponent_auth = opponent_auth;
		this.temp = temp;
	}
	
	public Communication(int user_seq, String opponent_nickname, String temp) {
		super();
		this.user_seq = user_seq;
		this.opponent_nickname = opponent_nickname;
		this.temp = temp;
	}

	public int getOpponent_seq() {
		return opponent_seq;
	}

	public void setOpponent_seq(int opponent_seq) {
		this.opponent_seq = opponent_seq;
	}

	public Communication(int user_seq, String opponent_nickname, int opponent_auth, String temp) {
		super();
		this.user_seq = user_seq;
		this.opponent_nickname = opponent_nickname;
		this.opponent_auth = opponent_auth;
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "Communication [user_seq=" + user_seq + ", user_nickname=" + user_nickname + ", opponent_nickname="
				+ opponent_nickname + ", user_auth=" + user_auth + ", opponent_auth=" + opponent_auth + ", temp=" + temp
				+ "]";
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

	public String getOpponent_nickname() {
		return opponent_nickname;
	}

	public void setOpponent_nickname(String opponent_nickname) {
		this.opponent_nickname = opponent_nickname;
	}

	public int getUser_auth() {
		return user_auth;
	}

	public void setUser_auth(int user_auth) {
		this.user_auth = user_auth;
	}

	public int getOpponent_auth() {
		return opponent_auth;
	}

	public void setOpponent_auth(int opponent_auth) {
		this.opponent_auth = opponent_auth;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

}