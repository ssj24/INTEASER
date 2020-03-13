package com.web.curation.service;

import java.util.List;

import com.web.curation.model.DM;
import com.web.curation.model.DmList;

public interface IDmService {

	List<DM> readdm(DM dm);

	void savedm(DM dm);

	void closetime(int user_seq);

	List<DmList> dmlist(int user_seq);


}
