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
  <h2 class="sectiontitle">${sessionScope.name}님의 예약 목록</h2>
  <table class="table">
   <tr>
    <th class="text-center">예약번호</th>
    <th class="text-center"></th>
    <th class="text-center">아이디</th>
    <th class="text-center">업체명</th>
    <th class="text-center">예약일</th>
    <th class="text-center">예약시간</th>
    <th class="text-center">인원</th>
    <th class="text-center"></th>
   </tr>
   <c:forEach var="vo" items="${list}">
     <tr>
        <td class="text-center">${vo.no }</td>
	    <td class="text-center">
	      <img src="${vo.poster }" style="width: 30px;height: 30px">
	    </td>
	    <td class="text-center">${vo.id }</td>
	    <td class="text-center">${vo.name }</td>
	    <td class="text-center">${vo.rday }</td>
	    <td class="text-center">${vo.rtime }</td>
	    <td class="text-center">${vo.inwon }</td>
	    <td class="text-center">
	      <c:if test="${vo.rok=='n' }">
	        <a href="../adminpage/admin_reserve_ok.do?no=${vo.no }" class="btn btn-sm btn-success">승인대기</a>
	      </c:if>
	      <c:if test="${vo.rok=='y' }">
	        <span class="btn btn-sm btn-default">승인완료</span>
	      </c:if>
	    </td>
     </tr>
   </c:forEach>
  </table>
</body>
</html>