<%@page import="com.sist.temp.SawonBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    String name=request.getParameter("name");
    String sex=request.getParameter("sex");
    String dept=request.getParameter("dept");
    String job=request.getParameter("job");
    String pay=request.getParameter("pay");
    
    // VO에 값을 채운다 
    SawonBean bean=new SawonBean();
    bean.setName(name);
    bean.setDept(dept);
    bean.setJob(job);
    bean.setSex(sex);
    bean.setPay(Integer.parseInt(pay));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  이름:<%=bean.getName() %><br>
  성별:<%=bean.getSex() %><br>
  부서:<%=bean.getDept() %><br>
  직위:<%=bean.getJob() %><br>
  연봉:<%=bean.getPay() %><br>
</body>
</html>