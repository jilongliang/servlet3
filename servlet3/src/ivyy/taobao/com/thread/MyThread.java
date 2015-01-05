package ivyy.taobao.com.servlet3;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;

/**
 *@Author:jilongliang
 *@Date  :2012-5-21
 *@Project:servlet3.0
 */
public class MyThread  extends Thread{
	AsyncContext context;
	
	public MyThread(AsyncContext context){
		this.context=context;
	}
	
	/**
	 * ��д�̵߳�run����
	 */
	@Override
	public void run() { 
		try {
			ServletResponse response=context.getResponse();
			PrintWriter out=response.getWriter();
			out.println("async start"+new Date()); 
			Thread.sleep(7000);//˯��7����
			out.println("async end"+new Date());
			context.complete();//���
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
