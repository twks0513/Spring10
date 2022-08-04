package com.care.file.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired JavaMailSender mailSender;
	public void sendMail(String to, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mm = new MimeMessageHelper(message,true,"UTF-8");
			mm.setSubject(subject);
			mm.setTo(to);
			mm.setText(body);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendMail2(String to, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mm = new MimeMessageHelper(message,true,"UTF-8");
			mm.setSubject(subject);
			mm.setTo(to);
			mm.setText(body,true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void auth(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userid="DDoob";
		String userkey = rand();
		session.setAttribute(userid, userkey);
		
		String body ="<h3>안녕하세요 인증입니다</h3><hr>";
		body+="<h5>"+userid+"님</h5>";
		body+="<p>인증하기 버튼을 누르면 로그인 됩니다</p>";
		body+="<a href='http://localhost:8085"+request.getContextPath() +"/auth_check?userid="+userid+"&userkey="+userkey+"'>인증하기</a>";
		sendMail2("twks0513@gmail.com","이메일 인증",body);
	}
	private String rand() {
		Random rad = new Random();
		String str ="";
		int num;
		while(str.length() !=20) {
			num = rad.nextInt(75)+48;
			if((num>=48 && num<=57)||(num>=65 && num<=90)||(num>=97&&num<=122)) {
				str+=(char)num;
			}
		}
		return str;
	}
}
