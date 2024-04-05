package com.ict.member.service;

import java.util.List;

import com.ict.member.dao.MemberVO;

public interface MemberService {
	
		// 로그인
		public MemberVO getLogin(MemberVO mvo);
		
		// 전체보기
		public List<MemberVO> getMemberList();
		
		// id 체크
		public String getIdChk(String m_id);
		
		// 가입하기
		public int getAjaxJoin(MemberVO mvo);
		
		// 삭제하기
		public int getAjaxDelete(String m_idx);
}














