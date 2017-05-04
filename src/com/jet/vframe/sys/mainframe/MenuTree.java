package com.jet.vframe.sys.mainframe;

import java.util.List;

public class MenuTree {
	public MenuTree() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuTree(String id, String name, List<SubMenuTree> subMenuList) {
		super();
		this.id = id;
		this.name = name;
		this.subMenuList = subMenuList;
	}

	private String id;
	private String name;
	private List<SubMenuTree> subMenuList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubMenuTree> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<SubMenuTree> subMenuList) {
		this.subMenuList = subMenuList;
	}
}
