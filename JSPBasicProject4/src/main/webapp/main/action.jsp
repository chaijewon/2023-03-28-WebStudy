<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>JSP액션 태그</h1>
  <table class="table">
   <tr>
     <td>
       <ul>
        <li>***&lt;jsp:include page=""&gt;: JSP특정영역 다른 JSP를 첨부(조립식)
            <br>
            &lt;jsp:include page=""&gt; => JSP를 따로  실행 HTML묶어서 사용(동적)
            <br>
            &lt;%@ include file="" %&lt; => JSP를 먼저 묶어서 한번 컴파일한다:메뉴,footer
        </li>
        <li>&lt;jsp:forward page=""&gt;:파일을 덮어쓴다(URL을 유지=request를 사용)</li>
        <li>&lt;jsp:param value="값" name="key"&gt;: 해당 JSP로 값을 전송할때 사용</li>
        <li>***&lt;jsp:useBean id="" class=""&gt;:클래스 메모리 할당</li>
        <li>***&lt;jsp:setProperty  name="객체명" property=""&gt;:빈즈(VO)에 값을 채운다</li>
       </ul>
     </td>
   </tr>
  </table>
</body>
</html>