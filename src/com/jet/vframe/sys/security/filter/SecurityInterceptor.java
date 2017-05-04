package com.jet.vframe.sys.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jet.vframe.sys.tool.SP;
import com.jet.vframe.sys.user.pojo.User;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub

		User webUser = (User) request.getSession().getAttribute("webUser");
		if (webUser != null && webUser.getPassword() != null && webUser.getPassword() != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + SP.getSystemValue("sys.security.error.page"));
			return false;
		}

	}
}
