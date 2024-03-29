package com.ict.guestbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ict.guestbook.dao.GuestBookVO;

@Service
public interface GuestBookService {

	
	
	// 리스트
	public List<GuestBookVO> getGuestBookList();
	
	// 상세보기
		public GuestBookVO getGuestBookDetail(String idx);
		// 삽입
		public int getGuestBookInsert(GuestBookVO gvo);
		
		// 삭제
		public int getGuestBookDelete(String idx);
		
		// 수정
		public int getGuestBookUpdate(GuestBookVO gvo);
		
		
}
