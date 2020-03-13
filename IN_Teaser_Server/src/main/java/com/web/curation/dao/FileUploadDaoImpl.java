package com.web.curation.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.UploadMapper;
import com.web.curation.model.UserProfile;

@Repository
public class FileUploadDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	public void restore(UserProfile profile) {
		sqlSession.getMapper(UploadMapper.class).restore(profile);
	}

}
