package com.web.curation.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.UserProfile;

@Mapper
public interface UploadMapper {

	public void restore(UserProfile profile);

}
