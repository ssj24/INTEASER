package com.web.curation.model;

public class Reporter {

	private String reporter_seq;
	private String reporter_nickname;
	private String report_content;

	public Reporter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reporter(String reporter_seq, String reporter_nickname, String report_content) {
		super();
		this.reporter_seq = reporter_seq;
		this.reporter_nickname = reporter_nickname;
		this.report_content = report_content;
	}

	@Override
	public String toString() {
		return "Reporter [reporter_seq=" + reporter_seq + ", reporter_nickname=" + reporter_nickname
				+ ", report_content=" + report_content + "]";
	}

	public String getreporter_seq() {
		return reporter_seq;
	}

	public void setreporter_seq(String reporter_seq) {
		this.reporter_seq = reporter_seq;
	}

	public String getreporter_nickname() {
		return reporter_nickname;
	}

	public void setreporter_nickname(String reporter_nickname) {
		this.reporter_nickname = reporter_nickname;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

}
