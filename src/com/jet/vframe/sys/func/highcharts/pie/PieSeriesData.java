package com.jet.vframe.sys.func.highcharts.pie;

public class PieSeriesData {
	private String name;
	private double y;
	boolean sliced;
	boolean selected;
	private double realY;
	
	
	
	public PieSeriesData(String name, double y, boolean sliced, boolean selected) {
		super();
		this.name = name;
		this.y = y;
		this.sliced = sliced;
		this.selected = selected;
	}

	public PieSeriesData(String name, double y, boolean sliced,
			boolean selected, double realY) {
		super();
		this.name = name;
		this.y = y;
		this.sliced = sliced;
		this.selected = selected;
		this.realY = realY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isSliced() {
		return sliced;
	}

	public void setSliced(boolean sliced) {
		this.sliced = sliced;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public double getRealY() {
		return realY;
	}

	public void setRealY(double realY) {
		this.realY = realY;
	}

}
