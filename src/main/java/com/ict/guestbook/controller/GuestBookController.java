package com.ict.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.guestbook.dao.GuestBookVO;
import com.ict.guestbook.service.GuestBookService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class GuestBookController {

	@Autowired
	private GuestBookService guestBookService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/")
	public ModelAndView getGuestBookIndex() {
		return new ModelAndView("index");
	}
	@GetMapping("gb_list.do")
	public ModelAndView getGuestBookList() {
		ModelAndView mv = new ModelAndView("guestbook/list");
		List<GuestBookVO> list = guestBookService.getGuestBookList();
		if (list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}

	@GetMapping("gb_write.do")
	public ModelAndView getGuestBookInsert() {
		return new ModelAndView("guestbook/write");
	}

	@PostMapping("gb_write_ok.do")
	public ModelAndView getGuestBookInsertOK(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:/");

		// 비번 암호화
		String pwd = passwordEncoder.encode(gvo.getPwd());
		gvo.setPwd(pwd);

		int result = guestBookService.getGuestBookInsert(gvo);
		if (result > 0) {
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}

	@GetMapping("gb_detail.do")
	public ModelAndView getGuestBookDetail(String idx) {
		ModelAndView mv = new ModelAndView("guestbook/detail");
		GuestBookVO gvo = guestBookService.getGuestBookDetail(idx);
		if (gvo != null) {
			mv.addObject("gvo", gvo);
			return mv;

		}

		return new ModelAndView("guestbook/error");
	}

	@PostMapping("gb_delete.do")
	public ModelAndView getGuestBookDelete(@ModelAttribute("idx") String idx) {
		return new ModelAndView("guestbook/delete");
	}
	/*
	 * @PostMapping("gb_delete.do") public ModelAndView getGuestBookDelete(String
	 * idx) { ModelAndView mv = new ModelAndView("guestbook/delete");
	 * mv.addObject("idx", idx); return mv; }
	 */

	@PostMapping("gb_delete_ok.do")
	public ModelAndView getGuestBookDeleteOK(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView();
		String cpwd = gvo.getPwd();

		GuestBookVO gvo2 = guestBookService.getGuestBookDetail(gvo.getIdx());
		String dpwd = gvo2.getPwd();

		// (passwordEncoder.matches(암호화X, 암호화O))
		// 일치하면 true, 아니면 false
		if (!passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("guestbook/delete");
			mv.addObject("pwdchk", "fail");
			mv.addObject("idx", gvo.getIdx());
			return mv;
		} else {
			int result = guestBookService.getGuestBookDelete(gvo.getIdx());
			if (result > 0) {
				mv.setViewName("redirect:/");
				return mv;
			}
			mv.setViewName("guestbook/error");
			return mv;
		}

	}

	@PostMapping("gb_update.do")
	public ModelAndView guestGueBookUpdate(String idx) {
		ModelAndView mv = new ModelAndView("guestbook/update");
		GuestBookVO gvo = guestBookService.getGuestBookDetail(idx);
		if (gvo != null) {
			mv.addObject("gvo", gvo);
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}

	@PostMapping("gb_update_ok.do")
	public ModelAndView getGuestBookUpdateOK(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView();
		String cpwd = gvo.getPwd();

		GuestBookVO gvo2 = guestBookService.getGuestBookDetail(gvo.getIdx());
		String dpwd = gvo2.getPwd();

		// (passwordEncoder.matches(암호화X, 암호화O))
		// 일치하면 true, 아니면 false
		if (!passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("guestbook/update");
			mv.addObject("pwdchk", "fail");
			mv.addObject("idx", gvo.getIdx());
			// 수정 전 내용을 되돌리려면 : gvo2 
			// 수정 후 내용을 되돌리려면 : gvo
			mv.addObject("gvo", gvo);
			
			return mv;
		} else {
			int result = guestBookService.getGuestBookUpdate(gvo);
			if (result > 0) {
				mv.setViewName("redirect:gb_detail.do"+gvo.getIdx());
				return mv;
			}
			mv.setViewName("guestbook/error");
			return mv;
		}
	}
	
}








