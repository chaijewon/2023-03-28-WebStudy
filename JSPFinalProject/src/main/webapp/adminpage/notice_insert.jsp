<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h2 class="sectiontitle">공지사항</h2>
  <form method=post action="../adminpage/notice_insert_ok.do">
  <table class="table">
    <tr>
     <th width=20% class="text-center">구분</th>
     <td width=80%>
       <select name="type" class="input-sm">
        <option value="1">일반공지</option>
        <option value="2">이벤트공지</option>
        <option value="3">맛집공지</option>
        <option value="4">여행공지</option>
        <option value="5">레시피공지</option>
       </select>
     </td>
    </tr>
    <tr>
     <th width=20% class="text-center">제목</th>
     <td width=80%>
       <input type=text name=subject size=50 class="input-sm">
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
       <button class="btn btn-sm btn-primary">등록</button>
       <input type=button class="btn btn-sm btn-primary"
         value="취소" onclick="javascript:history.back()">
      </td>
    </tr>
  </table>
  </form>
</body>
</html>