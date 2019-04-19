package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberProc
 */
@WebServlet("/member/memberProcServlet")
public class MemberProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = null;
		RequestDispatcher rd = null;
		String action = request.getParameter("action");
		String strId = request.getParameter("id");
		System.out.println(action + ", " + strId);
		
		switch(action) {
		case "update":			//수정버튼
			mDao = new MemberDAO();
			MemberDTO member = mDao.selectOne(Integer.parseInt(strId));
			request.setAttribute("member", member);
			rd = request.getRequestDispatcher("update.jsp");
			rd.forward(request, response);
			break;
			
		case "delete":			//삭제버튼
			mDao = new MemberDAO();
			mDao.deleteMember(Integer.parseInt(strId));
			mDao.close();
			//response.sendRedirect("loginMain.jsp");
			String message = "id = " + strId + " 가 삭제되었습니다.";
			String url = "loginMain.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			break;
			
		default:
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
