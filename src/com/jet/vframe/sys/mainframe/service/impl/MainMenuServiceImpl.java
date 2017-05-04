package com.jet.vframe.sys.mainframe.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jet.vframe.sys.base.service.impl.BaseServiceImpl;
import com.jet.vframe.sys.mainframe.dao.MainMenuDao;
import com.jet.vframe.sys.mainframe.pojo.MainMenu;
import com.jet.vframe.sys.mainframe.service.MainMenuService;

@Service
public class MainMenuServiceImpl extends BaseServiceImpl<MainMenu>implements MainMenuService {

	private static List<MainMenu> menuListCahce;

	@SuppressWarnings("unchecked")
	@Override
	public List<MainMenu> getAllMainMenu() {
		// TODO Auto-generated method stub
		// if (menuListCahce == null) {
		menuListCahce = (List<MainMenu>) ((MainMenuDao) this.baseDao).queryTopMenu();
		int size = menuListCahce.size();
		for (int i = 0; i < size; i++) {
			MainMenu topMainMenu = menuListCahce.get(i);
			topMainMenu.setChildren(getAllChildren(topMainMenu.getId()));
		}
		// }

		return menuListCahce;
	}

	@SuppressWarnings("unchecked")
	private List<MainMenu> getAllChildren(String parentId) {
		List<MainMenu> list1 = ((MainMenuDao) this.baseDao).queryChildMenu(parentId);
		int size = list1.size();
		for (int i = 0; i < size; i++) {
			list1.get(i).setChildren(((MainMenuDao) this.baseDao).queryChildMenu(list1.get(i).getId()));
		}
		return list1;
	}

	@Override
	public List<MainMenu> getMainMenuByType(int userType) {
		// TODO Auto-generated method stub

		menuListCahce = (List<MainMenu>) ((MainMenuDao) this.baseDao).queryTopMenuByType(userType);
		int size = menuListCahce.size();
		for (int i = 0; i < size; i++) {
			MainMenu topMainMenu = menuListCahce.get(i);
			// topMainMenu.setChildren(getAllChildren(topMainMenu.getId()));
			topMainMenu.setChildren(getChildrenByType(topMainMenu.getId(), userType));
		}

		return menuListCahce;
	}

	private List<MainMenu> getChildrenByType(String parentId, int userType) {
		// TODO Auto-generated method stub
		List<MainMenu> list1 = ((MainMenuDao) this.baseDao).queryChildMenuByType(parentId, userType);
		int size = list1.size();
		for (int i = 0; i < size; i++) {
			list1.get(i).setChildren(((MainMenuDao) this.baseDao).queryChildMenuByType(list1.get(i).getId(), userType));
		}
		return list1;
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return this.baseDao.queryCount(new String[] { "id" }, null, null);
	}

}
