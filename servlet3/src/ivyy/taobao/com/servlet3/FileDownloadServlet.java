package ivyy.taobao.com.servlet3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
/**
 * servlet3.0的文件下载
 * @author jilongliang
 * @date:2012-5-21
 */
@WebServlet("/download")
@SuppressWarnings("all")
public class FileDownloadServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String path = req.getParameter("path");

		long start = System.currentTimeMillis();
		downloadFile(path, null, resp);
		
	}
	
	private static void downloadFile(String filePath, String fileName,
			HttpServletResponse response) {
		if (filePath == null || filePath.trim().equals("") || response == null) {
			output(response, "请求参数有误!");
			return;
		}

		File file = new File(filePath);
		if (!file.exists() || file.isDirectory()) {
			output(response, "您请求的文件不存在或者路径有误!");
			return;
		}

		if (fileName == null || fileName.trim().equals("")) {
			fileName = FilenameUtils.getName(filePath);
		}

		try {
			// 对下载的文件名称进行编码，避免出现中文乱码问题
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));

			FileChannel fileChannel = new FileInputStream(file).getChannel();

			OutputStream out = response.getOutputStream();
			WritableByteChannel outChannel = Channels.newChannel(out);

			fileChannel.transferTo(0, file.length(), outChannel);

			out.flush();
			out.close();
		} catch (FileNotFoundException notFound) {
			output(response, "您所请求的文件不存在!");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			output(response, "您所请求的文件出现异常!");
		} catch (Exception e) {
			e.printStackTrace();
			output(response, "您所请求的文件出现异常!");
		}
	}
	
	private static void output(HttpServletResponse response, String message) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			Writer out = response.getWriter();
			out.write(message);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
