package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 *   GET => doGet()
 *   POST => doPost()  ==> 405
 *   ---- +
 *        service()
 *        
 *   @GetMapping
 *   @PostMapping 
 *   ------------ + @RequestMapping 
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // 전송타입 (response) => HTML
		  response.setContentType("text/html;charset=UTF-8");
		  request.setCharacterEncoding("UTF-8");
		  // 화면 출력 => 브라우저로 전송 
		  PrintWriter out=response.getWriter();
		  String mode=request.getParameter("mode");// 화면 변경 
		  String servlet="";
		  if(mode==null)
			  mode="1";
		  switch(mode)
		  {
		  case "1":
			  servlet="EmpListServlet";
			  break;
		  case "2":
			  servlet="EmpDetailServlet";
			  break;
		  case "3":
			  servlet="EmpInsertServlet";
			  break;
		  case "4":
			  servlet="EmpUpdateServlet";
			  break;
		  case "5":
			  servlet="EmpGroupDnameServlet";
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
	      out.write("      <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">사용자<span class=\"caret\"></span></a>");
	      out.write("        <ul class=\"dropdown-menu\">");
	      out.write("          <li><a href=\"MainServlet\">사원 목록</a></li>");
	      out.write("          <li><a href=\"MainServlet?mode=3\">사원 등록</a></li>");
	      out.write("        </ul>");
	      out.write("      </li>");
	      out.write("      <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">관리자<span class=\"caret\"></span></a>");
	      out.write("        <ul class=\"dropdown-menu\">");
	      out.write("          <li><a href=\"MainServlet?mode=5\">부서별 통계</a></li>");
	      out.write("          <li><a href=\"MainServlet?mode=6\">년도별 통계</a></li>"); 
	      out.write("          <li><a href=\"MainServlet?mode=7\">직위별 통계</a></li>"); 
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




