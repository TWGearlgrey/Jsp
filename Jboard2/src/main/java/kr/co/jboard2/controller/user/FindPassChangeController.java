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

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.UserService;

@WebServlet("/user/findPassChange.do")
public class FindPassChangeController extends HttpServlet {

	private static final long serialVersionUID = -6003194120330951061L;
	private UserService service = UserService.getInstance();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet()...1");
		
		HttpSession session = req.getSession();
		String uid = (String) session.getAttribute("uid");
		
		logger.debug("uid : " + uid);
		
		if(uid == null) {
			logger.debug("Redirenct reason UID IS NULL. uid : " + uid);
			resp.sendRedirect("/Jboard2/user/findPass.do");
			
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPassChange.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost()...1");
		
		String uid   = req.getParameter("uid");
		String pass1 = req.getParameter("pass1");
		
		service.updateUserPass(uid, pass1);
		
		resp.sendRedirect("/Jboard2/user/login.do?success=300");
	}
}