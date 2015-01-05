package ivyy.taobao.com.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 *@Author:jilongliang
 *@Date  :2012-5-21
 *@Project:servlet3.0
 */
@WebListener  //¼àÌý
public class TestListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("start  up ");
		
		ServletContext context=event.getServletContext();
		//×¢²áÒ»¸öservlet..
		ServletRegistration registration=context.addServlet("test4Servlet", "com.org.servlet3.Test4Servlet");
		registration.setInitParameter("servletInitParame","servletInitValue");
		registration.addMapping("/test4Servlet");
	}

}
