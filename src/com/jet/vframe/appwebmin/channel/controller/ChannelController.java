package com.jet.vframe.appwebmin.channel.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jet.vframe.appwebmin.channel.pojo.Channel;
import com.jet.vframe.appwebmin.channel.service.ChannelService;
import com.jet.vframe.sys.func.datagrid.BaseDataGridAction;
import com.jet.vframe.sys.func.datagrid.DataGrid;
import com.jet.vframe.sys.tool.HQLUtils;
import com.jet.vframe.sys.user.pojo.User;

@Controller
@RequestMapping("/appwebmin/channel/admin")
public class ChannelController extends BaseDataGridAction<Channel> {
	@Autowired
	private ChannelService channelService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {
		return "appwebmin/channel/admin/dataGrid";
	}

	@RequestMapping(value = "dataGrid")
	@ResponseBody
	public DataGrid<Channel> dataGrid(HttpServletRequest request) {
		User user = this.getWebUser(request);
		if (user != null) {
			return channelService.getDataGridByPage(
					new String[] { "id", "name", "level", "is_filter", "mark", "create_date" },
					"user_name = :user_name", "create_date DESC",
					HQLUtils.generalMapParamsSingle("user_name", user.getUser_name()), this.getPageStart(request),
					this.getPageLength(request));
		} else {
			return channelService.getNull();
		}
	}

	@RequestMapping(value = "edit/{id}")
	public ModelAndView edit(@PathVariable String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Channel channel = channelService.queryById(id);
		if (channel != null) {
			mv.addObject("form", channel);
		}
		mv.setViewName("appwebmin/channel/admin/edit");
		return mv;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() {
		return "appwebmin/channel/admin/add";
	}

	@RequestMapping(value = "save")
	public synchronized ModelAndView save(@ModelAttribute("form") Channel form, HttpServletRequest request) {
		String tip = "保存成功";
		ModelAndView mv = new ModelAndView();
		String form_id = form.getId();
		String exist_channle_id = channelService.getIdByName(form.getName());
		if (form_id != null && form_id.length() > 0) {// upadte
			if (exist_channle_id == null || exist_channle_id.equals(form.getId())) {
				form.setUser_name(this.getWebUser(request).getUser_name());
				channelService.update(form);
				mv.setViewName("appwebmin/channel/admin/edit");
			} else {// name disable
				tip = "保存失败，通道名称已经存在";
				mv.setViewName("appwebmin/channel/admin/add");
			}
		} else {
			if (exist_channle_id == null) {
				form.setCreate_date(new Date());
				form.setUser_name(this.getWebUser(request).getUser_name());
				channelService.insert(form);
				mv.setViewName("appwebmin/channel/admin/edit");
			} else {
				tip = "保存失败，通道名称已经存在";
				mv.setViewName("appwebmin/channel/admin/add");
			}

		}
		mv.addObject("tip", tip);
		
		return mv;
	}

	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {
		channelService.deleteById(id);

	}
}
