package com.jet.vframe.sys.mainframe;

public abstract class BaseMenu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -327045939920547971L;
	private String id;
	private String parentId;
	private String name;
	private int sort;

	// Constructors

	/** default constructor */
	public BaseMenu() {
	}

	public BaseMenu(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public BaseMenu(String id, String parentId, String name, int sort) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.sort = sort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}