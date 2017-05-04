package com.jet.vframe.sys.mainframe;

import java.util.List;

import com.jet.vframe.sys.base.dao.BaseDao;

public interface BaseMenuDao<T> extends BaseDao<T> {
	/**
	 * 通过父ID去查询所有子菜单
	 * 
	 * @param parentId
	 *            父ID
	 * @return 返回所有子菜单
	 */
	@SuppressWarnings("rawtypes")
	List queryChildMenu(String parentId);

	/**
	 * 查询所有最上层可以显示的菜单
	 * 
	 * @return 返回所有可显示的最上层菜单
	 */
	@SuppressWarnings("rawtypes")
	List queryTopMenu();
}
