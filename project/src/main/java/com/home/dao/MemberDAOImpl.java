package com.home.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.home.domain.MemberBean;

@Repository
public class MemberDAOImpl implements MemberDAO {
	// 마이바티스 객체 생성
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.home.mapper.MemberMapper";

	@Override
	public void insertMember(MemberBean mb) {
		System.out.println("MemberDAOImpl - insertMember");
		sqlSession.insert(namespace + ".insertMember",mb);
	}

	@Override
	public MemberBean userCheck(MemberBean mb) {
		System.out.println("MemberDAOImpl - userCheck");
		return sqlSession.selectOne(namespace + ".userCheck",mb);
	}

	@Override
	public MemberBean getMember(String id) {
		System.out.println("MemberDAOImpl - getMember");
		return sqlSession.selectOne(namespace + ".getMember" , id);
	}

	@Override
	public MemberBean getEmail(String id) {
		System.out.println("MemberDAOImpl - getEmail");
		return sqlSession.selectOne(namespace + ".getEmail", id);
	}

	@Override
	public void updateMember(MemberBean mb) {
		System.out.println("MemberDAOImpl - updateMember");
		sqlSession.update(namespace + ".updateMember", mb);
	}

	@Override
	public void deleteMember(MemberBean mb) {
		System.out.println("MemberDAOImpl - deleteMember");
		sqlSession.delete(namespace + ".deleteMember", mb);
	}

	@Override
	public List<MemberBean> getMemberList() {
		System.out.println("MemberDAOImpl - getMemberList");
		return sqlSession.selectList(namespace + ".getMemberList");
	}
	
	
	
}
