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
 * servlet3.0µÄÎÄ¼þÉÏ´«
 * @author jilongliang
 * @date:2012-5-21
 *ÔÚ´´½¨ÏîÄ¿µÄÊ±ºòÊ×ÏÈÌí¼ÓTomcat7.xµÄÖ§³Ö,È»ºó°Ñapache-tomcat-7.0.27\conf\web.xml¿½±´µ½ÏîÄ¿WEB-INFÄ¿Â¼ÏÂ
 *°ÑÖ®Ç°µÄweb.xml¸²¸Ç..
 ÅäÖÃÁôÏÂ
  
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
@MultipartConfig(maxRequestSize=222222)//ÉèÖÃÎÄ¼þÉÏ´«´óÐ¡
public class FileUploadServlet extends HttpServlet {

	/**
	 *·ÃÎÊ
	 *http://localhost:8080/servlet3.0/
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Part part=req.getPart("file"); //»ñÈ¡Ò³ÃæµÄname
		//System.out.println(part.getName());
		System.out.println(System.getProperty("user.dir"));//Êä³öµ±Ç°µÄÏîÄ¿´æ·ÅµÄÂ·¾¶
		String uploadPath=req.getSession().getServletContext().getRealPath("/upload");
		System.out.println(uploadPath);//Êä³öÉÏ´«µÄÎÄ¼þÂ·¾¶
		String value=part.getHeader("content-disposition");//ÉèÖÃÍ·ÐÅÏ¢
		System.out.println(value);
		String sub=value.substring(value.lastIndexOf("=")+2,value.length()-1);//½ØÈ¡ÎÄ¼þ
		System.out.println("file size: \t"+part.getSize());//ÎÄ¼þµÄ´óÐ¡
		part.write(uploadPath+sub);//Ð´ÈëÎÄ¼þ
		
	}
}
