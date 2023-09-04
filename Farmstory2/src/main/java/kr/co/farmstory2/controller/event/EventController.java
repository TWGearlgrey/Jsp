package kr.co.farmstory2.controller.event;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/event/event.do")
public class EventController extends HttpServlet {

	private static final long serialVersionUID = -3875578706176645676L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet()...1");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/event/event.jsp");
		dispatcher.forward(req, resp);
	}
}
