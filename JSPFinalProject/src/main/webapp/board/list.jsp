<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
   <h2 class="sectiontitle">자유게시판</h2>
   <div style="height: 450px">
   <table class="table">
    <tr>
     <td>
      <a href="../board/insert.do" class="btn btn-sm btn-danger">새글</a>
     </td>
    </tr>
   </table>
   <table class="table">
     <tr>
      <th width=10% class="text-center">번호</th>
      <th width=45% class="text-center">제목</th>
      <th width=15% class="text-center">이름</th>
      <th width=20% class="text-center">작성일</th>
      <th width=10% class="text-center">조회수</th>
     </tr>
     <c:forEach var="vo" items="${list }">
       <tr>
         <td width=10% class="text-center">${vo.no }</td>
         <td width=45%><a href="../board/detail.do?no=${vo.no }">${vo.subject }</a></td>
         <td width=15% class="text-center">${vo.name }</td>
         <td width=20% class="text-center">${vo.dbday }</td>
         <td width=10% class="text-center">${vo.hit }</td>
       </tr>
     </c:forEach>
   </table>
   </div>
  </main>
</div>
</body>
</html>