package com.ict.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.shop.dao.CartVO;
import com.ict.shop.dao.ShopVO;
import com.ict.shop.service.ShopService;

@Controller
public class ShopController {

	@Autowired
	private ShopService shopService ;
	
	@GetMapping("shop_list.do")
	public ModelAndView getShopList(String category) {
		try {
			ModelAndView mv = new ModelAndView("shop/shop_list");
			if (category == null || category == "") {
				category = "ele002";
			}
			List<ShopVO> shop_list = shopService.getShopList(category);
			if(shop_list != null) {
				mv.addObject("shop_list", shop_list);
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("shop/error");
	}
	
	@GetMapping("shop_detail.do")
	public ModelAndView getShopDetail(String shop_idx) {
		try {
			System.out.println(shop_idx);
			ModelAndView mv = new ModelAndView("shop/shop_detail");
			ShopVO svo = shopService.getShopDetail(shop_idx);
			if (svo != null) {
				mv.addObject("svo", svo);
				return mv;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("shop/error");
	}
	
	@GetMapping("shop_addCart.do")
	public ModelAndView getAddCart(@ModelAttribute("shop_idx")String shop_idx) {
		try {
			ModelAndView mv = new ModelAndView("redirect:shop_detail.do");
			// mv.addObject("shop_idx", shop_idx); 모델어트리뷰트랑 같은 역할
			
			// 로그인한 정보(id)를 가져와서 같이 가야 한다. 지금은 로그인 없으니 park 로 지정해준다.
			String m_id = "park";
			
			// shop_idx 로 제품 정보를 가져온다. 
			ShopVO svo = shopService.getShopDetail(shop_idx);
			
			// 아이디가 해당제품을 카트에 가지고 있는지 확인해야 한다. 있으면 수량 1증가 없으면 카트에 추가. 
			CartVO cavo = shopService.getCartChk(m_id, svo.getP_num());
			if(cavo == null) {
				// 카트에 정보가 없으면 제품 추가 인설트
				CartVO cavo2 = new CartVO();
				cavo2.setP_num(svo.getP_num());
				cavo2.setP_name(svo.getP_name());
				cavo2.setP_price(String.valueOf(svo.getP_price()));
				cavo2.setP_saleprice(String.valueOf(svo.getP_saleprice()));
				cavo2.setM_id(m_id);
				int result = shopService.getCartInsert(cavo2);
				
			}else {
				// 카트에 정보가 있으면 수량 1증가 하는 업데이트
				int result = shopService.getCartUpdate(cavo);
				
			}
			return mv;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("shop/error");
	}
	
	
	@GetMapping("shop_showCart.do")
	public ModelAndView getCartList() {
		try {
			ModelAndView mv = new ModelAndView("shop/cart_list");
			// 로그인한 정보를 가지고 m_id를 구하자.
			String m_id = "park";
			List<CartVO> cart_list = shopService.getCartList(m_id); 
			if (cart_list != null) {
				mv.addObject("cart_list", cart_list);
				return mv;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("shop/error");
	}
	
	@PostMapping("cart_edit.do")
	public ModelAndView getCartEdit(CartVO cavo) {
		try {
			ModelAndView mv = new ModelAndView("redirect:shop_showCart.do");
			
			int result = shopService.getCartEdit(cavo);
			if(result >0 ) {
				return mv;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("shop/error");
	}
	
	@GetMapping("shop_cart_delete.do")
	public ModelAndView getCartDelete(String cart_idx) {
		try {
			ModelAndView mv = new ModelAndView("redirect:shop_showCart.do");
			int result = shopService.getCartDelete(cart_idx);
			if (result > 0) {
				return mv;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("shop/error");
	}
	
}




























