package com.jet.vframe.sys.mainframe.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jet.vframe.sys.mainframe.BaseMenuDaoImpl;
import com.jet.vframe.sys.mainframe.dao.MainMenuDao;
import com.jet.vframe.sys.mainframe.pojo.MainMenu;
@Repository
public class MainMenuDaoImpl extends BaseMenuDaoImpl<MainMenu> implements MainMenuDao {

	public MainMenuDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MainMenu> queryTopMenuByType(int userType) {
		// TODO Auto-generated method stub
		String where = "parentId = :parentId AND type = :type AND isHidden=:isHidden";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", "0");
		params.put("type", userType);
		params.put("isHidden", false);
		return this.query(where, "sort", params);
	}

	@Override
	public List<MainMenu> queryChildMenuByType(String parentId, int userType) {
		// TODO Auto-generated method stub
		String where = "parentId = :parentId AND type = :type AND isHidden=:isHidden";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("type", userType);
		params.put("isHidden", false);
		return this.query(where, "sort", params);
	}
}
