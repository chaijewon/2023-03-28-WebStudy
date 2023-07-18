<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2 class="sectiontitle">답변하기</h2>
    <form method="post" action="../adminpage/replyboard_insert_ok.do">
      <table class="table">
        <tr>
          <th width=20% class="text-center">제목</th>
          <td width=80%>
           <input type=text name=subject size=50 class="input-sm">
           <input type=hidden name=pno value="${no }">
          </td>
        </tr>
        <tr>
          <th width=20% class="text-center">내용</th>
          <td width=80%>
           <textarea rows="10" cols="50" name=content></textarea>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center">
           <button class="btn btn-sm btn-success">답변</button>
           <input type=button value="취소" class="btn btn-sm btn-info"
            onclick="javascript:history.back()">
          </td>
        </tr>
      </table>
      </form>
</body>
</html>