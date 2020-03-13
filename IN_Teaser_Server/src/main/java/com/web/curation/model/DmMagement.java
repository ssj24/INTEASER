package com.web.curation.model;

public class DmMagement {

	private int dm_management_seq;
	private int user_seq;
	private int opponent_seq;
	private int server_seq;

	public DmMagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DmMagement(int dm_management_seq, int user_seq, int opponent_seq, int server_seq) {
		super();
		this.dm_management_seq = dm_management_seq;
		this.user_seq = user_seq;
		this.opponent_seq = opponent_seq;
		this.server_seq = server_seq;
	}

	public DmMagement(int user_seq) {
		this.user_seq = user_seq;
	}

	public int getDm_management_seq() {
		return dm_management_seq;
	}

	public void setDm_management_seq(int dm_management_seq) {
		this.dm_management_seq = dm_management_seq;
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

	public int getServer_seq() {
		return server_seq;
	}

	public void setServer_seq(int server_seq) {
		this.server_seq = server_seq;
	}

}
