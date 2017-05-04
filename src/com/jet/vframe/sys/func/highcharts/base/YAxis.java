package com.jet.vframe.sys.func.highcharts.base;

public class YAxis {

	public YAxis() {
		// TODO Auto-generated constructor stub
	}

	public YAxis(Title title, PlotLine[] plotLines) {
		super();
		this.title = title;
		this.plotLines = plotLines;
	}

	private Title title;
	private PlotLine[] plotLines;

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public PlotLine[] getPlotLines() {
		return plotLines;
	}

	public void setPlotLines(PlotLine[] plotLines) {
		this.plotLines = plotLines;
	}

}
