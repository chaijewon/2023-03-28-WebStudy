package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
@WebServlet("/EmpGroupDnameServlet")
public class EmpGroupDnameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		//              --------------------- HTML을 보내줄 브라우저와 연결 
		// 오라클에서 데이터 받기 
		  EmpDAO dao=new EmpDAO();
		  List<GroupVO> list=dao.groupDeptnoData();
		  out.write("<!DOCTYPE html>");
	      out.write("<html>");
	      out.write("<head>");
	      out.write("<meta charset=\"UTF-8\">");
	      out.write("<title>Insert title here</title>");
	      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
	      out.write("<style>");
	      out.write(".container{margin-top:50px}");
	      out.write(".row{margin:0px auto;width:800px}");
	      out.write("h1{text-align:center}");
	      out.write("</style>");
	      out.write("</head>");
	      out.write("<body>");
	      out.write("<div class=container>");
	      out.write("<h1>부서별 통계</h1>");
	      out.write("<div class=row>");
	      out.write("<table class=\"table table-striped\">");
	      out.write("<tr class=success>");
	      out.write("<th class=text-center>부서명</th>");
	      out.write("<th class=text-center>인원</th>");
	      out.write("<th class=text-center>급여평균</th>");
	      out.write("<th class=text-center>급여총합</th>");
	      out.write("<th class=text-center>최대급여</th>");
	      out.write("<th class=text-center>최소급여</th>");
	      out.write("<th class=text-center>급여순위</th>");
	      out.write("</tr>");
	      for(GroupVO vo:list)
	      {
	    	  out.write("<tr>");
		      out.write("<td class=text-center>"+vo.getDname()+"</td>");
		      out.write("<td class=text-center>"+vo.getCount()+"</td>");
		      out.write("<td class=text-center>"+vo.getAvg()+"</td>");
		      out.write("<td class=text-center>"+vo.getSum()+"</td>");
		      out.write("<td class=text-center>"+vo.getMax()+"</td>");
		      out.write("<td class=text-center>"+vo.getMin()+"</td>");
		      out.write("<td class=text-center>"+vo.getRank()+"</td>");
		      out.write("</tr>");
	      }
	      out.write("</table>");
	      out.write("</div>");
	      out.write("</div>");
	      out.write("</body>");
	      out.write("</html>");
	}

}
