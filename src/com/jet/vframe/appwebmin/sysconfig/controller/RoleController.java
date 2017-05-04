package com.jet.vframe.appwebmin.sysconfig.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jet.vframe.appwebmin.datalog.pojo.DataLog;
import com.jet.vframe.appwebmin.sysconfig.pojo.Role;
import com.jet.vframe.appwebmin.sysconfig.service.RoleService;
import com.jet.vframe.appwebmin.userlog.pojo.UserLog;
import com.jet.vframe.sys.base.controller.BaseController;
import com.jet.vframe.sys.func.datagrid.DataGrid;
import com.jet.vframe.sys.tool.HQLUtils;
import com.jet.vframe.sys.tool.RegularExpressionsUtils;
import com.jet.vframe.sys.user.pojo.User;

@Controller
@RequestMapping("/appwebmin/sysconfig/admin")
public class RoleController extends BaseController{
	
	@Autowired
	public RoleService service;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {
		return "appwebmin/sysconfig/admin/dataGrid";
	}
	
	@RequestMapping(value = "dataGrid")
	@ResponseBody
	public DataGrid<Role> dataGrid(HttpServletRequest request) {
		return service.getDataGridByPage(null, null, null, this.getPageStart(request),
				this.getPageLength(request));
	}
	
	@RequestMapping("save")
	public ModelAndView save(@ModelAttribute("form") Role form, HttpServletRequest request) {
		String tip = "保存成功";
		ModelAndView mv = new ModelAndView();
		String keyword = form.getKeyword();
		if  (service.isExist(keyword)) {
			tip = "敏感字已存在";
		} else {
			service.insert(form);
		}
		mv.addObject("tip", tip);
		mv.setViewName("appwebmin/sysconfig/admin/dataGrid");
		return mv;
	}
	
	
	@RequestMapping("delete/{keyword}")
	@ResponseBody
	public int delete(@PathVariable String keyword) {
		return service.deleteRoleKeyword(keyword);
	}
	
	
	
	@RequestMapping("add")
	public String add(HttpServletRequest request) {
		return "appwebmin/sysconfig/admin/add";
	}
	
	
	
}
