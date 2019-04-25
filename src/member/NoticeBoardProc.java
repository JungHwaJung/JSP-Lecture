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
		BbsDTO dto = null;
		int id = 0;
		String url = null;
		HttpSession session = request.getSession();
		int memberId = (Integer)session.getAttribute("memberId");
		String message = null;
		RequestDispatcher rd = null;
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int curPage = 1;
		// 세션이 만료되었으면 다시 로그인하게 만들어 줌
			try {
				memberId = (int)session.getAttribute("memberId");
			} catch (NullPointerException e) {
				System.out.println("세션이 만료되었습니다.");
			}
			if (memberId == 0) {
				rd = request.getRequestDispatcher("login.jsp");
		        rd.forward(request, response);
			}
		
		
		String writeAction = request.getParameter("action");
		List<String> pageList = new ArrayList<String>();
	
		switch(writeAction) {
		case "list":
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
			}
			bDao = new BbsDAO();
			int count = bDao.getCount();
			if (count == 0)			// 데이터가 없을 때 대비
				count = 1;
			int pageNo = (int)Math.ceil(count/10.0);
			if (curPage > pageNo)	// 경계선에 걸렸을 때 대비
				curPage--;
			session.setAttribute("currentBbsPage", curPage);
			// 리스트 페이지의 하단 페이지 데이터 만들어 주기
			String page = null;
			page = "<a href=#>&laquo;</a>&nbsp;";
			pageList.add(page);
			for (int i=1; i<=pageNo; i++) {
				page = "&nbsp;<a href=noticeServlet?action=list&page=" + i + ">" + i + "</a>&nbsp;";
				pageList.add(page);
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);
			
			List<BbsMember> bmList = bDao.selectJoinAll(curPage);
			request.setAttribute("bbsMemberList", bmList);
			request.setAttribute("pageList", pageList);
			rd = request.getRequestDispatcher("noticeBoard.jsp");
	        rd.forward(request, response);
			break;
			
		case "update":			//수정버튼
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			bDao = new BbsDAO();
			dto = bDao.selectOne(id);
			//System.out.println(dto.toString());
			if(memberId != dto.getMemberId()) {
				message = "글에 대한 수정 권한이 없습니다.";
				url = "noticeServlet?action=list&page=1";
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			bDto = bDao.read(id);
			bDao.close();
			
			dto.setContent(br2Lf(dto.getContent()));
			request.setAttribute("bDto", bDto);
			rd = request.getRequestDispatcher("nbupdate.jsp");
			rd.forward(request, response);
			break;
		
		case "write":			//글쓸때
			title = request.getParameter("title");
			content = request.getParameter("content");
			dto = new BbsDTO(memberId, title, content);
			
			bDao = new BbsDAO();
			bDao.insertBbs(dto);
			bDao.close();
			response.sendRedirect("noticeServlet?action=list&page=1");
			//response.sendRedirect("noticeBoard.jsp");
			break;
		
		case "read":			//글내용
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			bDao = new BbsDAO();
			bDto = bDao.read(id);
			bDao.close();
			
			request.setAttribute("bDto", bDto);
			rd = request.getRequestDispatcher("readContent.jsp");
			rd.forward(request, response);
			break;
		
		case "execute":				//수정 후 실행할 때
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			title = request.getParameter("title");
			content = lf2Br(request.getParameter("content"));

			
			bDto = new BbsMember(id, title, content);
			
			bDao = new BbsDAO();
			bDao.updateBbs(bDto);
			bDao.close();
			curPage = (int)session.getAttribute("currentBbsPage");
			response.sendRedirect("noticeServlet?action=list&page=" + curPage);
			break;
			
			//message = "다음과 같이 수정하였습니다.\\n" + bDto.toString();
			/*request.setAttribute("message", message);
			request.setAttribute("url", "noticeBoard.jsp");
			rd = request.getRequestDispatcher("alertMsg.jsp");
	        rd.forward(request, response);*/
			//response.sendRedirect("noticeBoard.jsp");
			
		case "delete":			//삭제버튼
			if(!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			bDao = new BbsDAO();
			dto = bDao.selectOne(id);
			curPage = (int)session.getAttribute("currentBbsPage");
			if(memberId != dto.getMemberId()) {
				message = "글에 대한 삭제 권한이 없습니다.";
				url = "noticeServlet?action=list&page=";
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			bDao.deleteBbs(id);
			bDao.close();
			//response.sendRedirect("loginMain.jsp");
			message = "글이 삭제되었습니다.";
			url = "noticeServlet?action=list&page=1";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			break;
			
		default: 
		}
	}

	protected String lf2Br(String content) {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<content.length(); i++) {
			if (content.charAt(i) == '\r') {
				sb.append("<br>");
				sb.append(content.charAt(i));
			} else
				sb.append(content.charAt(i));
		}
		return sb.toString();
	}
	protected String br2Lf(String content) {
		StringBuffer sb = new StringBuffer(content);
		int count = 0;
		while (true) {
			int index = sb.indexOf("<br>", count);
			if (index < 0)
				break;
			sb.delete(index, index+4);
			count += 4;
		}
		return sb.toString();
	}

}
