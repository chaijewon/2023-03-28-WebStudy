<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="model" class="com.sist.model.FoodModel"/>
<%
     model.foodListData(request);
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
  width:960px;
}
</style>
</head>
<body>
  <div class="container">
    <%--
        request.setAttribute("curpage", curpage); => ${curpage}
        request.setAttribute("totalpage", totalpage); => ${totalpage}
        request.setAttribute("list", list); => ${list}
                                              -------- key
                                         <%= request.getAttribute("curpage")%>
     --%>
    <div class="row">
      <c:forEach var="vo" items="${list }">
         <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="#">
			        <img src="${vo.getPoster()}"  style="width:100%">
			        <div class="caption">
			          <p>${vo.getName()}</p>
			        </div>
			      </a>
			    </div>
			  </div>
      </c:forEach>
    </div>
    <div style="height: 10px"></div>
    <div class="row">
      <div class="text-center">
       <a href="food_2.jsp?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-danger">이전</a>
         ${curpage } page / ${totalpage } pages
       <a href="food_2.jsp?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-danger">다음</a>
      </div>
    </div>
  </div>
</body>
</html>