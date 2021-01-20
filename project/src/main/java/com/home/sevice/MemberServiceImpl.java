package com.home.sevice;


import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.home.dao.MemberDAO;
import com.home.domain.MemberBean;


@Service
public class MemberServiceImpl implements MemberService{
	@Inject
	private MemberDAO memberDAO;

	@Override
	public void insertMember(MemberBean mb) {
		mb.setDate(new Timestamp(System.currentTimeMillis()));
		memberDAO.insertMember(mb);
	}

	@Override
	public MemberBean userCheck(MemberBean mb) {
		System.out.println("MemberServiceImpl - userCheck");
		return memberDAO.userCheck(mb);
	}

	@Override
	public MemberBean getMember(String id) {
		System.out.println("MemberServiceImpl - getMember");
		return memberDAO.getMember(id);
	}

	@Override
	public MemberBean getEmail(String id) {
		System.out.println("MemberServiceImpl - getEmail");
		return memberDAO.getEmail(id);
	}

	@Override
	public void updateMember(MemberBean mb) {
		System.out.println("MemberServiceImpl - updateMember");
		 memberDAO.updateMember(mb);

	}

	@Override
	public void deleteMember(MemberBean mb) {
		System.out.println("MemberServiceImpl - deleteMember");
		 memberDAO.deleteMember(mb);
	}

	@Override
	public List<MemberBean> getMemberList() {
		System.out.println("MemberServiceImpl - getMemberList");
		return  memberDAO.getMemberList();
	}
	
	
	
}
