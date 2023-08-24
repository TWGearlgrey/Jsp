package controller.user1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User1Service;

@WebServlet("/user1/delete.do")
public class DeleteController extends HttpServlet {
	
	private static final long serialVersionUID = 658509512422963978L;
	private User1Service service = new User1Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		service.deleteUser1(uid);
		
		resp.sendRedirect("/Ch10/user1/list.do");
	}
}
