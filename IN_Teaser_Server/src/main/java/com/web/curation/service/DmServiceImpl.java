package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.dao.DmDaoImpl;
import com.web.curation.model.Communication;
import com.web.curation.model.DM;
import com.web.curation.model.DmMagement;
import com.web.curation.model.DmList;
@Transactional
@Service
public class DmServiceImpl implements IDmService {

	@Autowired
	private DmDaoImpl dmdao;

	@Override
	public List<DM> readdm(DM dm) {
		if (!dmdao.haveserver(dm)) {
			// 젤 작은 방번호 +1번방 가지고오자
			// int server_seq = dmdao.newserverseq();
			Communication co = dmdao.newserverseq(dm);
			dm.setServer_seq(co.getUser_seq());
			System.out.println(dm.getServer_seq());
			dmdao.makeserver(dm);
			List<DM> comm = new ArrayList<DM>();
			comm.add(new DM(co.getUser_seq(), dm.getUser_seq(), dm.getOpponent_seq(),
					co.getUser_nickname() + "님과 " + co.getOpponent_nickname() + "님이 대화를 시작하였습니다."));
			
			dmdao.savedm(comm.get(0));
			return comm;
		} else {
			int server_seq = dmdao.getserver_seq(dm);
			System.out.println(server_seq);
			dm.setServer_seq(server_seq);
			List<DM> comm = dmdao.getcommunication(dm);
			System.out.println(comm.toString());
			return comm;
		}
	}

	@Override
	public void savedm(DM dm) {
		dmdao.savedm(dm);
	}

	@Override
	public void closetime(int user_seq) {

		dmdao.closetime(user_seq);
	}

	@Override
	public List<DmList> dmlist(int user_seq) {
		List<DmList> list = new ArrayList<DmList>();
		List<Integer> server_seqs = dmdao.getmylist(user_seq);
		DmMagement in = new DmMagement(user_seq);
		for (int i = 0; i < server_seqs.size(); i++) {
			int server_seq = server_seqs.get(i);
			in.setServer_seq(server_seq);
			DmList dmlist = dmdao.getdata(in);
			list.add(dmlist);
		}
		return list;
	}

}
