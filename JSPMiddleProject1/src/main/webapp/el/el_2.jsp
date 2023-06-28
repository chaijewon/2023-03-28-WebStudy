<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
     EL 지원하는 내장객체 (581page)
     ***1) requestScope  => request.setAttribute()
     ***2) sessionScope  => session.setAttribute()
     3) param            => request.getParameter()
     4) paramValues      => request.getParameterValues()
 --%>
<%
    String name="홍길동";
    request.setAttribute("name", "홍길동");
    session.setAttribute("name", "심청이");
    // request > session
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름:${name },${requestScope.name } <%-- requestScope. 생략 --%>
<%= request.getAttribute("name") %>
<%-- 변수명 => key를 설정  --%>
</body>
</html>