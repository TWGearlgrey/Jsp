package controller.user6;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.User3DTO;
import dto.User6DTO;
import service.User6Service;

@WebServlet("/user6/register.do")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 5467863413435L;
	private User6Service service = new User6Service();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		logger.info("user6::ListController init()...1");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user6::ListController doGet()...1");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user6/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user6::ListController doPost()...1");
		
		String uid  = req.getParameter("uid");
		String name = req.getParameter("name");
		String hp   = req.getParameter("hp");
		String age  = req.getParameter("age");
		
		User6DTO dto = new User6DTO();
		dto.setUid(uid);
		dto.setName(name);
		dto.setHp(hp);
		dto.setAge(age);
		
		service.insertUser6(dto);
		
		logger.info("user6::RegisterController doPost...2 : " + dto);
		
		resp.sendRedirect("/Ch10/user6/list.do");
	}
}
