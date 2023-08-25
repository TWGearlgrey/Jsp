package controller.user6;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.User6Service;

@WebServlet("/user6/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 54686745362145L;
	private User6Service service = new User6Service();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		logger.info("user6::DeleteController init()...1");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user6::DeleteController doGet()...1");
		
		String uid = req.getParameter("uid");
		service.deleteUser6(uid);
		
		resp.sendRedirect("/Ch10/user6/list.do");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user6::DeleteController doPost()...1");
	}
}
