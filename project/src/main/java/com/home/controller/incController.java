package com.home.controller;

import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.home.domain.MemberBean;
import com.home.sevice.MailServiceImpl;
import com.home.sevice.MemberService;
import com.home.sevice.MemberServiceImpl;

@Controller
public class incController {
	
	@Inject
	private MailServiceImpl mailServiceImpl;
	@Inject
	private MemberService memberService;
	
		// inc map
		@RequestMapping(value = "/main/map", method = RequestMethod.GET)
		public String map() {
			System.out.println("incController -  map");

			//main.jsp 이동
			return "/map/map";
		}
	
		// inc mail
		@RequestMapping(value = "/main/mail", method = RequestMethod.GET)
		public String joinMail() {
			System.out.println("incController -  joinMail(GET)");
			//main.jsp 이동
			return "/mail/mail";
		}
		
		// inc mail
		@RequestMapping(value = "/main/mail", method = RequestMethod.POST)
		public String joinMailPost(MemberBean mb,HttpServletRequest request,HttpSession session) {
			System.out.println("incController -  joinMailPost(POST)");
			String id = (String) session.getAttribute("id");
			System.out.println(id);
			
			MemberBean idd = memberService.getEmail(id);
			String sender = String.valueOf("idd");
			String receiver = "zzaaqqqaz@naver.com";
			String subject = "DAL 에서 "+ id +" 님이 보내신 메일입니다 =>       "+ request.getParameter("subject");
			String content = request.getParameter("content");

			try {
				Properties properties = System.getProperties();
				properties.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
				properties.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 주소
				properties.put("mail.smtp.auth", "true"); // gmail은 무조건 true 고정
				properties.put("mail.smtp.port", "587"); // gmail 포트
				Authenticator auth = new MailServiceImpl();
				Session s = Session.getDefaultInstance(properties, auth);
				//Session s = Session.getdefultInstance(properties, auth);
				Message message = new MimeMessage(s);
				Address sender_address = new InternetAddress(sender);
				Address receiver_address = new InternetAddress(receiver);
				message.setHeader("content-type", "text/html;charset=UTF-8");
				message.setFrom(sender_address);
				message.addRecipient(Message.RecipientType.TO, receiver_address);
				message.setSubject(subject);
				message.setContent(content, "text/html;charset=UTF-8");
				message.setSentDate(new java.util.Date());
				Transport.send(message);
				System.out.println("메일이 정상적으로 전송되었습니다.");
			} catch (Exception e) {
				System.out.println("SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.");
				e.printStackTrace();
			}
			//main.jsp 이동
			return "/main/main";
		}

		// inc map
		@RequestMapping(value = "/company/welcome", method = RequestMethod.GET)
		public String welcome() {
			System.out.println("incController -  welcome");

			//main.jsp 이동
			return "/company/welcome";
		}
		
		
		
}
