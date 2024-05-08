package com.teamdev.todo.Interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.teamdev.todo.data.entity.user.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {

		// 요청 메서드가 OPTIONS인 경우 처리하지 않음
		if ("OPTIONS".equals(request.getMethod())) {
			return true;
		}

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Not authenticated");
			return false; // 요청을 중단하고 응답을 반환
		}

		/* 사용자 권한 확인(리스트 호출 시)*/
		User sessionUser = (User)session.getAttribute("user");
		String path = request.getRequestURI();
		Pattern pattern = Pattern.compile("/list/(\\d+)");
		Matcher matcher = pattern.matcher(path);
		if (matcher.find()) {
			Long pathUserNo = Long.parseLong(matcher.group(1));
			if (!sessionUser.getUserNo().equals(pathUserNo)) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.getWriter().write("Access denied");
				return false;
			}
		}
		return true;
	}

}
