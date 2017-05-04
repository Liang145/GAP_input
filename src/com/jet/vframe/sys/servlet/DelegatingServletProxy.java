package com.jet.vframe.sys.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 在应用中一般普通的JavaPojo都是由Spring来管理的，所以使用autowire注解来进行注入不会产生问题，但是有两个东西是例外的，一个是
 * Filter，一个是Servlet，这两样东西都是由Servlet容器来维护管理的，所以如果想和其他的Bean一样使用Autowire来注入的
 * 话，是需要做一些额外的功夫的,该类就是实现在servlet中使用autowire业注入bean
 * 
 * @author admin
 * 
 */
public class DelegatingServletProxy extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -467451405807248124L;
	private String targetBean;
	private Servlet proxy;

	public DelegatingServletProxy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		proxy.service(req, res);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		this.targetBean = getServletName();
		getServletBean();
		proxy.init(getServletConfig());
	}

	private void getServletBean() {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		this.proxy = (Servlet) wac.getBean(targetBean);
	}

}
