package com.jet.vframe.sys.func.highcharts.pie;

import java.util.List;

import com.jet.vframe.sys.func.highcharts.base.Series;

public class PieSeries extends Series {
	public PieSeries(String name, List<PieSeriesData> data) {
		super(name, "pie");
		this.data = data;
	}

	private List<PieSeriesData> data;

	public List<PieSeriesData> getData() {
		return data;
	}

	public void setData(List<PieSeriesData> data) {
		this.data = data;
	}
}
