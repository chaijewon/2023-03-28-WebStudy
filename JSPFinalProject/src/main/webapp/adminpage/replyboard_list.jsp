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
  <h2 class="sectiontitle">묻고 답하기</h2>
  <table class="table">
   <tr>
    <th width=45% class="text-center">제목</th>
    <th width=15% class="text-center">이름</th>
    <th width=20% class="text-center">작성일</th>
    <th width=10% class="text-center">조회수</th>
    <th width=10% class="text-center"></th>
   </tr>
   <c:forEach var="vo" items="${list }">
    <tr>
	    <td width=45%>${vo.subject }</td>
	    <td width=15% class="text-center">${vo.name }</td>
	    <td width=20% class="text-center">${vo.dbday }</td>
	    <td width=10% class="text-center">${vo.hit }</td>
	    <td width=10% class="text-center">
	     <c:if test="${vo.isreply==0 }">
	      <a href="../adminpage/replyboard_insert.do?no=${vo.no }" class="btn btn-sm btn-primary">답변대기</a>
	     </c:if>
	     <c:if test="${vo.isreply==1 }">
	       <span class="btn btn-sm btn-default">답변완료</span>
	     </c:if>
	    </td>
	 </tr>
   </c:forEach>
   <tr>
     <td colspan="5" class="text-center">
      <a href="#" class="btn btn-sm btn-success">이전</a>
       ${curpage } page / ${totalpage } pages
      <a href="#" class="btn btn-sm btn-success">다음</a>
     </td>
   </tr>
  </table>
</body>
</html>