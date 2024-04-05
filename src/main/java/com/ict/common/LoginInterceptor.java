package com.ict.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements AsyncHandlerInterceptor{
	
	// Controller 가기 전 구동됨
	// 결과 true 이면 Controller 로 이동, 결과가 false 이면 특정 JSP 파일로 이동
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		// 로그인 체크를 해서 만약에 로그인이 안된 상태이면 value에 false 저장
		
		// 만약 session이 삭제된 상태라면 새로운 session을 생성해 준다. 
		// session이 삭제가 안된 상태라면 사용하고 있는 session을 그대로 전달해 준다. 
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute("loginChk");
		if(obj == null) {
			request.getRequestDispatcher("/WEB-INF/views/login_error.jsp").forward(request, response);
			return false;
		}
		
		
		return true;
	}

	// Controller 에서 리턴 되어 뷰리졸버로 가기 전에 구동된다. 
//	
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
//	
	
	// 모든 작업이 완료된 후 실행
	// 뷰리졸버가 뷰를 찾아서 보내고 나면 구동한다. 
	// 모든 뷰에서 최종 결과를 생성하는 일을 포함해 모든 작업이 완료된 후에 실행된다.
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
	
	
}























