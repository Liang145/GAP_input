package com.jet.vframe.sys.func.datagrid;

import javax.servlet.http.HttpServletRequest;

import com.jet.vframe.sys.base.controller.BaseController;

public class BaseDataGridAction<T> extends BaseController {

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
