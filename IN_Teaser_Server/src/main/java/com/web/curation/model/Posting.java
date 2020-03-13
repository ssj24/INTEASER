package com.web.curation.model;

import java.sql.Date;

public class Posting {

	private String date;
	private int posting_cnt;

	public Posting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Posting(String date, int posting_cnt) {
		super();
		this.date = date;
		this.posting_cnt = posting_cnt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPosting_cnt() {
		return posting_cnt;
	}

	public void setPosting_cnt(int posting_cnt) {
		this.posting_cnt = posting_cnt;
	}

	@Override
	public String toString() {
		return "Posting [date=" + date + ", posting_cnt=" + posting_cnt + "]";
	}

}
