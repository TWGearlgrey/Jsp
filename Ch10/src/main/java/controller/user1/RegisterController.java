package controller.user1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.User1DTO;
import service.User1Service;

@WebServlet("/user1/register.do")
public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 5882551264173206241L;
	private User1Service service = new User1Service();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		logger.info("user1::RegisterController init()...1");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user1::RegisterController doGet()...1");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user1/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user1::RegisterController doPost()...1");
		
		String uid 	= req.getParameter("uid");
		String name = req.getParameter("name");
		String hp 	= req.getParameter("hp");
		String age 	= req.getParameter("age");
		
		User1DTO dto = new User1DTO();
		dto.setUid(uid);
		dto.setName(name);
		dto.setHp(hp);
		dto.setAge(age);
		
		service.insertUser1(dto);
		
		logger.info("user1::RegisterController doPost()...2 : " + dto);
		
		resp.sendRedirect("/Ch10/user1/list.do");
	}
}
