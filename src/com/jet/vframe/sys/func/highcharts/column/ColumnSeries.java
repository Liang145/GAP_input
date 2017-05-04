package com.jet.vframe.sys.func.highcharts.column;

import java.util.List;

import com.jet.vframe.sys.func.highcharts.base.Series;

public class ColumnSeries extends Series {
	
	public ColumnSeries(String name, List<Double> data) {
		super(name, "column");
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
