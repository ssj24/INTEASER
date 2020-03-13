package com.web.curation.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * SHA-256 암호화
 * 
 * @author Minchang Jang
 *
 */
public class SHA256Util {
	/**
	 * SHA-256 암호화 함 난수 salt 발생시킴
	 * 
	 * @param source       원본
	 * @param salt(String) SALT 값
	 * @return
	 */
	public static String getEncrypt(String source, String salt) {
		return getEncrypt(source, salt.getBytes());
	}

	/**
	 * SHA-256 암호화 함
	 * 
	 * @param source       원본
	 * @param salt(byte[]) SALT 값
	 * @return
	 */
	public static String getEncrypt(String source, byte[] salt) {

		String result = "";

		byte[] a = source.getBytes();
		byte[] bytes = new byte[a.length + salt.length];

		System.arraycopy(a, 0, bytes, 0, a.length);
		System.arraycopy(salt, 0, bytes, a.length, salt.length);

		try {
			// 암호화 방식 지정 메소드
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytes);

			byte[] byteData = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
			}

			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * SALT를 얻어온다. 비밀번호와 salt를 조합해 암호화 시킴
	 * 
	 * @return
	 */
	public static String generateSalt() {
		Random random = new Random();

		byte[] salt = new byte[8];
		random.nextBytes(salt);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < salt.length; i++) {
			// byte 값을 Hex 값으로 바꾸기.
			sb.append(String.format("%02x", salt[i]));
		}

		return sb.toString();
	}

	public static String getCertificationCode() {
		// 인증코드 생성
		char[] charSet = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', '0', '1', '2', '3', '4', '5', '6', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
				'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z', '!', '@', '7', '8', '9', };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거)
			sb.append(charSet[idx]);
		}
		String confirmCode = sb.toString();
		return confirmCode;
	}

}
