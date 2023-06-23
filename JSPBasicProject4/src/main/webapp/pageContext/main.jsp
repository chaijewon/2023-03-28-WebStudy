<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <center>
    <table width=800 height=600 border=1 bordercolor=black>
      <tr>
        <td colspan="2" height=100>
         <%--
            pageContext.include("header.jsp");
         --%>
         <jsp:include page="header.jsp"></jsp:include>
        </td>
      </tr>
      <tr>
        <td width=200 height=400>
         <%--
            pageContext.include("menu.jsp");
         --%>
         <jsp:include page="menu.jsp"></jsp:include>
        </td>
        <td width=600 height=400>
         <%--
            pageContext.include("content.jsp");
         --%>
         <jsp:include page="content.jsp">
           <jsp:param value="hong" name="id"/>
         </jsp:include>
        </td>
      </tr>
      <tr>
        <td colspan="2" height=100>
         <%--
            pageContext.include("footer.jsp");
         --%>
         <jsp:include page="footer.jsp">
           <jsp:param value="1234" name="pwd"/>
         </jsp:include>
        </td>
      </tr>
    </table>
  </center>
</body>
</html>