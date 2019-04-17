package jspbook.ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ScoreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		int sum = kor + eng + math;
		double avg = (double)sum / 3;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>성적 계산</title></head>");
		out.println("<body><center>");
		out.println("<h2>성적계산 결과</h2><hr>");
		out.println("<table>");
		out.println("<thead><th>국어</th><th>영어</th><th>수학</th><th>합계</th><th>평균</th></thead>");
		out.println("<tr><td>"+kor+"</td><td>"+eng+"</td><td>"+math+"</td><td>"+sum+"</td><td>"+avg+"</td></tr>");
		out.println("</table>");
		out.println("</body></html>");
	}
}
