package com.jet.vframe.sys.func.highcharts;

import java.util.List;

import com.jet.vframe.sys.func.highcharts.base.Chart;
import com.jet.vframe.sys.func.highcharts.base.Series;
import com.jet.vframe.sys.func.highcharts.base.Title;
import com.jet.vframe.sys.func.highcharts.base.XAxis;
import com.jet.vframe.sys.func.highcharts.base.YAxis;

public class HChartsData {

	public HChartsData(Chart chart, Title title, Title subtitle, XAxis xAxis,
			YAxis yAxis, List<Series> series) {
		super();
		this.chart = chart;
		this.title = title;
		this.subtitle = subtitle;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.series = series;
	}

	private Chart chart;
	private Title title;
	private Title subtitle;
	private XAxis xAxis;
	private YAxis yAxis;
	private List<Series> series;

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Title getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(Title subtitle) {
		this.subtitle = subtitle;
	}

	public XAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(XAxis xAxis) {
		this.xAxis = xAxis;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public YAxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(YAxis yAxis) {
		this.yAxis = yAxis;
	}

}
