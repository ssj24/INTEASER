package com.web.curation.model;

import java.util.List;

public class FeedPage {
	private List<Feed> feedlist;
	private int currentPage;
	private int startPage;
	private int endPage;
	private int totPage;
	public FeedPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeedPage(List<Feed> feedlist, int currentPage, int startPage, int endPage, int totPage) {
		super();
		this.feedlist = feedlist;
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totPage = totPage;
	}
	@Override
	public String toString() {
		return "FeedPage [feedlist=" + feedlist + ", currentPage=" + currentPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", totPage=" + totPage + "]";
	}
	public List<Feed> getFeedlist() {
		return feedlist;
	}
	public void setFeedlist(List<Feed> feedlist) {
		this.feedlist = feedlist;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}

	

}
