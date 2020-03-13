package com.web.curation.model;

public class FollowList {
	private int followlist_seq;
	private int follow_state;
	private int user_seq;
	private String user_nickname;
	private int opponent_seq;
	private String opponent_nickname;
	private int opponent_auth;
	private String acceptYN;
	private boolean new_result;
	private boolean old_buffer;

	public FollowList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FollowList(int user_seq, int opponent_seq) {
		super();
		this.user_seq = user_seq;
		this.opponent_seq = opponent_seq;
	}

	public FollowList(int followlist_seq, int follow_state, int user_seq, String user_nickname, int opponent_seq,
			String opponent_nickname, int opponent_auth, String acceptYN, boolean new_result, boolean old_buffer) {
		super();
		this.followlist_seq = followlist_seq;
		this.follow_state = follow_state;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.opponent_seq = opponent_seq;
		this.opponent_nickname = opponent_nickname;
		this.opponent_auth = opponent_auth;
		this.acceptYN = acceptYN;
		this.new_result = new_result;
		this.old_buffer = old_buffer;
	}

	@Override
	public String toString() {
		return "FollowList [followlist_seq=" + followlist_seq + ", follow_state=" + follow_state + ", user_seq="
				+ user_seq + ", user_nickname=" + user_nickname + ", opponent_seq=" + opponent_seq
				+ ", opponent_nickname=" + opponent_nickname + ", opponent_auth=" + opponent_auth + ", acceptYN="
				+ acceptYN + ", new_result=" + new_result + ", old_buffer=" + old_buffer + "]";
	}

	public int getFollowlist_seq() {
		return followlist_seq;
	}

	public void setFollowlist_seq(int followlist_seq) {
		this.followlist_seq = followlist_seq;
	}

	public int getFollow_state() {
		return follow_state;
	}

	public void setFollow_state(int follow_state) {
		this.follow_state = follow_state;
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

	public int getOpponent_auth() {
		return opponent_auth;
	}

	public void setOpponent_auth(int opponent_auth) {
		this.opponent_auth = opponent_auth;
	}

	public String getAcceptYN() {
		return acceptYN;
	}

	public void setAcceptYN(String acceptYN) {
		this.acceptYN = acceptYN;
	}

	public boolean isNew_result() {
		return new_result;
	}

	public void setNew_result(boolean new_result) {
		this.new_result = new_result;
	}

	public boolean isOld_buffer() {
		return old_buffer;
	}

	public void setOld_buffer(boolean old_buffer) {
		this.old_buffer = old_buffer;
	}

}