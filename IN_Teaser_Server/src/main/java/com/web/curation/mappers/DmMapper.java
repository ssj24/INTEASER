package com.web.curation.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Communication;
import com.web.curation.model.DM;
import com.web.curation.model.DmMagement;
import com.web.curation.model.DmList;
@Mapper
public interface DmMapper {

//	int newserverseq();

	int getserver_seq(DM dm);

	List<DM> getcommunication(DM dm);

	void savedm(DM dm);

	List<Integer> getmylist(int user_seq);

	DmList getdata(DmMagement in);

	String getopponent(int opponent_seq);

	int haveserver(DM dm);

	void makeserver1(DM dm);

	void makeserver2(DM dm);

	void closetime(int user_seq);

	String getcontent(int server_seq);

	Communication newserverseq(DM dm);

}
