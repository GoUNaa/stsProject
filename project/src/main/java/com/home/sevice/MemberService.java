package com.home.sevice;

import java.util.List;

import com.home.domain.MemberBean;

public interface MemberService {
	
	//회원가입
	public void insertMember(MemberBean mb);

	// 로그인
	public MemberBean userCheck(MemberBean mb);
	
	// 아이디중복
	public MemberBean getMember(String id);
	
	// 메일보내기 
	public MemberBean getEmail(String id);
	
	// 수정
	public void updateMember(MemberBean mb);
	
	// 탈퇴
	public void deleteMember(MemberBean mb);

	// admin 회원목록
	public List<MemberBean> getMemberList();
}
