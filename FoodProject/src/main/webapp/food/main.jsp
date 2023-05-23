<%@page import="com.sist.vo.CategoryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>

<%
    // 자바가 들어가는 위치 
    FoodDAO dao=FoodDAO.newInstance();
    List<CategoryVO> list=dao.foodCategoryData();
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
  width: 100%;
  margin: 0px auto;
}
</style>
</head>
<body>
   <div class="container">
     <h1 style="color:orange">믿고보는 맛집 리스트</h1>
     <div class="row">
       <%
          for(int i=0;i<12;i++)
          {
        	  CategoryVO vo=list.get(i);
       %>
              <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="#">
			        <img src="<%=vo.getPoster()%>" 
			           title="<%=vo.getSubject() %>" style="width:100%">
			        <div class="caption">
			          <p><%=vo.getTitle() %></p>
			        </div>
			      </a>
			    </div>
			  </div>
       <%
          }
       %>
     </div>
     
     <h1 style="color:orange">지역별 인기 맛집</h1>
     <div class="row">
       <%
          for(int i=12;i<18;i++)
          {
        	  CategoryVO vo=list.get(i);
       %>
              <div class="col-md-4">
			    <div class="thumbnail">
			      <a href="#">
			        <img src="<%=vo.getPoster()%>" 
			           title="<%=vo.getSubject() %>" style="width:100%">
			        <div class="caption">
			          <p><%=vo.getTitle() %></p>
			        </div>
			      </a>
			    </div>
			  </div>
       <%
          }
       %>
     </div>
     
     <h1 style="color:orange">메뉴별 인기 맛집</h1>
     <div class="row">
       <%
          for(int i=18;i<30;i++)
          {
        	  CategoryVO vo=list.get(i);
       %>
              <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="#">
			        <img src="<%=vo.getPoster()%>" 
			           title="<%=vo.getSubject() %>" style="width:100%">
			        <div class="caption">
			          <p><%=vo.getTitle() %></p>
			        </div>
			      </a>
			    </div>
			  </div>
       <%
          }
       %>
     </div>
   </div>
</body>
</html>