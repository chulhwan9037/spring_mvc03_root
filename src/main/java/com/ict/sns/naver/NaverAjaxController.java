package com.ict.sns.naver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.sns.kakao.KakaoUserVO;

@RestController
public class NaverAjaxController {
//	@RequestMapping(value = "naverUser.do", produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public String memberChk(HttpSession session) {
//		// access_token를 가지고 사용자 정보를 가져올 수 있다.
//		String access_token = (String)session.getAttribute("access_token");
//		String header = "Bearer " + access_token;
//		
//		String apiURL = "https://openapi.naver.com/v1/nid/me";
//
//		Map<String, String> requestHeaders = new HashMap<String, String>();
//		requestHeaders.put("Authorization", header);
//		
//		String responseBody = naver_send(apiURL, requestHeaders, session);
//		
//		return responseBody;
//		
//	}
//	
//	public String naver_send(String apiURL, Map<String, String> requestHeaders, HttpSession session) {
//		HttpURLConnection conn = null;
//		try {
//			URL url = new URL(apiURL);
//			conn = (HttpURLConnection)url.openConnection();
//			
//			conn.setRequestMethod("POST");
//			conn.setDoOutput(true);
//			
//	
//			for (Map.Entry<String, String> k : requestHeaders.entrySet()) {
//				conn.setRequestProperty(k.getKey(), k.getValue());
//			}
//			
//			// 200이면 성공 => HttpURLConnection.HTTP_OK
//			int responseCode = conn.getResponseCode();
//			if (responseCode == HttpURLConnection.HTTP_OK) {
//				return readBody(conn.getInputStream(), session);
//			} else {
//				return readBody(conn.getErrorStream(), session);
//			}
//		} catch (Exception e) {
//			System.out.println("연결실패");
//		} finally {
//			try {
//				conn.disconnect();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//		return null;
//	}
//	
//	public String readBody(InputStream body, HttpSession session) {
//		InputStreamReader isr = new InputStreamReader(body);
//		try {
//			BufferedReader br = new BufferedReader(isr);
//			StringBuffer sb = new StringBuffer();
//			String line = "";
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			}
//			System.out.println(sb.toString());
//			// 혹시 DB에 저장하거나 로그인 성공 저장
//			session.setAttribute("loginChk", "ok");
//			return sb.toString();
//		} catch (Exception e) {
//			System.out.println("API 응답을 읽는데 실패");
//		}
//		return null;
//	}
	
	
	
	@RequestMapping(value = "naverUser.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String memberChk(HttpSession session) {
		// https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api 해당 사이트에서 사용자
		// 정보 가져오기
		// access_token을 가지고 사용자 정보를 가져올 수 있다.
		String access_token = (String) session.getAttribute("access_token");

		String apiURL = "https://openapi.naver.com/v1/nid/me";

		try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization", "Bearer " + access_token);

			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
			
				Gson gson = new Gson(); // json을 객체 => fromJson(변경할 json, 변경되는 객체)
				NaverUserVO nvo = gson.fromJson(result, NaverUserVO.class);
				
				String naver_id = nvo.getResponse().getId();
				String naver_nickname = nvo.getResponse().getNickname();
				String naver_email = nvo.getResponse().getEmail();
				String naver_name = nvo.getResponse().getName();
				String naver_profile_image = nvo.getResponse().getProfile_image();
				
				//DB에 저장하기
				
				return naver_id+"/"+naver_nickname+"/"+naver_email+"/"+naver_name+"/"+naver_profile_image;
			
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}
}
