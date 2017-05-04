package com.jet.vframe.sys.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.jet.vframe.sys.user.pojo.User;

/**
 * @author Administrator
 * 
 */
public abstract class BaseController {
	/**
	 * 获取Session中登录的用户
	 * 
	 * @return
	 */
	public User getWebUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("webUser");
	}

	public void setWebUser(HttpServletRequest request, User webUser) {
		
		request.getSession().setAttribute("webUser", webUser);
		
	}

	public void removeWebUser(HttpServletRequest request) {
		request.getSession().removeAttribute("webUser");
	}

	@InitBinder("form")
	public void initBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("form.");// 别名前缀
	}

	public int getPageStart(HttpServletRequest request) {
		// 每一页开始索引
		String start = request.getParameter("iDisplayStart");
		if (start != null) {
			return Integer.valueOf(start);
		} else {
			return 0;
		}
	}

	public int getPageLength(HttpServletRequest request) {
		// 每页显示条数
		String start = request.getParameter("iDisplayLength");
		if (start != null) {
			return Integer.valueOf(start);
		} else {
			return 0;
		}
	}

	public String getSearchString(HttpServletRequest request) {
		// 搜索内容
		return request.getParameter("sSearch");

	}
}
