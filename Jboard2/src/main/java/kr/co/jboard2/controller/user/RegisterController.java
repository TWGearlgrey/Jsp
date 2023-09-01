package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.UserService;

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 8112151718313563518L;
	private UserService service = UserService.getInstance();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet()...1");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost()...1");
		
		String uid   = req.getParameter("uid");
		String pass1 = req.getParameter("pass1");
		String name  = req.getParameter("name");
		String nick  = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp    = req.getParameter("hp");
		String zip   = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String regip = req.getRemoteAddr();
		
		logger.debug("uid : " + uid + ", pass1 : " + pass1 + ", name : " + name);
		logger.debug("nick : " + nick + ", email : " + email + ", hp : " + hp);
		logger.debug("zip : " + zip + ", addr1 : " + addr1 + ", addr2 : " + addr2);
		
		UserDTO dto = new UserDTO();
		dto.setUid(uid);
		dto.setPass(pass1);
		dto.setName(name);
		dto.setNick(nick);
		dto.setEmail(email);
		dto.setHp(hp);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setRegip(regip);
		
		logger.debug("dto : " + dto);
		
		service.insertUser(dto);
		
		logger.info("doGet()...2 : " + dto);
		
		resp.sendRedirect("/Jboard2/user/login.do?success=200");
	}
}
