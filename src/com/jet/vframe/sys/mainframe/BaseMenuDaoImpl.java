package com.jet.vframe.sys.mainframe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jet.vframe.sys.base.dao.impl.BaseDaoImpl;

public class BaseMenuDaoImpl<T> extends BaseDaoImpl<T> implements
		BaseMenuDao<T> {

	public BaseMenuDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryChildMenu(String parentId) {
		// TODO Auto-generated method stub
		String where = "parentId = :parentId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		return this.query(where, "sort", params);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryTopMenu() {
		// TODO Auto-generated method stub
		String where = "parentId = :parentId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", "0");
		return this.query(where, "sort", params);
	}
}
