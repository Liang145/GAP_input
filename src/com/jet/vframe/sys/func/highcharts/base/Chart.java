package com.jet.vframe.sys.func.highcharts.base;

public class Chart {
	public Chart(String type) {
		super();
		this.type = type;
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
