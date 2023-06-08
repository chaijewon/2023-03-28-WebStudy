package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
@WebServlet("/EmpUpdateServlet")
public class EmpUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		EmpDAO dao=new EmpDAO();
		List<Integer> mList=dao.empGetMgrData();
		List<Integer> dList=dao.empGetDeptnoData();
		List<Integer> sList=dao.empGetSalData();
		List<String> jList=dao.empGetJobData();
		String mode=request.getParameter("mode");
		String empno=request.getParameter("empno");
		EmpVO vo=dao.empUpdateData(Integer.parseInt(empno));
		  out.write("<!DOCTYPE html>");
	      out.write("<html>");
	      out.write("<head>");
	      out.write("<meta charset=\"UTF-8\">");
	      out.write("<title>Insert title here</title>");
	      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
	      out.write("<style>");
	      out.write(".container{margin-top:50px}");
	      out.write(".row{margin:0px auto;width:600px}");
	      out.write("h1{text-align:center}");
	      out.write("</style>");
	      out.write("</head>");
	      out.write("<body>");
	      out.write("<div class=container>");
	      out.write("<h1>사원 수정</h1>");
	      out.write("<div class=row>");
	      out.write("<form method=post action=EmpUpdateServlet>");
	      out.write("<table class=table>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">이름</td>");
	      out.write("<td width=80%><input type=text name=ename class=input-sm size=15 value="+vo.getEname()+"></td>");
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">직위</th>");
	      out.write("<td width=80%>");
	      out.write("<select name=job class=input-sm>");
	      for(String s:jList)
	      {
	    	  out.write("<option "+(s.equals(vo.getJob())?"selected":"")+">"+s+"</option>");
	      }
	      out.write("</select>");
	      out.write("</td>");
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">사수번호</td>");
	      out.write("<td width=80%>");
	      out.write("<select name=mgr class=input-sm>");
	      for(int i:mList)
	      {
	    	  out.write("<option "+(i==vo.getMgr()?"selected":"")+">"+i+"</option>");
	      }
	      out.write("</select>");
	      out.write("</td>");
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">급여</td>");
	      out.write("<td width=80%>");
	      out.write("<select name=sal class=input-sm>");
	      for(int i:sList)
	      {
	    	  out.write("<option "+(i==vo.getSal()?"selected":"")+">"+i+"</option>");
	      }
	      out.write("</select>");
	      out.write("</td>");
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">성과급</td>");
	      out.write("<td width=80%><input type=number name=comm class=input-sm size=15 max=500 min=100 step=50 value="+vo.getComm()+"></td>");
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<th width=20% class=\"text-right success\">부서번호</td>");
	      out.write("<td width=80%>");
	      out.write("<select name=deptno class=input-sm>");
	      for(int i:dList)
	      {
	    	  out.write("<option "+(i==vo.getSal()?"selected":"")+">"+i+"</option>");
	      }
	      out.write("</select>");
	      out.write("</td>");
	      out.write("</tr>");
	      out.write("<tr>");
	      out.write("<td colspan=2 class=text-center>");
	      out.write("<button class=\"btn btn-sm btn-primary\">수정</button>&nbsp;");
	      out.write("<input type=button value=취소 class=\"btn btn-sm btn-primary\" onclick=\"javascript:history.back()\">");
	      out.write("</td>");
	      out.write("</tr>");
	      out.write("</table>");
	      out.write("</form>");
	      out.write("</div>");
	      out.write("</div>");
	      out.write("</body>");
	      out.write("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
