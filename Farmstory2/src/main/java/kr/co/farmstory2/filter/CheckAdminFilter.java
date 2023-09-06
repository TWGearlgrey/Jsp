package kr.co.farmstory2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.UserDTO;

public class CheckAdminFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		logger.debug("doFilter...1");
		
		HttpServletRequest httprRequest = (HttpServletRequest) request;
		HttpSession session = httprRequest.getSession();
		
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		if(sessUser == null) {
			logger.debug("doFilter...2");
			((HttpServletResponse)response).sendRedirect("/Farmstory2/user/login.do?success=102");
		
		}else if(sessUser.getRole().equals("ADMIN")) {
			logger.debug("doFilter...3 : sessUser IS ADMIN");
			chain.doFilter(request, response);
			
		}else { // 다음 필터 호출. 필터 없을 시 최종 자원 요청
			logger.debug("doFilter...3 : sessUser IS NOT ADMIN");
			((HttpServletResponse)response).sendRedirect("/Farmstory2/user/login.do?success=102");
		}
	}
}