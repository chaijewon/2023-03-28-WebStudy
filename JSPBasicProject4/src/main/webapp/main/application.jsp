<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>ServletContext(application):180page</h1>
  <table class="table">
    <tr>
      <th width=20% class="text-center">클래스명</th>
      <td width=80%>ServletContext</td>
    </tr>
    <tr>
      <th width=20% class="text-center">주요기능</th>
      <td width=80%>
        <ul>
          <li>서버 정보</li>
          <li>로그정보</li>
          <li>자원정보(자원:이미지,파일..)</li>
        </ul>
      </td>
    </tr>
    <tr>
      <th width=20% class="text-center">주요메소드</th>
      <td width=80%>
        <ul>
          <li>
            서버정보
            <ul>
             <li>getServerInfo()</li> 
            </ul>
          </li>
          <li>
            로그정보
            <ul>
             <li>log()</li>
            </ul>
          </li>
          <li>
            자원 정보
            <ul>
             <li>***getInitParameter():web.xml에 등록된 데이터</li>
             <li>***getRealPath():톰캣이 읽어가는 실제 경로</li>
            </ul>
          </li>
        </ul>
      </td>
    </tr>
  </table>
</body>
</html>