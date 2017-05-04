package com.jet.vframe.sys.mainframe.dao;

import java.util.List;

import com.jet.vframe.sys.mainframe.BaseMenuDao;
import com.jet.vframe.sys.mainframe.pojo.MainMenu;

public interface MainMenuDao extends BaseMenuDao<MainMenu> {

	List<MainMenu> queryTopMenuByType(int userType);

	List<MainMenu> queryChildMenuByType(String parentId, int userType);
}
