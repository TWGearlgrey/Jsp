package controller.user2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.User2Service;

@WebServlet("/user2/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 6439808956743906L;
	private User2Service service = new User2Service();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		logger.info("user2::DeleteController init()...1");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user2::DeleteController doGet()...1");
		
		String uid = req.getParameter("uid");
		service.deleteUser2(uid);
		
		resp.sendRedirect("/Ch10/user2/list.do");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user2::DeleteController doPost()...1");
	}
}