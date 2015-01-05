package ivyy.taobao.com.servlet3;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test1Servlet
 * ÇëÇóÂ·¾¶£ºtest1Servlet£¬myservlet
 */
//http://localhost:8080/servlet3.0/Test1Servlet
//@WebServlet("/Test1Servlet")
//http://localhost:8080/servlet3.0/myservlet
@WebServlet(name="test1Servlet",urlPatterns={"/test1Servlet","/myservlet"},
initParams={@WebInitParam(name="paramName",value="paramValue")})

public class Test1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println(config.getInitParameter("paramName"));
		super.init();
	}
	
	
    public Test1Servlet() {
        super();
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		out.print("this is test!");
		
		out.flush();
		out.close();
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
