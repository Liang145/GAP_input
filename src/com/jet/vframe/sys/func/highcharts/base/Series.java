package com.jet.vframe.sys.func.highcharts.base;

public class Series {
	public Series(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	private String name;
	private String type;

	/*
	 * The type of series. Can be one of area, areaspline, bar, column, line,
	 * pie, scatter or spline. From version 2.3, arearange, areasplinerange and
	 * columnrange are supported with the highcharts-more.js component.
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
