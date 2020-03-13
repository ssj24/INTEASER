package com.web.curation.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.dao.FileUploadDaoImpl;
import com.web.curation.model.UserProfile;
@Transactional
@Service
public class FileUploadServiceImpl implements IFileUploadService {
//	private static final String SAVE_PATH = "/upload";	//실제저장주소
	private static final String SAVE_PATH = "/usr/share/nginx/html/upload";	//실제저장주소
	private static final String PREFIX_URL = "/upload/";	//가상주소
	@Autowired
	private FileUploadDaoImpl uploaddao;

	@Override
	public String restoreprofile(int user_seq, MultipartFile multipartFile) {
		String url = null;

		try {
			// 파일 정보
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			// Long size = multipartFile.getSize();
			// 서버에서 저장 할 파일 이름
			String saveFileName = genSaveFileName(extName);

//			System.out.println("getsize: "+multipartFile.getSize());

			writeFile(multipartFile, saveFileName);
			url = PREFIX_URL + saveFileName;
			UserProfile profile = new UserProfile();
			profile.setUser_seq(user_seq);
			profile.setUser_profile_route(url);

			uploaddao.restore(profile);
		} catch (IOException e) {
			// 원래라면 RuntimeException 을 상속받은 예외가 처리되어야 하지만
			// 편의상 RuntimeException을 던진다.
			// throw new FileUploadException();
			throw new RuntimeException(e);
		}
		return url;
	}

	@Override
	public String restorefile(MultipartFile multipartFile) {
		String url = null;
		try {
			// 파일 정보
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			String saveFileName = genSaveFileName(extName);

			writeFile(multipartFile, saveFileName);
			url = PREFIX_URL + saveFileName;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return url;
	}

	private String genSaveFileName(String extName) {
		// 현재 시간을 기준으로 파일 이름 생성
		String fileName = "";

		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}

	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		boolean result = false;

		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();

		return result;
	}

}
