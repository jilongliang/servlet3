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
 * servlet3.0���ļ��ϴ�
 * @author jilongliang
 * @date:2012-5-21
 *�ڴ�����Ŀ��ʱ���������Tomcat7.x��֧��,Ȼ���apache-tomcat-7.0.27\conf\web.xml��������ĿWEB-INFĿ¼��
 *��֮ǰ��web.xml����..
 ��������
  
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
@MultipartConfig(maxRequestSize=222222)//�����ļ��ϴ���С
public class FileUploadServlet extends HttpServlet {

	/**
	 *����
	 *http://localhost:8080/servlet3.0/
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Part part=req.getPart("file"); //��ȡҳ���name
		//System.out.println(part.getName());
		System.out.println(System.getProperty("user.dir"));//�����ǰ����Ŀ��ŵ�·��
		String uploadPath=req.getSession().getServletContext().getRealPath("/upload");
		System.out.println(uploadPath);//����ϴ����ļ�·��
		String value=part.getHeader("content-disposition");//����ͷ��Ϣ
		System.out.println(value);
		String sub=value.substring(value.lastIndexOf("=")+2,value.length()-1);//��ȡ�ļ�
		System.out.println("file size: \t"+part.getSize());//�ļ��Ĵ�С
		part.write(uploadPath+sub);//д���ļ�
		
	}
}
