package com.jet.vframe.sys.init.component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jet.vframe.appwebmin.datalog.service.DataLogService;
import com.jet.vframe.spec.UserSpec;
import com.jet.vframe.sys.mainframe.pojo.MainMenu;
import com.jet.vframe.sys.mainframe.service.MainMenuService;
import com.jet.vframe.sys.user.pojo.User;
import com.jet.vframe.sys.user.service.UserService;

@Component("initServlet")
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7372595749393225355L;
	@Autowired
	private UserService userService;
	@Autowired
	private MainMenuService mainMenuService;

	@Autowired
	private DataLogService dataLogService;

	public InitServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * HttpServlet中有两个init方法 ，init(ServletConfig
	 * config)是给tomcat调用的，在init(ServletConfig
	 * config)中会再调用init()，如果重写init(ServletConfig
	 * config)的程序员忘记写super.init(config)会导致错误，所以程序员最好只重写init()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		// System.out.println("初始化管理员用户");
		super.init();
		User user = userService.queryById(UserSpec.user_name_administrator);
		if (user == null) {
			//dataLogService.createIndex("ALTER TABLE `DEF_DataLog` ADD INDEX idx_datalog (`channel_name`,`status`,`create_date`)");
			dataLogService.createIndex("ALTER TABLE `DEF_DataLog` ADD INDEX idx_datalog (`channel_name`,`status`,`create_date`)");
			user = new User();
			user.setCreate_date(new Date());
			user.setEnable(true);
			user.setIs_filter(false);
			user.setLevel(UserSpec.levle_sys_user);
			user.setPassword(UserSpec.password_user);
			user.setReal_name(UserSpec.real_name_administrator);
			user.setRole(UserSpec.role_administrator);
			user.setUser_name(UserSpec.user_name_administrator);
			userService.insert(user);
			System.out.println("-----insert admin user");
		}
		user = null;
		user = userService.queryById(UserSpec.user_name_auditor);
		if (user == null) {
			user = new User();
			user.setCreate_date(new Date());
			user.setEnable(true);
			user.setIs_filter(false);
			user.setLevel(UserSpec.levle_sys_user);
			user.setPassword(UserSpec.password_user);
			user.setReal_name(UserSpec.real_name_auditor);
			user.setRole(UserSpec.role_auditor);
			user.setUser_name(UserSpec.user_name_auditor);
			userService.insert(user);
			// System.out.println("-----inset user");
		}
		user = null;
		user = userService.queryById(UserSpec.user_name_securitor);
		if (user == null) {
			user = new User();
			user.setCreate_date(new Date());
			user.setEnable(true);
			user.setIs_filter(false);
			user.setLevel(UserSpec.levle_sys_user);
			user.setPassword(UserSpec.password_user);
			user.setReal_name(UserSpec.real_name_securitor);
			user.setRole(UserSpec.role_securitor);
			user.setUser_name(UserSpec.user_name_securitor);
			userService.insert(user);
			// System.out.println("-----inset user");
		}
		user = null;
		user = userService.queryById(UserSpec.user_name_default);
		if (user == null) {
			user = new User();
			user.setCreate_date(new Date());
			user.setEnable(true);
			user.setIs_filter(false);
			user.setLevel(UserSpec.levle_worker_high);
			user.setPassword(UserSpec.password_user);
			user.setReal_name(UserSpec.real_name_default);
			user.setRole(UserSpec.role_worker);
			user.setUser_name(UserSpec.user_name_default);
			userService.insert(user);
			// System.out.println("-----inset user");
		}

//		long menuCount = mainMenuService.getCount();
//		if (menuCount > 0) {
//			System.out.println("no mainmenu update");
//			return;
//		}
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			String filePath = Thread.currentThread().getContextClassLoader().getResource("main-menu.csv").toURI()
					.getPath();
			//isr = new InputStreamReader(new FileInputStream(new File(filePath)), "GBK");
			isr = new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8");
			br = new BufferedReader(isr);
			String lineStr = null;
			int lineIndex = 0;
			while ((lineStr = br.readLine()) != null) {
				lineIndex++;
				if (lineIndex == 1)
					continue;
				String[] arr = lineStr.split(",");
				MainMenu mainMenu = new MainMenu();
				mainMenu.setId(arr[0].substring(1, arr[0].length()-1));
				mainMenu.setIsDefault("1".equals(arr[4].substring(1, arr[4].length()-1)));
				mainMenu.setIsHidden("1".equals(arr[7].substring(1, arr[7].length()-1)));
				mainMenu.setIsUrl("1".equals(arr[6].substring(1, arr[6].length()-1)));
				mainMenu.setName(arr[2].substring(1, arr[2].length()-1));
				mainMenu.setParentId(arr[1].substring(1, arr[1].length()-1));
				mainMenu.setReserved1(arr[8].substring(1, arr[8].length()-1));
				mainMenu.setReserved2(arr[9].substring(1, arr[9].length()-1));
				mainMenu.setSort(Integer.valueOf(arr[5].substring(1, arr[5].length()-1)));
				mainMenu.setSrc(arr[3].substring(1, arr[3].length()-1));
				mainMenu.setType(Integer.valueOf(arr[10].substring(1, arr[10].length()-1)));
				mainMenuService.saveOrUpdate(mainMenu);
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} finally {
			try {
				if (isr != null)
					isr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		System.out.println("mainmenu update complete");
	}
}
