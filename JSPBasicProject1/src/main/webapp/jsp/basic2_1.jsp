<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row {
  margin: 0px auto;
  width: 800px;
}
h1{
    text-align: center
}
</style>
</head>
<body>
  <div class="container">
     <h1>구구단</h1>
     <div class="row">
       <table class="table">
         <tr>
           <c:forEach var="i" begin="2" end="9">
             <th class="text-center">${ i }단</th>
           </c:forEach>
         </tr>
         <c:forEach var="i" begin="1" end="9">
           <tr>
            <c:forEach var="j" begin="2" end="9">
              <td class="text-center">${j }*${i }=${i*j }</td>
            </c:forEach>
           </tr>
         </c:forEach>
       </table>
     </div>
   </div>
</body>
</html>



