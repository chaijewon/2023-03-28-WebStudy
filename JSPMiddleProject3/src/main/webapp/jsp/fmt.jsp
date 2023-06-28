<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.text.*"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Date today=new Date();
%>
<c:set var="today" value="<%=today %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>Java</h1>
  <%= today %><br>
  <%
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
     String s=sdf.format(today);
  %>
  <%= s %>
  <h1>JSTL</h1>
  ${today}<br>
  <fmt:formatDate value="${today }" pattern="yyyy-MM-dd"/>
  <h1>Java</h1>
  <%
     int a=12345678;
     DecimalFormat d=new DecimalFormat("##,###,###");
     String num=d.format(a);
     
  %>
  <%=num %><br>
  <c:set var="a" value="<%= a %>"/>
  <h1>JSTL</h1>
  ${a }<br>
  <fmt:formatNumber value="${a }" type="currency" />
</body>
</html>