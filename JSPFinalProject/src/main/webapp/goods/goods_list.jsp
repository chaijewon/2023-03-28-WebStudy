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
  width:860px;
}
</style>
</head>
<body>
  <div class="wrapper row3">
   <main class="container clear">
     <h2 class="sectiontitle">${msg }</h2>
     <div class="row">
       <div class="text-center">
         <a href="../goods/goods_list.do?type=1" class="btn btn-sm btn-danger">전체 상품</a>
         <a href="../goods/goods_list.do?type=2" class="btn btn-sm btn-success">베스트 상품</a>
         <a href="../goods/goods_list.do?type=3" class="btn btn-sm btn-info">신상품</a>
         <a href="../goods/goods_list.do?type=4" class="btn btn-sm btn-primary">특가 상품</a>
       </div>
     </div>
     <div style="height: 20px"></div>
     <div class="row">
       <c:forEach var="vo" items="${list }">
         <div class="col-md-3">
		    <div class="thumbnail">
		      <a href="#">
		        <img src="${vo.goods_poster }" title="${vo.goods_name }" style="width:100%">
		        <div class="caption">
		          <p>${vo.goods_price }</p>
		        </div>
		      </a>
		    </div>
		  </div>
       </c:forEach>
     </div>
     <div style="height: 10px"></div>
     <div class="row">
       <div class="text-center">
         <a href="../goods/goods_list.do?page=${curpage>1?curpage-1:curpage }&type=${type}" class="btn btn-sm btn-danger">이전</a>
           ${curpage } page / ${totalpage } pages
         <a href="../goods/goods_list.do?page=${curpage<totalpage?curpage+1:curpage }&type=${type}" class="btn btn-sm btn-primary">다음</a>
       </div>
     </div>
   </main>
  </div>
</body>
</html>