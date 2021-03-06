package ivyy.taobao.com.servlet3;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * @project servlet3.0
 * servlet3.0的文件上传
 * @author jilongliang
 * @date:2012-5-21
 *在创建项目的时候首先添加Tomcat7.x的支持,然后把apache-tomcat-7.0.27\conf\web.xml拷贝到项目WEB-INF目录下
 *把之前的web.xml覆盖..
 配置留下
  
<?xml version="1.0" encoding="ISO-8859-1"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" 
xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
  
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
  
</web-app>
 */
@SuppressWarnings("all")
@WebServlet(name="fileUploadServlet",urlPatterns="/fileUploadServlet")
@MultipartConfig(maxRequestSize=222222)//设置文件上传大小
public class FileUploadServlet extends HttpServlet {

	/**
	 *访问
	 *http://localhost:8080/servlet3.0/
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Part part=req.getPart("file"); //获取页面的name
		//System.out.println(part.getName());
		System.out.println(System.getProperty("user.dir"));//输出当前的项目存放的路径
		String uploadPath=req.getSession().getServletContext().getRealPath("/upload");
		System.out.println(uploadPath);//输出上传的文件路径
		String value=part.getHeader("content-disposition");//设置头信息
		System.out.println(value);
		String sub=value.substring(value.lastIndexOf("=")+2,value.length()-1);//截取文件
		System.out.println("file size: \t"+part.getSize());//文件的大小
		part.write(uploadPath+sub);//写入文件
		
	}
}
