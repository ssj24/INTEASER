package com.web.curation.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

	String restoreprofile(int user_seq, MultipartFile multipartFile);

	String restorefile(MultipartFile multipartFile);
	
}
