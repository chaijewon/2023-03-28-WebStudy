<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String mode=request.getParameter("mode");
    if(mode==null)
    	mode="0";
    String jsp="";
    int index=Integer.parseInt(mode);
    switch(index)
    {
    case 0:
    	jsp="inner.jsp";
    	break;
    case 1:
    	jsp="request.jsp";
    	break;
    case 2:
    	jsp="response.jsp";
    	break;
    case 3:
    	jsp="application.jsp";
    	break;
    case 4:
    	jsp="pageContext.jsp";
    	break;
    case 5:
    	jsp="out.jsp";
    	break;
    case 6:
    	jsp="etc.jsp";
    	break;
    case 7:
    	jsp="session.jsp";
    	break;
    case 8:
    	jsp="action.jsp";
    	break;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Gasoek+One&family=Gothic+A1:wght@300&family=Monomaniac+One&family=Nanum+Gothic:wght@400;700&family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">

.container{
   margin-top: 50px;
   /* border:1px solid black; */
}
.row{
   margin: 0px auto;
   width:960px;
   height:100px;
   /* border:1px solid black; */
}
.row1{
   height: 500px;
   /* border:1px solid black; */
}
h1{
  text-align: center;
  font-family: 'Gasoek One', sans-serif;
  
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <h1>내장 객체 정리</h1>
    </div>
    <div class="row row1">
      <div class="col-sm-4">
       <div style="height: 100px"></div>
       <table class="table">
         <tr height="35">
          <td class="text-center"><a href="main.jsp?mode=0">Home</a></td>
         </tr>
         <tr height="35">
          <td class="text-center"><a href="main.jsp?mode=1">request</a></td>
         </tr>
         <tr height="35">
          <td class="text-center"><a href="main.jsp?mode=2">response</a></td>
         </tr>
         <tr height="35">
          <td class="text-center"><a href="main.jsp?mode=3">application</a></td>
         </tr>
         <tr height="35">
          <td class="text-center"><a href="main.jsp?mode=4">pageContext</a></td>
         </tr>
         <tr height="35">
          <td class="text-center"><a href="main.jsp?mode=5">out</a></td>
         </tr>
         <tr height="35">
          <td class="text-center"><a href="main.jsp?mode=6">etc</a></td>
         </tr>
         <tr height="35">
          <td class="text-center"><a href="main.jsp?mode=7">session</a></td>
         </tr>
         <tr height="35">
          <td class="text-center"><a href="main.jsp?mode=8">액션태그</a></td>
         </tr>
       </table>
      </div>
      <div class="col-sm-8">
        <jsp:include page="<%=jsp %>"></jsp:include>
      </div>
    </div>
    <div class="row">
      <h1>개인 정보</h1>
    </div>
  </div>
</body>
</html>