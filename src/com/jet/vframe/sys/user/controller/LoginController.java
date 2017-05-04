package com.jet.vframe.sys.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jet.vframe.appwebmin.userlog.service.UserLogService;
import com.jet.vframe.spec.UserLogSpec;
import com.jet.vframe.sys.base.controller.BaseController;
import com.jet.vframe.sys.tool.RequestUtils;
import com.jet.vframe.sys.user.pojo.User;
import com.jet.vframe.sys.user.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserLogService userLogService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("form")  @Valid User user,BindingResult result, String verifyCode, HttpServletRequest request) {
		// TODO Auto-generated method stub
		// if (webUser != null && UserType.MUGGLE == webUser.getUserType()) {
		// return "muggle";
		// }
		// System.out.println(request.getContextPath());
		// System.out.println(request.getScheme());
		//
		// System.out.println(request.getServerName());
		// System.out.println(request.getServerPort());
		// System.out.println(request.getRequestURI());
		// System.out.println(request.getRequestURL());
		if(result.hasErrors()){
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError error:list){
				System.out.println(error.getDefaultMessage());	
			}
			
		}
		String rightCode = (String) request.getSession().getAttribute("verifyCode");

		System.out.println("userInfo:" + user.getUser_name() + "user");

		ModelAndView mv = new ModelAndView();
		// check verifyCode
		if (verifyCode != null && verifyCode.equals(rightCode)) {

			user = userService.login(user.getUser_name(), user.getPassword());
			if (user != null) {
				if (user.getEnable()) {
					this.setWebUser(request, user);
					mv.setViewName("redirect:/mainFrame/admin/home");
					String ip = RequestUtils.getClientIp(request);
					userService.updateLastLoginInfo(user.getUser_name(), ip, new Date());
					userLogService.addLog(user.getUser_name(), RequestUtils.getClientIp(request), UserLogSpec.operate_name_login, UserLogSpec.operate_type_query);
					// mv.setView(new
					// RedirectView("http://baidu.com"));RedirectView适合全路径，否则会有相对路径和绝经路径但不带项目名称的问题
				} else {
					mv.addObject("errinfo", "帐户被锁定");
					mv.setViewName("login/login");
				}

			} else {
				mv.addObject("errinfo", "用户名或密码错误");
				mv.setViewName("login/login");
			}

		} else {
			mv.addObject("errinfo", "验证码错误");
			mv.setViewName("login/login");
		}

		return mv;
	}

	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		User user = this.getWebUser(request);
		String userName = null;
		if (user != null) {
			userName = user.getUser_name();
		}
		userLogService.addLog(userName, RequestUtils.getClientIp(request), UserLogSpec.operate_name_logout, UserLogSpec.operate_type_query);
		this.removeWebUser(request);

		return "login/login";
	}
}
