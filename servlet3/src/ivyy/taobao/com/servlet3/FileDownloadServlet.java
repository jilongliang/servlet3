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
 * servlet3.0���ļ�����
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
			output(response, "�����������!");
			return;
		}

		File file = new File(filePath);
		if (!file.exists() || file.isDirectory()) {
			output(response, "��������ļ������ڻ���·������!");
			return;
		}

		if (fileName == null || fileName.trim().equals("")) {
			fileName = FilenameUtils.getName(filePath);
		}

		try {
			// �����ص��ļ����ƽ��б��룬�������������������
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));

			FileChannel fileChannel = new FileInputStream(file).getChannel();

			OutputStream out = response.getOutputStream();
			WritableByteChannel outChannel = Channels.newChannel(out);

			fileChannel.transferTo(0, file.length(), outChannel);

			out.flush();
			out.close();
		} catch (FileNotFoundException notFound) {
			output(response, "����������ļ�������!");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			output(response, "����������ļ������쳣!");
		} catch (Exception e) {
			e.printStackTrace();
			output(response, "����������ļ������쳣!");
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
