package com.jet.vframe.appwebmin.sysconfig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jet.vframe.sys.tool.RuntimeUtils;

@Controller
@RequestMapping("/appwebmin/sysconfig/admin")
public class SysConfig {
	@RequestMapping(value = "time/edit")
	public ModelAndView editDate() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("start_date", RuntimeUtils.getDateByCmd());
		mv.setViewName("appwebmin/sysconfig/admin/time-edit");
		return mv;
	}

	@RequestMapping(value = "time/save")
	public ModelAndView saveDate(@ModelAttribute("start_date") String start_date) {
		// to change date
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("tip", RuntimeUtils.changeDateByCmd(start_date));
		mv.setViewName("appwebmin/sysconfig/admin/time-edit");
		return mv;
	}

}
