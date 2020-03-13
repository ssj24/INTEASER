package com.web.curation.model;

public class Notice {

	private int notice_seq;
	private int user_seq;
	private String user_nickname;
	private String opponent_nickname;
	private String notice_content;
	private boolean new_contentYN;
	private boolean old_buffer;
	private boolean notice_tabYN;
	private int feed_seq;
	private int project_seq;

	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(int notice_seq, int user_seq, String user_nickname, String opponent_nickname, String notice_content,
			boolean new_contentYN, boolean old_buffer, boolean notice_tabYN, int feed_seq, int project_seq) {
		super();
		this.notice_seq = notice_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.opponent_nickname = opponent_nickname;
		this.notice_content = notice_content;
		this.new_contentYN = new_contentYN;
		this.old_buffer = old_buffer;
		this.notice_tabYN = notice_tabYN;
		this.feed_seq = feed_seq;
		this.project_seq = project_seq;
	}

	public int getProject_seq() {
		return project_seq;
	}

	public void setProject_seq(int project_seq) {
		this.project_seq = project_seq;
	}

	public int getFeed_seq() {
		return feed_seq;
	}

	public void setFeed_seq(int feed_seq) {
		this.feed_seq = feed_seq;
	}

	public Notice(int user_seq, int notice_seq) {
		super();
		this.user_seq = user_seq;
		this.notice_seq = notice_seq;
	}

	public int getNotice_seq() {
		return notice_seq;
	}

	public void setNotice_seq(int notice_seq) {
		this.notice_seq = notice_seq;
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

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public boolean isNew_contentYN() {
		return new_contentYN;
	}

	public void setNew_contentYN(boolean new_contentYN) {
		this.new_contentYN = new_contentYN;
	}

	public boolean isOld_buffer() {
		return old_buffer;
	}

	public void setOld_buffer(boolean old_buffer) {
		this.old_buffer = old_buffer;
	}

	public boolean isNotice_tabYN() {
		return notice_tabYN;
	}

	public void setNotice_tabYN(boolean notice_tabYN) {
		this.notice_tabYN = notice_tabYN;
	}

	@Override
	public String toString() {
		return "Notice [notice_seq=" + notice_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", opponent_nickname=" + opponent_nickname + ", notice_content=" + notice_content + ", new_contentYN="
				+ new_contentYN + ", old_buffer=" + old_buffer + ", notice_tabYN=" + notice_tabYN + "]";
	}

}