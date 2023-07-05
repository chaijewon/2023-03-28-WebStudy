<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:960px;
}
</style>
</head>
<body>
  <div class="wrapper row3">
   <main class="container clear">
    <div class="row">
     <table class="table">
       <tr>
         <c:forTokens items="${vo.poster }" delims="^" var="img">
           <td>
            <img src="${img }" style="width: 100%">
           </td>
         </c:forTokens>
       </tr>
     </table>
    </div>
    <div style="height: 20px"></div>
    <div class="row">
      <div class="col-sm-8">
        <%-- 맛집 정보 --%>
        <table class="table">
         <tr>
           <td colspan="2">
            <h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3>
           </td>
         </tr>
         <tr>
          <th width=20% class="text-center">주소</th>
          <td width=80%>${addr1 }
            <br>
            <sub style="color:gray">지번:${addr2 }</sub>
          </td>
         </tr>
         <tr>
          <th width=20% class="text-center">전화</th>
          <td width=80%>${vo.phone }</td>
         </tr>
         <tr>
          <th width=20% class="text-center">음식종류</th>
          <td width=80%>${vo.type }</td>
         </tr>
         <tr>
          <th width=20% class="text-center">가격대</th>
          <td width=80%>${vo.price }</td>
         </tr>
         <tr>
          <th width=20% class="text-center">주차</th>
          <td width=80%>${vo.parking }</td>
         </tr>
         <tr>
          <th width=20% class="text-center">영업시간</th>
          <td width=80%>${vo.time }</td>
         </tr>
         <c:if test="${vo.menu!='no' }">
	         <tr>
	          <th width=20% class="text-center">메뉴</th>
	          <td width=80%>
	            <ul>
	              <c:forTokens items="${vo.menu }" delims="원" var="m">
	                <li>${m }</li>
	              </c:forTokens>
	            </ul>
	          </td>
	         </tr>
         </c:if>
        </table>
      </div>
      <div class="col-sm-4">
        <%--지도 , 인근 명소 --%>
      </div>
    </div>
   </main>
  </div>
</body>
</html>




