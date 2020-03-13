package com.web.curation.model;

import java.util.List;

public class Pagination {

	private int user_seq;
	private long since_id;
	private long max_id;
	private long final_id;
	private int count;
	private List<FeedDetail> feed;

	public Pagination() {
		super();
	}

	public Pagination(int user_seq, long since_id, long max_id, long final_id, int count, List<FeedDetail> feed) {
		super();
		this.user_seq = user_seq;
		this.since_id = since_id;
		this.max_id = max_id;
		this.final_id = final_id;
		this.count = count;
		this.feed = feed;
	}

	public Pagination(int user_seq, long since_id, long max_id, long final_id, int count) {
		super();
		this.user_seq = user_seq;
		this.since_id = since_id;
		this.max_id = max_id;
		this.final_id = final_id;
		this.count = count;
	}

	@Override
	public String toString() {
		return "Pagination [since_id=" + since_id + ", max_id=" + max_id + ", final_id=" + final_id + ", count=" + count
				+ ", feed=" + feed + "]";
	}

	public int getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}

	public long getSince_id() {
		return since_id;
	}

	public void setSince_id(long since_id) {
		this.since_id = since_id;
	}

	public long getMax_id() {
		return max_id;
	}

	public void setMax_id(long max_id) {
		this.max_id = max_id;
	}

	public long getFinal_id() {
		return final_id;
	}

	public void setFinal_id(long final_id) {
		this.final_id = final_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<FeedDetail> getFeed() {
		return feed;
	}

	public void setFeed(List<FeedDetail> feed) {
		this.feed = feed;
	}

}
