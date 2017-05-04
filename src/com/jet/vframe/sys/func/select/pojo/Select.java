package com.jet.vframe.sys.func.select.pojo;

public class Select {

	public Select() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Select(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Select(String id, String name, int sort) {
		super();
		this.id = id;
		this.name = name;
		this.sort = sort;
	}

	private String id;
	private String name;
	private int sort;

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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
