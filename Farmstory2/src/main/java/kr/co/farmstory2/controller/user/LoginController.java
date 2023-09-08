package kr.co.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.UserService;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {
	
	private String ctxPath;

	private static final long serialVersionUID = 1294748459753276074L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 컨텍스트 경로(/Farmstory2) 구하기(최초 1번, 모든 컨트롤러에 정의)
		ctxPath = config.getServletContext().getContextPath();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doGet()...1");
		
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
		
		logger.info("doPost()...1");
		
		String target = req.getParameter("target");
		String group  = req.getParameter("group");
		String cate   = req.getParameter("cate");
		String no     = req.getParameter("no");
		
		String uid    = req.getParameter("uid");
		String pass   = req.getParameter("pass");
		
		logger.debug("target : " + target);
		logger.debug("group  : " + group);
		logger.debug("cate   : " + cate);
		logger.debug("no     : " + no);
		logger.debug("uid    : " + uid);
		logger.debug("pass   : " + pass);
		
		UserDTO user = service.selectUser(uid, pass);
		
		logger.debug("user : " + user);
		
		if(user != null) {
			logger.info("doPost()...LOGIN SECCESS");
			
			// 현재 세션 구하기
			HttpSession session = req.getSession();
			
			// 사용자 세션 설정
			session.setAttribute("sessUser", user);
			logger.info("user : " + user);
			
			// 리다이렉트
			resp.sendRedirect(ctxPath);
			
		}else {
			logger.info("doPost()...LOGIN FAILES");
			
			resp.sendRedirect(ctxPath+"/user/login.do?success=100");
		}
	}
}