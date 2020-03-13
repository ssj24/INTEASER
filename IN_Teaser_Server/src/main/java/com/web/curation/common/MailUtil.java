package com.web.curation.common;

import org.apache.commons.mail.HtmlEmail;

public class MailUtil {
	
	public static void sendMail(String email, String subject , String mag) throws Exception{
	
		//Mail Server설정
		String charSet = "utf-8";
		String hostSTMP = "smtp.naver.com";//SMTP 서버명
		String hostSMTPid = "in-teaser"; //네이버 아이디
		String hostSMTPPWD = "Rhcrnfma^^!";//네이버 비밀번호
		
		//보내는 사람
		String fromEmail = "in-teaser@naver.com"; 
		String fromName = "꽃구름 인티저입니다.";
		
		//email 전송
		try{
			HtmlEmail mail = new HtmlEmail();
			mail.setDebug(true);
			mail.setCharset(charSet);
			mail.setSSLOnConnect(true); //SSL 사용 (TLS가 없는 경우 SSL사용)
			mail.setHostName(hostSTMP);
			mail.setSmtpPort(587);//SMTP포트번호
			mail.setAuthentication(hostSMTPid, hostSMTPPWD);
			mail.setStartTLSEnabled(true);//TLS사용
			mail.addTo(email);
			mail.setFrom(fromEmail,fromName,charSet);
			mail.setSubject(subject);
			mail.setHtmlMsg(mag);
			mail.send();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
