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
  width:800px;
}
</style>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
   <div class="jumbotron">
    <h3 class="text-center">${cvo.title }</h3>
    <h4 class="text-center">${cvo.subject }</h4>
   </div>
   <div class="row">
     <table class="table">
       <tr>
         <td>
            <%-- <%= request.getAttribute("list")%> --%>
           <c:forEach var="vo" items="${list }">
             <table class="table">
              <tr>
                <td width=35% class="text-center" rowspan="3">
                 <a href="../food/food_detail_before.do?fno=${vo.fno }">
                  <img src="${vo.poster }" style="width: 100%" class="img-rounded">
                 </a>
                </td>
                <td width=65%>
                 <h3><a href="../food/food_detail_before.do?fno=${vo.fno }">${vo.name }</a>&nbsp;<span style="color:orange">${vo.score }</span></h3>
                </td>
              </tr>
              <tr>
               <td width=65%>${vo.address }</td>
              </tr>
              <tr>
                <td width=65% height="150" valign="top" class="text-left">
                 <pre style="white-space: pre-wrap;background-color: white;border:none">${vo.msg }</pre>
                </td>
              </tr>
              
             </table>
           </c:forEach>
         </td>
       </tr>
       <tr>
            <td class="text-right">
              <a href="../main/main.do" class="btn btn-sm btn-danger">카테고리 목록</a>
            </td>
        </tr>
     </table>
   </div>
  </main>
</div>
</body>
</html>