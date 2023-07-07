<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <h2 class="sectiontitle">${vo.title }</h2>
    <table class="table">
     <tr>
      <td>
       <img src="${vo.poster }" style="width: 100%">
      </td>
     </tr>
     <tr>
      <td>
       ${vo.msg }
      </td>
     </tr>
     <tr>
      <td>
       ${vo.address}
      </td>
     </tr>
    </table>
  </main>
</div>
</body>
</html>