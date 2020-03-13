package com.web.curation.model;

import java.util.Arrays;

public class AboutMe {

	private int aboutme_seq;
	private int user_seq;
	private String user_nickname;
	private String[] technical_stack;
	private String interests;
	private boolean interests_onoff;
	private String contact;
	private boolean contact_onoff;
	private String user_profile_route;

	public AboutMe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AboutMe(int aboutme_seq, int user_seq, String user_nickname, String[] technical_stack, String interests,
			boolean interests_onoff, String contact, boolean contact_onoff, String user_profile_route) {
		super();
		this.aboutme_seq = aboutme_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.technical_stack = technical_stack;
		this.interests = interests;
		this.interests_onoff = interests_onoff;
		this.contact = contact;
		this.contact_onoff = contact_onoff;
		this.user_profile_route = user_profile_route;
	}

	@Override
	public String toString() {
		return "AboutMe [aboutme_seq=" + aboutme_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", technical_stack=" + Arrays.toString(technical_stack) + ", interests=" + interests
				+ ", interests_onoff=" + interests_onoff + ", contact=" + contact + ", contact_onoff=" + contact_onoff
				+ ", user_profile_route=" + user_profile_route + "]";
	}

	public int getAboutme_seq() {
		return aboutme_seq;
	}

	public void setAboutme_seq(int aboutme_seq) {
		this.aboutme_seq = aboutme_seq;
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

	public String[] getTechnical_stack() {
		return technical_stack;
	}

	public void setTechnical_stack(String[] technical_stack) {
		this.technical_stack = technical_stack;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public boolean isInterests_onoff() {
		return interests_onoff;
	}

	public void setInterests_onoff(boolean interests_onoff) {
		this.interests_onoff = interests_onoff;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean isContact_onoff() {
		return contact_onoff;
	}

	public void setContact_onoff(boolean contact_onoff) {
		this.contact_onoff = contact_onoff;
	}

	public String getUser_profile_route() {
		return user_profile_route;
	}

	public void setUser_profile_route(String user_profile_route) {
		this.user_profile_route = user_profile_route;
	}

}