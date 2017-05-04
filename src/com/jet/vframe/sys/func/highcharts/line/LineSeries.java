package com.jet.vframe.sys.func.highcharts.line;

import java.util.List;

import com.jet.vframe.sys.func.highcharts.base.Series;

public class LineSeries extends Series {

	public LineSeries(String name, List<Double> data) {
		super(name, "line");
		this.data = data;
	}

	private List<Double> data;

	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}

}
