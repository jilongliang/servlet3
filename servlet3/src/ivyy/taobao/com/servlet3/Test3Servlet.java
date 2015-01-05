package ivyy.taobao.com.servlet3;
import ivyy.taobao.com.listener.MyListener;
import ivyy.taobao.com.thread.MyThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("all")
@WebServlet(name = "test3Servlet", urlPatterns = "/test3",asyncSupported=true)
public class Test3Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("Servlet start"+new Date());
		AsyncContext context=req.startAsync();//启动异步
		context.addListener(new MyListener());
		MyThread thread=new MyThread(context);
		thread.start();//启动线程
		
		out.println("Servlet end"+new Date());
		out.close();
	}

}
