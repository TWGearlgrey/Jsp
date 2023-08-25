package controller.user3;

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
import service.User3Service;

@WebServlet("/user3/register.do")
public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 4539879348675934L;
	private User3Service service = new User3Service();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		logger.info("user3::DeleteController init()...1");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user3::DeleteController doGet()...1");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user3/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("user3::DeleteController doPost()...1");
		
		String uid  = req.getParameter("uid");
		String name = req.getParameter("name");
		String hp   = req.getParameter("hp");
		String age  = req.getParameter("age");
		
		User3DTO dto = new User3DTO();
		dto.setUid(uid);
		dto.setName(name);
		dto.setHp(hp);
		dto.setAge(age);
		
		service.insertUser3(dto);
		
		logger.info("user3::RegisterController doPost()...2 : " + dto);
		
		resp.sendRedirect("/Ch10/user3/list.do");
	}
}
