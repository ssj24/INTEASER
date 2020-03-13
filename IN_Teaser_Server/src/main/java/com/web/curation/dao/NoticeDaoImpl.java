package com.web.curation.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.NoticeMapper;
import com.web.curation.model.Communication;

@Repository
public class NoticeDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	public void pushnoticebyfeed(Communication comm) {
		sqlSession.getMapper(NoticeMapper.class).pushnoticebyfeed(comm);
	}
	
	public void pushnoticebycomment(Communication comm) {
		sqlSession.getMapper(NoticeMapper.class).pushnoticebycomment(comm);
	}

	public int getuserauth(int user_seq) {
		return sqlSession.getMapper(NoticeMapper.class).getuserauth(user_seq);
	}

	public void pushnoticebyproject(Communication comm) {
		sqlSession.getMapper(NoticeMapper.class).pushnoticebyproject(comm);
	}

}
