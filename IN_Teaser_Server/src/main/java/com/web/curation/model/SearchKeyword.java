package com.web.curation.model;

import java.util.Date;

public class SearchKeyword {
	
	private int keyword_seq;
	private int user_seq;
	private String user_nickname;
	private String search_keyword;
	private int search_keyword_user_seq;
	private Date insert_date;
	public SearchKeyword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchKeyword(int keyword_seq, int user_seq, String user_nickname, String search_keyword,
			int search_keyword_user_seq, Date insert_date) {
		super();
		this.keyword_seq = keyword_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.search_keyword = search_keyword;
		this.search_keyword_user_seq = search_keyword_user_seq;
		this.insert_date = insert_date;
	}
	@Override
	public String toString() {
		return "SearchKeyword [keyword_seq=" + keyword_seq + ", user_seq=" + user_seq + ", user_nickname="
				+ user_nickname + ", search_keyword=" + search_keyword + ", search_keyword_user_seq="
				+ search_keyword_user_seq + ", insert_date=" + insert_date + "]";
	}
	public int getKeyword_seq() {
		return keyword_seq;
	}
	public void setKeyword_seq(int keyword_seq) {
		this.keyword_seq = keyword_seq;
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
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	public int getSearch_keyword_user_seq() {
		return search_keyword_user_seq;
	}
	public void setSearch_keyword_user_seq(int search_keyword_user_seq) {
		this.search_keyword_user_seq = search_keyword_user_seq;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}
	
	
	
	
}
