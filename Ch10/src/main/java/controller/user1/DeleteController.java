package controller.user1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.User1Service;

@WebServlet("/user1/delete.do")
public class DeleteController extends HttpServlet {
	
	private static final long serialVersionUID = 658509512422963978L;
	private User1Service service = new User1Service();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {
		logger.info("user1::DeleteController init()...1");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user1::DeleteController doGet()...1");
		
		String uid = req.getParameter("uid");
		service.deleteUser1(uid);
		
		resp.sendRedirect("/Ch10/user1/list.do");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user1::DeleteController doPost()...1");
	}
}
