package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class JumpServlet
 */
public class JumpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

//    public JumpServlet() {
//        super();
//    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet");
		
		//forward 전송시 전송할 인자 생성
		request.setAttribute("java", 21);
		
		//forward(page 이동) : 기존의 URL은 고정된 상태로 내용만 변경
		RequestDispatcher rd
			= request.getRequestDispatcher("jump.jsp");
		
		rd.forward(request,  response);
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
