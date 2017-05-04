package com.jet.vframe.sys.mainframe.service;

import java.util.List;

import com.jet.vframe.sys.base.service.BaseService;
import com.jet.vframe.sys.mainframe.pojo.MainMenu;

public interface MainMenuService extends BaseService<MainMenu> {
	List<MainMenu> getAllMainMenu();

	// 通过用户类型取数据，0为最高级，数字越小，权限越小
	List<MainMenu> getMainMenuByType(int userType);

	long getCount();
}
