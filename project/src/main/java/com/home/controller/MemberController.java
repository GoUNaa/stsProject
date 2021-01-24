package com.home.controller;

import java.util.List;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.home.domain.MemberBean;
import com.home.sevice.MailServiceImpl;
import com.home.sevice.MemberService;

@Controller
public class MemberController {
	@Inject
	private MemberService memberService;

	@Inject
	private MailServiceImpl mail;

	@RequestMapping(value = "/main/main", method = RequestMethod.GET)
	public String main() {
		System.out.println("MemberController -  main");

		// main.jsp 이동
		return "main/main";
	}

	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		System.out.println("MemberController -  insert");

		// join.jsp 이동
		return "member/join";
	}

	@RequestMapping(value = "/member/insert", method = RequestMethod.POST)
	public String insertPost(MemberBean mb, HttpServletRequest request) {
		System.out.println("MemberController -  insertPost");

		memberService.insertMember(mb);
		// -----------------------------------
		String sender = "zzaaqqqaz@naver.com";
		sender = "DAL";
		String receiver = mb.getEmail();
		String subject = "DAL 회원가입을 축하드립니다";
		String content = mb.getName() + "님 DAL 회원가입을 축하드립니다";

		try {
			Properties properties = System.getProperties();
			properties.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
			properties.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 주소
			properties.put("mail.smtp.auth", "true"); // gmail은 무조건 true 고정
			properties.put("mail.smtp.port", "587"); // gmail 포트
			Authenticator auth = new MailServiceImpl();
			Session s = Session.getDefaultInstance(properties, auth);
			// Session s = Session.getdefultInstance(properties, auth);
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
			System.out.println("메일 전송");
		} catch (Exception e) {
			System.out.println("SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.");
			e.printStackTrace();
		}
		// -----------------------------------

		// join.jsp 이동
		return "redirect:/member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController -  login");

//			/WEB-INF/views/member/login.jsp
		return "member/login";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String loginPost(MemberBean mb, HttpSession session, Model model) {
		System.out.println("MemberController -  loginPost");
		System.out.println(mb.getId());
		System.out.println(mb.getPass());
		MemberBean mb2 = memberService.userCheck(mb);
		if (mb2 != null) {
			// 세션값 생성 "id",id
			session.setAttribute("id", mb.getId());

//				/main/main 가상주소 이동
			// response.sendRedirect() 같음
			return "redirect:/main/main";
		} else {
			model.addAttribute("msg", "입력하신 정보 틀립니다.");
//				/WEB-INF/views/member/msg.jsp
			return "member/msg";
		}
	}

	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("MemberController - logout");
		// 세션값 전체삭제
		session.invalidate();
//					http://localhost:8080/FunWeb/main/main 가상주소 이동
		// response.sendRedirect() 같음
		return "redirect:/main/main";
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		System.out.println("MemberController - update");
		String id = (String) session.getAttribute("id");

		MemberBean mb = memberService.getMember(id);

		model.addAttribute("mb", mb);

		return "member/updateForm";

	}

	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String updatePost(MemberBean mb, HttpSession session, Model model) {
		System.out.println("MemberController - updatePost");

		MemberBean mb2 = memberService.userCheck(mb);

		if (mb2 != null) {
			memberService.updateMember(mb);
		} else {
			model.addAttribute("msg", "입력하신 정보는 틀립니다.");
			return "member/msg";
		}
		// /WEB-INF/views/member/main
		return "/main/main";
	}

	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		System.out.println(id);
		return "/member/deleteForm";

	}

	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
	public String deletePost(MemberBean mb, HttpSession session, Model model) {

		String id = (String) session.getAttribute("id");
		mb.setId(id);
		MemberBean mb2 = memberService.userCheck(mb);
		System.out.println("id : " + mb.getId());
		System.out.println("pass : " + mb.getPass());
		if (mb2 != null) {
			memberService.deleteMember(mb);
			System.out.println(mb.getId());
			System.out.println(mb.getPass());
			session.invalidate();
		} else {
			model.addAttribute("msg", "입력하신 정보는 틀립니다.");
			return "member/msg";
		}
		// /WEB-INF/views/member/main
		return "redirect:/main/main";
	}

	@RequestMapping(value = "/member/memberList", method = RequestMethod.GET)
	public String list(HttpSession session , Model model) {
	
		List<MemberBean> mbList = memberService.getMemberList();
		
		model.addAttribute("mbList" , mbList);
		
		return "member/memberList";
	}

}
