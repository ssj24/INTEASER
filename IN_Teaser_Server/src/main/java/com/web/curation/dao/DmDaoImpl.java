package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.DmMapper;
import com.web.curation.model.Communication;
import com.web.curation.model.DM;
import com.web.curation.model.DmMagement;
import com.web.curation.model.DmList;

@Repository
public class DmDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	public boolean haveserver(DM dm) {
		int haveserver = sqlSession.getMapper(DmMapper.class).haveserver(dm);
		if(haveserver == 0)
			return false;
		else 
			return true;
	}	//

//	public int newserverseq() {
//		return sqlSession.getMapper(DmMapper.class).newserverseq();
//	}	

	public void makeserver(DM dm) {
		sqlSession.getMapper(DmMapper.class).makeserver1(dm);
		sqlSession.getMapper(DmMapper.class).makeserver2(dm);
	}	//

	public int getserver_seq(DM dm) {
		return sqlSession.getMapper(DmMapper.class).getserver_seq(dm);
	}	//

	public List<DM> getcommunication(DM dm) {
		return sqlSession.getMapper(DmMapper.class).getcommunication(dm);
	}	//

	public void savedm(DM dm) {
		sqlSession.getMapper(DmMapper.class).savedm(dm);
	}	//

	public List<Integer> getmylist(int user_seq) {
		return sqlSession.getMapper(DmMapper.class).getmylist(user_seq);
	}	//

	public DmList getdata(DmMagement in) {
		return sqlSession.getMapper(DmMapper.class).getdata(in);
	}	//

	public String getopponent(int opponent_seq) {
		return sqlSession.getMapper(DmMapper.class).getopponent(opponent_seq);
	}	//

	public void closetime(int user_seq) {
		sqlSession.getMapper(DmMapper.class).closetime(user_seq);
	}	//

	public Communication newserverseq(DM dm) {
		return sqlSession.getMapper(DmMapper.class).newserverseq(dm);
	}

}
