package com.home.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.home.domain.MemberBean;
import com.home.sevice.MemberService;


@RestController
public class AjaxController {
	
	@Inject
	private	MemberService memberService;
	
	@RequestMapping(value = "/member/idcheck", method = RequestMethod.GET)
	public ResponseEntity<String> idcheck(HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		String result="";
		try {
			String id = request.getParameter("id");
			MemberBean mb = memberService.getMember(id);
			if(mb!=null) {
				System.out.println("아이디 중복");
				result="iddup";
			} else {
				System.out.println("아이디 사용가능");
				result="idok";
			}
			entity = new ResponseEntity<String>(result,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
