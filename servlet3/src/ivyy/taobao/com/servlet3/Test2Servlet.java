package ivyy.taobao.com.servlet3;
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

/***
 * urlPatterns����·��
 * @author liangjilong
 *
 */
@SuppressWarnings("all")
@WebServlet(name = "test2Servlet", urlPatterns = "/test2",asyncSupported=true)
public class Test2Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("Servlet start"+new Date());
		AsyncContext context=req.startAsync();//�����첽
		
		MyThread thread=new MyThread(context);
		thread.start();//�����߳�
		
		out.println("Servlet end"+new Date());
		
		out.close();
		
	}

}
