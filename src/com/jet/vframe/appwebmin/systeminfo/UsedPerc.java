package com.jet.vframe.appwebmin.systeminfo;

import java.util.List;

public class UsedPerc {
	private List<Integer> cpuPercs;
	private int memPerc;
	private List<Integer> diskPercs;
	private List<Long> netSpeeds;

	public List<Integer> getCpuPercs() {
		return cpuPercs;
	}

	public void setCpuPercs(List<Integer> cpuPercs) {
		this.cpuPercs = cpuPercs;
	}

	public int getMemPerc() {
		return memPerc;
	}

	public void setMemPerc(int memPerc) {
		this.memPerc = memPerc;
	}

	public List<Integer> getDiskPercs() {
		return diskPercs;
	}

	public void setDiskPercs(List<Integer> diskPercs) {
		this.diskPercs = diskPercs;
	}

	public List<Long> getNetSpeeds() {
		return netSpeeds;
	}

	public void setNetSpeeds(List<Long> netSpeeds) {
		this.netSpeeds = netSpeeds;
	}

}
