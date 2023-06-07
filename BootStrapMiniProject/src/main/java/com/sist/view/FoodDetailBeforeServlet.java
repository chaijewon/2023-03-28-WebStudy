package com.sist.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FoodDetailBeforeServlet")
public class FoodDetailBeforeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode=request.getParameter("mode");
		String fno=request.getParameter("fno");
		// 저장 
		Cookie cookie=new Cookie("food_"+fno, fno);
		cookie.setMaxAge(60*60*24);// 초단위 저장 
		cookie.setPath("/");
		response.addCookie(cookie);
		// 본인의 브라우저에 저장 
		// 단점 : 보안 취약 , 저장값은 무조건 String 
		/*
		 *   1. Cookie 생성 
		 *      Cookie cookie=new Cookie("food_"+fno, fno);
		 *   2. 저장 기간을 설정
		 *   cookie.setMaxAge(60*60*24)
		 *   3. 저장 ROOT 결정 
		 *   cookie.setPath("/");
		 *   4. 브라우저로 전송 
		 *   response.addCookie(cookie);
		 *   5. response는 HTML/Cookie를 브라우저로 전송하는 역할 
		 *      => 한개의 서블릿이나 JSP에서 한개만 전송이 가능 
		 */
		response.sendRedirect("MainServlet?mode="+mode+"&fno="+fno);
	}

}
