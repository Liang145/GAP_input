package com.jet.vframe.appwebmin.systeminfo.controller;

import java.util.ArrayList;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jet.vframe.appwebmin.systeminfo.UsedPerc;

@Controller
@RequestMapping("/appwebmin/systeminfo/admin")
public class SysteminfoController {
	private static Sigar sigar = new Sigar();

	@RequestMapping(value = "info")
	public ModelAndView info() {
		ModelAndView mv = new ModelAndView();
		int cpuCount = 1;
		int netCount = 1;
		int diskPerc = 0;

		FileSystemUsage usage = null;
		try {
			Cpu[] cpus = sigar.getCpuList();
			if (cpus != null) {
				cpuCount = cpus.length;
			}
			String[] netList = sigar.getNetInterfaceList();
			if (netList != null) {
				netCount = netList.length;
				for (int i = 0; i < netList.length; i++) {
					if ("lo".equals(netList[i])) {
						netCount--;
					}
				}
			}
			FileSystem[] fslist = sigar.getFileSystemList();
			if (fslist != null && fslist.length > 0) {
				usage = sigar.getFileSystemUsage(fslist[0].getDirName());
			}
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (usage != null) {
			diskPerc = (int) (usage.getUsePercent() * 100);
		}

		mv.addObject("cpuCount", cpuCount);
		mv.addObject("netCount", netCount);
		mv.addObject("diskPerc", diskPerc);
		mv.setViewName("appwebmin/systeminfo/admin/systemInfo");
		return mv;
	}

	@RequestMapping(value = "usedPerc")
	@ResponseBody
	public UsedPerc usedPerc() {
		// 硬盘不需要实时，所以在此没有加入
		UsedPerc usedPerc = new UsedPerc();
		usedPerc.setCpuPercs(new ArrayList<Integer>());
		usedPerc.setMemPerc(0);
		usedPerc.setNetSpeeds(new ArrayList<Long>());

		CpuPerc[] cpuPercs = null;// 支持多核心CPU
		Mem mem = null;
		String[] netList = null;
		try {
			cpuPercs = sigar.getCpuPercList();
			// cpuPerc = sigar.getCpuPerc();//只是单核心CPU
			mem = sigar.getMem();
			netList = sigar.getNetInterfaceList();

		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cpuPercs != null) {
			for (int i = 0; i < cpuPercs.length; i++) {
				usedPerc.getCpuPercs().add((int) (cpuPercs[i].getCombined() * 100));
			}

		}
		if (mem != null) {
			usedPerc.setMemPerc((int) mem.getUsedPercent());
		}
		if (netList != null && netList.length > 0) {
			for (int i = 0; i < netList.length; i++) {
				if ("lo".equals(netList[i])) {
					continue;
				}
				NetInterfaceStat netStat = null;
				try {
					netStat = sigar.getNetInterfaceStat(netList[i]);
				} catch (SigarException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (netStat != null) {
					usedPerc.getNetSpeeds().add(netStat.getSpeed());
				}
			}
		}
		return usedPerc;
	}

	public static void main(String[] args) throws SigarException {
		Sigar sigar = null;
		sigar = new Sigar();
		String[] ifaces = sigar.getNetInterfaceList();
		for (int i = 0; i < ifaces.length; i++) {
			NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
			if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
					|| NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
				continue;
			}

			NetInterfaceStat netStat = sigar.getNetInterfaceStat(ifaces[i]);
			System.out.println(cfg.getName() + "speed:" + netStat.getRxBytes());
			System.out.println(cfg.getName() + "IP地址:" + cfg.getAddress());// IP地址
			System.out.println(cfg.getName() + "网关广播地址:" + cfg.getBroadcast());// 网关广播地址
			System.out.println(cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
			System.out.println(cfg.getName() + "子网掩码:" + cfg.getNetmask());// 子网掩码
			System.out.println(cfg.getName() + "网卡描述信息:" + cfg.getDescription());// 网卡描述信息
			System.out.println(cfg.getName() + "网卡类型" + cfg.getType());//
		}
	}
}
