package com.jet.vframe.sys.func.highcharts.dynaupdate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DynaSeriesData {
	private long x;
	private double y;

	public DynaSeriesData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DynaSeriesData(long x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
