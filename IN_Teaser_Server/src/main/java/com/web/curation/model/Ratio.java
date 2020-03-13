package com.web.curation.model;

public class Ratio {

	int users;
	int recruiters;
	double puser;
	double precruiter;

	public Ratio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ratio(int users, int recruiters, double puser, double precruiter) {
		super();
		this.users = users;
		this.recruiters = recruiters;
		this.puser = puser;
		this.precruiter = precruiter;
	}

	@Override
	public String toString() {
		return "Ratio [users=" + users + ", recruiters=" + recruiters + ", puser=" + puser + ", precruiter="
				+ precruiter + "]";
	}

	public int getUsers() {
		return users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	public int getRecruiters() {
		return recruiters;
	}

	public void setRecruiters(int recruiters) {
		this.recruiters = recruiters;
	}

	public double getPuser() {
		return puser;
	}

	public void setPuser(double puser) {
		this.puser = puser;
	}

	public double getPrecruiter() {
		return precruiter;
	}

	public void setPrecruiter(double precruiter) {
		this.precruiter = precruiter;
	}

}
