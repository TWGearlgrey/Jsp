<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 세션해제
	session.invalidate(); 
	// 여기서 session은 한명의 객체, 해당 코드를 통해 쿠키값(sessid, sesspw)이 날아간다.
			
	// 쿠키해제
	Cookie cookie = new Cookie("cid", null);
	cookie.setMaxAge(0);
	response.addCookie(cookie);
	
	// 리다이렉트		
	response.sendRedirect("./loginForm.jsp");
%>

<!-- 

클라이언트A [ {id : aaa, pw : 1234, input} ]─┐
										cookie storage [{JSESS, domain}, 
														{A123, localhost}]
클라이언트B [ {id : bbb, pw : 1234, input} ]─┐
										cookie storage [{JSESS, domain}, 
														{B123, localhost}]


WAS [  {loginProc.jsp, }]
│		
│
└ session table [ {JSES, sessid, },
				  {A123…, aaa, },
				  {B123…, bbb, } ] <-- logout에서 세션해제시 증발


-->