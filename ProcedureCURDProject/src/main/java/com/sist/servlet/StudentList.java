package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
// JDBC (원시소스) => DBCP (속도의 최적화) = 1차
// ORM (MyBatis(2차) , JPA (3차), Hibernate) 
// 연습용 => 오라클 사용(X) => MySQL , MariaDB
// Spring 
@WebServlet("/StudentList")
public class StudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   response.setContentType("text/html;charset=UTF-8");
	   PrintWriter out=response.getWriter();
	   StudentDAO dao=StudentDAO.newInstance();
	   List<StudentVO> list=dao.studentListData();
	   out.println("<html>");
	   out.println("<head>");
	   out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
	   out.println("<style>");
	   out.println(".container{margin-top:50px}");
	   out.println(".row{margin:0px auto;width:700px;}");
	   out.println("h1{text-align:center}>");
	   out.println("</style>");
	   out.println("</head>");
	   out.println("<body>");
	   out.println("<div class=container>");
	   out.println("<h1>학생 목록</h1>");
	   out.println("<div class=row>");
	   out.println("<table class=table>");
	   out.println("<tr class=danger>");
	   out.println("<th class=text-center>학번</th>");
	   out.println("<th class=text-center>이름</th>");
	   out.println("<th class=text-center>국어</th>");
	   out.println("<th class=text-center>영어</th>");
	   out.println("<th class=text-center>수학</th>");
	   out.println("<th class=text-center>총점</th>");
	   out.println("<th class=text-center>평균</th>");
	   out.println("<th class=text-center></th>");
	   out.println("</tr>");
	   for(StudentVO vo:list)
	   {
		   out.println("<tr>");
		   out.println("<td class=text-center>"+vo.getHakbun()+"</td>");
		   out.println("<td class=text-center>"+vo.getName()+"</td>");
		   out.println("<td class=text-center>"+vo.getKor()+"</td>");
		   out.println("<td class=text-center>"+vo.getEng()+"</td>");
		   out.println("<td class=text-center>"+vo.getMath()+"</td>");
		   out.println("<td class=text-center>"+vo.getTotal()+"</td>");
		   out.println("<td class=text-center>"+vo.getAvg()+"</td>");
		   out.println("<td class=text-center>");
		   out.println("<a href=StudentUpdate?hakbun="+vo.getHakbun()+" class=\"btn btn-sm btn-primary\">수정</a>&nbsp;");
		   out.println("<a href=StudentDelete?hakbun="+vo.getHakbun()+" class=\"btn btn-sm btn-info\">삭제</a>");
		   out.println("</td>");
		   out.println("</tr>");
	   }
	   out.println("</table>");
	   out.println("</div>");
	   out.println("</div>");
	   out.println("</body>");
	   out.println("</html>");
	}

}







