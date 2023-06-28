<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>자바 if</h1>
   <%
      for(int i=1;i<=10;i++)
      {
    	  if(i%2==0)
    	  {
   %>
              <%=i %>&nbsp;
   <%
    	  }
      }
   %>
   <h1>JSTL if</h1>
   <%--
      step="1" => 생략이 가능 
    --%>
   <c:forEach var="i" begin="1" end="10">
      <c:if test="${i%2==0 }">
        ${i }&nbsp;
      </c:if>
   </c:forEach>
</body>
</html>