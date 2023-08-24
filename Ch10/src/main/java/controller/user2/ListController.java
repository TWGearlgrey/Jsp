package controller.user2;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User2DTO;
import service.User2Service;

@WebServlet("/user2/list.do")
public class ListController extends HttpServlet {
	
	private static final long serialVersionUID = 5746984346678655446L;
	private User2Service service = new User2Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<User2DTO> users = service.selectUser2s();
		
		// users 참조를 위한 request Scope 저장하기
		req.setAttribute("users", users);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user2/list.jsp");
		dispatcher.forward(req, resp);
	}
}
