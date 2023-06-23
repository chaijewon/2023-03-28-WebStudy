<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>
<%--
    out.print()
    <%= %> ==> 같다 
    ------ 클래스로 변경될때 out.print()로 변환 
    자바코드를 최대한 줄여서 사용 (권장)
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  버퍼 크기: <%= out.getBufferSize() %><br>
  사용중인 버퍼 크기:<%= out.getRemaining() %><br>
  현재 사용중인 버퍼 크기 :<%= out.getBufferSize()-out.getRemaining() %>
</body>
</html>