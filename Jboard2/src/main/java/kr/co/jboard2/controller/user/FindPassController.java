package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.service.UserService;

@WebServlet("/user/findPass.do")
public class FindPassController extends HttpServlet {
	
	private static final long serialVersionUID = -6117860715016659397L;
	private UserService service = UserService.getInstance();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("FindPassController doGet()...1");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPass.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("FindPassController doPost()...1");
		
		String uid = req.getParameter("uid");
		
		HttpSession session = req.getSession();
		session.setAttribute("uid", uid);
		
		resp.sendRedirect("/Jboard2/user/findPassChange.do");
	}
}
