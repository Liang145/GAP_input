package com.jet.vframe.sys.func.highcharts.base;

import java.util.List;

public class XAxis {
	public XAxis(List<String> categories, String type, int tickInterval) {
		super();
		this.categories = categories;
		this.type = type;
		this.tickInterval = tickInterval;
		this.tickWidth = 4;
	}
	
	

	public XAxis(List<String> categories, String type, int tickInterval,
			int tickWidth) {
		super();
		this.categories = categories;
		this.type = type;
		this.tickInterval = tickInterval;
		this.tickWidth = tickWidth;
		this.tickLength = 5;
	}



	private List<String> categories;
	private String type;
	private int tickInterval;//X轴时间间隔，毫秒
	private int tickWidth;//X轴上刻度线的宽度
	private int tickLength;//X轴上刻度线的长度

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTickInterval() {
		return tickInterval;
	}

	public void setTickInterval(int tickInterval) {
		this.tickInterval = tickInterval;
	}

	public int getTickWidth() {
		return tickWidth;
	}

	public void setTickWidth(int tickWidth) {
		this.tickWidth = tickWidth;
	}



	public int getTickLength() {
		return tickLength;
	}



	public void setTickLength(int tickLength) {
		this.tickLength = tickLength;
	}
	
	
	
}
