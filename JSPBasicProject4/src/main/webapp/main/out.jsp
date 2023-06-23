<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>JspWriter(out):175page</h1>
   <table class="table">
    <tr>
      <th width=20% class="text-center">클래스명</th>
      <td width=80%>JspWriter</td>
    </tr>
    <tr>
      <th width=20% class="text-center">주요기능</th>
      <td width=80%>
        <ul>
          <li>화면출력기능</li>
          <li>버퍼에 있는 데이터 지우는 기능</li>
          <li>출력버퍼에 대한 정보</li>
        </ul>
      </td>
    </tr>
    <tr>
      <th width=20% class="text-center">주요메소드</th>
      <td width=80%>
        <ul>
          <li>
            화면출력기능
            <ul>
             <li>***print() : &lt;%= %&gt;</li> 
             <li>write() : &lt;%= %&gt;</li>
            </ul>
          </li>
          <li>
            버퍼지우는 기능
            <ul>
             <li>clear():flush():page지시자안에 등록 => autoFlush="true"</li>
            </ul>
          </li>
          <li>
            출력버퍼에 대한 정보  
            <ul>
             
             <li>getBufferSize():총버퍼</li>
             <li>getRemaining():남아 있는 버퍼</li>
            </ul>
          </li>
        </ul>
      </td>
    </tr>
  </table>
</body>
</html>