package com.web.curation.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.Communication;

@Mapper
public interface NoticeMapper {

	public void pushnoticebyfeed(Communication comm);

	public void pushnoticebycomment(Communication comm);

	public int getuserauth(int user_seq);

	public void pushnoticebyproject(Communication comm);

}
