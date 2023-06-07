package com.sist.view;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 화면 분할 => 모든 이동 (MainServlet) => 레이아웃 (include) 
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // 전송타입 (response) => HTML
		  response.setContentType("text/html;charset=UTF-8");
		  // 화면 출력 => 브라우저로 전송 
		  PrintWriter out=response.getWriter();
		  request.setCharacterEncoding("UTF-8");
		  String mode=request.getParameter("mode"); // 사용자가 보내준 값을 받는다
		  String servlet="";
		  if(mode==null)
			  mode="1";
		  switch(mode)
		  {
		  case "1":
			  servlet="FoodCategoryServlet";
			  break;
		  case "2":
			  servlet="FoodListServlet";
			  break;
		  case "3":
			  servlet="FoodDetailServlet";
			  break;
		  case "4":
			  servlet="FoodSearchServlet";
			  break;
		  case "5":
			  servlet="SeoulServlet";
			  break;
		  }
		  out.write("<!DOCTYPE html>");
	      out.write("<html>");
	      out.write("<head>");
	      out.write("<meta charset=\"UTF-8\">");
	      out.write("<title>Insert title here</title>");
	      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
	      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js\"></script>");
	      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
	      out.write("</head>");
	      out.write("<body>");
	      out.write("<nav class=\"navbar navbar-inverse navbar-fixed-top\">");
	      out.write("  <div class=\"container-fluid\">");
	      out.write("    <div class=\"navbar-header\">");
	      out.write("      <a class=\"navbar-brand\" href=\"MainServlet\">MiniProject</a>");
	      out.write("    </div>");
	      out.write("    <ul class=\"nav navbar-nav\">");
	      out.write("      <li class=\"active\"><a href=\"MainServlet\">Home</a></li>");
	      out.write("      <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">서울 맛집<span class=\"caret\"></span></a>");
	      out.write("        <ul class=\"dropdown-menu\">");
	      out.write("          <li><a href=\"MainServlet\">맛집 목록</a></li>");
	      out.write("          <li><a href=\"MainServlet?mode=4\">맛집 검색</a></li>");
	      out.write("        </ul>");
	      out.write("      </li>");
	      out.write("      <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">서울 여행<span class=\"caret\"></span></a>");
	      out.write("        <ul class=\"dropdown-menu\">");
	      out.write("          <li><a href=\"MainServlet?mode=5&type=1\">명소</a></li>");
	      out.write("          <li><a href=\"MainServlet?mode=5&type=2\">자연&관광</a></li>");
	      out.write("          <li><a href=\"MainServlet?mode=5&type=3\">쇼핑</a></li>");
	      out.write("        </ul>");
	      out.write("      </li>");
	      out.write("      ");
	      
	      out.write("    </ul>");
	      out.write("  </div>");
	      out.write("</nav>");
	     
	      // include => <jsp:include page="">
	      RequestDispatcher rd=request.getRequestDispatcher(servlet);
	      rd.include(request, response);
	       
	      out.write("</body>");
	      out.write("</html>");
	}

}






