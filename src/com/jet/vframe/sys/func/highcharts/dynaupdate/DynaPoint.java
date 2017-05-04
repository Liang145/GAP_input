package com.jet.vframe.sys.func.highcharts.dynaupdate;

public class DynaPoint extends DynaSeriesData {

	private boolean redraw;
	private boolean shift;

	public DynaPoint(long x, double y, boolean redraw, boolean shift) {
		super(x, y);
		this.redraw = redraw;
		this.shift = shift;
	}

	public DynaPoint(long x, double y) {
		super(x, y);
		this.redraw = true;
		this.shift = true;
	}

	public boolean isRedraw() {
		return redraw;
	}

	public void setRedraw(boolean redraw) {
		this.redraw = redraw;
	}

	public boolean isShift() {
		return shift;
	}

	public void setShift(boolean shift) {
		this.shift = shift;
	}

}
