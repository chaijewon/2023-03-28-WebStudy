<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="dao" class="com.sist.dao.ReplyBoardDAO"/>
<%
     request.setCharacterEncoding("UTF-8");
     String fs=request.getParameter("fs");
     String ss=request.getParameter("ss");
     
     int count=dao.boardFindCount(fs, ss);
     List<ReplyBoardVO> list=dao.boardFindData(fs, ss);
     
     request.setAttribute("count", count);
     request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width:800px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
     <div class="text-center">
       <img src="image/qna.jpg" style="width:550px;height: 100px" class="img-rounded">
     </div>
    </div>
    <div style="height: 20px"></div>
    <div class="row">
     <c:if test="${count==0 }">
      <table class="table">
        <tr>
         <td class="text-center"><h3>검색 결과가 없습니다</h3></td>
        </tr>
      </table>
     </c:if>
     <c:if test="${count>0 }">
       <table class="table">
        <tr>
         <td class="text-right">검색결과:${count }건</td>
        </tr>
       </table>
       <div style="height: 10px"></div>
       <table class="table">
         <tr class="success">
           <th width=10% class="text-center">번호</th>
           <th width=45% class="text-center">제목</th>
           <th width=15% class="text-center">이름</th>
           <th width=20% class="text-center">작성일</th>
           <th width=10% class="text-center">조회수</th>
         </tr>
         <c:forEach var="vo" items="${list }">
           <tr>
               <td width=10% class="text-center">${vo.no }</td>
	           <td width=45%>${vo.subject }</td>
	           <td width=15% class="text-center">${vo.name }</td>
	           <td width=20% class="text-center">${vo.regdate }</td>
	           <td width=10% class="text-center">${vo.hit }</td>
           </tr>
         </c:forEach>
         <tr>
           <td colspan="5" class="text-right">
            <a href="list.jsp" class="btn btn-sm btn-danger">목록</a>
           </td>
         </tr>
       </table>
     </c:if>
    </div>
   </div>

</body>
</html>