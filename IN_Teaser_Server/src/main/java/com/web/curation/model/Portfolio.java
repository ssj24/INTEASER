package com.web.curation.model;

public class Portfolio {
	private int portfolio_seq;
	private int user_seq;
	private String user_nickname;
	private boolean portfolio_onoff;
	public Portfolio() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Portfolio(int portfolio_seq, int user_seq, String user_nickname, boolean portfolio_onoff) {
		super();
		this.portfolio_seq = portfolio_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.portfolio_onoff = portfolio_onoff;
	}
	@Override
	public String toString() {
		return "Portfolio [portfolio_seq=" + portfolio_seq + ", user_seq=" + user_seq + ", user_nickname="
				+ user_nickname + ", portfolio_onoff=" + portfolio_onoff + "]";
	}
	public int getPortfolio_seq() {
		return portfolio_seq;
	}
	public void setPortfolio_seq(int portfolio_seq) {
		this.portfolio_seq = portfolio_seq;
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
	public boolean isPortfolio_onoff() {
		return portfolio_onoff;
	}
	public void setPortfolio_onoff(boolean portfolio_onoff) {
		this.portfolio_onoff = portfolio_onoff;
	}
	
	
	
}
