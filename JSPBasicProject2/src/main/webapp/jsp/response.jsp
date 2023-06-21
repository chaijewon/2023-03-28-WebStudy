<%--
   173page response (응답)
   => request(요청) , response(응답) 
   => response : HttpServletResponse 
      응답메소드 
      setHeader() : 사전에 전송할 데이터가 있는 경우 
      ------------- 권한을 부여 , 다운로드 
      sendRedirect() : 서버에서 화면 이동 
      --------------
        로그인 => main
        글쓰기 => 목록
        수정 => 목록/상세
      addCookie() : 쿠키 전송 (클라이언트 브라우저로 전송)
      *** 쿠키와 동시에 HTML을 전송할 수 없다 
      
      *** response / request는 각 JSP마다 따로 가지고 있다 
      *** request는 화면 변경되면 => 초기화 
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <a href="response_1.jsp?id=admin&pwd=1234">전송</a>
</body>
</html>