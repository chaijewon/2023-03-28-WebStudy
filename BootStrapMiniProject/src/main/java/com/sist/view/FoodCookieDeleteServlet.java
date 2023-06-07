package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FoodCookieDeleteServlet")
public class FoodCookieDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//?mode=2&cno=
		String mode=request.getParameter("mode");
		String cno=request.getParameter("cno");
		
		// Cookie가지고 오기 
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)
		{
			for(int i=0;i<cookies.length;i++)
			{
				if(cookies[i].getName().startsWith("food_"))
				{
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);// 삭제
					response.addCookie(cookies[i]);
				}
			}
		}
		// 이동 
		response.sendRedirect("MainServlet?mode="+mode+"&cno="+cno);
	}

}
