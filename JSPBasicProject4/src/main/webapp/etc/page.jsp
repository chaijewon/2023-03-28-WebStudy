<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
    String msg="Hello JSP!!";// 멤버변수 
    public String display()//메소드 
    {
	   return msg;
    }
    /*
       public class page_jsp extends HttpServlet
       {
            String msg="Hello JSP!!";// 멤버변수 
		    public String display()//메소드 
		    {
			   return msg;
		    }
            public void _jspService()
            {
                String msg=""
               --------------------
                 this.msg
               --------------------
            }
       }
    */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%
      String msg="Hello JSP(지역변수)";
      
  %>
  <%= this.msg%>
</body>
</html>