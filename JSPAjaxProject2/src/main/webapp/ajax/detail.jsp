<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
     String fno=request.getParameter("fno");
     FoodDAO dao=new FoodDAO();
     FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
     
     request.setAttribute("vo", vo);
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
.row1 {
  margin: 0px auto;
  width:600px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row row1">
      <table class="table">
        <tr>
          <c:forTokens items="${vo.poster }" delims="^" var="img">
            <td><img src="${img }" style="width: 100%"></td>
          </c:forTokens>
        </tr>
      </table>
    </div>
    <div style="height: 10px"></div>
    <div class="row row1">
        <table class="table">
          <tr>
            <td colspan="2">
             <h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3>
            </td>
          </tr>
          <tr>
           <th width=20% style="color:gray">주소</th>
           <td width=80%>
            ${vo.address}
           </td>
          </tr>
          <tr>
           <th width=20% style="color:gray">전화</th>
           <td width=80%>${vo.phone }</td>
          </tr>
          <tr>
           <th width=20% style="color:gray">음식종류</th>
           <td width=80%>${vo.type }</td>
          </tr>
          <tr>
           <th width=20% style="color:gray">가격대</th>
           <td width=80%>
             ${vo.price }
           </td>
          </tr>
          <tr>
           <th width=20% style="color:gray">주차</th>
           <td width=80%>${vo.parking }</td>
          </tr>
          <tr>
           <th width=20% style="color:gray">영업시간</th>
           <td width=80%>${vo.time }</td>
          </tr>
          <c:if test="${vo.menu!='no' }">
          <tr>
           <th width=20% style="color:gray">메뉴</th>
           <td width=80%>
            <ul>
              <c:forTokens items="${vo.menu }" delims="원" var="m">
                <li>${m}원</li>
              </c:forTokens>
            </ul>
           </td>
          </tr>
          </c:if>
        </table>
      </div>
  </div>
</body>
</html>