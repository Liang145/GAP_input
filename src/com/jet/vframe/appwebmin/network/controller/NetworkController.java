package com.jet.vframe.appwebmin.network.controller;

import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.jet.vframe.appwebmin.network.Network;
import com.jet.vframe.sys.base.controller.BaseController;
import com.jet.vframe.sys.tool.PropUtils;
import com.jet.vframe.sys.tool.RuntimeUtils;
import com.jet.vframe.sys.tool.SP;

@Controller
@RequestMapping("/appwebmin/network/admin")
public class NetworkController extends BaseController {
	@RequestMapping(value = "edit/{type}")
	public ModelAndView editData(@PathVariable String type) {
		ModelAndView mv = new ModelAndView();
		Properties prop = null;
		if ("data".equals(type)) {
			prop = PropUtils.load(SP.getSystemValue("network_file_path_data"));
		} else if ("manage".equals(type)) {
			prop = PropUtils.load(SP.getSystemValue("network_file_path_manage"));
		}

		if (prop != null) {
			Network form = new Network();
			form.setDns1(prop.getProperty(SP.getSystemValue("network_key_dns1")));
			form.setDns2(prop.getProperty(SP.getSystemValue("network_key_dns2")));
			form.setGateway(prop.getProperty(SP.getSystemValue("netwokr_key_gateway")));
			form.setIp(prop.getProperty(SP.getSystemValue("network_key_ip")));
			form.setNetmask(prop.getProperty(SP.getSystemValue("network_key_netmask")));
			mv.addObject("form", form);
		}
		mv.setViewName("appwebmin/network/admin/edit-" + type);

		return mv;
	}

	@RequestMapping(value = "save/{type}")
	public synchronized ModelAndView saveData(@PathVariable String type, @ModelAttribute("form") Network form) {
		String tip = "保存成功";
		ModelAndView mv = new ModelAndView();
		if (form != null) {
			Properties prop = null;
			Properties prop1 = null;
			if ("data".equals(type)) {
				prop = PropUtils.load(SP.getSystemValue("network_file_path_data"));
				if (prop != null) {
					prop1 = PropUtils.load(SP.getSystemValue("network_file_path_manage"));
				} else {
					tip = "保存失败";
				}
			} else if ("manage".equals(type)) {
				prop = PropUtils.load(SP.getSystemValue("network_file_path_manage"));
				if (prop != null) {
					prop1 = PropUtils.load(SP.getSystemValue("network_file_path_data"));
				} else {
					tip = "保存失败";
				}
			}
			if (!form.getIp().equals(prop1.getProperty(SP.getSystemValue("network_key_ip")))) {
				prop.setProperty(SP.getSystemValue("network_key_ip"), form.getIp());
				prop.setProperty(SP.getSystemValue("netwokr_key_gateway"), form.getGateway());
				prop.setProperty(SP.getSystemValue("network_key_netmask"), form.getNetmask());
				String dns1 = form.getDns1();
				if (dns1 != null && dns1.length() > 0) {
					prop.setProperty(SP.getSystemValue("network_key_dns1"), dns1);
				} else {
					prop.remove(SP.getSystemValue("network_key_dns1"));
				}
				String dns2 = form.getDns2();
				if (dns2 != null && dns2.length() > 0) {
					prop.setProperty(SP.getSystemValue("network_key_dns2"), dns2);
				} else {
					prop.remove(SP.getSystemValue("network_key_dns2"));
				}
				boolean res = false;
				if ("data".equals(type)) {
					res = PropUtils.write(prop, SP.getSystemValue("network_file_path_data"));
				} else if ("manage".equals(type)) {
					res = PropUtils.write(prop, SP.getSystemValue("network_file_path_manage"));
				}
				if (res) {
					System.out.println(RuntimeUtils.execWithReturn(SP.getSystemValue("cmd_restart_network"), null));
				} else {
					tip = "保存失败";
				}
			} else {
				tip = "保存失败,IP repeat";
			}
		} else {
			tip = "保存失败";
		}
		mv.addObject("tip", tip);
		mv.setViewName("appwebmin/network/admin/edit-" + type);
		return mv;
	}
}
