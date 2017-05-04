package com.jet.vframe.sys.func.highcharts.base;

/**
 * 用于设置图表中的标准线
 * 
 * @author wangzisong
 * 
 */
public class PlotLine {
	
	public PlotLine(int value) {
		super();
		this.value = value;
		this.color = "#FF0000";
		this.width = 1;
	}

	public PlotLine(String color, int width, int value) {
		super();
		this.color = color;
		this.width = width;
		this.value = value;
	}
	private String color;//标准线的颜色
	private int width;//标准线的宽度
	private int value;//标准线的位置

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
