package com.ict.sns.kakao;

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

@RestController
public class KakaoAjaxController2 {
	@RequestMapping(value = "kakaoUser2.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String memberChk(HttpSession session) {
		// https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api 해당 사이트에서 사용자
		// 정보 가져오기
		// access_token을 가지고 사용자 정보를 가져올 수 있다.
		String access_token = (String) session.getAttribute("access_token");

		String apiURL = "https://kapi.kakao.com/v2/user/me";

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
				KakaoUserVO kvo = gson.fromJson(result, KakaoUserVO.class);
				
				String kakao_id = kvo.getId();
				String kakao_nickname = kvo.getProperties().getNickname();
				String kakao_email = kvo.getKakao_account().getEmail();
				
				//DB에 저장하기
				
				return kakao_id+"/"+kakao_nickname+"/"+kakao_email;
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}

}
