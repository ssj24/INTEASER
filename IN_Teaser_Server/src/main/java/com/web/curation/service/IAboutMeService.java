package com.web.curation.service;

import java.util.List;

import com.web.curation.model.Pagination;
import com.web.curation.model.Project;
import com.web.curation.model.UserProfile;

public interface IAboutMeService {

	UserProfile getMyAccount(int user_seq);

	Pagination uploadfeed(int login_user_seq, Pagination criteria);

	Pagination likefeed(Pagination criteria);

	Pagination scrapfeed(Pagination criteria);

	Pagination hidefeed(Pagination criteria);

	List<Project> likepjt(int user_seq);

	UserProfile readsetting(int user_seq);

	void updatesetting(UserProfile setting);

	void secession(int user_seq);

	int getlikecnt(int user_seq);

	void removephoto(int user_seq);

}
