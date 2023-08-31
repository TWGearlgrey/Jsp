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

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 4736583377544488787L;
	private UserService service = UserService.getInstance();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("LoginController doGet()...1");
		
		
		String success = req.getParameter("success");
		req.setAttribute("success", success);
		
		if(success == null) {
			logger.info("[SUCC] is null : LOGIN PAGE ENTER");
		}else if(success.equals("100")) {
			logger.info("[SUCC] 100 : FAILED LOGIN");
		}else if(success.equals("101")) {
			logger.info("[SUCC] 101 : 비정상적 접근");
		}else if(success.equals("200")) {
			logger.info("[SUCC] 200 : SUCCESS LOGOUT");
		}else if(success.equals("300")) {
			logger.info("[SUCC] 300 : SUCCESS PASS MODIFY");
		}
			
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("LoginController doPost()...1");
		
		String uid  = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		logger.debug("uid : " + uid);
		logger.debug("pass : " + pass);
	
		UserDTO user = service.selectUser(uid, pass);
		
		logger.debug("user : " + user);
		
		if(user != null) {
			logger.info("LoginController doPost()...2-1 SUCCESS");
			
			// 현재 세션 구하기
			HttpSession session = req.getSession();
			
			// 사용자 세션 설정
			session.setAttribute("sessUser", user);
			
			// 리다이렉트
			resp.sendRedirect("/Jboard2/list.do");
			
		}else {
			logger.info("LoginController doPost()...2-2 FAILED : USER IS NULL");
			
			// 리다이렉트
			resp.sendRedirect("/Jboard2/user/login.do?success=100");
		}
	}
}
