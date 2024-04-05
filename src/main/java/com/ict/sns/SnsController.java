package com.ict.sns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class SnsController {
	
	@Autowired
	private AddrDAO addrDAO;
	
	
	@RequestMapping("spring_sns_go.do")
	public ModelAndView getSpringSnsGo() {
		return new ModelAndView("sns/loginForm");
	}

	@RequestMapping("kakaologin.do")
	public ModelAndView KakaoLogIn(HttpServletRequest request) {
		// 1. 인증코드 받기
		String code = request.getParameter("code");
		System.out.println("code : " + code);

		// 2. 토큰 받기 (인증코드 필요)
		String reqURL = "https://kauth.kakao.com/oauth/token";
		System.out.println("ddd" + reqURL);
		try {
			URL url = new URL(reqURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// 헤더 요청
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			// 본문
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=0fa70f7227d7cf37af4189a7ca7b3cda");
			sb.append("&redirect_uri=http://localhost:8090/kakaologin2.do");
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이면 성공
			int responseCode = conn.getResponseCode();

			// 토큰 요청을 통한 결과를 얻는데 JSON 타입
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			StringBuffer sb2 = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb2.append(line);
			}
			String result = sb2.toString();
			// System.out.println(result);

			// 3. 사용자 정보(토큰을 이용해서 사용자 정보를 받자)
			// 받은 정보는 session에 저장 (ajax를 사용해서 사용자 정보를 가져온다.)
			Gson gson = new Gson();
			KakaoVO kvo = gson.fromJson(result, KakaoVO.class);
			// System.out.println(kvo.getAccess_token());
			// System.out.println(kvo.getRefresh_token());
			// System.out.println(kvo.getToken_type());
			request.getSession().setAttribute("access_token", kvo.getAccess_token());
			request.getSession().setAttribute("refresh_token", kvo.getRefresh_token());
			request.getSession().setAttribute("token_type", kvo.getToken_type());

			return new ModelAndView("sns/result");

		} catch (Exception e) {
			System.out.println(e);
		}

		return new ModelAndView("sns/error");
	}

	@RequestMapping("naverlogin.do")
	public ModelAndView naverlogin(HttpServletRequest request) {
		// 1. 인증코드 받기
		String code = request.getParameter("code");
		String state = request.getParameter("state");

		// 2. 토큰 받기 (인증코드필요)
		String reqURL = "https://nid.naver.com/oauth2.0/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// 헤더 요청
			// conn.setRequestProperty("Content-type",
			// "application/x-www-form-urlencoded;charset=utf-8");

			// 본문
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=mwaKBFts3znxiC9xP4xy");
			sb.append("&client_secret=HCsVRneSmH");
			sb.append("&state=" + state);
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이면 성공
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			// 토큰요청을 통한 결과를 얻는데 JSON 타입
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			StringBuffer sb2 = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb2.append(line);
			}
			String result = sb2.toString();
			System.out.println(result);

			// 받는 정보는 session 저장 (ajax를 사용해서 사용자정보를 가져온다.)
			Gson gson = new Gson();
			NaverVO nvo = gson.fromJson(result, NaverVO.class);
			// System.out.println(kvo.getAccess_token());
			// System.out.println(kvo.getRefresh_token());
			// System.out.println(kvo.getToken_type());
			request.getSession().setAttribute("access_token", nvo.getAccess_token());
			request.getSession().setAttribute("refresh_token", nvo.getRefresh_token());
			request.getSession().setAttribute("token_type", nvo.getToken_type());

			return new ModelAndView("sns/result2");

		} catch (Exception e) {
			System.out.println(e);
		}

		return new ModelAndView("sns/error");
	}

	@GetMapping("kakaoLogout.do")
	public ModelAndView kakaoLogout(HttpServletRequest request) {
		// 1. 인증코드 받기
		String code = request.getParameter("code");

		// 2. 토큰 받기 (인증코드 필요)
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			String access = (String) request.getSession().getAttribute("access_token");

			// 헤더 요청
			conn.setRequestProperty("Authorization", "Bearer " + access);

			// 결과 코드가 200이면 성공
			int responseCode = conn.getResponseCode();
			System.out.println();

			// 토큰 요청을 통한 결과를 얻는데 JSON 타입
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			StringBuffer sb2 = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb2.append(line);
			}
			String result = sb2.toString();
			System.out.println(result);
			
			request.getSession().removeAttribute("access_token");
			request.getSession().removeAttribute("refresh_token");
			request.getSession().removeAttribute("token_type");

			return new ModelAndView("sns/loginForm");

		} catch (Exception e) {
			System.out.println(e);
		}

		return new ModelAndView("sns/error");
	}
	
	@GetMapping("naverLogout.do")
	public ModelAndView naverLogout(HttpServletRequest request) {
		// 1. 인증코드 받기
		
		// 2. 토큰 받기 (인증코드 필요)
		String reqURL = "https://nid.naver.com/oauth2.0/token";
		try {
			URL url = new URL(reqURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			
			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			String access_token =  (String) request.getSession().getAttribute("access_token");
			System.out.println(access_token);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuffer sb = new StringBuffer();
			sb.append("client_id=dWuJjgBSuwUlP52dk2F4");
			sb.append("&client_secret=xVwnls3GZM");
			sb.append("&access_token="+access_token);
			sb.append("&grant_type=delete");
			sb.append("&service_provider=NAVER");
			bw.write(sb.toString());
			bw.flush();
			
			
			// 결과 코드가 200이면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("여기2 : "+responseCode);
			
			// 토큰 요청을 통한 결과를 얻는데 JSON 타입
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			StringBuffer sb2 = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb2.append(line);
			}
			String result = sb2.toString();
			System.out.println("여기3 : "+result);
			
			// 세션 초기화 
			request.getSession().removeAttribute("access_token");
			request.getSession().removeAttribute("refresh_token");
			request.getSession().removeAttribute("token_type");
			
			return new ModelAndView("sns/loginForm");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return new ModelAndView("sns/error");
	}
	
	
	
	
	
	
	
	
	@RequestMapping("kakaomap01.do")
	public ModelAndView kakaoMap01() {
		return new ModelAndView("sns/kakao_map01");
	}
	@RequestMapping("kakaomap02.do")
	public ModelAndView kakaoMap02() {
		return new ModelAndView("sns/kakao_map02");
	}
	@RequestMapping("kakaomap03.do")
	public ModelAndView kakaoMap03() {
		return new ModelAndView("sns/kakao_map03");
	}
	@RequestMapping("kakaomap04.do")
	public ModelAndView kakaoMap04() {
		return new ModelAndView("sns/kakao_map04");
	}
	@RequestMapping("kakaoaddr.do")
	public ModelAndView kakaoAddr() {
		return new ModelAndView("sns/kakao_addr");
	}
	@RequestMapping("kakaoaddr_ok.do")
	public ModelAndView kakaoAddr_OK(AddrVO addrvo) {
		try {
			// DB에 등록
			int result = addrDAO.Addr_Insert(addrvo);
			
			if (result>0) {
				return new ModelAndView("sns/loginForm");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("sns/error");
	}
	
	
	
	@RequestMapping("dynamic_query.do")
	public ModelAndView dynamicQuery() {
		return new ModelAndView("emp/dynamicQuery");
	}
	
	
	
	
	
}

























