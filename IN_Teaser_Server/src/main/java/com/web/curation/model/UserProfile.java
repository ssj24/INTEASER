package com.web.curation.model;

import java.util.Arrays;

public class UserProfile {
	private int aboutme_seq;
	private int user_seq;
	private String user_nickname;
	private int user_auth;
	private String[] technical_stack;
	private String user_profile_route;
	private String user_email;
	private boolean user_email_onoff;
	private String user_name;
	private boolean user_name_onoff;
	private String user_intro;
	private boolean user_intro_onoff;
	private String interests;
	private boolean interests_onoff;
	private String contact;
	private boolean contact_onoff;
	private String user_utilityYN;
	private int like_count; // 받은 전체 좋아요 수
	private int follower_count; // 1. 나를 보는 사람 수
	private int following_count; // 2. 내가 보는 사람 수

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfile(int aboutme_seq, int user_seq, String user_nickname, int user_auth, String[] technical_stack,
			String user_profile_route, String user_email, boolean user_email_onoff, String user_name,
			boolean user_name_onoff, String user_intro, boolean user_intro_onoff, String interests,
			boolean interests_onoff, String contact, boolean contact_onoff, String user_utilityYN, int like_count,
			int follower_count, int following_count) {
		super();
		this.aboutme_seq = aboutme_seq;
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.user_auth = user_auth;
		this.technical_stack = technical_stack;
		this.user_profile_route = user_profile_route;
		this.user_email = user_email;
		this.user_email_onoff = user_email_onoff;
		this.user_name = user_name;
		this.user_name_onoff = user_name_onoff;
		this.user_intro = user_intro;
		this.user_intro_onoff = user_intro_onoff;
		this.interests = interests;
		this.interests_onoff = interests_onoff;
		this.contact = contact;
		this.contact_onoff = contact_onoff;
		this.user_utilityYN = user_utilityYN;
		this.like_count = like_count;
		this.follower_count = follower_count;
		this.following_count = following_count;
	}

	public UserProfile(int user_seq, String interest) {
		this.user_seq = user_seq;
		this.interests = interest;
	}

	@Override
	public String toString() {
		return "UserProfile [aboutme_seq=" + aboutme_seq + ", user_seq=" + user_seq + ", user_nickname=" + user_nickname
				+ ", user_auth=" + user_auth + ", technical_stack=" + Arrays.toString(technical_stack)
				+ ", user_profile_route=" + user_profile_route + ", user_email=" + user_email + ", user_email_onoff="
				+ user_email_onoff + ", user_name=" + user_name + ", user_name_onoff=" + user_name_onoff
				+ ", user_intro=" + user_intro + ", user_intro_onoff=" + user_intro_onoff + ", interests=" + interests
				+ ", interests_onoff=" + interests_onoff + ", contact=" + contact + ", contact_onoff=" + contact_onoff
				+ ", user_utilityYN=" + user_utilityYN + ", like_count=" + like_count + ", follower_count="
				+ follower_count + ", following_count=" + following_count + "]";
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

	public int getUser_auth() {
		return user_auth;
	}

	public void setUser_auth(int user_auth) {
		this.user_auth = user_auth;
	}

	public String[] getTechnical_stack() {
		return technical_stack;
	}

	public void setTechnical_stack(String[] technical_stack) {
		this.technical_stack = technical_stack;
	}

	public String getUser_profile_route() {
		return user_profile_route;
	}

	public void setUser_profile_route(String user_profile_route) {
		this.user_profile_route = user_profile_route;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public boolean isUser_name_onoff() {
		return user_name_onoff;
	}

	public void setUser_name_onoff(boolean user_name_onoff) {
		this.user_name_onoff = user_name_onoff;
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

	public String getUser_utilityYN() {
		return user_utilityYN;
	}

	public void setUser_utilityYN(String user_utilityYN) {
		this.user_utilityYN = user_utilityYN;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public int getFollower_count() {
		return follower_count;
	}

	public void setFollower_count(int follower_count) {
		this.follower_count = follower_count;
	}

	public int getFollowing_count() {
		return following_count;
	}

	public void setFollowing_count(int following_count) {
		this.following_count = following_count;
	}

}