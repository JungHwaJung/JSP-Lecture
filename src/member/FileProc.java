package member;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class FileProc
 */
@WebServlet("/member/fileServlet")
public class FileProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FileProc.class);
       
    public FileProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("");
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		String sb = null;
		String client = null;
		int length;
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		switch(action) {
		case "member":
			MemberDAO mDao = new MemberDAO();
			sb = mDao.prepareDownload();
			client = request.getHeader("User-Agent");
			// 파일 다운로드 헤더 지정
			response.reset() ;
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");
			
			if(client.indexOf("MSIE") != -1) {		// Internet Explorer
				response.setHeader ("Content-Disposition", "attachment; filename=ClientMemberList.csv");
			} else {			// IE 이외
				response.setHeader("Content-Disposition", "attachment; filename=\"ClientMemberList.csv\"");
				response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
			} 
			File file = new File("C:/Temp/MemberList.csv");
			response.setHeader ("Content-Length", "" + file.length());
			
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] bytes = new byte[1024];
				while ((length = bis.read(bytes)) != -1) {
					LOG.debug("Length = " + length);
					bos.write(bytes, 0, length);
				}
				bos.flush();
				bos.close();
				bis.close();
				fis.close();
			} catch (IllegalStateException e1) {
				LOG.info("doGet(): IllegalStateException Error");
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}
			LOG.trace("After try");	
			break;
			
		case "bbs":
			BbsDAO bDao = new BbsDAO();
			sb = bDao.prepareDownload();
			client = request.getHeader("User-Agent");
			// 파일 다운로드 헤더 지정
			response.reset() ;
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");
			
			if(client.indexOf("MSIE") != -1) {		// Internet Explorer
				response.setHeader ("Content-Disposition", "attachment; filename=ClientNoticeList.csv");
			} else {			// IE 이외
				response.setHeader("Content-Disposition", "attachment; filename=\"ClientNoticeList.csv\"");
				response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
			} 
			file = new File("C:/Temp/NoticeList.csv");
			response.setHeader ("Content-Length", "" + file.length());
			
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] bytes = new byte[1024];
				while ((length = bis.read(bytes)) != -1) {
					LOG.debug("Length = " + length);
					bos.write(bytes, 0, length);
				}
				bos.flush();
				bos.close();
				bis.close();
				fis.close();
			} catch (IllegalStateException e1) {
				LOG.info("doGet(): IllegalStateException Error");
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}
			LOG.trace("After try");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
