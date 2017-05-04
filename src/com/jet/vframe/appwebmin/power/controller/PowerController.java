package com.jet.vframe.appwebmin.power.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jet.vframe.sys.tool.RuntimeUtils;
import com.jet.vframe.sys.tool.SP;

@Controller
@RequestMapping("/appwebmin/power/admin")
public class PowerController {
	@RequestMapping(value = "func")
	public String func() {
		return "appwebmin/power/admin/func";
	}

	@RequestMapping(value = "shutdown")
	@ResponseBody
	public void shutdown() {
		RuntimeUtils.exec(SP.getSystemValue("cmd_shutdown"));
	}

	@RequestMapping(value = "reboot")
	@ResponseBody
	public void reboot() {
		RuntimeUtils.exec(SP.getSystemValue("cmd_reboot"));
	}
}
