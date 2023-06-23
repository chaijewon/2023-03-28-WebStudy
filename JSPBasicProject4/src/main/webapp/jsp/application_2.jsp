<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
    application : 자원관리 (log,path)
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%
      // web.xml에 등록된 내용을 읽어 올 수 있다
      String driver=application.getInitParameter("driver");
      String url=application.getInitParameter("url");
      String username=application.getInitParameter("username");
      String password=application.getInitParameter("password"); 
      
      /*System.out.println("driver:"+driver);
      System.out.println("url:"+url);
      System.out.println("username:"+username);
      System.out.println("password:"+password);*/
      application.log("driver:"+driver);
      application.log("url:"+url);
      application.log("username:"+username);
      application.log("password:"+password);
      String path=application.getRealPath("/");
      System.out.println(path);
  %>
</body>
</html>