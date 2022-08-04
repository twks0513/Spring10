package com.care.file.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.care.file.service.MailService;

@Controller
public class MailController {
	@Autowired MailService ms;
	
	@GetMapping("sendmail")
	public void sendMail(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ms.sendMail("twks0513@gmail.com","테스트메일(제목)","안녕하세요(내용)");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("메일을 전송하였습니다.");
	}
	@GetMapping("sendmail2")
	public void sendMail2(HttpServletResponse response, HttpServletRequest request) throws Exception {
		StringBuffer sb = new StringBuffer(); //String보다 처리속도가 빠르다
		sb.append("<html><body>");
		sb.append("<a href='https://www.naver.com'>");
		sb.append("<img src='localhost:8085/file/download?=file=test.jfif' width='100px' height='100px'>");
		sb.append("</a></body></html>");
		String str = sb.toString();
		
		ms.sendMail2("twks0513@gmail.com","광고(제목)",str);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("메일을 전송하였습니다.");
	}
	
	@GetMapping("auth_form")
	public String authForm(HttpServletRequest request) {
		ms.auth(request);
		return "auth_form";
	}
	
	@GetMapping("auth_check")
	public String authCheck(String userid,String userkey,HttpSession session) {
		String key = (String)session.getAttribute(userid);
		if(key != null) {
			if(key.equals(userkey)) {
				session.setAttribute("userid", userid);
			}
		}
		return "redirect:auth_form";
	}
}
