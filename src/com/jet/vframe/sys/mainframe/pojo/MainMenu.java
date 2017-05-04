package com.jet.vframe.sys.mainframe.pojo;

import java.util.List;

import com.jet.vframe.sys.mainframe.BaseMenu;

public class MainMenu extends BaseMenu implements java.io.Serializable {

	private static final long serialVersionUID = -5485893623380902579L;

	public MainMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MainMenu(String id, String parentId, String name, int sort) {
		super(id, parentId, name, sort);
		// TODO Auto-generated constructor stub
	}

	public MainMenu(String src, boolean isDefault, boolean isUrl, boolean isHidden, String reserved1, String reserved2, List<MainMenu> children) {
		super();
		this.src = src;
		this.isDefault = isDefault;
		this.isUrl = isUrl;
		this.isHidden = isHidden;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.children = children;
	}

	private String src;
	private boolean isDefault;
	private boolean isUrl = false;
	private boolean isHidden = false;
	private String reserved1;
	private String reserved2;
	private int type;
	// 非数据库字段，只具封装作用
	private List<MainMenu> children;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean getIsUrl() {
		return isUrl;
	}

	public void setIsUrl(boolean isUrl) {
		this.isUrl = isUrl;
	}

	public boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public List<MainMenu> getChildren() {
		return children;
	}

	public void setChildren(List<MainMenu> children) {
		this.children = children;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
