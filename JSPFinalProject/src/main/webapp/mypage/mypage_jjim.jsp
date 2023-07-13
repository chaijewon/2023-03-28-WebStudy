<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 class="sectiontitle">${sessionScope.name}님의 찜 목록</h2>
  <table class="table">
   <tr>
    <th class="text-center">번호</th>
    <th class="text-center"></th>
    <th class="text-center">업체명</th>
    <th class="text-center">전화번호</th>
    <th class="text-center"></th>
   </tr>
   <c:forEach var="vo" items="${list}">
     <tr>
        <td class="text-center">${vo.no }</td>
	    <td class="text-center">
	      <img src="${vo.poster }" style="width: 30px;height: 30px">
	    </td>
	    <td class="text-center">${vo.name }</td>
	    <td class="text-center">${vo.tel }</td>
	    <td class="text-center">
	       <a href="../jjim/jjim_cancel.do?no=${vo.no }" class="btn btn-sm btn-success">취소</a>
	    </td>
     </tr>
   </c:forEach>
  </table>
</body>
</html>