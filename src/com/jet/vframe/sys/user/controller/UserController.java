package com.jet.vframe.sys.user.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.jet.vframe.sys.base.controller.BaseController;
import com.jet.vframe.sys.func.datagrid.DataGrid;
import com.jet.vframe.sys.tool.HQLUtils;
import com.jet.vframe.sys.tool.RegularExpressionsUtils;
import com.jet.vframe.sys.user.ModifyPw;
import com.jet.vframe.sys.user.pojo.User;
import com.jet.vframe.sys.user.service.UserService;

@Controller
@RequestMapping("/user/admin")
public class UserController extends BaseController {
	@Autowired
	private UserService service;

	@RequestMapping(value = "modifypw", method = RequestMethod.POST, headers = { "content-type=application/json" })
	@ResponseBody
	public String modifyPassword(@RequestBody ModifyPw modifyPw) {

		if (service.modifyPassword(modifyPw)) {
			return "保存成功";
		}

		return "保存失败";
	}

	@RequestMapping("list/info")
	public String listInfo(HttpServletRequest request) {
		return "user/admin/dataGrid-info";
	}

	@RequestMapping("list/level")
	public String listLevel(HttpServletRequest request) {
		return "user/admin/dataGrid-level";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid<User> dataGrid(HttpServletRequest request) {
		return service.getDataGridByPage(new String[] { "user_name", "real_name", "role", "level", "is_filter", "enable", "create_date" }, "role=:role", "create_date DESC",
				HQLUtils.generalMapParamsSingle("role", 3), this.getPageStart(request), this.getPageLength(request));
	}

	@RequestMapping("add")
	public String add(HttpServletRequest request) {
		return "user/admin/add";
	}

	@RequestMapping("edit/info/{user_name}")
	public ModelAndView editInfo(@PathVariable String user_name, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("form", service.queryById(user_name));
		mv.setViewName("user/admin/edit-info");
		return mv;
	}

	@RequestMapping("edit/level/{user_name}")
	public ModelAndView editLevel(@PathVariable String user_name, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("form", service.queryById(user_name));
		mv.setViewName("user/admin/edit-level");
		return mv;
	}

	@RequestMapping("save")
	public ModelAndView save(@ModelAttribute("form") User form, HttpServletRequest request) {
		String tip = "保存成功";
		ModelAndView mv = new ModelAndView();
		String user_name = form.getUser_name();
		if (!RegularExpressionsUtils.isLetterNumberOrChinese(user_name)) {
			tip = "用户名只能包括字母、数字和汉字";
		} else if (!RegularExpressionsUtils.isLetterNumber(user_name)) {
			tip = "用户名只能包括字母和数字";
		} else if (service.isExist(user_name)) {
			tip = "用户名已经被使用";
		} else {
			form.setCreate_date(new Date());
			form.setRole(3);
			form.setLevel(0);
			service.insert(form);
		}
		mv.addObject("tip", tip);
		mv.setViewName("user/admin/edit-info");
		return mv;
	}

	@RequestMapping("update/info")
	public ModelAndView updateInfo(@ModelAttribute("form") User form, HttpServletRequest request) {
		String tip = "保存成功";
		ModelAndView mv = new ModelAndView();
		int res = service.updateInfo(form);
		if (res <= 0) {
			tip = "保存失败";
		}

		mv.addObject("tip", tip);
		mv.setViewName("user/admin/edit-info");
		return mv;
	}

	@RequestMapping("update/level")
	public ModelAndView updateLevel(@ModelAttribute("form") User form, HttpServletRequest request) {
		String tip = "保存成功";
		ModelAndView mv = new ModelAndView();
		int res = service.updateLevel(form);
		if (res <= 0) {
			tip = "保存失败";
		}

		mv.addObject("tip", tip);
		mv.setViewName("user/admin/edit-level");
		return mv;
	}

	@RequestMapping("delete/{user_name}")
	@ResponseBody
	public int delete(@PathVariable String user_name) {
		return service.deleteUserAndChannel(user_name);
	}
}
