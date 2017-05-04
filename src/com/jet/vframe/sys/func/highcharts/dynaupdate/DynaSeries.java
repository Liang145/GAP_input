package com.jet.vframe.sys.func.highcharts.dynaupdate;

import java.util.List;

import com.jet.vframe.sys.func.highcharts.base.Series;

public class DynaSeries extends Series {
	private List<DynaSeriesData> data;

	public DynaSeries(String name, List<DynaSeriesData> data) {
		super(name, "spline");
		this.data = data;
	}

	public List<DynaSeriesData> getData() {
		return data;
	}

	public void setData(List<DynaSeriesData> data) {
		this.data = data;
	}

}
