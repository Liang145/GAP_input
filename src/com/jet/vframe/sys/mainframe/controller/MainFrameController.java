package com.jet.vframe.sys.mainframe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.jet.vframe.sys.base.controller.BaseController;
import com.jet.vframe.sys.mainframe.pojo.MainMenu;
import com.jet.vframe.sys.mainframe.service.MainMenuService;
import com.jet.vframe.sys.user.pojo.User;

@Controller
@RequestMapping("/mainFrame/admin")
public class MainFrameController extends BaseController {

	@Autowired
	private MainMenuService mainMenuService;

	@RequestMapping("/home")
	public ModelAndView mainFrame(HttpServletRequest request) {

		// mainMenuList = new ArrayList<MainMenu>();
		//
		// MainMenu m111 = new
		// MainMenu("111","11","土地资源采集1","webapp/login/main.jsp","",1,true,null,null);
		// MainMenu m112 = new
		// MainMenu("112","11","土地资源采集2","webapp/login/main.jsp","",2,true,null,null);
		// List<MainMenu> list11 = new ArrayList<MainMenu>();
		// list11.add(m111);
		// list11.add(m112);
		// MainMenu m11 = new
		// MainMenu("11","1","土地资源采集","","glyphicon glyphicon-tree-conifer",1,false,null,null);
		// m11.setChildren(list11);
		//
		//
		// MainMenu m121 = new
		// MainMenu("121","12","矿产资源采集1","webapp/login/main.jsp","",1,true,null,null);
		// MainMenu m122 = new
		// MainMenu("122","12","矿产资源采集2","webapp/login/main.jsp","",2,true,null,null);
		// List<MainMenu> list12 = new ArrayList<MainMenu>();
		// list12.add(m121);
		// list12.add(m122);
		// MainMenu m12 = new
		// MainMenu("12","1","矿产资源采集","","glyphicon glyphicon-th",2,false,null,null);
		// m12.setChildren(list12);
		//
		// MainMenu m131 = new MainMenu("131","13",
		// "房产资源采集1","webapp/login/main.jsp","",1,true,null,null);
		// MainMenu m132 = new MainMenu("132","13",
		// "房产资源采集2","webapp/login/main.jsp","",2,true,null,null);
		// List<MainMenu> list13 = new ArrayList<MainMenu>();
		// list13.add(m131);
		// list13.add(m132);
		// MainMenu m13 = new
		// MainMenu("13","1","矿产资源采集","","glyphicon glyphicon-home",3,false,null,null);
		// m13.setChildren(list13);
		//
		// List<MainMenu> list1 = new ArrayList<MainMenu>();
		// list1.add(m11);
		// list1.add(m12);
		// list1.add(m13);
		//
		// MainMenu m1 = new
		// MainMenu("1","0","数据采集","","glyphicon glyphicon-cloud-upload",1,false,null,null);
		// m1.setChildren(list1);
		//
		// //two
		// MainMenu m211 = new
		// MainMenu("211","21","土地资源监控1","webapp/login/main.jsp","",1,true,null,null);
		// MainMenu m212 = new
		// MainMenu("212","21","土地资源监控2","webapp/login/main.jsp","",2,true,null,null);
		// List<MainMenu> list21 = new ArrayList<MainMenu>();
		// list21.add(m211);
		// list21.add(m212);
		// MainMenu m21 = new
		// MainMenu("21","2","土地资源监控","","glyphicon glyphicon-tree-conifer",1,false,null,null);
		// m21.setChildren(list21);
		//
		// MainMenu m221 = new MainMenu("221","22",
		// "矿产资源监控1","webapp/login/main.jsp","",1,true,null,null);
		// MainMenu m222 = new MainMenu("222","22",
		// "矿产资源监控2","webapp/login/main.jsp","",2,true,null,null);
		// List<MainMenu> list22 = new ArrayList<MainMenu>();
		// list22.add(m221);
		// list22.add(m222);
		// MainMenu m22 = new
		// MainMenu("22","2","矿产资源监控","","glyphicon glyphicon-th",2,false,null,null);
		// m22.setChildren(list22);
		//
		// MainMenu m231 = new MainMenu("231","23",
		// "房产资源监控1","webapp/login/main.jsp","",1,true,null,null);
		// MainMenu m232 = new MainMenu("232","23"
		// ,"房产资源监控2","webapp/login/main.jsp","",2,true,null,null);
		// List<MainMenu> list23 = new ArrayList<MainMenu>();
		// list23.add(m231);
		// list23.add(m232);
		// MainMenu m23 = new
		// MainMenu("23","2","矿产资源监控","","glyphicon glyphicon-home",3,false,null,null);
		// m23.setChildren(list23);
		//
		// List<MainMenu> list2 = new ArrayList<MainMenu>();
		// list2.add(m21);
		// list2.add(m22);
		// list2.add(m23);
		//
		// MainMenu m2 = new
		// MainMenu("2","0","数据监控","","glyphicon glyphicon-time",1,false,null,null);
		// m2.setChildren(list2);
		//
		// mainMenuList = new ArrayList<MainMenu>();
		// mainMenuList.add(m1);
		// mainMenuList.add(m2);
		
		 int role = this.getWebUser(request).getRole();
//		List<MainMenu> mainMenuList = mainMenuService.getAllMainMenu();
		 List<MainMenu> mainMenuList =
		 mainMenuService.getMainMenuByType(role);
		ModelAndView mv = new ModelAndView();
		mv.addObject("mainMenuList", mainMenuList);
		mv.setViewName("/mainFrame/admin/home");
		return mv;
	}
}
