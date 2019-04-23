package member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NoticeBoardProc
 */
@WebServlet("/member/noticeServlet")
public class NoticeBoardProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeBoardProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsMember bDto = null;
		BbsDAO bDao = null;
		int id = 0;
		String url = null;
		HttpSession session = request.getSession();
		int memberId = (Integer)session.getAttribute("memberId");
		String message = null;
		RequestDispatcher rd = null;
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		ServletContext application = request.getServletContext();
		String writeAction = request.getParameter("action");
		
		switch(writeAction) {
		case "update":			//수정버튼
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			if(memberId != (Integer)session.getAttribute("memberId")) {
				message = "id=" + id + " 에 대한 수정 권한이 없습니다.";
				url = "noticeBoard.jsp";
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			bDao = new BbsDAO();
			bDto = bDao.selectOne(id);
			request.setAttribute("bDto", bDto);
			rd = request.getRequestDispatcher("nbupdate.jsp");
			rd.forward(request, response);
			break;
		
		case "write":
			title = request.getParameter("title");
			content = request.getParameter("content");
			BbsDTO dto = new BbsDTO(memberId, title, content);
			
			bDao = new BbsDAO();
			bDao.insertBbs(dto);
			bDao.close();
			response.sendRedirect("noticeBoard.jsp");
			break;
		
		case "execute":
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			title = request.getParameter("title");
			content = request.getParameter("content");

			
			bDto = new BbsMember(id, title, content);
			
			bDao = new BbsDAO();
			bDao.updateBbs(bDto);
			bDao.close();
			
			//message = "다음과 같이 수정하였습니다.\\n" + bDto.toString();
			/*request.setAttribute("message", message);
			request.setAttribute("url", "noticeBoard.jsp");
			rd = request.getRequestDispatcher("alertMsg.jsp");
	        rd.forward(request, response);*/
			response.sendRedirect("noticeBoard.jsp");
			break;
			
		case "delete":			//삭제버튼
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			if(memberId != (Integer)session.getAttribute("memberId")) {
				message = "id=" + id + " 에 대한 삭제 권한이 없습니다.";
				url = "noticeBoard.jsp";
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			bDao = new BbsDAO();
			bDao.deleteBbs(id);
			bDao.close();
			//response.sendRedirect("loginMain.jsp");
			message = "글이 삭제되었습니다.";
			url = "noticeBoard.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			break;
			
		default: 
		}
	}

}
