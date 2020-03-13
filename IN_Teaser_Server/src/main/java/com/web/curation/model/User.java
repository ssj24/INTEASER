package com.web.curation.model;

import java.util.Date;

public class User {
	private int user_seq;
	private String user_pwd;
	private int user_auth;
	private String user_email;
	private boolean user_email_onoff;
	private String user_certification_code;
	private String user_email_certification;
	private Date user_certification_deadline;
	private String login_type;
	private String user_nickname;
	private String user_name;
	private String user_intro;
	private boolean user_intro_onoff;
	private String user_utilityYN;
	private String user_salt;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int user_seq) {
		super();
		this.user_seq = user_seq;
	}

	public User(int user_seq, String user_pwd, int user_auth, String user_email, boolean user_email_onoff,
			String user_certification_code, String user_email_certification, Date user_certification_deadline,
			String login_type, String user_nickname, String user_name, String user_intro, boolean user_intro_onoff,
			String user_utilityYN, String user_salt) {
		super();
		this.user_seq = user_seq;
		this.user_pwd = user_pwd;
		this.user_auth = user_auth;
		this.user_email = user_email;
		this.user_email_onoff = user_email_onoff;
		this.user_certification_code = user_certification_code;
		this.user_email_certification = user_email_certification;
		this.user_certification_deadline = user_certification_deadline;
		this.login_type = login_type;
		this.user_nickname = user_nickname;
		this.user_name = user_name;
		this.user_intro = user_intro;
		this.user_intro_onoff = user_intro_onoff;
		this.user_utilityYN = user_utilityYN;
		this.user_salt = user_salt;
	}

	@Override
	public String toString() {
		return "User [user_seq=" + user_seq + ", user_pwd=" + user_pwd + ", user_auth=" + user_auth + ", user_email="
				+ user_email + ", user_email_onoff=" + user_email_onoff + ", user_certification_code="
				+ user_certification_code + ", user_email_certification=" + user_email_certification
				+ ", user_certification_deadline=" + user_certification_deadline + ", login_type=" + login_type
				+ ", user_nickname=" + user_nickname + ", user_name=" + user_name + ", user_intro=" + user_intro
				+ ", user_intro_onoff=" + user_intro_onoff + ", user_utilityYN=" + user_utilityYN + ", user_salt="
				+ user_salt + "]";
	}

	public int getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public int getUser_auth() {
		return user_auth;
	}

	public void setUser_auth(int user_auth) {
		this.user_auth = user_auth;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public boolean isUser_email_onoff() {
		return user_email_onoff;
	}

	public void setUser_email_onoff(boolean user_email_onoff) {
		this.user_email_onoff = user_email_onoff;
	}

	public String getUser_certification_code() {
		return user_certification_code;
	}

	public void setUser_certification_code(String user_certification_code) {
		this.user_certification_code = user_certification_code;
	}

	public String getUser_email_certification() {
		return user_email_certification;
	}

	public void setUser_email_certification(String user_email_certification) {
		this.user_email_certification = user_email_certification;
	}

	public Date getUser_certification_deadline() {
		return user_certification_deadline;
	}

	public void setUser_certification_deadline(Date user_certification_deadline) {
		this.user_certification_deadline = user_certification_deadline;
	}

	public String getLogin_type() {
		return login_type;
	}

	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_intro() {
		return user_intro;
	}

	public void setUser_intro(String user_intro) {
		this.user_intro = user_intro;
	}

	public boolean isUser_intro_onoff() {
		return user_intro_onoff;
	}

	public void setUser_intro_onoff(boolean user_intro_onoff) {
		this.user_intro_onoff = user_intro_onoff;
	}

	public String getuser_utilityYN() {
		return user_utilityYN;
	}

	public void setuser_utilityYN(String user_utilityYN) {
		this.user_utilityYN = user_utilityYN;
	}

	public String getUser_salt() {
		return user_salt;
	}

	public void setUser_salt(String user_salt) {
		this.user_salt = user_salt;
	}

}
